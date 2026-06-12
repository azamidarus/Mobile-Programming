package com.example.a211409_drnazatulaini_project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a211409_drnazatulaini_project2.ui.theme.A211409_DrNazatulAini_Project2Theme

// TAMBAH: Community dalam senarai Route
enum class Route { Home, Scaler, Extra, List, Profile, Scanner, Community }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A211409_DrNazatulAini_Project2Theme {
                NgamNgamApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NgamNgamApp(viewModel: NgamNgamViewModel = viewModel(factory = NgamNgamViewModel.Factory)) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Route.Home.name
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = currentRoute == Route.Home.name,
                    onClick = { navController.navigate(Route.Home.name) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("List") },
                    selected = currentRoute == Route.List.name,
                    onClick = { navController.navigate(Route.List.name) }
                )
                // TAMBAH: Butang Donate kat menu bawah
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Share, contentDescription = null) },
                    label = { Text("Donate") },
                    selected = currentRoute == Route.Community.name,
                    onClick = { navController.navigate(Route.Community.name) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Profile") },
                    selected = currentRoute == Route.Profile.name,
                    onClick = { navController.navigate(Route.Profile.name) }
                )
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Route.Home.name, modifier = Modifier.padding(innerPadding)) {
            composable(Route.Home.name) {
                HomeScreen(
                    onNavigateToScaler = { navController.navigate(Route.Scaler.name) },
                    onNavigateToExtra = { navController.navigate(Route.Extra.name) },
                    onNavigateToScanner = { navController.navigate(Route.Scanner.name) }
                )
            }
            composable(Route.Scaler.name) {
                PortionScalerScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
            }
            composable(Route.Extra.name) {
                AddExtraScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
            }
            composable(Route.List.name) {
                ShoppingListScreen(uiState = uiState, viewModel = viewModel)
            }
            // TAMBAH: Skrin Community diletakkan dalam Navigasi
            composable(Route.Community.name) {
                CommunityScreen(viewModel = viewModel)
            }
            composable(Route.Profile.name) {
                ProfileScreen(uiState = uiState)
            }
            composable(Route.Scanner.name) {
                ScannerScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
            }
        }
    }
}