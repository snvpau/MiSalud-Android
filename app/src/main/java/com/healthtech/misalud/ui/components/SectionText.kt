package com.healthtech.misalud.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionText(title: String, value: String, spacing: Int = 0, alignment: Alignment.Horizontal = Alignment.CenterHorizontally, isClickable: Boolean = false, onClick: () -> Unit = {}) {
    Spacer(modifier = Modifier.padding(spacing.dp))
    Column(
        horizontalAlignment = alignment,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(enabled = isClickable) { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 16.sp)
            Text(
                text = value,
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                color = if (isClickable) Color.Black else Color.Unspecified
            )
        }
    }
}