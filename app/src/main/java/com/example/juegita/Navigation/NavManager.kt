package com.example.juegita.Navigation


import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.juegita.MemoramaGame
import com.example.juegita.MinijuegosApp
import com.example.juegita.SimonSays.SimonSaysActivity
import com.example.juegita.TicTacToeGame
import com.example.juegita.camera.CameraScreen
import com.example.juegita.interfaces.ForgotPasswordScreen
import com.example.juegita.interfaces.PasswordEditScreen
import com.example.juegita.interfaces.ProfileEditScreen
import com.example.juegita.ui.theme.TermsConditionsScreen
import com.example.juegita.interfaces.VerificationCodeScreen
import com.example.juegita.interfaces.*
import com.example.juegita.snake.ElJuegoDeLaViborita

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "inicio-sesion") {
        composable("inicio-sesion") { LoginScreen(navController) }
        composable("registro") { RegisterScreen(navController) }
        composable("minijuegos") { MinijuegosApp(navController) }
        composable("settings") { GlobalSettingsScreen(navController) }
        composable("terminos") { TermsConditionsScreen(navController) }
        composable("perfil/{imageUri}/{nombre}/{username}") { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri") ?: ""
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val username = backStackEntry.arguments?.getString("username") ?: ""
            UserProfileScreen(
                navController = navController,
                imageUri = imageUri,
                nombre = nombre,
                username = username
            )
        }

        composable("perfil1"){UserProfileScreen(navController = navController)}
        composable("editar-perfil") { ProfileEditScreen(navController) }
        composable("cambiar-contraseña") { PasswordEditScreen(navController) }
        composable("Tic-Tac-Toe") { TicTacToeGame(navController) }
        composable("Memorama") { MemoramaGame(navController) }
        composable("olvidar-contraseña") { ForgotPasswordScreen(navController) }
        composable("codigo-verificacion/{email}") {
            VerificationCodeScreen(navController = navController)
        }
        composable("acerca-de") { ProjectInfoScreen(navController) }
        composable("contacto") { ContactUsScreen(navController) }
        composable("new-password") { NewPasswordScreen(navController) }
        composable("Simon says") {
            val context = LocalContext.current
            context.startActivity(Intent(context, SimonSaysActivity::class.java))
        }
        composable("camera") {
            // Obtener el contexto actual y pasarlo a CameraScreen
            val context = LocalContext.current
            CameraScreen(context)
        }
        composable("Snake") { ElJuegoDeLaViborita(navController) }
    }
}
