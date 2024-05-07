package com.healthtech.misalud.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
data class FilterItem(
    val title: String,
    val action: () -> Unit,
    var selected: Boolean
)

@Composable
fun FilterBar(items: List<FilterItem>){
    Spacer(modifier = Modifier.padding(8.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items.forEach { item ->
            RoundedButton(
                text = item.title,
                bold = true,
                onClick = { item.action() },
                spaced = false,
                selected = item.selected
            )
        }
    }
}