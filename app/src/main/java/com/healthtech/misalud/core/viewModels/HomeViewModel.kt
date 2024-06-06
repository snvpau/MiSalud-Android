package com.healthtech.misalud.core.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healthtech.misalud.core.navigation.Navigation
import com.healthtech.misalud.core.network.data.services.PeopleService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _peopleService = PeopleService()

    private val _firstName = MutableStateFlow("")
    val firstName : StateFlow<String> = _firstName

    private val _score = MutableLiveData<String>()
    val score : MutableLiveData<String> = _score

    private val _activeTime = MutableLiveData<String>()
    val activeTime : MutableLiveData<String> = _activeTime

    private val _meals = MutableLiveData<String>()
    val meals : MutableLiveData<String> = _meals

    private val _exercises = MutableLiveData<String>()
    val exercises : MutableLiveData<String> = _exercises

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getHomeDetails() {
        viewModelScope.launch {

            _isLoading.value = true

            val phoneNumber = UserManagement.getUserAttributeString("phoneNumber")
            val uuid = UserManagement.getUserAttributeString("uuid")
            val accessToken = TokenManagement.accessToken

            if(phoneNumber != null){
                val result = async { _peopleService.doGetUser(phoneNumber=phoneNumber, accessToken = "Bearer " + accessToken!!) }
                val infoDeffered = result.await()

                if(infoDeffered.success){
                    _firstName.value = infoDeffered.user.firstName
                    UserManagement.saveUserAttributeString(key = "firstName", infoDeffered.user.firstName)
                    UserManagement.saveUserAttributeString(key = "lastName", infoDeffered.user.lastName)
                }

                val resultFetchUI = async { _peopleService.doGetHomeScreenData(accessToken = "Bearer " + accessToken!!, uuid!!) }
                val infoDefferedUI = resultFetchUI.await()

                if(infoDeffered.success){
                    _score.value = infoDefferedUI.data.score
                    _activeTime.value = infoDefferedUI.data.activeTime
                    _meals.value = infoDefferedUI.data.meals
                    _exercises.value = infoDefferedUI.data.exercises
                }
            }
            _isLoading.value = false
        }
    }

    fun navigate(route: String){
        Navigation.controller!!.navigate(route)
    }
}