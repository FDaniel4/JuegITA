package com.example.juegita.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.juegita.components.BottomNavItem
import com.example.juegita.components.CustomExtendedFAB
import com.example.juegita.components.IconsButton
import com.example.juegita.components.Texts
import com.example.juegita.components.TitleBar
import com.example.juegita.components.VideoGameBottomNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Perfil de usuario")
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

                    // Nombre de usuario
                    Text(
                        text = "Username",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Nombre
                    Texts(name = "Nombre", fontSize = 16)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Sección de editar perfil
                    CustomExtendedFAB(
                        text = "EDITAR PERFIL",
                        icon = Icons.Filled.Edit,
                        onClick = { navController.navigate("editar-perfil") }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    // Sección de editar perfil
                    CustomExtendedFAB(
                        text = "CAMBIAR CONTRASEÑA",
                        icon = Icons.Filled.Lock,
                        onClick = { navController.navigate("cambiar-contraseña") }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    // Sección de actividades recientes
                    AssistChip(
                        onClick = {},
                        label = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Actividad reciente",
                                    color = Color.Black,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center),
                                )
                            }},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        elevation = AssistChipDefaults.assistChipElevation(1.dp),
                        border = null,
                        colors = AssistChipDefaults.assistChipColors(Color.White)

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RecentActivitiesSection()


                }
            }
        },
        bottomBar = {
            val navItems = listOf(
                BottomNavItem("minijuegos", "Juegos", Icons.Filled.Star),
                BottomNavItem("perfil", "Cuenta", Icons.Filled.AccountCircle),
                BottomNavItem("amigos", "Amigos", Icons.Filled.AccountBox),
                BottomNavItem("settings", "Configuración", Icons.Filled.Settings)
            )
            VideoGameBottomNavigation(navController, navItems)
        }
    )
}

@Composable
fun RecentActivitiesSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        ActivityCard(activity = "Jugó Memorama - 10/10/2024")
        ActivityCard(activity = "Completó el curso de Kotlin - 09/10/2024")
        ActivityCard(activity = "Publicó una nueva foto - 08/10/2024")
    }
}

@Composable
fun ActivityCard(activity: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Text(
            text = activity,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen(navController = NavController(LocalContext.current))
}