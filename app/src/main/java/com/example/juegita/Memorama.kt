package com.example.juegita

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

// Este es el juego de Trivia con 50 preguntas y 15 aleatorias

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoramaGame(navController: NavController) {
    // Definir las 50 preguntas y respuestas de la trivia
    val questions = listOf(
        Question("¿Cuál es la capital de México?", listOf("México DF", "Guadalajara", "Monterrey"), 0),
        Question("¿Cuántos planetas hay en el sistema solar?", listOf("8", "9", "7"), 0),
        Question("¿Quién escribió 'Cien años de soledad'?", listOf("Gabriel García Márquez", "Mario Vargas Llosa", "Carlos Fuentes"), 0),
        Question("¿Qué elemento químico tiene el símbolo H?", listOf("Hidrógeno", "Helio", "Hafnio"), 0),
        Question("¿Qué país tiene forma de bota?", listOf("Italia", "Chile", "España"), 0),
        Question("¿Cuántos continentes hay en el mundo?", listOf("5", "6", "7"), 2),
        Question("¿Quién pintó la Mona Lisa?", listOf("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh"), 0),
        Question("¿Cuál es el río más largo del mundo?", listOf("Amazonas", "Nilo", "Yangtsé"), 0),
        Question("¿En qué año llegó el hombre a la luna?", listOf("1969", "1959", "1979"), 0),
        Question("¿Cuál es el continente más grande?", listOf("Asia", "América", "África"), 0),
        Question("¿En qué año se fundó Facebook?", listOf("2004", "2000", "2010"), 0),
        Question("¿Cuál es la lengua más hablada en el mundo?", listOf("Chino mandarín", "Inglés", "Español"), 0),
        Question("¿Qué animal es conocido por su lentitud?", listOf("Perezoso", "Liebre", "Tortuga"), 2),
        Question("¿Cuál es el metal más caro del mundo?", listOf("Oro", "Platino", "Rutenio"), 2),
        Question("¿Qué gas respiramos los seres humanos?", listOf("Oxígeno", "Nitrógeno", "Dióxido de carbono"), 0),
        Question("¿Qué año comenzó la Segunda Guerra Mundial?", listOf("1939", "1945", "1929"), 0),
        Question("¿Cuál es el río más largo de Europa?", listOf("Volga", "Danubio", "Rin"), 0),
        Question("¿Qué país tiene más habitantes?", listOf("China", "India", "Estados Unidos"), 0),
        Question("¿Quién fue el primer presidente de los Estados Unidos?", listOf("George Washington", "Abraham Lincoln", "Thomas Jefferson"), 0),
        Question("¿Cuál es el océano más grande?", listOf("Pacífico", "Atlántico", "Índico"), 0),
        Question("¿En qué continente se encuentra el desierto de Sahara?", listOf("África", "Asia", "Australia"), 0),
        Question("¿Cuál es el metal más utilizado en la fabricación de cables eléctricos?", listOf("Cobre", "Aluminio", "Oro"), 0),
        Question("¿Quién es el autor de la teoría de la relatividad?", listOf("Albert Einstein", "Isaac Newton", "Galileo Galilei"), 0),
        Question("¿Qué instrumento musical tiene 88 teclas?", listOf("Piano", "Guitarra", "Violín"), 0),
        Question("¿Qué animal tiene el cuello más largo?", listOf("Jirafa", "Elefante", "Cebra"), 0),
        Question("¿Cuántos huesos tiene el cuerpo humano adulto?", listOf("206", "208", "201"), 0),
        Question("¿Cuál es el nombre del famoso barco hundido en 1912?", listOf("Titanic", "Bismarck", "Lusitania"), 0),
        Question("¿En qué país se inventó la pizza?", listOf("Italia", "Francia", "España"), 0),
        Question("¿Cuál es la capital de Japón?", listOf("Tokio", "Pekín", "Seúl"), 0),
        Question("¿Cuántos jugadores tiene un equipo de fútbol?", listOf("11", "12", "10"), 0),
        Question("¿En qué año terminó la Primera Guerra Mundial?", listOf("1918", "1914", "1920"), 0),
        Question("¿Quién pintó la famosa obra 'La noche estrellada'?", listOf("Vincent van Gogh", "Pablo Picasso", "Claude Monet"), 0),
        Question("¿Qué tipo de animal es un delfín?", listOf("Mamífero", "Pez", "Reptil"), 0),
        Question("¿Cuántos estados tiene Estados Unidos?", listOf("50", "51", "52"), 0),
        Question("¿En qué país se encuentra la Gran Muralla?", listOf("China", "Japón", "Corea del Sur"), 0),
        Question("¿Cómo se llama el proceso mediante el cual las plantas fabrican su propio alimento?", listOf("Fotosíntesis", "Respiración", "Fermentación"), 0),
        Question("¿Quién escribió la obra 'Don Quijote de la Mancha'?", listOf("Miguel de Cervantes", "Garcilaso de la Vega", "Luis de Góngora"), 0),
        Question("¿Qué es el ADN?", listOf("Ácido desoxirribonucleico", "Ácido ribonucleico", "Ácido desoxirriboso"), 0),
        Question("¿Qué país tiene la mayor cantidad de islas?", listOf("Suecia", "Indonesia", "Filipinas"), 0),
        Question("¿En qué continente se encuentra Egipto?", listOf("África", "Asia", "Europa"), 0),
        Question("¿Cuál es el animal terrestre más grande?", listOf("Elefante africano", "Ballena azul", "Rinoceronte"), 0),
        Question("¿Qué tipo de animal es un pingüino?", listOf("Ave", "Mamífero", "Reptil"), 0),
        Question("¿Cuál es la montaña más alta del mundo?", listOf("Everest", "K2", "Kangchenjunga"), 0),
        Question("¿Cuál es la moneda oficial de Japón?", listOf("Yen", "Won", "Dólar"), 0),
        Question("¿En qué ciudad se encuentra la Torre Eiffel?", listOf("París", "Londres", "Roma"), 0),
        Question("¿Qué gas es responsable del cambio climático?", listOf("Dióxido de carbono", "Oxígeno", "Nitrógeno"), 0),
        Question("¿Cuántos países hay en América?", listOf("35", "30", "50"), 0),
        Question("¿Quién inventó la bombilla eléctrica?", listOf("Thomas Edison", "Nikola Tesla", "Alexander Graham Bell"), 0),
        Question("¿Qué océano está entre América y Europa?", listOf("Atlántico", "Pacífico", "Índico"), 0),
        Question("¿Qué país tiene el mayor número de pirámides?", listOf("Sudán", "Egipto", "México"), 0),
        Question("¿En qué año comenzó la Revolución Mexicana?", listOf("1910", "1920", "1900"), 0),
        Question("¿Qué instrumento musical tiene cuerdas?", listOf("Guitarra", "Batería", "Trompeta"), 0)
    )


    // Escoge 15 preguntas aleatorias cada vez que inicias el juego
    val randomQuestions = remember { questions.shuffled().take(15) }

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var showResults by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val gradientColors = listOf(Color.Blue, Color.Black)
                        Text(
                            text = "Trivia",
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
                    IconButton(onClick = { navController.navigate("minijuegos") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_2), // Asegúrate de tener esta imagen en drawable
                contentDescription = "Fondo Trivia",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            if (!showResults) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pregunta ${currentQuestionIndex + 1}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1976D2),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = randomQuestions[currentQuestionIndex].question,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,  // Cambié el color a blanco
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Mostrar las opciones de respuesta
                    randomQuestions[currentQuestionIndex].answers.forEachIndexed { index, answer ->
                        AnswerButton(
                            answerText = answer,
                            onClick = {
                                // Verifica si la respuesta es correcta
                                if (index == randomQuestions[currentQuestionIndex].correctAnswerIndex) {
                                    score++
                                }
                                // Avanza a la siguiente pregunta
                                if (currentQuestionIndex < randomQuestions.size - 1) {
                                    currentQuestionIndex++
                                } else {
                                    // Muestra los resultados
                                    showResults = true
                                }
                            }
                        )
                    }

                    // Botón para regresar al menú
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = { navController.navigate("minijuegos") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Volver al Menú",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                // Mostrar resultados
                ResultadosView(score = score, navController = navController)
            }
        }
    }
}

// Función para mostrar cada botón de respuesta
@Composable
fun AnswerButton(answerText: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = answerText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

// Vista de resultados
@Composable
fun ResultadosView(score: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Juego Terminado!",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1976D2),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Aquí cambiamos el color a blanco para el texto del puntaje
        Text(
            text = "Tu puntaje es: $score/15",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,  // Cambié el color a blanco
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para regresar al menú
        Button(
            onClick = { navController.navigate("minijuegos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Volver al Menú",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

// Clase de datos para cada pregunta
data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
