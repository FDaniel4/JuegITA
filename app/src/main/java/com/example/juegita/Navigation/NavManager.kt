package com.example.juegita.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.juegita.MemoramaGame
import com.example.juegita.MinijuegosApp
import com.example.juegita.TicTacToeGame
import com.example.juegita.interfaces.ForgotPasswordScreen
import com.example.juegita.interfaces.PasswordEditScreen
import com.example.juegita.ui.theme.ProfileEditScreen
import com.example.juegita.ui.theme.TermsConditionsScreen
import com.example.juegita.interfaces.VerificationCodeScreen
import com.example.juegita.interfaces.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "inicio-sesion") {
        composable("inicio-sesion") { LoginScreen(navController) }
        composable("registro") { RegisterScreen(navController) }
        composable("minijuegos") { MinijuegosApp(navController) }
        composable("settings") { GlobalSettingsScreen(navController) }
        composable("terminos") {TermsConditionsScreen(navController) }
        composable("perfil") { UserProfileScreen(navController) }
        composable("editar-perfil") { ProfileEditScreen(navController) }
        composable("cambiar-contraseña") { PasswordEditScreen(navController) }
        composable("tic-tac-toe") { TicTacToeGame(navController) }
        composable("memorama") { MemoramaGame(navController) }
        composable("olvidar-contraseña") { ForgotPasswordScreen(navController) }
        composable("codigo-verificacion/{email}") { backStackEntry ->
            VerificationCodeScreen(navController = navController)
        }
        composable("acerca-de") { ProjectInfoScreen(navController) }
        composable("contacto") { ContactUsScreen(navController) }
        composable("new-password") { NewPasswordScreen(navController) }
    }
}
