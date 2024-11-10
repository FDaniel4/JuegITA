package com.example.juegita.interfaces


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.juegita.R
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.CustomPasswordField
import com.example.juegita.components.IconsButton
import com.example.juegita.components.Texts
import com.example.juegita.components.TitleBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Nueva contraseña")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Icono
                    Image(
                        painter = painterResource(id = R.drawable.passunlocked),
                        contentDescription = "User Icon",
                        modifier = Modifier
                            .size(180.dp)
                            .padding(bottom = 16.dp)
                    )

                    Texts(
                        name = "Por favor ingrese una nueva contraseña diferente a la anterior",
                        fontSize = 24
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //Contraseña actual
                    var contraseñaNueva by rememberSaveable { mutableStateOf("") }
                    var passwordVisible by rememberSaveable { mutableStateOf(false) }

                    CustomPasswordField(
                        password = contraseñaNueva,
                        onPasswordChange = { contraseñaNueva = it },
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                        label = "Contraseña nueva",
                        showError = false
                    )

                    //Confirmar contraseña
                    var confirmPassword by rememberSaveable { mutableStateOf("") }
                    var passwordsMatch by rememberSaveable { mutableStateOf(true) }
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
                                passwordsMatch =
                                    it == contraseñaNueva // Verifica si las contraseñas coinciden
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
                    Spacer(modifier = Modifier.height(24.dp))

                    // Register Button
                    BotonAncho(name = "Guardar") {
                        navController.navigate("inicio-sesion")
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewNewPasswordScreen() {
    NewPasswordScreen(navController = NavController(LocalContext.current))
}