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
    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> get() = _errorText

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _password = MutableLiveData<String>("******** Change")
    val password: LiveData<String> = _password

    init {
        loadUserData()
    }

    private fun loadUserData() {
        _name.value = UserManagement.getUserAttributeString("firstName") ?: "N/A"
        _phoneNumber.value = UserManagement.getUserAttributeString("phoneNumber") ?: "N/A"
    }

    fun changePassword(uuid: String, currentPassword: String, newPassword: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = _authService.changePassword(uuid, currentPassword, newPassword)
                if (response.isSuccessful && response.body()?.success == true) {
                } else {
                    _errorText.value = response.body()?.error?.message ?: "Error changing password"
                }
            } catch (e: Exception) {
                _errorText.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

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