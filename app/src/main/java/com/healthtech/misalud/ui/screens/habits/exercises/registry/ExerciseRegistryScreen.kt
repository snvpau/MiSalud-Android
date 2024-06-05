package com.healthtech.misalud.ui.screens.habits.exercises.registry

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
import com.healthtech.misalud.core.viewModels.ExercisesViewModel
import com.healthtech.misalud.ui.components.InputField
import com.healthtech.misalud.ui.components.CustomScaffold
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.ui.components.ScoreSlider

@Composable
fun ExerciseRegistryScreen(viewModel: ExercisesViewModel){
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        CustomScaffold(
            title = "Añadir Ejercicio",
            returnRoute = "HomeScreen",
            content = { ScreenContents(viewModel, it) }
        )
    }
}

@Composable
fun ScreenContents(viewModel: ExercisesViewModel, paddingValues: PaddingValues){
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
fun Body(viewModel: ExercisesViewModel){
    val name : String by viewModel.name.observeAsState(initial = "")
    val duration : String by viewModel.duration.observeAsState(initial = "")
    val score : Float by viewModel.score.observeAsState(initial = 1f)

    viewModel.onScoreChange(score)

    InputField(
        placeholder = "Nombre del Ejercicio",
        onChange = { viewModel.onNameChange(it) },
        textValue = name,
    )

    InputField(
        placeholder = "Duracion del ejercicio (Minutos)",
        onChange = { viewModel.onDurationChange(it) },
        textValue = duration,
        spaced = true,
        padding = 5
    )

    ScoreSlider(
        title = "Calificación del Ejercicio",
        score = score,
        onChangeFunction = { viewModel.onScoreChange(it) },
        spaced = true,
        padding = 5
    )
}

@Composable
fun Footer(viewModel: ExercisesViewModel){
    RoundedButton(
        text = "Añadir Ejercicio",
        onClick = { viewModel.addRecord() },
        fullWidth = true,
        bold = true
    )
}