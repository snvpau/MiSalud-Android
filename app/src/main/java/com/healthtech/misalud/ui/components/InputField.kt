package com.healthtech.misalud.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    placeholder: String,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    spaced: Boolean = false,
    padding: Int = 4,
    focusedColor: Color = Color(241, 242, 246),
    onChange: (String) -> Unit
){
    if(spaced){
        Spacer(modifier = Modifier.padding(padding.dp))
    }

    if(keyboardType != KeyboardType.Password){
        TextField(
            value = textValue,
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
            ),
        )
    } else {
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        TextField(
            value = textValue,
            onValueChange = onChange,
            placeholder = { Text(text = placeholder) },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = focusedColor,
                unfocusedContainerColor = focusedColor,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                val image = if(passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Ocultar Contraseña" else "Mostrar Contraseña"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
    }


}