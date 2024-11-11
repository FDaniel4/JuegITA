package com.example.juegita.interfaces

import ProjectInfoScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.juegita.MemoramaGame
import com.example.juegita.MinijuegosApp
import com.example.juegita.TicTacToeGame
import com.example.juegita.ui.theme.ForgotPasswordScreen
import com.example.juegita.ui.theme.PasswordEditScreen
import com.example.juegita.ui.theme.ProfileEditScreen
import com.example.juegita.ui.theme.TermsConditionsScreen
import com.example.juegita.ui.theme.VerificationCodeScreen
import com.example.juegita.views.ElJuegoDeLaViborita

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMultiScreenApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkedRemember by remember { mutableStateOf(true) }
    // Background Color
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6200EA)) // Purple background
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Icono grande circular encima de todo
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(140.dp) // Tamaño del ícono
                    //.clip(CircleShape) // Forma circular
                    //.background(Color.Black)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "User Icon",
                    tint = Color(Color.White.value),
                    modifier = Modifier.size(194.dp)
                )
            }
            val gradientColors = listOf(Color.Cyan, Color.Magenta)
            Text(
                text = "Iniciar sesión",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    brush = Brush.linearGradient(colors = gradientColors)
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp), // Padding alrededor de toda la caja
                verticalAlignment = Alignment.CenterVertically, // Alinear verticalmente al centro
            ){
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )
                TextField(
                    value = email,
                    onValueChange = {email = it},
                    label = { Text("Correo") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Green,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Contraseña",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
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
            // Checkbox "Recordar contraseña"
            Row(
                verticalAlignment = Alignment.CenterVertically
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
                Text(text = "Recordar contraseña", color = Color.White)
                TextButton(onClick = { navController.navigate("olvidar-contraseña") }) {
                    Text(
                        text = "¿Olvidaste tu contraseña?", color = Color.Yellow,
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = { navController.navigate("minijuegos") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)) // Orange Button
            ) {
                Text(text = "Iniciar sesión", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            TextButton(onClick = { navController.navigate("registro") }) {
                Text("No estas registrado? Registrate!", color = Color.Yellow)
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = NavHostController(LocalContext.current))
}

@Preview(showBackground = true)
@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    Surface (color = Color.White) {
        SetupNavGraph(navController = navController)
    }
}
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "inicio-sesion") {
        composable("inicio-sesion") { LoginScreen(navController) }
        composable("registro") { RegisterScreen(navController) }
        composable("minijuegos") { MinijuegosApp(navController) }
        composable("settings") { GlobalSettingsScreen(navController) }
        composable("terminos") { TermsConditionsScreen(navController) }
        composable("perfil") { UserProfileScreen(navController) }
        composable("editar-perfil") { ProfileEditScreen(navController) }
        composable("cambiar-contraseña") { PasswordEditScreen(navController) }
        composable("tic-tac-toe") { TicTacToeGame(navController) }
        composable("memorama") { MemoramaGame(navController) }
        composable("olvidar-contraseña") { ForgotPasswordScreen(navController) }
        composable("codigo-verificacion") { VerificationCodeScreen(navController) }
        composable("acerca-de") { ProjectInfoScreen(navController) }
        composable("juego-de-la-viborita") { ElJuegoDeLaViborita() } // Nueva pantalla
    }
}
