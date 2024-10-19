package com.example.juegita.interfaces

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalSettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val gradientColors = listOf(Color.Cyan, Color.Magenta)
                        Text(
                            text = "Configuración",
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
                    IconButton(onClick = {  navController.navigate("minijuegos") }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Ir a la pantalla principal",
                            tint = Color.White,
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
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Sección de Cuenta
                ExtendedFloatingActionButton(onClick = { navController.navigate("perfil") },
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ) {
                    Icon(Icons.Filled.AccountCircle, "")
                    Text(
                        text =" CUENTA",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                AssistChip(
                    onClick = {},
                    label = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "PREFERNCIAS",
                                color = Color.Black,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }},
                    modifier = Modifier.fillMaxWidth()
                        .height(45.dp),
                    elevation = AssistChipDefaults.assistChipElevation(1.dp),
                    border = null,
                    colors = AssistChipDefaults.assistChipColors(Color.White)

                )
                // Sección de Audio
                ToggleSwitch(settingName = "Sonido", isChecked = true)

                // Sección de Notificaciones
                ToggleSwitch(settingName = "Notificaciones", isChecked = false)

                // Sección de Lenguaje
                LanguageDropdown()

                // Sección de Ayuda
                ExtendedFloatingActionButton(onClick = { navController.navigate("contacto") },
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ) {
                    Icon(Icons.Filled.Email, "")
                    Text(
                        text =" CONTACTAR",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Sección de Acerca de
                ExtendedFloatingActionButton(onClick = { navController.navigate("acerca-de") },
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ) {
                    Icon(Icons.Filled.Info, "")
                    Text(
                        text =" ACERCA DE",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    )
}

@Composable
fun ToggleSwitch(settingName: String, isChecked: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = settingName, modifier = Modifier.weight(1f),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        var checked by remember { mutableStateOf(isChecked)}
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            thumbContent = {
                if (checked) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "",
                        Modifier.size(InputChipDefaults.AvatarSize),
                        tint = Color.Black
                    )
                }else{
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "",
                        Modifier.size(InputChipDefaults.AvatarSize),
                        tint = Color.White
                    )
                }
            },
            colors = SwitchDefaults.colors(
                checkedBorderColor =Color.Green,
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Red,
                checkedTrackColor = Color.White,
                uncheckedTrackColor = Color.White,
                uncheckedBorderColor =Color.Red,
            )
        )
    }
}

@Composable
fun LanguageDropdown() {
    var selectedLanguage by remember { mutableStateOf("Español") }
    val languages = listOf("Español", "Inglés", "Francés", "Alemán")

    Column {
        Text("Seleccionar idioma", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        DropdownMenuWithItems(
            selectedItem = selectedLanguage,
            items = languages,
            onItemSelected = { selectedLanguage = it }
        )
    }
}

@Composable
fun DropdownMenuWithItems(
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .border(2.dp, Color.Black, RoundedCornerShape(5.dp))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = selectedItem, fontSize = 18.sp)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Icono de menú",
                tint = Color.Black
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GlobalSettingsScreenPreview() {
    GlobalSettingsScreen(navController = rememberNavController())
}
