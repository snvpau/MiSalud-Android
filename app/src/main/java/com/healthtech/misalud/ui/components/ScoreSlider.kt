package com.healthtech.misalud.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScoreSlider(
    title: String,
    score: Float,
    onChangeFunction: (Float) -> Unit,
    spaced: Boolean = false,
    padding: Int = 4,
){
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }
    
    Text(text = title)
    
    Spacer(modifier = Modifier.padding(5.dp))
    
    Slider(
        value = score,
        onValueChange = { onChangeFunction(it) },
        valueRange = 0f..2f,
        steps = 1,
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Malo")
        Text(text = "Regular")
        Text(text = "Bueno")
    }
}