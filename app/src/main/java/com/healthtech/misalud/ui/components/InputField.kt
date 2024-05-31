package com.healthtech.misalud.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    placeholder: String,
    //textValue: String,
    textValue: String? = null,
    textValueInt: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    spaced: Boolean = false,
    padding: Int = 4,
    focusedColor: Color = Color(241, 242, 246),
    onChange: (String) -> Unit
){
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    val displayValue = textValue ?: textValueInt?.toString() ?: ""

    TextField(
        value = displayValue,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,

        colors = TextFieldDefaults.colors(
            focusedContainerColor = focusedColor,
            unfocusedContainerColor = focusedColor,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}