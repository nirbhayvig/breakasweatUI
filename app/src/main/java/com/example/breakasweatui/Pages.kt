package com.example.breakasweatui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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
        Text(text = "Welcome to Break a Sweat!", modifier = Modifier.padding(bottom = 24.dp))

        ElevatedButton(modifier = Modifier.padding(vertical = 12.dp), onClick = navBegin) {
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
            ElevatedButton(onClick = navBack) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(24.dp))

            ElevatedButton(onClick = navDuring, ) {
                Text("Start")
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
            ElevatedButton(onClick = navBack) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(24.dp))

            ElevatedButton(onClick = navNext) {
                Text("Next")
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
            ElevatedButton(onClick = navBack) {
                Text("Back")
            }
            Spacer(modifier = Modifier.width(24.dp))
            ElevatedButton(onClick = navNext) {
                Text("Continue")
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
        ElevatedButton(onClick = navHome, modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Home")
        }

        ElevatedButton(onClick = navHistory, modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Workout History")
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

        ElevatedButton(onClick = navHome) {
            Text("Home")
        }
        ElevatedButton(onClick = navBack) {
            Text(text = "Back")
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

        ElevatedButton(onClick = navHome) {
            Text("Home")
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

        ElevatedButton(onClick = navBack) {
            Text(text = "Back")
        }

        ElevatedButton(onClick = navHome) {
            Text("Home")
        }
    }

}