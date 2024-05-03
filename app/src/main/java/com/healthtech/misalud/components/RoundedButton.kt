package com.healthtech.misalud.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

@Composable
fun RoundedButton(
    text: String,
    onClick: () -> Unit,
    bold: Boolean = false,
    backgroundColor: Color = Color(241, 242, 246),
    contentColor: Color = Color.Black,
    disabledBackgroundColor: Color = Color.Gray,
    disabledContentColor: Color = Color.Black,
    enabled: Boolean = true,
    fullWidth: Boolean = false,
    spaced: Boolean = true,
    padding: Int = 8,
){
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = Modifier.conditional(fullWidth){ fillMaxWidth() },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor
        )
    ){
        if(bold){
            Text(text = text, fontWeight = FontWeight.Bold)
        } else {
            Text(text = text)
        }
    }
}