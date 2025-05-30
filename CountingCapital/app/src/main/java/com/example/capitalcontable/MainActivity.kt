package com.example.capitalcontable

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
import com.example.capitalcontable.ui.theme.CapitalContableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapitalContableTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CapitalContableScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CapitalContableScreen(modifier: Modifier = Modifier) {
    var capitalInicial by remember { mutableStateOf("") }
    var ingresos by remember { mutableStateOf("") }
    var gastos by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = capitalInicial,
            onValueChange = { capitalInicial = it },
            label = { Text("Capital Inicial") }
        )
        OutlinedTextField(
            value = ingresos,
            onValueChange = { ingresos = it },
            label = { Text("Ingresos") }
        )
        OutlinedTextField(
            value = gastos,
            onValueChange = { gastos = it },
            label = { Text("Gastos") }
        )
        Button(onClick = {
            val ci = capitalInicial.toDoubleOrNull() ?: 0.0
            val ing = ingresos.toDoubleOrNull() ?: 0.0
            val gas = gastos.toDoubleOrNull() ?: 0.0
            resultado = ci + ing - gas
        }) {
            Text("Calcular Capital Final")
        }

        resultado?.let {
            Text(text = "Capital Final: $it", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CapitalContablePreview() {
    CapitalContableTheme {
        CapitalContableScreen()
    }
}
