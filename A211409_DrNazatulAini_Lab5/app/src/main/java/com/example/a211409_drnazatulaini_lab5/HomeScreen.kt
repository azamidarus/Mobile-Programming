package com.example.a211409_drnazatulaini_lab5

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onNavigateToScaler: () -> Unit, onNavigateToExtra: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(100.dp))
        Text("NgamNgam", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text("Zero Waste Shopping", fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateToScaler, modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Text("Plan a Meal (Portion Scaler)")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onNavigateToExtra, modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Text("Add Cravings / Snacks")
        }
    }
}