package com.healthtech.misalud.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterBar(){
    Spacer(modifier = Modifier.padding(8.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)){
        RoundedButton(text = "Hoy", bold = true)
        RoundedButton(text = "Ayer", bold = true)
        RoundedButton(text = "Seleccionar Fecha", bold = true)
    }
}