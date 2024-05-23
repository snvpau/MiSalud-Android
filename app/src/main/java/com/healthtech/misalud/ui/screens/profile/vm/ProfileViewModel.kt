package com.healthtech.misalud.ui.screens.profile.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.network.data.services.AuthService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.launch

class ProfileViewModel(navigationController: NavHostController) : ViewModel() {

    private val _authService = AuthService()
    private val _navigationController = navigationController

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun logOut(){

        viewModelScope.launch {
            _isLoading.value = true

            TokenManagement.refreshToken?.let { Log.i("test", it) }

            val result = _authService.doLogout(TokenManagement.refreshToken!!)
            if(result.success == true){
                TokenManagement.removeContents()
                UserManagement.removeContents()

                _navigationController.navigate("LoginScreen"){
                    popUpTo(_navigationController.graph.findStartDestination().id){
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