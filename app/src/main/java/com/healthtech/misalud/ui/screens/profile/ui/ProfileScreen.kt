package com.healthtech.misalud.ui.screens.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.ui.screens.profile.vm.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            tint = Color(44, 90, 168)
        )
        Text(text = "Profile", color = Color.Black)
        Spacer(modifier = Modifier.padding(13.dp))
        Button(
            content = { Text(text = "Cerrar Sesion")},
            onClick = {viewModel.logOut()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(44, 90, 168)
            )
        )
    }
}