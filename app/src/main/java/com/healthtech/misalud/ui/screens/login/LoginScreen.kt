package com.healthtech.misalud.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.healthtech.misalud.core.viewModels.LoginViewModel
import com.healthtech.misalud.ui.components.CustomDivider
import com.healthtech.misalud.ui.components.CustomTextButton
import com.healthtech.misalud.ui.components.ErrorText
import com.healthtech.misalud.ui.components.HeaderImage
import com.healthtech.misalud.ui.components.InputField
import com.healthtech.misalud.ui.components.RoundedButton

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val phoneNumber : String by viewModel.phoneNumber.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnabled: Boolean by viewModel.loginEnabled.observeAsState(initial = false)

    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val errorText : String by viewModel.errorText.observeAsState(initial = "")

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        if(isLoading){
            Box(Modifier.fillMaxSize()){
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            Column(modifier = Modifier.align(Alignment.Center)) {
                HeaderImage(
                    image = R.drawable.misalud_transparent,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                InputField(
                    placeholder = "Numero de Telefono",
                    textValue = phoneNumber,
                    onChange = { viewModel.onLoginChanged(it, password) },
                    keyboardType = KeyboardType.Phone,
                    spaced = true,
                    padding = 16
                )
                InputField(
                    placeholder = "Contraseña",
                    textValue = password,
                    onChange = { viewModel.onLoginChanged(phoneNumber, it) },
                    keyboardType = KeyboardType.Password,
                    spaced = true,
                    padding = 4
                )
                ErrorText(
                    text = errorText,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    spaced = true,
                    padding = 6
                )
                RoundedButton(
                    text = "Iniciar Sesión",
                    onClick = { viewModel.onLoginSelected() },
                    fullWidth = true,
                    enabled = loginEnabled,
                    backgroundColor = Color(2, 172, 237),
                    disabledBackgroundColor = Color(102, 192, 226, 255),
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
                    title = "Crea una Cuenta",
                    onClick = { Navigation.controller!!.navigate("RegistryScreen") },
                    spaced = true,
                    padding = 10,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}