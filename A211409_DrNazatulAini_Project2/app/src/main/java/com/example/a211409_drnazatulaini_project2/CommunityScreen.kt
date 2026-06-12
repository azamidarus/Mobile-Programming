package com.example.a211409_drnazatulaini_project2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommunityScreen(viewModel: NgamNgamViewModel) {
    val communityItems by viewModel.communityList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCommunityItems()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Community Food Bank", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Live data from Firebase Firestore", color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))

        if (communityItems.isEmpty()) {
            Text("No food donated yet. Be the first!")
        } else {
            LazyColumn {
                items(communityItems) { item ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(text = "Amount Available: ${item.amount} ${item.unit}")
                        }
                    }
                }
            }
        }
    }
}