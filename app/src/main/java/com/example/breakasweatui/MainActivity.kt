package com.example.breakasweatui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakaSweatUITheme {
                main(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
fun main(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "welcome"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("welcome") {
            WelcomeScreen(
                navNext = { navController.navigate("BeginningWorkout") })
        }
        composable("beginningWorkout") {
            BeginningWorkout(
                navNext = { navController.navigate("duringWorkout") },
                navPrev = { navController.navigate("welcome") })
        }
        composable("duringWorkout") {
            DuringWorkout(
                navNext = { /*TODO*/ },
                navPrev = { navController.navigate("beginningWorkout")})
        }
    }
}



@Composable
fun WelcomeScreen(
    navNext: () -> Unit,
    modifier: Modifier = Modifier) {
    // TODO: This state should be hoisted

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Break a Sweat!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = navNext
        ) {
            Text("Begin Workout")
        }
    }
}

@Composable
fun BeginningWorkout(
    navNext: () -> Unit,
    navPrev: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Begin Workout:")
        Button(
            onClick = navNext
        ) {
            Text("Start")
        }
        Button(
            onClick = navPrev
        ) {
            Text(text = "Back")
        }
        
    }
}

@Composable
fun DuringWorkout(
    navNext: () -> Unit,
    navPrev: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("DuringWorkout:")
        Button(
            onClick = navNext
        ) {
            Text("Start")
        }
        Button(
            onClick = navPrev
        ) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun WelcomePreview() {
    BreakaSweatUITheme {
        WelcomeScreen(navNext = {})
    }
}
