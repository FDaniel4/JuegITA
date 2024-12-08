package com.example.juegita.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.juegita.components.CardNote
import com.example.juegita.components.CustomExtendedFAB
import com.example.juegita.components.TitleBar
import com.example.juegita.components.VideoGameBottomNavigation
import com.example.juegita.viewModel.NotesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController,notesViewModel: NotesViewModel) {

    LaunchedEffect(Unit){
        notesViewModel.fetchNotes()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Perfil de usuario")
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("AddNoteView")
                    }){
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Agregar nota",
                            tint = Color.White
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Icono
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "User Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(194.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Sección de editar perfil
                    CustomExtendedFAB(
                        text = "EDITAR PERFIL",
                        icon = Icons.Filled.Edit,
                        onClick = { navController.navigate("editar-perfil") }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    // Sección de notas

                    AssistChip(
                        onClick = {},
                        label = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Mis notas",
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
                    val datos by notesViewModel.notesData.collectAsState()
                    LazyColumn{
                        items(datos){ item ->
                            CardNote(title = item.title, note = item.note, date = item.date ) {
                                navController.navigate("EditNoteView/${item.idDoc}")
                            }
                        }
                    }
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

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen(navController = NavController(LocalContext.current), notesViewModel = NotesViewModel())
}