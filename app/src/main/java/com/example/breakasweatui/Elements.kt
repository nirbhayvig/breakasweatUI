package com.example.breakasweatui

import android.widget.EditText
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme


data class Exercise(val name: String, val reps: Int?, val sets: Int?, val weight: Int?)

@Composable
fun CustomElevatedButton(
    xText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp),
    color: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer)
) {
    ElevatedButton(modifier = modifier, onClick = xOnClick, colors = color) {
        Text(xText, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Composable
fun CustomElevatedButtonWithSubtext(
    xText: String,
    xSubText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp)
) {
    ElevatedButton(
        modifier = modifier,
        onClick = xOnClick,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                xText,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                xSubText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CustomText(
    xText: String, modifier: Modifier = Modifier.padding(bottom = 20.dp)
) {
    Text(text = xText, modifier = modifier)
}


@Composable
fun WorkoutEditList(
    exercises: List<Workout>, modifier: Modifier = Modifier, update: (Workout) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(25.dp)
            )
            .height(300.dp)
            .width(225.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(exercises.size) { i ->
            WorkoutUpdateButton(exercise = exercises[i], update)
        }
    }
}

@Composable
fun WorkoutViewList(
    exercises: List<Workout>, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(25.dp)
            )
            .height(300.dp)
            .width(225.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(exercises.size) { i ->
            WorkoutViewButton(exercise = exercises[i])
        }
    }
}

@Composable
fun WorkoutToggleList(
    exercises: List<Workout>, modifier: Modifier = Modifier
) {
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
}


@Composable
fun WorkoutToggleButton(
    exercise: Workout
) {
    val exerciseSubText: String =
        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
    if (exercise.name == "Resting") {
        CustomElevatedButton(
            xText = exercise.name,
            xOnClick = { },
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 5.dp)
                .width(200.dp)
        )
    } else {
        CustomElevatedButtonWithSubtext(
            xText = exercise.name,
            xSubText = exerciseSubText,
            xOnClick = { },
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 5.dp)
                .width(200.dp)
        )
    }

}

@Composable
fun WorkoutViewButton(
    exercise: Workout
) {
    val exerciseSubText: String =
        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
    if (exercise.name == "Resting") {
        CustomElevatedButton(
            xText = exercise.name,
            xOnClick = { },
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 5.dp)
                .width(200.dp)
        )
    } else {
        CustomElevatedButtonWithSubtext(
            xText = exercise.name,
            xSubText = exerciseSubText,
            xOnClick = { },
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 5.dp)
                .width(200.dp)
        )
    }

}

@Composable
fun WorkoutUpdateButton(
    exercise: Workout, update: (Workout) -> Unit
) {
    val exerciseSubText: String =
        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
    if (exercise.name == "Resting") {
        CustomElevatedButton(
            xText = exercise.name,
            xOnClick = { update(exercise) },
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 5.dp)
                .width(200.dp)
        )
    } else {
        CustomElevatedButtonWithSubtext(
            xText = exercise.name,
            xSubText = exerciseSubText,
            xOnClick = { update(exercise) },
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 5.dp)
                .width(200.dp)
        )
    }

}

@Composable
fun TextEdit(
    defaultText: String = "", modifier: Modifier = Modifier
) {
    var inputText by rememberSaveable { mutableStateOf(defaultText) }

//    val exerciseSubText: String =
//        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
//    CustomText(xText = exercise.name)
//    CustomText(xText = exerciseSubText)

    OutlinedTextField(value = inputText,
        onValueChange = { inputText = it },
        label = { Text(defaultText) })
}







