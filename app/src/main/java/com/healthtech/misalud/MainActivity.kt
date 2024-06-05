package com.healthtech.misalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.healthtech.misalud.core.navigation.Navigation
import com.healthtech.misalud.core.viewModels.HomeViewModel
import com.healthtech.misalud.ui.screens.login.LoginScreen
import com.healthtech.misalud.core.viewModels.LoginViewModel
import com.healthtech.misalud.core.viewModels.ProfileViewModel
import com.healthtech.misalud.core.viewModels.RegistryViewModel
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import com.healthtech.misalud.core.viewModels.ExercisesViewModel
import com.healthtech.misalud.ui.screens.habits.meals.records.MealRecordScreen
import com.healthtech.misalud.ui.screens.habits.meals.registry.MealRegistryScreen
import com.healthtech.misalud.core.viewModels.MealsViewModel
import com.healthtech.misalud.ui.screens.habits.exercises.records.ExerciseRecordScreen
import com.healthtech.misalud.ui.screens.habits.exercises.registry.ExerciseRegistryScreen
import com.healthtech.misalud.ui.screens.home.HomeScreen
import com.healthtech.misalud.ui.screens.profile.ProfileScreen
import com.healthtech.misalud.ui.screens.registry.RegistryScreen
import com.healthtech.misalud.ui.theme.MiSaludTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TokenManagement.init(this)
        UserManagement.init(this)

        setContent {
            MiSaludTheme {
                Navigation.Init()
                val coroutineScope = rememberCoroutineScope()

                val loginViewModel = LoginViewModel()
                val registryViewModel = RegistryViewModel()
                val homeViewModel = HomeViewModel()
                val profileViewModel = ProfileViewModel()
                val mealsViewModel = MealsViewModel()
                val exercisesViewModel = ExercisesViewModel()

                val calendarState = rememberUseCaseState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val refreshToken = TokenManagement.refreshToken
                    val phoneNumber = UserManagement.getUserAttributeString("phoneNumber")

                    val entryPoint : String = if(refreshToken == null && phoneNumber == null){
                        "LoginScreen"
                    } else {
                        "HomeScreen"
                    }

                    NavHost(navController = Navigation.controller!!, startDestination = entryPoint){
                        composable("LoginScreen") { LoginScreen(loginViewModel) }
                        composable("RegistryScreen") { RegistryScreen(registryViewModel) }
                        composable("HomeScreen") { HomeScreen(homeViewModel) }
                        composable("MealRecord") { MealRecordScreen(mealsViewModel, calendarState, coroutineScope) }
                        composable("MealRegistry") { MealRegistryScreen(mealsViewModel) }
                        composable("ExerciseRecord") { ExerciseRecordScreen(exercisesViewModel, calendarState, coroutineScope) }
                        composable("ExerciseRegistry") { ExerciseRegistryScreen(exercisesViewModel) }
                        composable("ProfileScreen") { ProfileScreen(profileViewModel) }
                    }
                }
            }
        }
    }
}