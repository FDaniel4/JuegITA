package com.example.juegita

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.components.BottomNavItem
import com.example.juegita.components.Texts
import com.example.juegita.components.TitleBar
import com.example.juegita.components.VideoGameBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinijuegosApp(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Mini juegos")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        },
        content = { paddingValues ->
            PantallaPrincipal(navController = navController, modifier = Modifier.padding(paddingValues))
        },
        bottomBar = {
            val navItems = listOf(
                BottomNavItem("minijuegos", "Juegos", Icons.Filled.Star),
                BottomNavItem("perfil", "Cuenta", Icons.Filled.AccountCircle),
                BottomNavItem("amigos", "Amigos", Icons.Filled.AccountBox),
                BottomNavItem("settings", "Ajustes", Icons.Filled.Settings)
            )
            VideoGameBottomNavigation(navController, navItems)
        }
    )
}

@Composable
fun PantallaPrincipal(navController: NavHostController, modifier: Modifier = Modifier) {
    var selectedGame by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Texts(name = "Selecciona tu Minijuego", fontSize = 28)

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxHeight(0.8f) // Ajusta la altura del grid
        ) {
            items(listOf(
                "Tic-Tac-Toe" to R.drawable.tres_en_raya,
                "Memorama" to R.drawable.dado,
                "Buscaminas" to R.drawable.buscaminas
            )) { (minijuego, image) ->
                MinijuegoButton(
                    minijuego = minijuego,
                    image = image,
                    onClick = { selectedGame = minijuego }
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Botón para jugar el juego seleccionado
        if (selectedGame != null) {
            Text(
                text = "Juego seleccionado: $selectedGame",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(selectedGame!!) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
            ) {
                Text(
                    text = "Jugar $selectedGame",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun MinijuegoButton(
    minijuego: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color.Gray),
        modifier = Modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Imagen del minijuego
            Image(
                painter = painterResource(id = image),
                contentDescription = "Imagen de $minijuego",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Texto del minijuego
            Text(
                text = minijuego,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MinijuegosApp(navController = rememberNavController())
}
