import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.R

class SelectGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinijuegosApp(navController = rememberNavController())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinijuegosApp(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val gradientColors = listOf(Color.Cyan, Color.Magenta)
                        Text(
                            text = "Mini Juegos",
                            color = Color.Black,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(
                                brush = Brush.linearGradient(colors = gradientColors)
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {  navController.navigate("inicio-sesion") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Ir a la pantalla principal",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 1.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  navController.navigate("settings") }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Abrir configuración",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 1.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
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
            fontSize = 28.sp,
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
                minijuego = "Tres en raya",
                image = R.drawable.tres_en_raya
            )
            MinijuegoButton(
                minijuego = "Buscaminas",
                image = R.drawable.buscaminas
            )
            MinijuegoButton(
                minijuego = "Memorama",
                image = R.drawable.dado
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
fun MinijuegoButton(minijuego: String,@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Acción al seleccionar un minijuego */ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color.Gray),
        modifier = Modifier
            .width(200.dp)
            .height(150.dp)
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

@Composable
fun OpcionesButton(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = texto,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MinijuegosApp(navController = rememberNavController())
}
