package com.example.inss

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
                    INSSScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun INSSScreen(modifier: Modifier = Modifier) {
    var nombreEmpleado by remember { mutableStateOf("") }
    var salarioBruto by remember { mutableStateOf("") }
    var inss by remember { mutableStateOf<Double?>(null) }
    var salarioNeto by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = nombreEmpleado,
            onValueChange = { nombreEmpleado = it },
            label = { Text("Nombre del Empleado") }
        )
        OutlinedTextField(
            value = salarioBruto,
            onValueChange = { salarioBruto = it },
            label = { Text("Salario Bruto") }
        )

        Button(onClick = {
            val salario = salarioBruto.toDoubleOrNull() ?: 0.0
            inss = salario * 0.07
            salarioNeto = salario - (inss ?: 0.0)
        }) {
            Text("Calcular INSS")
        }

        inss?.let {
            Text(text = "Empleado: $nombreEmpleado", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Descuento INSS (7%): $${"%.2f".format(it)}", style = MaterialTheme.typography.bodyLarge)
        }

        salarioNeto?.let {
            Text(text = "Salario Neto: $${"%.2f".format(it)}", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun INSSPreview() {
    MaterialTheme {
        INSSScreen()
    }
}
