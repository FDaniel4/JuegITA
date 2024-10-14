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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

import com.example.juegita.R

class MainActivity : ComponentActivity() {
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
                imageRes = R.drawable.minijuego1
            )
            MinijuegoButton(
                minijuego = "Minijuego 2",
                imageRes = R.drawable.minijuego2
            )
            MinijuegoButton(
                minijuego = "Minijuego 3",
                imageRes = R.drawable.minijuego3
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
fun MinijuegoButton(minijuego: String, imageRes: Int) {
    Button(
        onClick = { /* Acción al seleccionar un minijuego */ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(200.dp)  // Ajusta el ancho del botón
            .height(150.dp), // Ajusta el alto del botón
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent  // Fondo del botón transparente
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen como fondo del botón
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Imagen de $minijuego",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Texto con degradado sobre la imagen
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Blue, Color.Red)  // Degradado azul a rojo
                            )
                        )
                    ) {
                        append(minijuego)
                    }
                },
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)  // Centra el texto en el botón
            )
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
