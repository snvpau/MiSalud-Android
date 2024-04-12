package com.healthtech.misalud.components.registry.ui

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.network.data.services.RegistryService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.launch

class RegistryViewModel(context: Context) : ViewModel(){

    private val registryService = RegistryService()
    private val tokenManager = TokenManagement(context)
    private val userManager = UserManagement(context)

    private val _firstName = MutableLiveData<String>()
    val firstName : LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName : LiveData<String> = _lastName

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber : LiveData<String> = _phoneNumber

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword : LiveData<String> = _confirmPassword

    private val _registerEnabled = MutableLiveData<Boolean>()
    val registerEnabled : LiveData<Boolean> = _registerEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun onRegisterFormChanged(firstName: String, lastName: String, phoneNumber: String, password: String, confirmPassword: String){
        _firstName.value = firstName
        _lastName.value =  lastName
        _phoneNumber.value = phoneNumber
        _password.value = password
        _confirmPassword.value = confirmPassword

        _registerEnabled.value = isValidName(firstName) &&
                                 isValidName(lastName) &&
                                 isValidPhoneNumber(phoneNumber) &&
                                 isValidPassword(password, confirmPassword)
    }

    private fun isValidName(name: String) : Boolean = Regex("[A-Z][a-z]{2,}|[A-Z][a-z]{2,}+//s[A-Z][a-z]").matches(name)
    private fun isValidPhoneNumber(phoneNumber: String) : Boolean = Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length == 10
    private fun isValidPassword(password: String, confirmPassword: String) : Boolean = password.length > 6 && password == confirmPassword

    fun onRegisterSelected(navigationController: NavHostController){
        viewModelScope.launch {
            _isLoading.value = true

            val result = registryService.doUserRegistry(firstName.value!!, lastName.value!! ,phoneNumber.value!!, password.value!!)
            if(result.success == true){
                Log.i("RegistryViewModel", result.accessToken.toString())
                Log.i("RegistryViewModel", result.refreshToken.toString())

                tokenManager.saveAccessToken(result.accessToken.toString())
                tokenManager.saveRefreshToken(result.refreshToken.toString())

                userManager.saveUserAttributeString("phoneNumber", _phoneNumber.value!!)
                Log.i("LoginViewModel", "Tokens Saved")

                navigationController.navigate("HomeScreen"){
                    popUpTo(navigationController.graph.findStartDestination().id){
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