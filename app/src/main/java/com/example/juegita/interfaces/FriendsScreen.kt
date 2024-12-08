
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.components.BottomNavItem
import com.example.juegita.components.TitleBar
import com.example.juegita.components.VideoGameBottomNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmigosScreen(navController: NavHostController) {
    var showOnlineOnly by remember { mutableStateOf(false) }

    // Lista de amigos con su estado (en línea o desconectado)
    val amigos = remember {
        listOf(
            "Juan Pérez" to "En línea",
            "Ana Gómez" to "Desconectado",
            "Carlos Ruiz" to "En línea",
            "María López" to "Desconectado"
        )
    }

    val filteredAmigos = amigos.filter {
        it.second == "En línea" || !showOnlineOnly
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Lista de amigos")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                ),
                actions = {
                    IconButton(onClick = { showOnlineOnly = !showOnlineOnly }) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Filtrar amigos en línea",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Acción para agregar amigo */ }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Amigo")
            }
        },
        content = { padding ->
            // Usamos un Column con Modifier.weight(1f) para distribuir el espacio correctamente
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                AmigosList(amigos = filteredAmigos)
            }
        },
        bottomBar = {
            val navItems = listOf(
                BottomNavItem("minijuegos", "Juegos", Icons.Filled.Star),
                BottomNavItem("perfil1", "Cuenta", Icons.Filled.AccountCircle),
                BottomNavItem("amigos", "Amigos", Icons.Filled.AccountBox),
                BottomNavItem("settings", "Configuración", Icons.Filled.Settings)
            )
            VideoGameBottomNavigation(navController, navItems)
        }
    )

}

@Composable
fun AmigosList(amigos: List<Pair<String, String>>) {
    LazyColumn {
        items(amigos) { amigo ->
            AmigoItem(amigo = amigo)
        }
    }
}

@Composable
fun AmigoItem(amigo: Pair<String, String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = amigo.first,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = amigo.second,
            color = if (amigo.second == "En línea") Color.Green else Color.Gray,
            modifier = Modifier.padding(start = 8.dp) // Añadimos un espacio entre el nombre y el estado
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAmigosScreen() {
    AmigosScreen(navController = rememberNavController()   )
}

