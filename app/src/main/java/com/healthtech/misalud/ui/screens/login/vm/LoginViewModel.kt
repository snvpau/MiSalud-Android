package com.healthtech.misalud.ui.screens.login.vm

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.network.data.services.AuthService
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.launch

class LoginViewModel(context: Context) : ViewModel() {

    private val _authService = AuthService()
    private val _tokenManager = TokenManagement(context)
    private val _userManager = UserManagement(context)

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber : LiveData<String> = _phoneNumber

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled : LiveData<Boolean> = _loginEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorText = MutableLiveData<String>()
    val errorText : LiveData<String> = _errorText

    fun onLoginChanged(phoneNumber: String, password: String){
        _phoneNumber.value = phoneNumber
        _password.value =  password

        _loginEnabled.value = isValidPhoneNumber(phoneNumber) && isValidPassword(password)
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean = Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length == 10

    private fun isValidPassword(password: String): Boolean = password.length > 6

    fun onLoginSelected(navigationController: NavHostController) {
        viewModelScope.launch {
            _isLoading.value = true

            val result = _authService.doLogin(phoneNumber.value!!, password.value!!)
            if(result.success == true){
                Log.i("LoginViewModel", result.accessToken.toString())
                Log.i("LoginViewModel", result.refreshToken.toString())

                _tokenManager.saveAccessToken(result.accessToken.toString())
                _tokenManager.saveRefreshToken(result.refreshToken.toString())

                _userManager.saveUserAttributeString("phoneNumber", _phoneNumber.value!!)

                Log.i("saveUUID", result.uuid.toString())
                _userManager.saveUserAttributeString("uuid", result.uuid.toString())

                Log.i("LoginViewModel", "Tokens Saved")

                navigationController.navigate("HomeScreen"){
                    popUpTo(navigationController.graph.findStartDestination().id){
                        inclusive = true
                    }
                }
            } else {
                _errorText.value = result.error?.message.toString()
                _isLoading.value = false
            }
        }
    }

    fun testToken(){
        viewModelScope.launch {
            Log.i("Token Test", _tokenManager.getAccessToken().toString())
            Log.i("Token Test", _tokenManager.getRefreshToken().toString())
        }
    }

}