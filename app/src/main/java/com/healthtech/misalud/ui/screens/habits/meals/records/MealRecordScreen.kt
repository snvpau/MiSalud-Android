package com.healthtech.misalud.ui.screens.habits.meals.records

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.core.viewModels.MealsViewModel
import com.healthtech.misalud.ui.components.ActionRow
import com.healthtech.misalud.ui.components.DatePicker
import com.healthtech.misalud.ui.components.FilterBar
import com.healthtech.misalud.ui.components.FilterItem
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.ui.components.SectionTitle
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import kotlinx.coroutines.launch

@Composable
fun MealRecordScreen(viewModel: MealsViewModel, calendarState: UseCaseState){

    val coroutineScope = rememberCoroutineScope()

    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val allowedDays by viewModel.allowedDays.observeAsState(initial = listOf())

    fun onCalendarOK(date: String){
        Log.i("Test", date)
    }

    fun showCalendar(){
        coroutineScope.launch {
            Log.i("Start","Start")
            viewModel.getRecordDays()
            Log.i("Fetch","Fetch")
            calendarState.show()
            Log.i("Invoke","Invoke")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getRecords("today")
    }

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Header(viewModel) { showCalendar() }
            Body(viewModel)
            Footer()
            DatePicker(calendarState, ::onCalendarOK, allowedDays)
        }
    }
}

@Composable
fun Header(viewModel: MealsViewModel, showCalendar: () -> Unit){
    val filterItems by viewModel.filterItemList.observeAsState(initial = listOf(
        FilterItem("Hoy", { viewModel.changeFilter(0, "today") }, true),
        FilterItem("Ayer", { viewModel.changeFilter(1, "yesterday") }, false),
        FilterItem("Seleccionar Fecha", { showCalendar() }, false),
    ))
    viewModel.setFilterItems(filterItems)

    SectionTitle(title = "Comidas")
    FilterBar(filterItems)
    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Body(viewModel: MealsViewModel){
    val records by viewModel.records.observeAsState(initial = listOf())

    records.forEach { record ->
        ActionRow(title = record.type, description = record.name, icon = Icons.Rounded.AccessTime)
    }
    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Footer(){
    RoundedButton(text = "Agregar Comida", fullWidth = true, bold = true, onClick = {})
}