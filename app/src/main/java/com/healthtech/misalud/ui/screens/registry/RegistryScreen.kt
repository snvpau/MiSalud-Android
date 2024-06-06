package com.healthtech.misalud.ui.screens.registry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.R
import com.healthtech.misalud.core.navigation.Navigation
import com.healthtech.misalud.core.viewModels.RegistryViewModel
import com.healthtech.misalud.ui.components.CustomDivider
import com.healthtech.misalud.ui.components.CustomTextButton
import com.healthtech.misalud.ui.components.HeaderImage
import com.healthtech.misalud.ui.components.InputField
import com.healthtech.misalud.ui.components.RoundedButton

@Composable
fun RegistryScreen(viewModel: RegistryViewModel) {
    val firstName : String by viewModel.firstName.observeAsState(initial = "")
    val lastName : String by viewModel.lastName.observeAsState(initial = "")
    val phoneNumber : String by viewModel.phoneNumber.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val confirmPassword : String by viewModel.confirmPassword.observeAsState(initial = "")
    val registerEnabled: Boolean by viewModel.registerEnabled.observeAsState(initial = false)

    Box(Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.align(Alignment.Center).fillMaxWidth()) {
            HeaderImage(
                image = R.drawable.misalud_transparent,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            InputField(
                placeholder = "Primer Nombre",
                textValue = firstName,
                onChange = { viewModel.onRegisterFormChanged(it, lastName, phoneNumber, password, confirmPassword) },
                keyboardType = KeyboardType.Text,
                spaced = true,
                padding = 16
            )
            InputField(
                placeholder = "Apellido Paterno",
                textValue = lastName,
                onChange = { viewModel.onRegisterFormChanged(firstName, it, phoneNumber, password, confirmPassword) },
                keyboardType = KeyboardType.Text,
                spaced = true,
            )
            InputField(
                placeholder = "Telefono",
                textValue = phoneNumber,
                onChange = { viewModel.onRegisterFormChanged(firstName, lastName, it, password, confirmPassword) },
                keyboardType = KeyboardType.Phone,
                spaced = true,
            )
            InputField(
                placeholder = "Ingresa una Contraseña",
                textValue = password,
                onChange = { viewModel.onRegisterFormChanged(firstName, lastName, phoneNumber, it, confirmPassword) },
                keyboardType = KeyboardType.Password,
                spaced = true,
            )
            InputField(
                placeholder = "Confirma tu Contraseña",
                textValue = confirmPassword,
                onChange = { viewModel.onRegisterFormChanged(firstName, lastName, phoneNumber, password, it) },
                keyboardType = KeyboardType.Password,
                spaced = true,
            )
            RoundedButton(
                text = "Crear Cuenta",
                onClick = { viewModel.onRegisterSelected() },
                fullWidth = true,
                enabled = registerEnabled,
                backgroundColor = Color(44, 90, 168),
                disabledBackgroundColor = Color(99, 156, 255, 255),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                height = 48
            )
            CustomDivider(
                type = "orDivider",
                spaced = true,
                padding = 10
            )
            CustomTextButton(
                title = "Inicia Sesión",
                onClick = { Navigation.controller!!.navigate("LoginScreen") },
                spaced = true,
                padding = 10,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}