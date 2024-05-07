package com.healthtech.misalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.healthtech.misalud.ui.screens.home.vm.HomeViewModel
import com.healthtech.misalud.ui.screens.login.ui.LoginScreen
import com.healthtech.misalud.ui.screens.login.vm.LoginViewModel
import com.healthtech.misalud.ui.screens.navigationcontroller.ui.NavigationController
import com.healthtech.misalud.ui.screens.profile.vm.ProfileViewModel
import com.healthtech.misalud.ui.screens.registry.ui.RegistryScreen_1
import com.healthtech.misalud.ui.screens.registry.vm.RegistryViewModel
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import com.healthtech.misalud.ui.screens.habits.meals.records.MealRecordScreen
import com.healthtech.misalud.ui.screens.habits.meals.registry.MealRegistryScreen
import com.healthtech.misalud.core.viewModels.MealsViewModel
import com.healthtech.misalud.ui.theme.MiSaludTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MiSaludTheme {
                val navigationController  = rememberNavController()

                val context = LocalContext.current

                val tokenManager = TokenManagement(context)
                val userManager = UserManagement(context)

                val mealsViewModel = MealsViewModel(navigationController = navigationController, context = context)

                val calendarState = rememberUseCaseState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val refreshToken = tokenManager.getRefreshToken()
                    val phoneNumber = userManager.getUserAttributeString("phoneNumber")

                    val entryPoint : String = if(refreshToken == null && phoneNumber == null){
                        "LoginScreen"
                    } else {
                        "LoginScreen"
                    }

                    NavHost(navController = navigationController, startDestination = entryPoint){
                        composable("LoginScreen") { LoginScreen(navigationController, LoginViewModel(context)) }
                        composable("RegistryScreen_1") { RegistryScreen_1(navigationController, RegistryViewModel(context)) }
                        composable("HomeScreen") { NavigationController(navigationController, HomeViewModel(context), ProfileViewModel(context)) }
                        composable("MealRecord") { MealRecordScreen(mealsViewModel, calendarState) }
                        composable("MealRegistry") { MealRegistryScreen(mealsViewModel) }
                    }
                }
            }
        }
    }
}