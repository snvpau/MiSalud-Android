package com.healthtech.misalud.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthtech.misalud.core.navigation.Navigation
import com.healthtech.misalud.ui.components.ActionRow
import com.healthtech.misalud.ui.components.InfoCard
import com.healthtech.misalud.ui.components.SectionTitle
import com.healthtech.misalud.core.viewModels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getHomeDetails()
    }

    val userName by viewModel.firstName.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    val score : String by viewModel.score.observeAsState(initial = "")
    val activeTime : String by viewModel.activeTime.observeAsState(initial = "")
    val meals : String by viewModel.meals.observeAsState(initial = "")
    val exercises : String by viewModel.exercises.observeAsState(initial = "")

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
            horizontalAlignment = Alignment.Start,
        ) {
            Header(userName)
            HeaderGrid(score, activeTime, meals, exercises)
            SectionTitle("Añade una Comida o Entrenamiento", alignment = Alignment.Start)
            ActionRow(
                title = "Comida",
                description = "Registre alimentos consumidos",
                icon = Icons.Rounded.Fastfood,
                onClickFunction = { viewModel.navigate("MealRegistry") }
            )
            ActionRow(
                title = "Ejercicio",
                description = "Registre ejercicio realizado",
                icon = Icons.Rounded.FitnessCenter,
                onClickFunction = { viewModel.navigate("ExerciseRegistry") }
            )

            SectionTitle("Resumen Diario", alignment = Alignment.Start)

            ActionRow(
                title = "Comidas Registradas",
                description = "$meals/3",
                icon = Icons.Rounded.CheckCircleOutline,
                onClickFunction = { viewModel.navigate("MealRecord") }
            )
            ActionRow(
                title = "Rutinas Realizadas",
                description = "$exercises/1",
                icon = Icons.Rounded.CheckCircleOutline,
                onClickFunction = { viewModel.navigate("ExerciseRecord") }
            )
        }
    }
}

@Composable
fun Header(userName: String){
    Spacer(modifier = Modifier.padding(5.dp))
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically){
        Text(text = "Bienvenido ", color = Color.Black, fontSize = 23.sp)
        Text(text = userName, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 23.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { Navigation.controller!!.navigate("ProfileScreen")}) {
            Icon(imageVector = Icons.Outlined.PersonOutline, contentDescription = null, modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun HeaderGrid(c1: String, c2: String, c3: String, c4: String){
    Spacer(modifier = Modifier.padding(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ){
        InfoCard(title = "Puntuación ", content = c1)
        InfoCard(title = "Tiempo Activo", content = c2)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ){
        InfoCard(title = "Comidas", content = c3)
        InfoCard(title = "Entrenamientos", content = c4)
    }
}