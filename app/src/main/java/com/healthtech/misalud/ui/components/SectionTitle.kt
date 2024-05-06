package com.healthtech.misalud.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(title: String, spacing: Int = 0, fontSize: Int = 18, alignment: Alignment.Horizontal = Alignment.CenterHorizontally){
    Spacer(modifier = Modifier.padding(spacing.dp))
    Column(
        horizontalAlignment = alignment,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(8.dp),
        )
    }

}