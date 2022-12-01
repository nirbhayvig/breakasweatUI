package com.example.breakasweatui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.breakasweatui.ui.theme.*

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
//        ExerciseEditList(exercises = exercises)

        AddNewWorkout(exercises)

        CustomElevatedButton(xText = "Delete All", xOnClick = {
            var workoutsToDelete = workoutDao.getAll()
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
    exercises: List<Workout>,
    modifier: Modifier = Modifier
) {
    var addNew by remember { mutableStateOf(false)}
    val popupWidth = 300.dp
    val popupHeight = 100.dp

    CustomElevatedButton(
        xText = "Add new", xOnClick = {
            workoutDao.insertAll(Workout(name = "Vigg", sets = 3, reps = 10, weight = 15))
            var exercises = workoutDao.getAll()
            addNew = true
        }, modifier = Modifier.padding(6.dp)
    )
    if(addNew) {
        Popup(
            onDismissRequest = { addNew = false },
            alignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .size(popupWidth, popupHeight)
                    .padding(top = 5.dp)
                    .background(DarkColors.background)
                    .border(BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextEdit(defaultText = "Exercise")
                }
            }
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