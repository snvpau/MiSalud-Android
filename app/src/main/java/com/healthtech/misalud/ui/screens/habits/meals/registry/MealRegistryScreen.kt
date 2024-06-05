package com.healthtech.misalud.ui.screens.habits.meals.registry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.ui.components.InputField
import com.healthtech.misalud.ui.components.RadialSelector
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.core.viewModels.MealsViewModel
import com.healthtech.misalud.ui.components.CustomScaffold
import com.healthtech.misalud.ui.components.ScoreSlider

@Composable
fun MealRegistryScreen(viewModel: MealsViewModel){
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        CustomScaffold(
            title = "Añadir Comida",
            returnRoute = "HomeScreen",
            content = { ScreenContents(viewModel, it) }
        )
    }
}

@Composable
fun ScreenContents(viewModel: MealsViewModel, paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.padding(paddingValues))
        Body(viewModel)
        Footer(viewModel)
    }
}

@Composable
fun Body(viewModel: MealsViewModel){
    val name : String by viewModel.name.observeAsState(initial = "")
    val items = listOf("Desayuno", "Comida", "Cena", "Colación")
    val score : Float by viewModel.score.observeAsState(initial = 0f)
    val selectorState : String by viewModel.selectorState.observeAsState(items[0])
    viewModel.onSelectorChange(selectorState)

    InputField(
        placeholder = "Nombre del Alimento",
        onChange = { viewModel.onNameChange(it) },
        textValue = name
    )

    Spacer(modifier = Modifier.padding(5.dp))

    RadialSelector(
        items = items,
        selector = selectorState,
        onChange = { viewModel.onSelectorChange(it) },
        paddingTop = 3,
        paddingItem = 6
    )

    Spacer(modifier = Modifier.padding(5.dp))

    ScoreSlider(
        title = "Calificación del Alimento",
        score = score,
        onChangeFunction = { viewModel.onScoreChange(it) },
        spaced = true,
        padding = 5
    )
}

@Composable
fun Footer(viewModel: MealsViewModel){
    RoundedButton(
        text = "Agregar Comida",
        onClick = { viewModel.addRecord() },
        fullWidth = true,
        bold = true
    )
}