package com.healthtech.misalud.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextButton(
    title: String,
    onClick: () -> Unit,
    spaced: Boolean = false,
    color: Color = Color(26, 152, 220),
    fontSize: Int = 12,
    fontWeight: FontWeight = FontWeight.Bold,
    padding: Int = 4,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    Text(
        text = title,
        modifier = modifier.clickable { onClick() },
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = color
    )
}