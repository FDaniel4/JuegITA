package com.example.juegita.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(
    val icon : ImageVector,
    val text: String,
    val route: String
) {
    object ContactUsScreen : Routes(icon = Icons.Default.Email, "Contactar", "contacto")

    object MinijuegosApp : Routes(icon = Icons.Default.Star, "Juegos", "contacto")
    object UserProfileScreen : Routes(icon = Icons.Default.AccountCircle, "Cuenta", "cuenta")
    object AmigosScreen : Routes(icon = Icons.Default.Person, "Amigos", "amigos")
    object GlobalSettingsScreen : Routes(icon = Icons.Default.Settings, "Ajustes", "ajustes")
    object HomeView : Routes(icon = Icons.Default.ThumbUp, "Noticias", "juegos")

}