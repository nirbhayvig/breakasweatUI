package com.example.breakasweatui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

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
        val exercises = workoutDao.getAll()
        resetToggle(exercises)
        CustomText(xText = "Begin Workout:")
        CustomText(xText = "Exercises for today:")
        WorkoutViewList(exercises = exercises)
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
    navNext: () -> Unit,
    navResting: () -> Unit,
    navBack: () -> Unit,
    openDrawer: () -> Unit,
    navDone: () -> Unit,
    navCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavBar(onButtonClicked = openDrawer)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(xText = "DuringWorkout:")
        val workoutList = workoutDao.getAll()
        var exercises by remember { mutableStateOf(workoutList) }
        var activeWorkout by remember { mutableStateOf(getActive(exercises)!!) }


        LazyColumn(
            modifier = Modifier
                .border(
                    BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(25.dp)
                )
                .height(300.dp)
                .width(225.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(exercises.size) { i ->
                WorkoutToggleButton(exercise = exercises[i])
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {

            CustomElevatedButton(xText = "Previous", xOnClick = {
                if(isFirst(exercises, activeWorkout)){
                    navCancel()
                } else if (activeWorkout.name == "Resting"){
                    activeWorkout = decrementToggle(exercises, activeWorkout)!!
                    exercises = workoutDao.getAll()
                    navNext()
                } else {
                    activeWorkout = decrementToggle(exercises, activeWorkout)!!
                    exercises = workoutDao.getAll()
                    navBack()
                }
            })

            Spacer(modifier = Modifier.width(24.dp))


            CustomElevatedButton(xText = "Next", xOnClick = {
                exercises = workoutDao.getAll()
                if (activeWorkout.name == "Resting") {
                    if(isLast(exercises,activeWorkout)) {
                        navDone()
                    } else {
                        activeWorkout = incrementToggle(exercises, activeWorkout)!!
                        navResting()
                    }
                } else if (isLast(exercises, activeWorkout)) {
                    navDone()
                } else {
                    activeWorkout = incrementToggle(exercises, activeWorkout)!!
                    navNext()
                }
            })
        }
        CustomElevatedButton(xText = "Cancel Workout", xOnClick = {
            resetToggle(exercises)
            exercises = workoutDao.getAll()
            navCancel()
        })

    }
}

fun isLast(
    exercises: List<Workout>, currentActive: Workout
): Boolean {
    var index = -1
    for (exercise in exercises) {
        if (exercise.equals(currentActive)) {
            index = exercises.indexOf(exercise)
        }
    }
    return index + 1 == exercises.size
}
fun isFirst(
    exercises: List<Workout>, currentActive: Workout
): Boolean {
    var index = -1
    for (exercise in exercises) {
        if (exercise.equals(currentActive)) {
            index = exercises.indexOf(exercise)
        }
    }
    return index == 0
}

fun getActive(
    exercises: List<Workout>
): Workout? {
    for (exercise in exercises) {
        if (exercise.isActive) {
            return exercise
        }
    }
    return null
}

fun resetToggle(
    exercises: List<Workout>
) {
    for (exercise in exercises) {
        if (exercise == exercises[0]) {
            exercise.isCompleted = false
            exercise.isActive = true
            workoutDao.updateUsers(exercise)
        } else {
            exercise.isCompleted = false
            exercise.isActive = false
            workoutDao.updateUsers(exercise)
        }
    }
    return
}

fun incrementToggle(
    exercises: List<Workout>, currentActive: Workout
): Workout? {
    var index = -1
    for (exercise in exercises) {
        if (exercise.equals(currentActive)) {
            index = exercises.indexOf(exercise)
        }
    }
    if (index >= 0 && index < exercises.size - 1) {
        val prevActive = currentActive
        prevActive.isActive = false
        prevActive.isCompleted = true
        workoutDao.updateUsers(prevActive)

        val nextActive = exercises[index.inc()]
        nextActive.isActive = true
        nextActive.isCompleted = false
        workoutDao.updateUsers(nextActive)
        return nextActive
    } else {
        return null
    }

}

fun decrementToggle(
    exercises: List<Workout>, currentActive: Workout
): Workout? {
    var index = -1
    for (exercise in exercises) {
        if (exercise.equals(currentActive)) {
            index = exercises.indexOf(exercise)
        }
    }
    if (index > 0 && index < exercises.size) {
        val prevActive = currentActive
        prevActive.isActive = false
        prevActive.isCompleted = false
        workoutDao.updateUsers(prevActive)

        val nextActive = exercises[index.dec()]
        nextActive.isActive = true
        nextActive.isCompleted = false
        workoutDao.updateUsers(nextActive)
        return nextActive
    } else {
        return null
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
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Timer(
                totalTime = 100L * 1000L,
                handleColor = Color.Green,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color(0xFF37B900),
                modifier = Modifier.size(200.dp)
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomText("Next Up:   ")
            CustomText("Exercise 3")
        }
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
        WorkoutEditList(exercises = exercises, update = update)

        CustomElevatedButton(
            xText = "Add new workout", xOnClick = addNew, modifier = Modifier.padding(6.dp)
        )

        CustomElevatedButton(
            xText = "Add new rest", xOnClick = {
                workoutDao.insertAll(
                    Workout(
                        name = "Resting",
                        reps = null,
                        sets = null,
                        weight = null,
                        isActive = false,
                        isCompleted = false
                    )
                )
                exercises = workoutDao.getAll()
            }, modifier = Modifier.padding(6.dp)
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
                        weight = weightInput.toInt(),
                        isActive = false,
                        isCompleted = false
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
                        weight = weightInput.toInt(),
                        isActive = false,
                        isCompleted = false
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