package com.healthtech.misalud.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoCard(title: String, content: String){
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