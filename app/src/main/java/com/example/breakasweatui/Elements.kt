package com.example.breakasweatui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput


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


//point representation
data class Point(val x: Float, val y: Float)

@Composable
fun SuperSimpleLineChart(
    modifier: Modifier = Modifier,
    yPoints: List<Float> = listOf(
        445f, 400f, 320f, 330f, 270f, 150f
    ),
    graphColor: Color = Color.Green,
    xOnClick: () -> Unit,
) {
    val spacing = 100f
    Box(
        modifier = Modifier
            .padding(all = 20.dp)
    ){
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(190.dp)

        ) {
            rotate(degrees = -90f, Offset(230f, 240f)) {
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawText(
                        "Max Set (Pounds * Reps)",
                        0f, 0.dp.toPx(),
                        android.graphics.Paint().apply {
                            textSize = 40f
                        }
                    )
                }
            }
            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(
                    "Date",
                    440f, 205.dp.toPx(),
                    android.graphics.Paint().apply {
                        textSize = 40f
                    }
                )
            }

            drawRect(
                color = Color.Black,
                topLeft = Offset.Zero,
                size = Size(
                    width = size.width,
                    height = size.height
                ),
                style = Stroke()
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
                path = strokePath,
                color = graphColor,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )


            (normX.indices).forEach {
                drawCircle(
                    Color.Black,
                    radius = 3.dp.toPx(),
                    center = Offset(normX[it], normY[it])
                )
            }
        }
    }
}










