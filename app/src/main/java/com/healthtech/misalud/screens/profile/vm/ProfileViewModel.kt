package com.healthtech.misalud.screens.profile.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.healthtech.misalud.core.network.data.services.AuthService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {

    private val loginService = AuthService()

    private val tokenManager = TokenManagement(context)
    private val userManager = UserManagement(context)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun logOut(navController: NavController){

        viewModelScope.launch {
            _isLoading.value = true

            val result = loginService.doLogout(tokenManager.getRefreshToken()!!)
            if(result.success == true){
                tokenManager.removeContents()
                userManager.removeContents()

                navController.navigate("LoginScreen"){
                    popUpTo(navController.graph.findStartDestination().id){
                        inclusive = true
                    }
                }
            } else {
                //_errorText.value = result.error?.message.toString()
                _isLoading.value = false
            }
        }
    }


}