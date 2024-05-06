package com.healthtech.misalud.ui.screens.habits.meals.registry.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.network.data.services.PeopleService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import kotlinx.coroutines.launch

class MealRegistryViewModel(navigationController: NavHostController, context: Context): ViewModel() {

    private val _peopleService = PeopleService()
    private val _navigationController = navigationController
    private val _tokenManager = TokenManagement(context)
    private val _userManager = UserManagement(context)

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private val _selectorState = MutableLiveData<String>()
    val selectorState: LiveData<String> = _selectorState

    /*private val _buttonState = MutableLiveData<String>()
    val buttonState: LiveData<String> = _buttonState*/

    fun onNameChange(text: String){
        _name.value = text

        //TODO("Diable and Enable button")
    }

    fun onSelectorChange(state: String){
        _selectorState.value = state
    }

    fun addRecord(){
        viewModelScope.launch {
            //_isLoading.value = true

            val accessToken = _tokenManager.getAccessToken()

            val result = _peopleService.doAddRecord("Bearer " + accessToken!!, "de7ef4d9-aba0-4450-9572-ad2c7a658b7a", "Chilaquiles con Pollo", "Comida", 1)
            if(result.success == true){
                _navigationController.navigate("MealRecord")
            } else {
                //_errorText.value = result.error?.message.toString()
                //_isLoading.value = false
            }
        }
    }

}