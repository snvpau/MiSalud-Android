package com.healthtech.misalud.components.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.healthtech.misalud.R

@Composable
fun LoginScreen(navigationController: NavHostController, viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Login(navigationController, Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(navigationController: NavHostController, modifier: Modifier, viewModel: LoginViewModel) {

    val phoneNumber : String by viewModel.phoneNumber.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnabled: Boolean by viewModel.loginEnabled.observeAsState(initial = false)

    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    val errorText : String by viewModel.errorText.observeAsState(initial = "")

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            PhoneField(phoneNumber) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(phoneNumber, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(modifier = Modifier.align(Alignment.End), viewModel)
            Spacer(modifier = Modifier.padding(6.dp))
            ErrorText(modifier = Modifier.align(Alignment.CenterHorizontally), errorText)
            Spacer(modifier = Modifier.padding(10.dp))
            LoginButton(loginEnabled, viewModel, navigationController)
            Spacer(modifier = Modifier.padding(10.dp))
            LoginDivider()

            Spacer(modifier = Modifier.padding(10.dp))
            CreateAccount(navigationController = navigationController, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.misalud_transparent),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Composable
fun PhoneField(phoneNumber: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = phoneNumber,
        onValueChange = onTextFieldChanged,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Numero de Telefono") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contaseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
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

@Composable
fun ForgotPassword(modifier: Modifier, loginViewModel: LoginViewModel) {
    Text(
        text = "¿Olvidaste tu Contraseña?",
        modifier = modifier.clickable { loginViewModel.testToken() },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(26, 152, 220)
    )
}

@Composable
fun ErrorText(modifier: Modifier, textError: String){
    Text(text = textError, modifier, color = Color.Red)
}

@Composable
fun LoginButton(loginEnabled: Boolean, loginViewModel: LoginViewModel, navigationController: NavHostController) {
    Button(
        enabled = loginEnabled,
        onClick = { loginViewModel.onLoginSelected(navigationController) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(44, 90, 168),
            disabledContainerColor = Color(99, 156, 255, 255),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ){
        Text(text = "Iniciar Sesión")
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(text = "O",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun CreateAccount(navigationController: NavHostController, modifier: Modifier) {
    Text(
        text = "Crea una Cuenta",
        modifier = modifier.clickable { navigationController.navigate("RegistryScreen_1")},
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(26, 152, 220)
    )
}