package com.example.juegita.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val gradientColors = listOf(Color.Cyan, Color.Magenta)
                        Text(
                            text = "Editar perfil",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(
                                brush = Brush.linearGradient(colors = gradientColors)
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("perfil") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Ir al perfil",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 1.dp)
                        )
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
                        Text(
                            text = "Subir imagen",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    var nombre by rememberSaveable { mutableStateOf("") }
                    // Edicion de nombre
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp), // Padding alrededor de toda la caja
                        verticalAlignment = Alignment.CenterVertically, // Alinear verticalmente al centro
                    ){
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Nombre",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        TextField(
                            value = nombre,
                            onValueChange = {nombre = it},
                            label = { Text("Nombre") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Green,
                                unfocusedIndicatorColor = Color.LightGray
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // Sección de editar perfil
                    var username by rememberSaveable { mutableStateOf("") }
                    // Email Input
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp), // Padding alrededor de toda la caja
                        verticalAlignment = Alignment.CenterVertically, // Alinear verticalmente al centro
                    ){
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Username",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        TextField(
                            value = username,
                            onValueChange = {username = it},
                            label = { Text("Username") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Green,
                                unfocusedIndicatorColor = Color.LightGray
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { navController.navigate("perfil") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)) // Orange Button
                    ) {
                        Text(text = "Aceptar", color = Color.White, fontSize = 19.sp)
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