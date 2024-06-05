package com.healthtech.misalud.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorText(
    text: String,
    modifier: Modifier,
    spaced: Boolean = false,
    padding: Int = 4,
){
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    Text(text = text, modifier, color = Color.Red)
}