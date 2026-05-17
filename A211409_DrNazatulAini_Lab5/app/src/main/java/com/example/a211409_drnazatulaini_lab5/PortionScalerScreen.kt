package com.example.a211409_drnazatulaini_lab5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun PortionScalerScreen(viewModel: NgamNgamViewModel, onBack: () -> Unit) {
    var ingredientName by remember { mutableStateOf("") }
    var baseAmount by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var portions by remember { mutableFloatStateOf(1f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Dynamic Portion Scaler", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = ingredientName, onValueChange = { ingredientName = it }, label = { Text("Ingredient (e.g. Chicken)") }, modifier = Modifier.fillMaxWidth())
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = baseAmount, onValueChange = { baseAmount = it }, label = { Text("Base Amount (1 pax)") }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(value = unit, onValueChange = { unit = it }, label = { Text("Unit (g, kg)") }, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("How many people eating? ${portions.roundToInt()} pax", fontWeight = FontWeight.Bold)
        Slider(value = portions, onValueChange = { portions = it }, valueRange = 1f..10f, steps = 8)

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
            val totalAmount = (baseAmount.toFloatOrNull() ?: 0f) * portions.roundToInt()
            Text("Total Needed: $totalAmount $unit", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = {
                val finalAmount = (baseAmount.toFloatOrNull() ?: 0f) * portions.roundToInt()
                viewModel.addIngredient(ingredientName, finalAmount, unit)
                onBack()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = ingredientName.isNotBlank() && baseAmount.isNotBlank()
        ) { Text("Add to Smart List") }
    }
}