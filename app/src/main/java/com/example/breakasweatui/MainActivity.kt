package com.example.breakasweatui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakaSweatUITheme {
                main(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
fun main(modifier: Modifier = Modifier) {
    
    var shouldShowWelcome by remember { mutableStateOf(true) }
    Surface(modifier) {
        if (shouldShowWelcome) {
            WelcomeScreen(onBeginClicked = {shouldShowWelcome = false})
        } else {
            BeginningWorkout()
        }
    }
}


@Composable
fun WelcomeScreen(
    onBeginClicked: () -> Unit,
    modifier: Modifier = Modifier) {
    // TODO: This state should be hoisted

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Break a Sweat!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onBeginClicked
        ) {
            Text("Begin Workout")
        }
    }
}

@Composable
fun BeginningWorkout(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Begin Workout:")
        Button(
            onClick = {}
        ) {
            Text("Start")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun WelcomePreview() {
    BreakaSweatUITheme {
        WelcomeScreen(onBeginClicked = {})
    }
}
