package com.example.breakasweatui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
        CustomText(xText = "Welcome to Break a Sweat")
        CustomElevatedButton(xText = "Start Workout", xOnClick = navBegin,
            modifier = Modifier.height(100.dp).width(300.dp))
        CustomElevatedButton(xText = "Workout History", xOnClick = navHistory, modifier = Modifier.padding(top=40.dp))
        CustomElevatedButton(xText = "Edit Workouts", xOnClick = navModify)
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
        val exercises = PopulateData()
        CustomText(xText = "Begin Workout:")
        CustomText(xText = "Exercises for today:")
        ExerciseList(exercises = exercises)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomElevatedButton(xText = "Back", xOnClick = navBack)

            Spacer(modifier = Modifier.width(24.dp))

            CustomElevatedButton(xText = "Start", xOnClick = navDuring)
        }
    }

}

@Composable
fun DuringWorkout(
    navNext: () -> Unit, navBack: () -> Unit, openDrawer: () -> Unit, modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(xText = "DuringWorkout:")

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomElevatedButton(xText = "Back", xOnClick = navBack)
            Spacer(modifier = Modifier.width(24.dp))
            CustomElevatedButton(xText = "Next", xOnClick = navNext)
        }
    }
}

@Composable
fun Resting(
    navNext: () -> Unit, navBack: () -> Unit, openDrawer: () -> Unit, modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText("Resting:")
        CustomText("Next up:")
        CustomText("Exercise 3")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomElevatedButton(xText = "Back", xOnClick = navBack)
            Spacer(modifier = Modifier.width(24.dp))
            CustomElevatedButton(xText = "Continue", xOnClick = navNext)
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
        CustomText("Workout Completed")
        CustomText("Congrats! You completed a workout!")
        Spacer(modifier = Modifier.height(24.dp))
        CustomElevatedButton(xOnClick = navHome, xText = "Home")
        CustomElevatedButton(xOnClick = navHistory, xText = "Workout History")
    }
}

@Composable
fun WorkoutHistory(
    modifier: Modifier = Modifier, navHome: () -> Unit, openDrawer: () -> Unit, navBack: () -> Unit
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText("Workout History")
        CustomElevatedButton(xText = "Home", xOnClick = navHome)
        CustomElevatedButton(xText = "Back", xOnClick = navBack)
    }
}

@Composable
fun ModifyRoutine(                                                                  // Edit Workout
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
        CustomText("Edit Workout Routine")
        val exercises = PopulateData()
        ExerciseEditList(exercises = exercises)
        CustomElevatedButton(xText = "Home", xOnClick = navHome)
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
        CustomText("Settings:")

        CustomElevatedButton(xText = "Back", xOnClick = navBack)
        CustomElevatedButton(xText = "Home", xOnClick = navHome)
    }

}