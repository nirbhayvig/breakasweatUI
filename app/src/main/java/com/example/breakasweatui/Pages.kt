package com.example.breakasweatui

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun HomeScreen(
    navBegin: () -> Unit,
    navHistory: () -> Unit,
    navModify: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {

    NavBar(onButtonClicked = openDrawer)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Welcome to Break a Sweat!", modifier = Modifier.padding(bottom = 70.dp))

        ElevatedButton(modifier = Modifier
            .padding(vertical = 30.dp)
            .height(100.dp)
            .width(270.dp), onClick = navBegin,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("Start Workout", fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary)
        }

        ElevatedButton(modifier = Modifier.padding(vertical = 6.dp), onClick = navHistory,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
            Text("Workout History", color = MaterialTheme.colorScheme.onSecondary)
        }

        ElevatedButton(modifier = Modifier.padding(vertical = 6.dp), onClick = navModify,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
            Text("Edit Workouts", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Composable
fun BeginningWorkout(
    navDuring: () -> Unit,
    navBack: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Begin Workout:")
        Text("Exercises:")
        Text("Exercise 1, 2, & 3")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            ElevatedButton(onClick = navBack,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Text("Back", color = MaterialTheme.colorScheme.onSecondaryContainer)
            }

            Spacer(modifier = Modifier.width(24.dp))

            ElevatedButton(onClick = navDuring,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.width(150.dp).height(50.dp)) {
                Text("Start", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }

}

@Composable
fun DuringWorkout(
    navNext: () -> Unit,
    navBack: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("DuringWorkout:")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            ElevatedButton(onClick = navBack,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Text("Back", color = MaterialTheme.colorScheme.onSecondaryContainer)
            }


            Spacer(modifier = Modifier.width(24.dp))

            ElevatedButton(onClick = navNext,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                Text("Next", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
fun Resting(
    navNext: () -> Unit,
    navBack: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resting:")
        Text("Next up:")
        Text("Exercise 3")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            ElevatedButton(onClick = navBack,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Text("Back", color = MaterialTheme.colorScheme.onSecondaryContainer)
            }
            Spacer(modifier = Modifier.width(24.dp))
            ElevatedButton(onClick = navNext,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                Text("Continue", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
fun Completed(
    navHome: () -> Unit,
    navHistory: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Workout Completed")
        Text("Congrats! You completed a workout!")
        Spacer(modifier = Modifier.height(24.dp))
        ElevatedButton(onClick = navHome, modifier = Modifier.padding(vertical = 12.dp).height(80.dp).width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("Home", color=MaterialTheme.colorScheme.onPrimary)
        }

        ElevatedButton(onClick = navHistory, modifier = Modifier.padding(vertical = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
            Text("Workout History", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Composable
fun WorkoutHistory(
    modifier: Modifier = Modifier,
    navHome: () -> Unit,
    openDrawer: () -> Unit,
    navBack: () -> Unit
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Workout History")

        ElevatedButton(onClick = navHome,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("Home", color=MaterialTheme.colorScheme.onPrimary)
        }
        ElevatedButton(onClick = navBack,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
            Text(text = "Back", color = MaterialTheme.colorScheme.onSecondaryContainer)
        }
    }
}

@Composable
fun ModifyRoutine(
    modifier: Modifier = Modifier,
    navHome: () -> Unit,
    openDrawer: () -> Unit,

    ) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Edit Workout Routine")

        ElevatedButton(onClick = navHome,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("Home", color=MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    navHome: () -> Unit,
    openDrawer: () -> Unit,
    navBack: () -> Unit,
    ) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings:")

        ElevatedButton(onClick = navBack,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
            Text(text = "Back", color = MaterialTheme.colorScheme.onSecondaryContainer)
        }

        ElevatedButton(onClick = navHome,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("Home", color=MaterialTheme.colorScheme.onPrimary)
        }
    }

}