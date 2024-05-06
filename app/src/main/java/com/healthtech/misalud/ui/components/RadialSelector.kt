package com.healthtech.misalud.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RadialSelector(
    items: List<String>,
    selector: String,
    onChange: (String) -> Unit,
    fontSize: Int = 15,
    spaced: Boolean = true,
    paddingTop: Int = 8,
    paddingItem: Int = 6
){

    if(spaced){
        Spacer(modifier = Modifier.padding(paddingTop.dp))
    }

    items.forEach {item ->
        Surface(
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(223, 225, 225)),
            color = Color.Transparent,
            modifier = Modifier.selectable(
                selected = selector == item,
                onClick = { onChange(item) }
            )
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(17.dp)
            ){
                Text(
                    text = item,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize.sp
                )
                RadioButton(
                    selected = selector == item,
                    onClick = null
                )
            }
        }
        Spacer(modifier = Modifier.padding(paddingItem.dp))
    }
}