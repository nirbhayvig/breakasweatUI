package com.example.breakasweatui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Modifier
import com.example.breakasweatui.ui.theme.BreakaSweatUITheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import kotlinx.coroutines.launch
import com.example.breakasweatui.*


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakaSweatUITheme {
                Main(modifier = Modifier.fillMaxSize())
            }
        }
        val db = WorkoutDatabase.getInstance(applicationContext)
        val workoutDao = db.workoutDao()
        if (workoutDao.getAll().isEmpty()) {
            workoutDao.insertAll(Workout(name = "INIT", sets = 3, reps = 10, weight = 15))
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = "Home"
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
            NavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    Drawer(onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route)
                    })
                }) {
                // Handles all navigation between pages.
                NavHost(
                    navController = navController, startDestination = DrawerScreens.Home.route
                ) {
                    composable("Home") {
                        HomeScreen(navBegin = { navController.navigate("BeginningWorkout") },
                            navHistory = { navController.navigate("WorkoutHistory") },
                            navModify = { navController.navigate("ModifyRoutine") },
                            openDrawer = { openDrawer() })
                    }
                    composable("BeginningWorkout") {
                        BeginningWorkout(navDuring = { navController.navigate("DuringWorkout1") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("DuringWorkout1") {
                        DuringWorkout1(navNext = { navController.navigate("Resting1") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("Resting1") {
                        Resting1(navNext = { navController.navigate("DuringWorkout2") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("DuringWorkout2") {
                        DuringWorkout2(navNext = { navController.navigate("Resting2") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("Resting2") {
                        Resting2(navNext = { navController.navigate("DuringWorkout3") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("DuringWorkout3") {
                        DuringWorkout3(navNext = { navController.navigate("Resting3") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("Resting3") {
                        Resting3(navNext = { navController.navigate("CompletedWorkout") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("WorkoutHistory") {
                        WorkoutHistory(navHome = { navController.navigate("Home") },
                            navBack = { navController.popBackStack() },
                            openDrawer = { openDrawer() })
                    }
                    composable("ModifyRoutine") {
                        ModifyRoutine(navHome = { navController.navigate("Home") },
                            openDrawer = { openDrawer() },
                            addNew = { navController.navigate("AddNew") })
                    }
                    composable("CompletedWorkout") {
                        Completed(navHome = { navController.navigate("Home") },
                            navHistory = { navController.navigate("WorkoutHistory") },
                            openDrawer = { openDrawer() })
                    }
                    composable("Settings") {
                        Settings(navHome = { navController.navigate("Home") },
                            openDrawer = { openDrawer() },
                            navBack = { navController.popBackStack() })
                    }
                    composable("AddNew") {
                        AddNewWorkout(navBack = { navController.navigate("ModifyRoutine") },
                            addNew = { navController.navigate("ModifyRoutine") })
                    }
                }
            }
        }
    }
}