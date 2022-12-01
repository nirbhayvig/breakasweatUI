package com.example.breakasweatui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

val db = WorkoutDatabase.getInstance(null)
val workoutDao = db.workoutDao()


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
        CustomElevatedButton(
            xText = "Start Workout",
            xOnClick = navBegin,
            modifier = Modifier
                .height(100.dp)
                .width(300.dp)
        )
        CustomElevatedButton(
            xText = "Workout History",
            xOnClick = navHistory,
            modifier = Modifier.padding(top = 40.dp)
        )
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
fun ModifyRoutine(
    // Edit Workout
    modifier: Modifier = Modifier,
    navHome: () -> Unit,
    openDrawer: () -> Unit,
    addNew: () -> Unit,
    update: (Workout) -> Unit
) {
    NavBar(onButtonClicked = openDrawer)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomText("Edit Workout Routine")
        val workoutList = workoutDao.getAll()
        var exercises by remember { mutableStateOf(workoutList) }
        WorkoutList(exercises = exercises, update = update)

        CustomElevatedButton(
            xText = "Add new", xOnClick = addNew, modifier = Modifier.padding(6.dp)
        )

        CustomElevatedButton(xText = "Delete All", xOnClick = {
            val workoutsToDelete = workoutDao.getAll()
            for (workout in workoutsToDelete) {
                workoutDao.delete(workout)
                exercises = workoutDao.getAll()
            }
        }, modifier = Modifier.padding(6.dp))
        CustomElevatedButton(xText = "Home", xOnClick = navHome, modifier = Modifier.padding(6.dp))
    }
}

@Composable
fun AddNewWorkout(
    navBack: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var exerciseInput by remember { mutableStateOf("") }
        var setsInput by remember { mutableStateOf("") }
        var repsInput by remember { mutableStateOf("") }
        var weightInput by remember { mutableStateOf("") }
        var isSaved by remember { mutableStateOf(false) }

        TextField(value = exerciseInput,
            onValueChange = { exerciseInput = it },
            label = { Text(text = "Exercise") })
        TextField(value = repsInput,
            onValueChange = { repsInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Number of repetitions") })
        TextField(value = setsInput,
            onValueChange = { setsInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Number of sets") })
        TextField(value = weightInput,
            onValueChange = { weightInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Weight in pounds") })

        CustomElevatedButton(xText = "Back", xOnClick = navBack)
        if (!isSaved) {
            CustomElevatedButton(xText = "Add new workout", xOnClick = {
                workoutDao.insertAll(
                    Workout(
                        name = exerciseInput,
                        reps = repsInput.toInt(),
                        sets = setsInput.toInt(),
                        weight = weightInput.toInt()
                    )
                )
                isSaved = true
            })
        } else {
            CustomText(
                xText = "Workout has been saved! Please return to previous menu",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
fun UpdateWorkout(
    navBack: () -> Unit, workout: Workout, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var exerciseInput by remember { mutableStateOf(workout.name) }
        var setsInput by remember { mutableStateOf(workout.sets.toString()) }
        var repsInput by remember { mutableStateOf(workout.reps.toString()) }
        var weightInput by remember { mutableStateOf(workout.weight.toString()) }
        var isSaved by remember { mutableStateOf(false) }
        TextField(value = exerciseInput,
            onValueChange = { exerciseInput = it },
            label = { Text(text = "Exercise") })
        TextField(value = repsInput,
            onValueChange = { repsInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Number of repetitions") })
        TextField(value = setsInput,
            onValueChange = { setsInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Number of sets") })
        TextField(value = weightInput,
            onValueChange = { weightInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Weight in pounds") })

        CustomElevatedButton(xText = "Back", xOnClick = navBack)
        if (!isSaved) {
            CustomElevatedButton(xText = "Add new workout", xOnClick = {
                workoutDao.updateUsers(
                    Workout(
                        uid = workout.uid,
                        name = exerciseInput,
                        reps = repsInput.toInt(),
                        sets = setsInput.toInt(),
                        weight = weightInput.toInt()
                    )
                )
                isSaved = true
            })
        } else {
            CustomText(
                xText = "Workout has been saved! Please return to previous menu",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
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
        CustomText("Settings:")

        CustomElevatedButton(xText = "Back", xOnClick = navBack)
        CustomElevatedButton(xText = "Home", xOnClick = navHome)
    }

}