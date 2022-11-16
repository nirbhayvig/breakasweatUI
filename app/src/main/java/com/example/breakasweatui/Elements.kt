package com.example.breakasweatui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


data class Exercise(val name: String, val reps: Int?, val sets: Int?, val weight: Int?)

fun PopulateData() : List<Exercise> {
    return listOf<Exercise>(
        Exercise("Bicep Curls", 10, 3, 20),
        Exercise("Bench Press", 10, 3, 120),
        Exercise("Deadlift", 10, 3, 200),
        Exercise("Bicep Curls", 10, 3, 20),
        Exercise("Bench Press", 10, 3, 120),
        Exercise("Deadlift", 10, 3, 200),
    )
}

@Composable
fun CustomElevatedButton(
    xText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp)
) {
    ElevatedButton(modifier = modifier, onClick = xOnClick) {
        Text(xText)
    }
}

@Composable
fun CustomText(
    xText: String,
    modifier: Modifier = Modifier
) {
    Text(text = xText, modifier = Modifier.padding(bottom = 20.dp))
}

@Composable
fun ExerciseList(
    exercises: List<Exercise>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.border(BorderStroke(2.dp, color = Color.Gray)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(exercises.size) { i->
            ExerciseButton(exercise = exercises[i])
        }
    }
}

@Composable
fun ExerciseButton (
    exercise: Exercise,
) {
    CustomElevatedButton(xText = exercise.name, xOnClick = { /*TODO*/ }, modifier = Modifier.padding(vertical = 4.dp).width(200.dp))
}