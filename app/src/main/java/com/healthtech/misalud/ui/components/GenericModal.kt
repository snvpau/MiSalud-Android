package com.healthtech.misalud.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GenericModal(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    color: Color = Color.White,
    disableCancel: Boolean = false,
    content: @Composable (() -> Unit)
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
                CustomTextButton(title = "Confirmar", onClick = onConfirm, fontSize = 14)
        },
        dismissButton = {
            if(!disableCancel){
                CustomTextButton(title = "Cancelar", onClick = onDismiss, fontSize = 14)
            }
        },
        containerColor = color,
        title = { Text(title) },
        text = content
    )
}