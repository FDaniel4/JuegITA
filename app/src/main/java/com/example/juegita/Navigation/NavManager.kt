package com.example.juegita.Navigation


import AmigosScreen
import LoginScreen
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.juegita.MemoramaGame
import com.example.juegita.MinijuegosApp
import com.example.juegita.SimonSays.SimonSaysActivity
import com.example.juegita.TicTacToeGame
import com.example.juegita.camera.CameraScreen
import com.example.juegita.interfaces.ProfileEditScreen
import com.example.juegita.ui.theme.TermsConditionsScreen
import com.example.juegita.interfaces.*
import com.example.juegita.snake.Game
import com.example.juegita.snake.Snake
import com.example.juegita.viewModel.GamesViewModel
import com.example.juegita.viewModel.LoginViewModel
import com.example.juegita.viewModel.NotesViewModel
import com.example.juegita.viewsAPI.DetailView
import com.example.juegita.viewsAPI.HomeView
import com.example.juegita.viewsAPI.SearchGameView

@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: GamesViewModel, loginViewModel: LoginViewModel, notesViewModel: NotesViewModel) {
    NavHost(navController = navController, startDestination = "blank") {
        composable("juegos") { HomeView(viewModel, navController) }
        composable("DetailView/{id}/?{name}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("name") { type = NavType.StringType }
        )  ){
            val id = it.arguments?.getInt("id") ?: 0
            val name = it.arguments?.getString("name") ?: ""
            DetailView(viewModel, navController, id, name)
        }
        composable("SearchGameView"){
            SearchGameView(viewModel, navController)
        }
        composable("AddNoteView"){
            AddNoteView(navController, notesViewModel)
        }
        composable("EditNoteView/{idDoc}", arguments = listOf(
            navArgument("idDoc") { type = NavType.StringType }
        )){
            val idDoc = it.arguments?.getString("idDoc") ?: ""
            EditNoteView(navController, notesViewModel, idDoc)
        }
        composable("blank") { BlankView(navController) }
        composable("inicio-sesion") { LoginScreen(navController, loginViewModel) }
        composable("registro") { RegisterScreen(navController, loginViewModel) }
        composable("minijuegos") { MinijuegosApp(navController, notesViewModel) }
        composable("settings") { GlobalSettingsScreen(navController) }
        composable("terminos") { TermsConditionsScreen(navController) }
        composable("perfil") { UserProfileScreen(navController = navController,notesViewModel)}

        composable("editar-perfil") { ProfileEditScreen(navController) }
        composable("Tic-Tac-Toe") { TicTacToeGame(navController) }
        composable("Memorama") { MemoramaGame(navController) }

        composable("acerca-de") { ProjectInfoScreen(navController) }
        composable("contacto") { ContactUsScreen(navController) }
        composable("Simon says") {
            val context = LocalContext.current
            context.startActivity(Intent(context, SimonSaysActivity::class.java))
        }
        composable("camera") {
            // Obtener el contexto actual y pasarlo a CameraScreen
            val context = LocalContext.current
            CameraScreen(context)
        }
        composable("Snake") { val coroutineScope = rememberCoroutineScope()
            val game = remember { Game(coroutineScope) }
            Snake(game, navController) }
        composable("amigos") { AmigosScreen(navController) }

        composable(Routes.ContactUsScreen.route) {
            ContactUsScreen(navController)
        }
    }
}
