package com.example.juegita.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TitleBar(name: String){
    val gradientColors = listOf(Color.Cyan, Color.Magenta)
    Text(
        text = name,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = Brush.linearGradient(colors = gradientColors)
        )
    )
}
@Composable
fun TextsColors(name: String, fontSize: Int){
    val gradientColors = listOf(Color.Cyan, Color.Magenta)
    Text(
        text = name,
        fontSize = fontSize.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = Brush.linearGradient(colors = gradientColors)
        )
    )
}
@Composable
fun Texts(name: String, fontSize: Int){
    Text(
        text = name,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}
