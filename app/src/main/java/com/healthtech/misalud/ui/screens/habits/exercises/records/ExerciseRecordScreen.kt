package com.healthtech.misalud.ui.screens.habits.exercises.records

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.core.viewModels.ExercisesViewModel
import com.healthtech.misalud.ui.components.CustomScaffold
import com.healthtech.misalud.ui.components.DatePicker
import com.healthtech.misalud.ui.components.FilterBar
import com.healthtech.misalud.ui.components.FilterItem
import com.healthtech.misalud.ui.components.InfoRow
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.ui.components.WarningNoData
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExerciseRecordScreen(viewModel: ExercisesViewModel, calendarState: UseCaseState, coroutineScope: CoroutineScope){
    LaunchedEffect(Unit) {
        viewModel.getExerciseRecords("today")
    }

    CustomScaffold(
        title = "Ejercicios",
        returnRoute = "HomeScreen",
        content = { ScreenContents(viewModel, calendarState, it, coroutineScope) }
    )
}

@Composable
fun ScreenContents(viewModel: ExercisesViewModel, calendarState: UseCaseState, paddingValues: PaddingValues, coroutineScope: CoroutineScope) {

    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val allowedDays by viewModel.allowedDays.observeAsState(initial = listOf())

    fun onCalendarOK(date: String) {
        viewModel.changeFilter(1, date)
    }

    fun showCalendar() {
        coroutineScope.launch {
            viewModel.getRecordDays()
            calendarState.show()
        }
    }

    if(isLoading){
        Box(Modifier.fillMaxSize().padding()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Header(viewModel, paddingValues) { showCalendar() }
            Body(viewModel)
            Footer(viewModel)
            DatePicker(calendarState, ::onCalendarOK, allowedDays)
        }
    }
}

@Composable
fun Header(viewModel: ExercisesViewModel, paddingValues: PaddingValues, showCalendar: () -> Unit){
    Spacer(modifier = Modifier.padding(paddingValues))
    val filterItems by viewModel.filterItemList.observeAsState(initial = listOf(
        FilterItem("Hoy", { viewModel.changeFilter(0, "today") }, true),
        FilterItem("Seleccionar Fecha", { showCalendar() }, false),
    ))
    viewModel.setFilterItems(filterItems)

    FilterBar(filterItems)
    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Body(viewModel: ExercisesViewModel){
    val records by viewModel.records.observeAsState(initial = listOf())

    if(records.isEmpty()){
        WarningNoData()
    } else {
        records.forEach { record ->
            InfoRow(title = record.name, description = "Duración: " + record.duration.toString() + " min", score = record.score, icon = Icons.Rounded.AccessTime) {}
        }
    }

    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Footer(viewModel: ExercisesViewModel){
    RoundedButton(text = "Agregar Ejercicio", fullWidth = true, bold = true, onClick = {viewModel.navigate("ExerciseRegistry")})
}
