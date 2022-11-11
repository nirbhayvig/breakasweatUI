package com.example.breakasweatui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Modifier
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakaSweatUITheme {
                Main(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
fun Main(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "Home"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("Home") {
            HomeScreen(
                navNext = { navController.navigate("BeginningWorkout") },
                navHistory = {navController.navigate("WorkoutHistory")},
                navModify = { navController.navigate("ModifyRoutine")},
                navSettings = { navController.navigate("Settings") }
                )
        }
        composable("BeginningWorkout") {
            BeginningWorkout(
                navNext = { navController.navigate("DuringWorkout") },
                navPrev = { navController.navigate("Home") })
        }
        composable("DuringWorkout") {
            DuringWorkout(
                navNext = { navController.navigate("Resting")},
                navPrev = { navController.navigate("BeginningWorkout")})
        }
        composable("WorkoutHistory") {
            WorkoutHistory(
                navHome = {navController.navigate("Home")}
            )
        }
        composable("ModifyRoutine") {
            ModifyRoutine(
                navHome = {navController.navigate("Home")}
            )
        }
        composable("Resting") {
            Resting(
                navNext = { navController.navigate("CompletedWorkout") },
                navPrev = { navController.navigate("DuringWorkout") }
            )
        }
        composable("CompletedWorkout") {
            Completed(
                navHome = { navController.navigate("Home") },
                navHistory = { navController.navigate("WorkoutHistory") }
            )
        }
        composable("Settings") {
            Settings(
                navHome = { navController.navigate("Home") }
            )
        }
    }
}



@Composable
fun HomeScreen(
    navNext: () -> Unit,
    navHistory: () -> Unit,
    navModify: () -> Unit,
    navSettings: () -> Unit,
    modifier: Modifier = Modifier) {
    // TODO: This state should be hoisted
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton( modifier = Modifier.padding(vertical = 4.dp),onClick = navSettings) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }

    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Break a Sweat!", modifier = Modifier.padding(bottom = 24.dp))

        ElevatedButton(modifier = Modifier.padding(vertical = 12.dp), onClick = navNext) {
            Text("Start Workout")
        }

        ElevatedButton(modifier = Modifier.padding(vertical = 6.dp), onClick = navHistory) {
            Text("Workout History")
        }

        ElevatedButton(modifier = Modifier.padding(vertical = 6.dp), onClick = navModify) {
            Text("Edit Workouts")
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
        Text("Exercises:")
        Text("Exercise 1, 2, & 3")

        ElevatedButton(onClick = navNext) {
            Text("Start")
        }

        ElevatedButton(onClick = navPrev) {
            Text("Back")
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
        ElevatedButton(onClick = navNext) {
            Text("Next")
        }

        ElevatedButton(onClick = navPrev) {
            Text("Back")
        }
    }
}

@Composable
fun Resting(
    navNext: () -> Unit,
    navPrev: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resting:")
        Text("Next up:")
        Text("Exercise 3")

        ElevatedButton(onClick = navNext) {
            Text("Continue")
        }

        ElevatedButton(onClick = navPrev) {
            Text("Back")
        }
    }
}

@Composable
fun Completed(
    navHome: () -> Unit,
    navHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Workout Completed")
        Text("Congrats! You completed a workout!")

        ElevatedButton(onClick = navHome) {
            Text("Home")
        }

        ElevatedButton(onClick = navHistory) {
            Text("Workout History")
        }
    }
}

@Composable
fun WorkoutHistory(
    modifier: Modifier = Modifier,
    navHome: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text("Workout History")

        ElevatedButton(onClick = navHome) {
            Text("Home")
        }
    }
}

@Composable
fun ModifyRoutine(
    modifier: Modifier = Modifier,
    navHome: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Edit Workout Routine")

        ElevatedButton(onClick = navHome) {
            Text("Home")
        }
    }
}

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    navHome: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings:")

        ElevatedButton(onClick = navHome) {
            Text("Home")
        }
    }
}

//
//@Preview(showBackground = true, widthDp = 320, heightDp = 320)
//@Composable
//fun WelcomePreview() {
//    BreakaSweatUITheme {
//        WelcomeScreen(navNext = {})
//    }
//}
