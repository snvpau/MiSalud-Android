package com.healthtech.misalud.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoRow(
    title: String,
    description: String,
    score: Float = 0f,
    icon: ImageVector? = null,
    spaced: Boolean = false,
    padding: Int = 4,
    endContent: @Composable RowScope.() -> Unit
){
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    val textScore = when (score) {
        0f -> "Bajo"
        1f -> "Medio"
        else -> "Alto"
    }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(241, 242, 246))
        ){
            if(icon != null){
                Icon(icon, contentDescription = null)
            }
        }
        Column{
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(text = description)
            Text(text = "Puntuación: $textScore")
        }
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp), content = endContent)
    }
}