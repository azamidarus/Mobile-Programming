package com.example.a211409_drnazatulaini_lab5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(uiState: NgamNgamState) {
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { startAnimation = true }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AnimatedVisibility(visible = startAnimation, enter = scaleIn(animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(100.dp), tint = MaterialTheme.colorScheme.primary)
                Text("Azami Darus", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Matric No: A211409", color = MaterialTheme.colorScheme.secondary)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        AnimatedVisibility(visible = startAnimation, enter = slideInVertically(initialOffsetY = { it }, animationSpec = tween(800)) + fadeIn(animationSpec = tween(800))) {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SDG 12 Impact \uD83C\uDF0E", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Meals Planned Smartly: ${uiState.totalMealsPlanned}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("You are an Earth Saver! By using NgamNgam, you prevent overbuying and stop food waste at the source.", textAlign = TextAlign.Center)
                }
            }
        }
    }
}