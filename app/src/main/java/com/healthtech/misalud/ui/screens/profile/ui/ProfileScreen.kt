package com.healthtech.misalud.ui.screens.profile.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.healthtech.misalud.ui.screens.profile.vm.ProfileViewModel
import com.healthtech.misalud.ui.theme.MiSaludTheme

class ProfileScreen : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            MiSaludTheme{
                val viewModel: ProfileViewModel = viewModel()
                ProfileScreen(viewModel)
            }
        }
    }
}

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    onConfirm: (currentPassword: String, newPassword: String) -> Unit,
) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            androidx.compose.material3.TextButton(
                onClick = {
                    if (newPassword == confirmPassword) {
                       onConfirm(currentPassword, newPassword)
                    } else {
                        // mensaje de error
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Change Password") },
        text = {
            Column {
                TextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    placeholder = { Text("Current Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    placeholder = { Text("New Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val name by viewModel.name.observeAsState()
    val phoneNumber by viewModel.phoneNumber.observeAsState()
    val password by viewModel.password.observeAsState()
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") }
            )
        }
    ){ padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Personal Information", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            ProfileItem(label = "Name", value = name ?: "N/A")
            ProfileItem(label = "Phone Number", value = phoneNumber ?: "N/A")
            //ProfileItem(label = "Password", value = password ?: "N/A", isClickable = true)
            ProfileItem(
                label = "Password",
                value = password ?: "N/A",
                isClickable = true,
                onClick = { showChangePasswordDialog = true }
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Settings", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            ProfileItem(label = "Notifications", value = "→", isClickable = true)
            ProfileItem(label = "Privacy", value = "→", isClickable = true)
            ProfileItem(label = "Help Center", value = "→", isClickable = true)

            /*Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color(44, 90, 168)
            )
            Text(text = "Profile", color = Color.Black)
            Spacer(modifier = Modifier.padding(13.dp))*/

            Button(
                content = { Text(text = "Cerrar Sesion")},
                onClick = {viewModel.logOut()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(44, 90, 168)
                )
            )
        }
    }
    if (showChangePasswordDialog) {
        ChangePasswordDialog(
            onDismiss = { showChangePasswordDialog = false },
            onConfirm = { currentPassword, newPassword ->
                viewModel.changePassword("uuid", currentPassword, newPassword)
                showChangePasswordDialog = false
            }
        )
    }
}
@Composable
fun ProfileItem(label: String, value: String, isClickable: Boolean = false, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(enabled = isClickable) { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 16.sp)
        Text(
            text = value,
            fontSize = 16.sp,
            textAlign = TextAlign.End,
            color = if (isClickable) Color.Black else Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MiSaludTheme {
        val viewModel: ProfileViewModel = viewModel()
        ProfileScreen(viewModel)
    }
}