package com.example.ivaproducto

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
                    IVAScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IVAScreen(modifier: Modifier = Modifier) {
    var nombreProducto by remember { mutableStateOf("") }
    var precioSinIVA by remember { mutableStateOf("") }
    var iva by remember { mutableStateOf<Double?>(null) }
    var precioFinal by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = nombreProducto,
            onValueChange = { nombreProducto = it },
            label = { Text("Nombre del Producto") }
        )
        OutlinedTextField(
            value = precioSinIVA,
            onValueChange = { precioSinIVA = it },
            label = { Text("Precio sin IVA") }
        )

        Button(onClick = {
            val precio = precioSinIVA.toDoubleOrNull() ?: 0.0
            iva = precio * 0.16
            precioFinal = precio + (iva ?: 0.0)
        }) {
            Text("Calcular IVA")
        }

        iva?.let {
            Text(text = "Producto: $nombreProducto", style = MaterialTheme.typography.bodyLarge)
            Text(text = "IVA (16%): C$${"%.2f".format(it)}", style = MaterialTheme.typography.bodyLarge)
        }

        precioFinal?.let {
            Text(text = "Precio Final: C$${"%.2f".format(it)}", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IVAPreview() {
    MaterialTheme {
        IVAScreen()
    }
}
