package com.example.breakasweatui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme


sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "Home")
    object Settings : DrawerScreens("Settings", "Settings")
    object History : DrawerScreens("Workout History", "WorkoutHistory")
}

private val screens = listOf(
    DrawerScreens.Home, DrawerScreens.Settings, DrawerScreens.History
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier, onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Text(
            text = "Menu:",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(text = screen.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                })
        }
    }
}

@Composable
fun NavBar(onButtonClicked: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
    ) {
        IconButton(modifier = Modifier.padding(vertical = 4.dp), onClick = onButtonClicked) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
    }
}
