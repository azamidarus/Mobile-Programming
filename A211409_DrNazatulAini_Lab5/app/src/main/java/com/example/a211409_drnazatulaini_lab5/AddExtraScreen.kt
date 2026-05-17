package com.example.a211409_drnazatulaini_lab5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddExtraScreen(viewModel: NgamNgamViewModel, onBack: () -> Unit) {
    var snackName by remember { mutableStateOf("") }
    var showWarningDialog by remember { mutableStateOf(false) }

    if (showWarningDialog) {
        AlertDialog(
            onDismissRequest = { showWarningDialog = false },
            title = { Text("Anti-Waste Warning \u26A0\uFE0F") },
            text = { Text("Are you sure you need '$snackName'? Buying unnecessary extras is the leading cause of food waste.") },
            confirmButton = {
                Button(onClick = {
                    viewModel.addIngredient(snackName, 1f, "pack", isExtra = true)
                    showWarningDialog = false
                    onBack()
                }) { Text("I Really Need It") }
            },
            dismissButton = {
                OutlinedButton(onClick = { showWarningDialog = false }) { Text("Cancel (Save Money)") }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Add Extra Cravings", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = snackName, onValueChange = { snackName = it }, label = { Text("Snack Name (e.g. Chips)") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { showWarningDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth(),
            enabled = snackName.isNotBlank()
        ) { Text("Add to List") }
    }
}