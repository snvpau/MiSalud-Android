package com.healthtech.misalud.ui.screens.habits.exercises.registry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.healthtech.misalud.core.viewModels.ExercisesViewModel
import com.healthtech.misalud.ui.components.InputField
import com.healthtech.misalud.ui.components.RadialSelector
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.ui.components.SectionTitle

@Composable
fun ExerciseRegistryScreen(viewModel: ExercisesViewModel){
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

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
            Header()
            Body(viewModel)
            Footer(viewModel)
        }
    }
}

@Composable
fun Header(){
    SectionTitle(title = "Agregar Ejercicio")
}

@Composable
fun Body(viewModel: ExercisesViewModel){
    val name : String by viewModel.name.observeAsState(initial = "")
    val items = listOf("Caminar", "Correr", "Levantamiento de pesas", "Natación")
    val selectorState : String by viewModel.selectorState.observeAsState(items[0])
    viewModel.onSelectorChange(selectorState)

    InputField(
        placeholder = "Ingresa el nombre del Ejercicio",
        onChange = { viewModel.onNameChange(it) },
        textValue = name,
        spaced = true,
        padding = 15
    )

    Spacer(modifier = Modifier.padding(10.dp))

    RadialSelector(
        items = items,
        selector = selectorState,
        onChange = { viewModel.onSelectorChange(it) },
        paddingTop = 6,
        paddingItem = 6
    )
}

@Composable
fun Footer(viewModel: ExercisesViewModel){
    RoundedButton(
        text = "Agregar Ejercicio",
        onClick = { viewModel.addRecord() },
        fullWidth = true,
        bold = true
    )
}