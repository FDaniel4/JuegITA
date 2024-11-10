package com.example.juegita.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    validate: (String) -> Boolean = { true }, // Función de validación personalizada
    errorMessage: String = "Formato inválido", // Mensaje de error personalizado
) {
    // Estado para la validación
    var isValid by remember { mutableStateOf(validate(value)) }

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
                    isValid = validate(it) // Ejecuta la validación cada vez que el usuario escribe
                },
                label = { Text(label) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = if (isValid) Color.Green else Color.Red,
                    unfocusedIndicatorColor = if (isValid) Color.LightGray else Color.Red
                ),
                isError = !isValid // Muestra el borde de error si no es válido
            )
        }
        // Muestra el mensaje de error si la validación falla
        if (!isValid) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    focusedIndicatorColor: Color = Color.Green,
    unfocusedIndicatorColor: Color = Color.LightGray,
    errorMessage: String = "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial.",
    showError: Boolean = true
) {
    // Estado para la validación
    var isPasswordValid by remember { mutableStateOf(true) }

    // Validación de la contraseña (mínimo 8 caracteres, una mayúscula, un número y un carácter especial)
    val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,}$")

    // Comprobamos si la contraseña es válida
    isPasswordValid = passwordPattern.matches(password)

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
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = containerColor,
                focusedIndicatorColor = if (isPasswordValid) focusedIndicatorColor else Color.Red,
                unfocusedIndicatorColor = if (isPasswordValid) unfocusedIndicatorColor else Color.Red
            ),
            isError = !isPasswordValid
        )
    }

    if (!isPasswordValid && showError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp)
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

