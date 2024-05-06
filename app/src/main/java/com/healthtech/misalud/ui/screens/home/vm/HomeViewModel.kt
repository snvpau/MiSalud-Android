package com.healthtech.misalud.ui.screens.home.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.network.data.services.PeopleService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {

    private val _peopleService = PeopleService()
    private val _tokenManager = TokenManagement(context)
    private val _userManager = UserManagement(context)

    private val _firstName = MutableStateFlow("")
    val firstName : StateFlow<String> = _firstName

    /*private val _lastName = MutableLiveData<String>()
    val lastName : LiveData<String> = _lastName*/

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    init{
        viewModelScope.launch {

            _isLoading.value = true

            val phoneNumber = _userManager.getUserAttributeString("phoneNumber")
            val accessToken = _tokenManager.getAccessToken()

            if(phoneNumber != null){
                val result = async { _peopleService.doGetUser(phoneNumber=phoneNumber, accessToken = "Bearer " + accessToken!!) }
                val infoDeffered = result.await()

                if(infoDeffered.success){
                    _firstName.value = infoDeffered.user.firstName
                }
            }

            _isLoading.value = false
        }
    }

    fun navigate(navigationController: NavHostController, route: String){
        navigationController.navigate(route)
    }
}