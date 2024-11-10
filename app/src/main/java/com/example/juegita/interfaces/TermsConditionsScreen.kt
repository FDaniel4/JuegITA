package com.example.juegita.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.components.IconsButton
import com.example.juegita.components.TitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsConditionsScreen(navController: NavController) {
    val termsText = """
1. Aceptación de los Términos
Al descargar, instalar y/o usar esta aplicación de minijuegos (en adelante, "la App"), usted acepta cumplir y estar sujeto a los siguientes términos y condiciones (en adelante, "los Términos"). Si no está de acuerdo con estos Términos, no debe utilizar la App.

2. Descripción del Servicio
La App ofrece una colección de juegos simples (por ejemplo, Tres en Raya, Buscaminas, etc.) diseñados para el entretenimiento. Los juegos son de uso gratuito, aunque pueden incluir anuncios o compras dentro de la aplicación (si aplica).

3. Elegibilidad
Para usar la App, debe tener al menos 13 años de edad. Si es menor de edad, deberá contar con el consentimiento de un tutor legal.

4. Uso Aceptable
Usted se compromete a utilizar la App de manera legal y adecuada. Esto incluye, pero no se limita a:

No realizar actividades que violen las leyes aplicables.
No interferir con el funcionamiento de la App.
No utilizar ningún sistema automatizado para interactuar con la App.
No violar los derechos de otros usuarios o de los propietarios de la App.
5. Propiedad Intelectual
Todos los derechos de propiedad intelectual en relación con los juegos, diseños, gráficos, y cualquier otro contenido proporcionado por la App son propiedad exclusiva del desarrollador o de sus licenciantes. No se le concede ningún derecho sobre dicho contenido, salvo los permisos limitados de uso establecidos en estos Términos.

6. Publicidad y Compras dentro de la App
Publicidad: La App puede contener anuncios de terceros. No somos responsables del contenido de estos anuncios.
Compras dentro de la App (si aplica): Algunas funciones o contenido pueden requerir pagos adicionales. Todos los pagos son finales y no reembolsables, salvo indicación contraria en la ley.

7. Protección de Datos y Privacidad
Nos comprometemos a proteger su privacidad. Cualquier información personal que recopilemos de usted será utilizada conforme a nuestra [Política de Privacidad]. No compartimos información personal con terceros, salvo lo dispuesto en la ley o cuando sea necesario para el funcionamiento de la App.

8. Exención de Responsabilidad
La App se proporciona "tal cual" y no garantizamos que sea completamente libre de errores o interrupciones. No garantizamos que los resultados obtenidos a través de la App serán precisos o confiables. Usted acepta utilizar la App bajo su propio riesgo.

9. Limitación de Responsabilidad
En ningún caso, el desarrollador será responsable de ningún daño directo, indirecto, incidental, especial o consecuente que surja del uso de la App o la incapacidad de usarla, incluso si se ha advertido de la posibilidad de tales daños.

10. Modificaciones a los Términos
Nos reservamos el derecho de modificar estos Términos en cualquier momento. Si realizamos cambios sustanciales, le notificaremos a través de la App. Su uso continuado de la App después de cualquier modificación constituirá su aceptación de los Términos revisados.

11. Cancelación y Suspensión
Nos reservamos el derecho de suspender o cancelar su acceso a la App en cualquier momento si incumple estos Términos o por cualquier otra razón válida.

13. Contacto
Si tiene alguna pregunta o inquietud sobre estos Términos, puede ponerse en contacto con nosotros a través de juegita@gmail.com.

    """.trimIndent()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Terminos y condiciones")
                },
                navigationIcon = {
                    IconsButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(2.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = termsText,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = Color.Black
                    )
                }
            }

                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
                ) {
                    Text(text = "Ok"
                        , color = Color.White, fontSize = 19.sp)
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsConditionsScreen(
        navController = rememberNavController()
    )
}
