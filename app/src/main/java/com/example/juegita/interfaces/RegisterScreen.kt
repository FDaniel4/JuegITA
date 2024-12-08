package com.example.juegita.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.R
import com.example.juegita.components.Alert
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.CustomPasswordField
import com.example.juegita.components.IconTextField
import com.example.juegita.components.TextsColors
import com.example.juegita.viewModel.LoginViewModel

@Composable
fun RegisterScreen(navController: NavHostController, loginViewModel: LoginViewModel) {

    // Verificar si todos los campos son válidos

    // Background Color
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6200EA)) // Light Blue background
            .padding(5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(140.dp)
                    .padding(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.person_add),
                    contentDescription = "Create Icon",
                    tint = Color(Color.White.value),
                    modifier = Modifier.size(194.dp)
                )
            }
            TextsColors(name = "Registrarse", fontSize = 30)

            Spacer(modifier = Modifier.height(32.dp))

            // Username Input
            var username by rememberSaveable { mutableStateOf("") }

            IconTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                icon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email Input
            var email by rememberSaveable { mutableStateOf("") }

            IconTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de contraseña
            var password by rememberSaveable { mutableStateOf("") }
            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            CustomPasswordField(
                password = password,
                onPasswordChange = { password = it },
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                label = "Contraseña"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Register Button
            BotonAncho(name = "Registrarse") {
                loginViewModel.createUser(email, password, username) {
                    navController.navigate("minijuegos")
                }
            }
            if (loginViewModel.showAlert) {
                Alert(title = "Error de inicio de sesión",
                    message = "Usuario no creado",
                    confirmText = "Aceptar",
                    onConfirmClick = { loginViewModel.closeAlert() }) {

                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Go to Login Button
            TextButton(onClick = { navController.navigate("inicio-sesion") }) {
                Text("¿Ya tienes una cuenta? Inicia sesión!", color = Color.Yellow)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController(), loginViewModel = LoginViewModel())
}