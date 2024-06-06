package com.healthtech.misalud.core.viewModels

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

class ProfileViewModel : ViewModel() {

    private val _authService = AuthService()

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    private val _changePasswordModal = MutableLiveData<Boolean>()
    val changePasswordModal: LiveData<Boolean> = _changePasswordModal

    private val _successPasswordModal = MutableLiveData<Boolean>()
    val successPasswordModal: LiveData<Boolean> = _successPasswordModal

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    init {
        if(UserManagement.getUserAttributeString("firstName") != null && UserManagement.getUserAttributeString("lastName") != null){
            _name.value = UserManagement.getUserAttributeString("firstName") + " " + UserManagement.getUserAttributeString("lastName")
        } else {
            _name.value = "N/A"
        }

        _phoneNumber.value = UserManagement.getUserAttributeString("phoneNumber") ?: "N/A"

        _isLoading.value = false
    }

    fun enableChangePasswordModal(enabled: Boolean){
        _changePasswordModal.value = enabled
    }

    fun enableSuccessPasswordModal(enabled: Boolean){
        _successPasswordModal.value = enabled
    }

    fun changePassword(newPassword: String, confirmPassword: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val uuid = UserManagement.getUserAttributeString("uuid")

            if(newPassword != confirmPassword){
                _errorText.value = "Las Contraseñas no Coinciden"
                _isLoading.value = false
                return@launch
            }
            try {
                val response = _authService.doChangePassword(uuid!!, newPassword)
                if (response.success == true) {
                    enableChangePasswordModal(false)
                    enableSuccessPasswordModal(true)
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _errorText.value = e.message ?: "Ocurrio un Error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logOut(){

        viewModelScope.launch {
            _isLoading.value = true

            val result = _authService.doLogout(TokenManagement.refreshToken!!)
            if(result.success == true){
                TokenManagement.removeContents()
                UserManagement.removeContents()

                Navigation.controller!!.navigate("LoginScreen"){
                    popUpTo(Navigation.controller!!.graph.findStartDestination().id){
                        inclusive = true
                    }
                }

            }

            _isLoading.value = false
        }
    }

}