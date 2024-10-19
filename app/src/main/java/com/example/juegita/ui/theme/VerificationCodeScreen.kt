package com.example.juegita.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.AnnotatedString

@Composable
fun VerificationCodeScreen(
    onCodeComplete: (String) -> Unit
) {
    var code by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val maxDigits = 6
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until maxDigits) {
            OutlinedTextField(
                value = if (i < code.length) code[i].toString() else "",
                onValueChange = { value ->
                    if (value.length <= 1 && value.all { it.isDigit() }) {
                        code = if (value.isEmpty()) code.dropLast(1) else code + value
                        if (code.length == maxDigits) {
                            onCodeComplete(code)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.size(48.dp),
                visualTransformation = CodeDigitVisualTransformation(),
                singleLine = true
            )
        }
    }
}

@Composable
fun CodeDigitVisualTransformation(): VisualTransformation {
    return VisualTransformation { text ->
        TransformedText(
            AnnotatedString(if (text.isEmpty()) " " else text),
            OffsetMapping.Identity
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationCodeScreenPreview() {
    VerificationCodeScreen { code ->
        // Aquí puedes manejar lo que ocurre cuando el código se complete
        println("Código completado: $code")
    }
}


