package com.example.juegita.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.IconTextField
import com.example.juegita.components.IconsButton
import com.example.juegita.components.Texts
import com.example.juegita.components.TitleBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Editar perfil")
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

                    //
                    TextButton(onClick = { print("Hello") }) {
                        Texts(name = "Subir imagen", fontSize = 24)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    var nombre by rememberSaveable { mutableStateOf("") }
                    // Edicion de nombre
                    IconTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = "Username",
                        icon = Icons.Default.Edit,
                        keyboardType = KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    var username by rememberSaveable { mutableStateOf("") }
                    // Username Input
                    IconTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = "Username",
                        icon = Icons.Default.Person,
                        keyboardType = KeyboardType.Text
                    )
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
fun PreviewProfileEditScreen() {
    ProfileEditScreen(navController = NavController(LocalContext.current))
}