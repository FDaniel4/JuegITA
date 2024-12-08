package com.example.juegita.interfaces


import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.R
import com.example.juegita.components.IconsButton
import com.example.juegita.components.TitleBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(navController: NavController) {
    val snackbarHostState = remember{ SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        TitleBar(name = "Contactar")
                    }
                },
                navigationIcon = {
                    IconsButton(icon = Icons.Default.ArrowBack) {
                        navController.navigate("settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }, // SnackbarHost para mostrar el snackbar
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
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "User Icon",
                        modifier = Modifier
                            .size(180.dp)
                            .padding(bottom = 16.dp)
                    )

                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = "CONTACTAR CON EL ADMINISTRADOR",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campos de entrada de datos
                    var nombre by rememberSaveable { mutableStateOf("") }
                    var correo by rememberSaveable { mutableStateOf("") }
                    var mensaje by rememberSaveable { mutableStateOf("") }

                    // Campo para el nombre
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Usuario",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Green,
                                unfocusedIndicatorColor = Color.LightGray
                            )
                        )
                    }

                    // Campo para el correo
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Correo electrónico",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        TextField(
                            value = correo,
                            onValueChange = { correo = it },
                            label = { Text("Correo electrónico") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Green,
                                unfocusedIndicatorColor = Color.LightGray
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Campo para el mensaje
                    TextField(
                        value = mensaje,
                        onValueChange = { newValue ->
                            if (newValue.length <= 255) {
                                mensaje = newValue
                            }
                        },
                        label = { Text("Mensaje") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Green,
                            unfocusedIndicatorColor = Color.LightGray
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón con SnackBar y envío de correo electrónico
                    Button(
                        onClick = {
                            // Mostrar SnackBar y luego intentar enviar el correo
                            coroutineScope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Mensaje enviado al administrador",
                                    actionLabel = "OK"
                                )
                                // Si se presiona el botón de acción del SnackBar o se muestra, enviar correo
                                if (result == SnackbarResult.ActionPerformed || result == SnackbarResult.Dismissed) {
                                    val intent = Intent(Intent.ACTION_SEND).apply {
                                        type =
                                            "message/rfc822" // Tipo de datos para aplicaciones de correo
                                        putExtra(
                                            Intent.EXTRA_EMAIL,
                                            arrayOf("palaciohernandezdaniel@gmail.com")
                                        )
                                        putExtra(Intent.EXTRA_SUBJECT, "Nuevo mensaje de usuario")
                                        putExtra(
                                            Intent.EXTRA_TEXT, """
                        Nombre: $nombre
                        Correo: $correo
                        Mensaje: $mensaje
                    """.trimIndent()
                                        )
                                    }
                                    try {
                                        // Intent para iniciar la app de correo
                                        navController.context.startActivity(
                                            Intent.createChooser(intent, "Enviar correo con:")
                                        )
                                    } catch (e: ActivityNotFoundException) {
                                        // Manejo en caso de que no haya aplicaciones de correo disponibles
                                        snackbarHostState.showSnackbar("No hay aplicaciones de correo instaladas.")
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)) // Botón color naranja
                    ) {
                        Text(text = "Aceptar", color = Color.White, fontSize = 19.sp)
                    }

                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactUsScreenPreview() {
    ContactUsScreen(navController = rememberNavController())
}
