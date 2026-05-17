package com.example.a211409_drnazatulaini_lab5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShoppingListScreen(uiState: NgamNgamState, viewModel: NgamNgamViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Smart Merged List", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Items are merged automatically to prevent double-buying.", color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.groceryList.isEmpty()) {
            Text("Your list is empty. Good job not hoarding! \uD83C\uDF3F", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn {
                items(uiState.groceryList) { item ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = item.isBought, onCheckedChange = { viewModel.toggleItemBought(item) })
                            Column {
                                Text(
                                    text = "${item.name} ${if (item.isExtra) "(Extra)" else ""}",
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = if (item.isBought) TextDecoration.LineThrough else TextDecoration.None
                                )
                                Text(text = "${item.amount} ${item.unit}")
                            }
                        }
                    }
                }
            }
        }
    }
}