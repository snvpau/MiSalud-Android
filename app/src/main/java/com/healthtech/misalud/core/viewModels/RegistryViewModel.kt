package com.healthtech.misalud.core.viewModels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.healthtech.misalud.core.navigation.Navigation
import com.healthtech.misalud.core.network.data.services.AuthService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.launch

class RegistryViewModel : ViewModel(){

    private val _authService = AuthService()

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

    fun onRegisterSelected(){
        viewModelScope.launch {
            _isLoading.value = true

            val result = _authService.doCreateAccount(firstName.value!!, lastName.value!! ,phoneNumber.value!!, password.value!!)
            if(result.success == true){
                TokenManagement.accessToken = result.accessToken.toString()
                TokenManagement.refreshToken = result.refreshToken.toString()

                UserManagement.saveUserAttributeString("uuid", result.uuid.toString())

                UserManagement.saveUserAttributeString("phoneNumber", _phoneNumber.value!!)
                UserManagement.saveUserAttributeString("firstName", firstName.value!!)
                UserManagement.saveUserAttributeString("lastName", lastName.value!!)

                Navigation.controller!!.navigate("HomeScreen"){
                    popUpTo(Navigation.controller!!.graph.findStartDestination().id){
                        inclusive = true
                    }
                }
            } else {
                //_errorText.value = result.error?.message.toString()
                _isLoading.value = false
            }
        }
    }

    fun navigate(route: String) {
        Navigation.controller!!.navigate(route)
    }

}