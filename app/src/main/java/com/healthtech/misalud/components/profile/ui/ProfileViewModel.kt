package com.healthtech.misalud.components.profile.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement

class ProfileViewModel(context: Context) : ViewModel() {

    private val tokenManager = TokenManagement(context)
    private val userManager = UserManagement(context)

    fun logOut(navController: NavController){

        tokenManager.removeContents()
        userManager.removeContents()

        navController.navigate("LoginScreen"){
            popUpTo(navController.graph.findStartDestination().id){
                inclusive = true
            }
        }

    }


}