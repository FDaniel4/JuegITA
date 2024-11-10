package com.example.juegita.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.R
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.CustomPasswordField
import com.example.juegita.components.IconTextField
import com.example.juegita.components.TextsColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, modifier: Modifier = Modifier ) {

    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var checkedRemember by rememberSaveable { mutableStateOf(false) }
    var passwordsMatch by rememberSaveable { mutableStateOf(true) }

    // Verificar si todos los campos son válidos

    // Background Color
    Box(
        modifier = modifier
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
                keyboardType = KeyboardType.Text,
                validate = { it.isNotEmpty() },
                errorMessage = "El nombre de usuario no puede estar vacío",
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email Input
            var email by rememberSaveable { mutableStateOf("") }

            IconTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                validate = { it.matches(Regex("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) },
                errorMessage = "Formato de correo inválido"
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
                label = "Contraseña",
                showError = !passwordsMatch
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Campo de confirmación de contraseña
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Confirmar contraseña",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )
                TextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        passwordsMatch = it == password // Verifica si las contraseñas coinciden
                    },
                    label = { Text("Confirmar Contraseña") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Default.Info
                        else
                            Icons.Default.Info

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Green,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            ) {

                Checkbox(
                    checked = checkedRemember,
                    onCheckedChange = { checkedRemember = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Green,
                        uncheckedColor = Color.White,
                        checkmarkColor = Color.White
                    )
                )
                TextButton(onClick = { navController.navigate("terminos") }) {
                    Text(
                        text = "Estoy de acuerdo con los terminos y condiciones",
                        color = Color.Yellow,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Register Button
            BotonAncho(name = "Registrarse") {
                    navController.navigate("minijuegos")
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
    RegisterScreen(navController = rememberNavController())
}