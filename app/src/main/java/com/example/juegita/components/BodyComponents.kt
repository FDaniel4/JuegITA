package com.example.juegita.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.juegita.model.GameList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.Black,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            TextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                label = { Text(label) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )
        }
    }
}



@Composable
fun CustomPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(label) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityChange) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Info else Icons.Default.Info,
                        contentDescription = null
                    )
                }
            }
        )
    }

}



@Composable
fun CustomExtendedFAB(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentColor: Color = Color.Black,
    fontSize: Int = 22,
    fontWeight: FontWeight = FontWeight.Bold
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Icon(icon, contentDescription = null)
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = fontWeight
        )
    }
}



@Composable
fun BotonAncho(name: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
    ) {
        Text(text = name, color = Color.White, fontSize = 19.sp)
    }
}

@Composable
fun IconsButton(icon: ImageVector, onClick: () -> Unit){
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun VideoGameBottomNavigation(
    navController: NavController,
    items: List<BottomNavItem>
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF0D1A35),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(item.icon, contentDescription = item.label)
                },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Evita múltiples instancias de la misma ruta en la pila
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.LightGray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Color(0xFF6200EA)
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNoti(title: String, showBackButton: Boolean = false, onClickBackButton: () -> Unit,onClickAction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF2B2626)
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onClickBackButton() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            if (!showBackButton) {
                IconButton(onClick = { onClickAction() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    )
}

@Composable
fun CardGame(game: GameList, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(10.dp)
            .shadow(40.dp)
            .clickable { onClick() }
    ) {
        Column {
            MainImage(image = game.background_image)
        }
    }
}


@Composable
fun MainImage(image: String){
    val image = rememberImagePainter(data = image)

    Image(painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

@Composable
fun MetaWebsite(url: String) {

    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    Column {
        Text(
            text = "METASCORE",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        Button(
            onClick = { context.startActivity(intent) }, colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Gray

            )
        ) {
            Text(text = "Sitio Web")
        }
    }
}

@Composable
fun ReviewCard(metascore: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF209B14)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = metascore.toString(),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp
            )
        }
    }
}

@Composable
fun Loader(){
    val circleColors: List<Color> = listOf(
        Color(0xFF5851D8),
        Color(0xFF833AB4),
        Color(0xFFC13584),
        Color(0xFFE1306C),
        Color(0xFFFD1D1D),
        Color(0xFFF56040),
        Color(0xFFF77737),
        Color(0xFFFCAF45),
        Color(0xFFFFDC80),
        Color(0xFF5851D8)
    )

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 360,
                easing = LinearEasing
            )
        ), label = ""
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(size = 100.dp)
            .rotate(degrees = rotateAnimation)
            .border(
                width = 4.dp,
                brush = Brush.sweepGradient(circleColors),
                shape = CircleShape
            ),
        progress = 1f,
        strokeWidth = 1.dp,
        color = MaterialTheme.colorScheme.background

    )
}

