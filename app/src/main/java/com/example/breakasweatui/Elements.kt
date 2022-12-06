package com.example.breakasweatui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import com.example.breakasweatui.ui.theme.md_theme_dark_background
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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
fun CustomOutlinedButton(
    xText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp),
    color: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer)
) {
    OutlinedButton(modifier = modifier, onClick = xOnClick, colors = color) {
        Text(xText, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Composable
fun CustomOutlinedButtonHeadline(
    xText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp),
    color: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer)
) {
    OutlinedButton(modifier = modifier, onClick = xOnClick, colors = color) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                xText,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CustomElevatedButtonWithSubtext(
    xText: String,
    xSubText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp),
    color: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
) {
    ElevatedButton(
        modifier = modifier, onClick = xOnClick, colors = color
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                xText, color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                xSubText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CustomOutlinedButtonWithSubtext(
    xText: String,
    xSubText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp),
    color: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
) {
    OutlinedButton(
        modifier = modifier, onClick = xOnClick, colors = color
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                xText,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                xSubText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CustomOutlinedButtonWithHeadlineSubtext(
    xText: String,
    xSubText: String,
    xOnClick: () -> Unit,
    modifier: Modifier = Modifier.padding(vertical = 12.dp),
    color: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
) {
    OutlinedButton(
        modifier = modifier, onClick = xOnClick, colors = color
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                xText,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                xSubText,
                style = MaterialTheme.typography.titleSmall,
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
    exercises: List<Workout>,
    modifier: Modifier = Modifier,
    update: (Workout) -> Unit,
    deleted: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(25.dp)
            )
            .height(300.dp)
            .width(300.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(exercises.size) { i ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    update(exercises[i])
                }) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Delete",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
                WorkoutUpdateButton(exercise = exercises[i], update)
                IconButton(onClick = {
                    workoutDao.delete(exercises[i])
                    deleted()
                }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun WorkoutViewList(
    exercises: List<Workout>, buttonType: String, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, color = Color.Gray), RoundedCornerShape(25.dp)
            )
            .height(300.dp)
            .width(225.dp)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(exercises.size) { i ->
            WorkoutViewButton(exercise = exercises[i], buttonType)
        }
    }
}

@Composable
fun WorkoutToggleButton(
    exercise: Workout,
    color: ButtonColors = if (exercise.isActive) {
        ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)
    } else if (exercise.isCompleted) {
        ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background)
    } else {
        ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
    },
    modifier: Modifier = Modifier
        .padding(vertical = 6.dp, horizontal = 5.dp)
        .width(200.dp),
) {
    val exerciseSubText: String =
        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
    if (exercise.name == "Resting") {
        CustomOutlinedButton(
            xText = exercise.name, xOnClick = { }, modifier = modifier, color = color
        )
    } else {
        CustomOutlinedButtonWithSubtext(
            xText = exercise.name,
            xSubText = exerciseSubText,
            xOnClick = { },
            modifier = modifier,
            color = color
        )
    }
}

@Composable
fun WorkoutViewButton(
    exercise: Workout,
    buttonType: String,
) {
    val exerciseSubText: String =
        "" + exercise.sets + "x" + exercise.reps + "@" + exercise.weight + "lbs"
    if (exercise.name == "Resting") {
        if (buttonType == "Filled") {
            CustomElevatedButton(
                xText = exercise.name,
                xOnClick = { },
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 5.dp)
                    .width(200.dp)
            )
        } else if (buttonType == "Outlined") {
            CustomOutlinedButton(
                xText = exercise.name,
                xOnClick = { },
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 5.dp)
                    .width(200.dp),
                color = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    } else {
        if (buttonType == "Filled") {
            CustomElevatedButtonWithSubtext(
                xText = exercise.name,
                xSubText = exerciseSubText,
                xOnClick = { },
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 5.dp)
                    .width(200.dp)
            )
        } else if (buttonType == "Outlined") {
            CustomOutlinedButtonWithSubtext(
                xText = exercise.name,
                xSubText = exerciseSubText,
                xOnClick = { },
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 5.dp)
                    .width(200.dp),
                color = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        }
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

//point representation
data class Point(val x: Float, val y: Float)

@Composable
fun SuperSimpleLineChart(
    modifier: Modifier = Modifier, yPoints: List<Float> = listOf(
        445f, 400f, 320f, 330f, 270f, 150f
    ), graphColor: Color = Color.Green
) {
    val outColor = MaterialTheme.colorScheme.onBackground
    val spacing = 100f
    Box(
        modifier = Modifier.padding(all = 20.dp)
    ) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(190.dp)
        ) {
            rotate(degrees = -90f, Offset(230f, 240f)) {
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawText("Max Set (Pounds * Reps)",
                        0f,
                        0.dp.toPx(),
                        android.graphics.Paint().apply {
                            textSize = 40f
                            color = outColor.toArgb()
                        })
                }
            }
            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText("Date",
                    775f,
                    205.dp.toPx(),
                    android.graphics.Paint().apply {
                        textSize = 40f
                        color = outColor.toArgb()
                    })
            }

            drawRect(
                color = outColor, topLeft = Offset.Zero, size = Size(
                    width = size.width, height = size.height
                ), style = Stroke()
            )

            val spacePerHour = (size.width - spacing) / yPoints.size

            val normX = mutableListOf<Float>()
            val normY = mutableListOf<Float>()

            val strokePath = Path().apply {

                for (i in yPoints.indices) {

                    val currentX = spacing + i * spacePerHour

                    if (i == 0) {

                        moveTo(currentX, yPoints[i])
                    } else {

                        val previousX = spacing + (i - 1) * spacePerHour

                        val conX1 = (previousX + currentX) / 2f
                        val conX2 = (previousX + currentX) / 2f

                        val conY1 = yPoints[i - 1]
                        val conY2 = yPoints[i]


                        cubicTo(
                            x1 = conX1,
                            y1 = conY1,
                            x2 = conX2,
                            y2 = conY2,
                            x3 = currentX,
                            y3 = yPoints[i]
                        )
                    }

                    // Circle dot points
                    normX.add(currentX)
                    normY.add(yPoints[i])

                }
            }


            drawPath(
                path = strokePath, color = graphColor, style = Stroke(
                    width = 3.dp.toPx(), cap = StrokeCap.Round
                )
            )

            (normX.indices).forEach {
                drawCircle(
                    outColor, radius = 3.dp.toPx(), center = Offset(normX[it], normY[it])
                )

            }
        }
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
    activeBarColor: Color, modifier: Modifier = Modifier,

    // set initial value to 1
    initialValue: Float = 1f, strokeWidth: Dp = 5.dp
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
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }
    Box(contentAlignment = Alignment.Center, modifier = modifier.onSizeChanged {
        size = it
    }) {
        // draw the timer
        Canvas(modifier = modifier) {
            // draw the inactive arc with following parameters
            /*drawArc(
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
            )*/
        }
        // add value of the timer
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        // create button to start or stop the timer
        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            }, modifier = Modifier.align(Alignment.BottomCenter),
            // change button color
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.secondary
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
