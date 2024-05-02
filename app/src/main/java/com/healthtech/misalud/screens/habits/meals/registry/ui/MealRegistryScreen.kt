package com.healthtech.misalud.screens.habits.meals.registry.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.components.InputField
import com.healthtech.misalud.components.RadialSelector
import com.healthtech.misalud.components.RoundedButton
import com.healthtech.misalud.components.SectionTitle

@Composable
fun MealRegistryScreen(){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp),
        horizontalAlignment = Alignment.Start
    ){
        Header()
        Body()
        Footer()
    }
}

@Composable
fun Header(){
    SectionTitle(title = "Agregar Comida")
}

@Composable
fun Body(){
    InputField(
        placeholder = "Ingresa el Nombre del Alimento",
        onChange = {},
        textValue = "",
        spaced = true,
        padding = 15
    )

    RadialSelector(text = "Desayuno", padding = 12)
    RadialSelector(text = "Comida", padding = 6)
    RadialSelector(text = "Cena", padding = 6)
    RadialSelector(text = "Colación", padding = 6)
}

@Composable
fun Footer(){
    RoundedButton(text = "Agregar Comida", fullWidth = true, bold = true)
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview(){
    MealRegistryScreen()
}