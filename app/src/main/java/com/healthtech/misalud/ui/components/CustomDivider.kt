package com.healthtech.misalud.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDivider(
    type: String = "",
    spaced: Boolean = false,
    padding: Int = 4,
) {
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        HorizontalDivider(Modifier.background(Color(0xFFF9F9F9)).height(1.dp).weight(1f))
        if(type == "orDivider"){
            Text(text = "O",
                modifier = Modifier.padding(horizontal = 18.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB5B5B5)
            )
        }
        HorizontalDivider(Modifier.background(Color(0xFFF9F9F9)).height(1.dp).weight(1f))
    }
}