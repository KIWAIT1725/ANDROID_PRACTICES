package com.example.promedioestudiante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotaFinalScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NotaFinalScreen(modifier: Modifier = Modifier) {
    var corte1 by remember { mutableStateOf("") }
    var corte2 by remember { mutableStateOf("") }
    var corte3 by remember { mutableStateOf("") }
    var promedio by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = corte1,
            onValueChange = { corte1 = it },
            label = { Text("Nota del Corte 1") }
        )
        OutlinedTextField(
            value = corte2,
            onValueChange = { corte2 = it },
            label = { Text("Nota del Corte 2") }
        )
        OutlinedTextField(
            value = corte3,
            onValueChange = { corte3 = it },
            label = { Text("Nota del Corte 3") }
        )

        Button(onClick = {
            val n1 = corte1.toDoubleOrNull() ?: 0.0
            val n2 = corte2.toDoubleOrNull() ?: 0.0
            val n3 = corte3.toDoubleOrNull() ?: 0.0
            promedio = (n1 + n2 + n3) / 3
        }) {
            Text("Calcular Promedio Final")
        }

        promedio?.let {
            Text(
                text = "Promedio Final: %.2f".format(it),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotaFinalPreview() {
    MaterialTheme {
        NotaFinalScreen()
    }
}
