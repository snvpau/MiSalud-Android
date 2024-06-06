package com.healthtech.misalud.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun HeaderImage(
    modifier: Modifier,
    image: Int
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Header",
        modifier = modifier
    )
}