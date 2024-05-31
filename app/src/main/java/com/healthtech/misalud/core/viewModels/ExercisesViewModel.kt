package com.healthtech.misalud.core.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.healthtech.misalud.core.models.ExerciseRecord
import com.healthtech.misalud.core.network.data.services.PeopleService
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import com.healthtech.misalud.core.storage.sharedPreferences.UserManagement
import com.healthtech.misalud.ui.components.FilterItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExercisesViewModel(navigationController: NavHostController): ViewModel() {

    private val _peopleService = PeopleService()
    private val _navigationController = navigationController

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //ExerciseRecord
    private val _filterItemList = MutableLiveData<List<FilterItem>>()
    val filterItemList : LiveData<List<FilterItem>> = _filterItemList

    private val _records = MutableLiveData<List<ExerciseRecord>>()
    val records: LiveData<List<ExerciseRecord>> = _records

    private val _allowedDays = MutableLiveData<List<String>>()
    val allowedDays: LiveData<List<String>> = _allowedDays

    //ExerciseRegistry

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private val _duration = MutableLiveData<Int>()
    val duration : LiveData<Int> = _duration

    private val _score = MutableLiveData<Float>()
    val score : LiveData<Float> = _score

    fun onNameChange(text: String){
        _name.value = text
    }

    fun onDurationChange(num: Int){
        _duration.value = num
    }

    fun onScoreChange(num: Float){
        _score.value = num
    }

    fun setFilterItems(list: List<FilterItem>){
        _filterItemList.value = list
    }

    fun changeFilter(index: Int, range: String){
        val newList = _filterItemList.value!!.mapIndexed { idx, item ->
            if (idx == index) item.copy(selected = true) else item.copy(selected = false)
        }
        _filterItemList.value = newList
        getExerciseRecords(range)
    }

    fun navigate(route: String) {
        _navigationController.navigate(route)
    }

    fun getExerciseRecords(range: String){
        viewModelScope.launch {
            _isLoading.value = true

            val accessToken = "Bearer " + TokenManagement.accessToken
            val uuid = UserManagement.getUserAttributeString("uuid")!!

            val result = async { _peopleService.doGetExerciseRecords(accessToken, uuid, range) }
            val infoDeffered = result.await()

            if(infoDeffered.success){
                _records.value = infoDeffered.records
            } else {
                _records.value = listOf()
            }

            _isLoading.value = false
        }
    }

    fun getRecordDays(){
        viewModelScope.launch {
            Log.i("StartFetch","StartFetch")
            _isLoading.value = true

            val accessToken = "Bearer " + TokenManagement.accessToken
            val uuid = UserManagement.getUserAttributeString("uuid")!!

            val result = async { _peopleService.doGetRecordDays(accessToken, uuid) }
            val infoDeffered = result.await()

            if(infoDeffered.success){
                _allowedDays.value = infoDeffered.dates
            } else {
                _allowedDays.value = listOf()
            }

            _isLoading.value = false
            Log.i("EndFetch","EndFetch")
        }
    }

    fun addRecord(){
        viewModelScope.launch {
            _isLoading.value = true

            val accessToken = "Bearer " + TokenManagement.accessToken
            val uuid = UserManagement.getUserAttributeString("uuid")!!

            val result = _peopleService.doAddExerciseRecord(accessToken, uuid, _name.value!!, _duration.value!!, _score.value!!)
            if(result.success == true){
                _navigationController.navigate("ExerciseRecord")
            } else {
                //_errorText.value = result.error?.message.toString()
                Log.i("error", result.error?.message.toString())
            }

            _isLoading.value = false
        }
    }
}