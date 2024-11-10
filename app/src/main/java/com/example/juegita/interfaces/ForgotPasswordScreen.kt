package com.example.juegita.interfaces


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
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
import androidx.navigation.compose.rememberNavController
import com.example.juegita.R
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.IconTextField
import com.example.juegita.components.IconsButton
import com.example.juegita.components.Texts
import com.example.juegita.components.TitleBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Olvidar contraseña")
                },
                navigationIcon = {
                    IconsButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
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
                        painter = painterResource(id = R.drawable.forgotpass),
                        contentDescription = "User Icon",
                        modifier = Modifier
                            .size(180.dp)
                            .padding(bottom = 16.dp)
                    )

                    Texts(
                        name = "Por favor ingrese su correo electrónico para recibir un código de " +
                                "verificación",
                        fontSize = 24
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //Correo electronico
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

                    Spacer(modifier = Modifier.height(10.dp))

                    BotonAncho(name = "Aceptar") {
                         navController.navigate("codigo-verificacion/$email")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewForgotPasswordScreen() {
    ForgotPasswordScreen(navController = rememberNavController())
}