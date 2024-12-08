import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.juegita.components.Alert
import com.example.juegita.components.BotonAncho
import com.example.juegita.components.CustomPasswordField
import com.example.juegita.components.IconTextField
import com.example.juegita.components.TextsColors
import com.example.juegita.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
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
            TextsColors(name = "Iniciar sesión", fontSize = 30)

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            IconTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            CustomPasswordField(
                password = password,
                onPasswordChange = { password = it },
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                label = "Ingrese su contraseña",
                containerColor = Color.White,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            BotonAncho(name = "Iniciar sesión") {
                loginViewModel.login(email, password){
                    navController.navigate("minijuegos")
                }
            }
            if (loginViewModel.showAlert) {
                Alert(title = "Error de inicio de sesión",
                    message = "Usuario y/o contraseña incorrectos",
                    confirmText = "Aceptar",
                    onConfirmClick = { loginViewModel.closeAlert() }) {

                }
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
    LoginScreen(navController = NavHostController(LocalContext.current), loginViewModel = LoginViewModel())
}
