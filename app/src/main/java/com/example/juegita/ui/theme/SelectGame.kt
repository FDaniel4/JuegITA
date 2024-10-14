import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter

class SelectGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinijuegosApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinijuegosApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Minijuegos") },
                navigationIcon = {
                    IconButton(onClick = { /* Acción para volver a la pantalla principal */ }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Ir a la pantalla principal",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción para ir a Configuración */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Abrir configuración",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { paddingValues ->
            // Pasamos el padding a PantallaPrincipal
            PantallaPrincipal(modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selecciona tu Minijuego",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Scroll horizontal para los botones de minijuegos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()), // Habilitar scroll horizontal
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Espaciado entre botones
        ) {
            MinijuegoButton(
                minijuego = "Minijuego 1",
                imageUrl = "https://www4.minijuegosgratis.com/v3/games/thumbnails/208506_1.jpg"
            )
            MinijuegoButton(
                minijuego = "Minijuego 2",
                imageUrl = "https://images8.alphacoders.com/397/397848.jpg"
            )
            MinijuegoButton(
                minijuego = "Minijuego 3",
                imageUrl = "https://tecnoguia.net/wp-content/uploads/2022/12/Como-se-juega-al-Buscaminas-e1672181342656.jpeg"
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Botón de estadísticas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OpcionesButton(texto = "Estadísticas") { /* Acción para ver Estadísticas */ }
        }
    }
}

@Composable
fun MinijuegoButton(minijuego: String, imageUrl: String) {
    Button(
        onClick = { /* Acción al seleccionar un minijuego */ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(200.dp) // Ajusta el ancho del botón
            .height(150.dp) // Ajusta el alto del botón
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Imagen del minijuego
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "Imagen de $minijuego",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Texto del minijuego
            Text(text = minijuego, fontSize = 18.sp)
        }
    }
}

@Composable
fun OpcionesButton(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = texto, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MinijuegosApp()
}
