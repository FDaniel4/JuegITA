package com.example.juegita

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegisterClick: () -> Unit, onLoginClick: () -> Unit, modifier: Modifier = Modifier) {
    // Background Color
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF6200EA)) // Light Blue background
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Registro",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Username Input
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email Input
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Correo") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Register Button
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)) // Orange Button
            ) {
                Text(text = "Registrarse", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Go to Login Button
            TextButton(onClick = onLoginClick) {
                Text("¿Ya tienes una cuenta? Iniciar sesión!", color = Color.Yellow)
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(onRegisterClick = {}, onLoginClick = {})
}
