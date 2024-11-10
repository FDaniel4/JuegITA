package com.example.juegita.interfaces


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.CustomPasswordField
import com.example.juegita.components.IconsButton
import com.example.juegita.components.TitleBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordEditScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Cambiar contraseña")
                },
                navigationIcon = {
                    IconsButton(icon = Icons.Default.ArrowBack) {
                        navController.navigate("perfil")
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
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "User Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(194.dp)
                    )
                        Text(
                            text = "Username",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                    Spacer(modifier = Modifier.height(16.dp))

                    //Contraseña actual
                    var contraseñaActual by rememberSaveable { mutableStateOf("") }
                    var passwordVisible by rememberSaveable { mutableStateOf(false) }

                    CustomPasswordField(
                        password = contraseñaActual,
                        onPasswordChange = { contraseñaActual = it },
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                        label = "Contraseña actual",
                        showError = false
                    )
                    //Confirmar contraseña
                    var contraseñaNueva by rememberSaveable { mutableStateOf("") }

                    CustomPasswordField(
                        password = contraseñaNueva,
                        onPasswordChange = { contraseñaNueva = it },
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                        label = "Contraseña nueva"
                    )
                    // Contraseña nueva

                    Spacer(modifier = Modifier.height(10.dp))

                    BotonAncho(name = "Aceptar") {
                        navController.navigate("perfil")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordEditScreen() {
    PasswordEditScreen(navController = NavController(LocalContext.current))
}