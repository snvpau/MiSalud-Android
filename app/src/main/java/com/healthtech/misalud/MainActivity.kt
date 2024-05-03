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
import com.healthtech.misalud.screens.home.vm.HomeViewModel
import com.healthtech.misalud.screens.login.ui.LoginScreen
import com.healthtech.misalud.screens.login.vm.LoginViewModel
import com.healthtech.misalud.screens.navigationcontroller.ui.NavigationController
import com.healthtech.misalud.screens.profile.vm.ProfileViewModel
import com.healthtech.misalud.screens.registry.ui.RegistryScreen_1
import com.healthtech.misalud.screens.registry.vm.RegistryViewModel
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import com.healthtech.misalud.screens.habits.meals.records.ui.MealRecordScreen
import com.healthtech.misalud.screens.habits.meals.registry.ui.MealRegistryScreen
import com.healthtech.misalud.screens.habits.meals.registry.vm.MealRegistryViewModel
import com.healthtech.misalud.ui.theme.MiSaludTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MiSaludTheme {
                val context = LocalContext.current

                val tokenManager = TokenManagement(context)
                val userManager = UserManagement(context)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController  = rememberNavController()
                    val refreshToken = tokenManager.getRefreshToken()
                    val phoneNumber = userManager.getUserAttributeString("phoneNumebr")

                    val entryPoint : String = if(refreshToken == null && phoneNumber == null){
                        "LoginScreen"
                    } else {
                        "LoginScreen"
                    }

                    NavHost(navController = navigationController, startDestination = entryPoint){
                        composable("LoginScreen") { LoginScreen(navigationController, LoginViewModel(context)) }
                        composable("RegistryScreen_1") { RegistryScreen_1(navigationController, RegistryViewModel(context)) }
                        composable("HomeScreen") { NavigationController(navigationController, HomeViewModel(context), ProfileViewModel(context)) }
                        composable("MealRecord") { MealRecordScreen() }
                        composable("MealRegistry") { MealRegistryScreen(MealRegistryViewModel(navigationController)) }
                    }
                }
            }
        }
    }
}