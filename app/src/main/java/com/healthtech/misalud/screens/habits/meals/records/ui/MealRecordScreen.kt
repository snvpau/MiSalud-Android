package com.healthtech.misalud.screens.habits.meals.records.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.components.ActionRow
import com.healthtech.misalud.components.FilterBar
import com.healthtech.misalud.components.RoundedButton
import com.healthtech.misalud.components.SectionTitle

@Composable
fun MealRecordScreen(){
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
    SectionTitle(title = "Comidas")
    FilterBar()
    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Body(){
    ActionRow(
        title = "Desayuno",
        description = "2 huevos, 1 Aguacate, 1 pedazoas...",
        icon = Icons.Rounded.AccessTime,
    )
    ActionRow(
        title = "Comida",
        description = "2 huevos, 1 Aguacate, 1 pedazoas...",
        icon = Icons.Rounded.AccessTime,
    )
    ActionRow(
        title = "Cena",
        description = "2 huevos, 1 Aguacate, 1 pedazoas...",
        icon = Icons.Rounded.AccessTime,
    )
    ActionRow(
        title = "Cena",
        description = "2 huevos, 1 Aguacate, 1 pedazoas...",
        icon = Icons.Rounded.AccessTime,
    )
    Spacer(modifier = Modifier.padding(12.dp))
}

@Composable
fun Footer(){
    RoundedButton(text = "Agregar Comida", fullWidth = true, bold = true, onClick = {})
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview(){
    MealRecordScreen()
}