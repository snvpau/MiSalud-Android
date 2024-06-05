package com.healthtech.misalud.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.healthtech.misalud.core.viewModels.ProfileViewModel
import com.healthtech.misalud.ui.components.CustomScaffold
import com.healthtech.misalud.ui.components.RoundedButton
import com.healthtech.misalud.ui.components.SectionText
import com.healthtech.misalud.ui.components.SectionTitle
import com.healthtech.misalud.ui.components.GenericModal
import com.healthtech.misalud.ui.components.InputField

@Composable
fun ProfileScreen(viewModel: ProfileViewModel){
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        CustomScaffold(
            title = "Perfil",
            returnRoute = "HomeScreen",
            content = { ScreenContents(viewModel = viewModel, paddingValues = it) }
        )
    }
}

@Composable
fun ScreenContents(viewModel: ProfileViewModel, paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.padding(paddingValues))
        Body(viewModel)
        Footer(viewModel)
    }
}

@Composable
fun Body(viewModel: ProfileViewModel){
    val name by viewModel.name.observeAsState()
    val phoneNumber by viewModel.phoneNumber.observeAsState()

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    SectionTitle(title = "Información Personal", alignment = Alignment.Start)

    SectionText(title = "Nombre", value = name ?: "N/A", spacing = 8)
    SectionText(title = "Numero Telefonico", value = phoneNumber ?: "N/A")
    SectionText(title = "Contraseña", value = "Cambiar", isClickable = true, onClick = { showChangePasswordDialog = true })

    if (showChangePasswordDialog) {
        GenericModal(
            title = "Cambiar Contraseña",
            onDismiss = { showChangePasswordDialog = false },
            onConfirm = { },
            content = {
                Column {
                    InputField(
                        placeholder = "Contraseña Actual",
                        textValue = currentPassword,
                        keyboardType = KeyboardType.Password,
                        onChange = { currentPassword = it },
                    )
                    InputField(
                        placeholder = "Nueva Contraseña",
                        textValue = newPassword,
                        keyboardType = KeyboardType.Password,
                        onChange = { newPassword = it },
                        spaced = true
                    )
                    InputField(
                        placeholder = "Confirmar Contraseña",
                        textValue = confirmPassword,
                        keyboardType = KeyboardType.Password,
                        onChange = { confirmPassword = it },
                        spaced = true
                    )
                }
            }
        )
    }
}

@Composable
fun Footer(viewModel: ProfileViewModel){
    RoundedButton(
        text = "Cerrar Sesión",
        onClick = { viewModel.logOut() },
        fullWidth = true,
        bold = true
    )
}
