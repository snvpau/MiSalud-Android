package com.healthtech.misalud.ui.screens.habits.meals.records

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthtech.misalud.core.viewModels.MealsViewModel
import com.healthtech.misalud.ui.components.DatePicker
import com.healthtech.misalud.ui.components.FilterBar
import com.healthtech.misalud.ui.components.FilterItem
import com.healthtech.misalud.ui.components.InfoRow
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.ui.components.WarningNoData
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealRecordScreen(viewModel: MealsViewModel, calendarState: UseCaseState, coroutineScope: CoroutineScope){

    LaunchedEffect(Unit) {
        viewModel.getRecords("today")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {viewModel.navigate("HomeScreen")}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Comidas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(8.dp),
                    )
                },
            )
        },
        content = {
            ScreenContents(viewModel, calendarState, it, coroutineScope)
        }
    )
}

@Composable
fun ScreenContents(viewModel: MealsViewModel, calendarState: UseCaseState, paddingValues: PaddingValues, coroutineScope: CoroutineScope) {

    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val allowedDays by viewModel.allowedDays.observeAsState(initial = listOf())

    fun onCalendarOK(date: String) {
        viewModel.changeFilter(1, date)
    }

    fun showCalendar() {
        Log.i("Test","SHOW")
        coroutineScope.launch {
            viewModel.getRecordDays()
            calendarState.show()
            Log.i("Test","SHOW")
        }
    }

    if(isLoading){
        Box(
            Modifier
                .fillMaxSize()
                .padding()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
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
fun Header(viewModel: MealsViewModel, paddingValues: PaddingValues, showCalendar: () -> Unit){
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
fun Body(viewModel: MealsViewModel){
    val records by viewModel.records.observeAsState(initial = listOf())

    if(records.isEmpty()){
        WarningNoData()
    } else {
        records.forEach { record ->
            InfoRow(title = record.type, description = record.name, score = record.score, icon = Icons.Rounded.AccessTime, endContent = {})
        }
    }

    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Footer(viewModel: MealsViewModel){
    RoundedButton(text = "Agregar Comida", fullWidth = true, bold = true, onClick = {viewModel.navigate("MealRegistry")})
}