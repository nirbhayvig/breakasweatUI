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
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


data class Exercise(val name: String, val reps: Int?, val sets: Int?, val weight: Int?)

fun PopulateData(): List<Exercise> {
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
    xText: String, xOnClick: () -> Unit, modifier: Modifier = Modifier.padding(vertical = 12.dp),
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
    ElevatedButton(modifier = modifier, onClick = xOnClick,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(xText, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onTertiaryContainer)
            Text(xSubText, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onTertiaryContainer)
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
fun ExerciseList(
    exercises: List<Exercise>, modifier: Modifier = Modifier
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
            ExerciseButton(exercise = exercises[i])
        }
    }
}

@Preview
@Composable
fun ExerciseListPreview(

) {
    BreakaSweatUITheme() {
        ExerciseList(exercises = PopulateData())
    }
}

@Composable
fun ExerciseButton(
    exercise: Exercise
) {
    val exerciseSubText: String =
        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
    CustomElevatedButtonWithSubtext(
        xText = exercise.name,
        xSubText = exerciseSubText,
        xOnClick = { /*TODO*/ },
        modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 5.dp)
            .width(200.dp)
    )

}

@Composable
fun ExerciseEditList(
    exercises: List<Workout>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(10.dp)
            )
            .height(300.dp)
            .width(225.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(exercises.size) { i ->
            TextEdit(exercises[i].name)
        }
    }
}

@Composable
fun TextEdit(
    defaultText: String = "",
    modifier: Modifier = Modifier
) {
    var inputText by rememberSaveable{ mutableStateOf(defaultText) }

//    val exerciseSubText: String =
//        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
//    CustomText(xText = exercise.name)
//    CustomText(xText = exerciseSubText)

    OutlinedTextField(value = inputText, onValueChange = { inputText = it }, label = { Text(defaultText)})
}

// **NOTE**
// This Timer functionality comes from https://www.geeksforgeeks.org/how-to-create-a-timer-using-jetpack-compose-in-android/amp/
// This is not original work from our team, all credit to the author of this page

// create a composable to
// Draw arc and handle
@Composable
fun Timer(
    // total time of the timer
    totalTime: Long,

    // circular handle color
    handleColor: Color,

    // color of inactive bar / progress bar
    inactiveBarColor: Color,

    // color of active bar
    activeBarColor: Color,
    modifier: Modifier = Modifier,

    // set initial value to 1
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    // create variable for
    // size of the composable
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    // create variable for value
    var value by remember {
        mutableStateOf(initialValue)
    }
    // create variable for current time
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    // create variable for isTimerRunning
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        // draw the timer
        Canvas(modifier = modifier) {
            // draw the inactive arc with following parameters
            drawArc(
                color = inactiveBarColor, // assign the color
                startAngle = -215f, // assign the start angle
                sweepAngle = 250f, // arc angles
                useCenter = false, // prevents our arc to connect at te ends
                size = Size(size.width.toFloat(), size.height.toFloat()),
                // to make ends of arc round
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            // draw the active arc with following parameters
            drawArc(
                color = activeBarColor, // assign the color
                startAngle = -215f,  // assign the start angle
                sweepAngle = 250f * value, // reduce the sweep angle
                // with the current value
                useCenter = false, // prevents our arc to connect at te ends
                size = Size(size.width.toFloat(), size.height.toFloat()),
                // to make ends of arc round
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            // calculate the value from arc pointer position
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            // draw the circular pointer/ cap
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round  // make the pointer round
            )
        }
        // add value of the timer
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        // create button to start or stop the timer
        Button(
            onClick = {
                if(currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            // change button color
            colors = ButtonDefaults.buttonColors(
                  Color.Green
            )
        ) {
            Text(
                // change the text of button based on values
                text = if (isTimerRunning && currentTime >= 0L) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Restart"
            )
        }
    }
}
