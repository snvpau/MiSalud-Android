package com.healthtech.misalud.ui.screens.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthtech.misalud.ui.components.ActionRow
import com.healthtech.misalud.ui.components.SectionTitle
import com.healthtech.misalud.ui.screens.home.vm.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getHomeDetails()
    }

    val userName by viewModel.firstName.collectAsState()
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    val c1 = "0"
    val c2 = "0h"
    val c3 = "0"
    val c4 = "0"

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
            GreetingText(userName)
            HeaderGrid(c1, c2, c3, c4)
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
                description = "0/3",
                icon = Icons.Rounded.CheckCircleOutline,
                onClickFunction = { viewModel.navigate("MealRecord") }
            )
            ActionRow(
                title="Rutinas Realizadas",
                description="0/1",
                icon=Icons.Rounded.CheckCircleOutline,
                onClickFunction = { viewModel.navigate("ExerciseRecord") }
            )
        }
    }
}
@Composable
fun HeaderBox(title: String, content: String){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(180.dp)
            .height(120.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(241, 242, 246))
    ) {
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
        }
    }
}

@Composable
fun GreetingText(userName: String){
    Spacer(modifier = Modifier.padding(5.dp))
    Row(modifier = Modifier.padding(8.dp)){
        Text(text = "Bienvenido ", color = Color.Black, fontSize = 23.sp)
        Text(text = userName, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 23.sp)
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
        HeaderBox(title = "Calorias", content = c1)
        HeaderBox(title = "Tiempo Activo", content = c2)
    }
    Row(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ){
        HeaderBox(title = "Comidas", content = c3)
        HeaderBox(title = "Entrenamientos", content = c4)
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview(){
    HomeScreen()
}
*/