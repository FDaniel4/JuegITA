package com.example.juegita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun UserProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6200EA)), // Fondo azul claro
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
                tint = Color(Color.White.value),
                modifier = Modifier.size(194.dp)
            )

            // Nombre de usuario
            Text(
                text = "Username",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2) // Color azul oscuro
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Descripción
            Text(
                text = "Descripción breve del usuario o una cita inspiradora.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Sección de actividades recientes
            Text(
                text = "Actividades Recientes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            RecentActivitiesSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { /* Acción para editar perfil */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Editar Perfil", color = Color.White)
                }
                Button(
                    onClick = { /* Acción para ver configuración */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Configuración", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Enlaces a redes sociales
            Text(
                text = "Redes Sociales",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            SocialMediaLinks()
        }
    }
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
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = activity,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun SocialMediaLinks() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextButton(onClick = { /* Acción para Instagram */ }) {
            Text("Instagram", color = Color(0xFF1976D2))
        }
        TextButton(onClick = { /* Acción para Twitter */ }) {
            Text("Twitter", color = Color(0xFF1976D2))
        }
        TextButton(onClick = { /* Acción para Facebook */ }) {
            Text("Facebook", color = Color(0xFF1976D2))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen()
}