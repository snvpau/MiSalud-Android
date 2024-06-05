package com.healthtech.misalud.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GenericModal(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    color: Color = Color.White,
    content: @Composable (() -> Unit)
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onConfirm) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onDismiss) {
                Text("Cancelar")
            }
        },
        containerColor = color,
        title = { Text(title) },
        text = content
    )
}