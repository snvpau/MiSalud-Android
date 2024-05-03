package com.healthtech.misalud.screens.habits.meals.registry.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.network.data.services.MealService
import kotlinx.coroutines.launch

class MealRegistryViewModel(navigationController: NavHostController): ViewModel() {

    private val _mealService = MealService()

    private val _navigationController = navigationController

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

            val result = _mealService.doAddRecord("de7ef4d9-aba0-4450-9572-ad2c7a658b7a", "Chilaquiles con Pollo", "Comida", 1)
            if(result.success == true){
                _navigationController.navigate("MealRecord")
            } else {
                //_errorText.value = result.error?.message.toString()
                //_isLoading.value = false
            }
        }
    }

}