package com.bcit.assignmenttracker

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.bcit.assignmenttracker.data.local.Assignment
import com.bcit.assignmenttracker.data.AssignmentRepository
import com.bcit.assignmenttracker.data.local.MyDatabase
import com.bcit.assignmenttracker.data.remote.MotivationRemoteSource
import com.bcit.assignmenttracker.data.remote.client
import kotlinx.coroutines.launch


@Composable
fun MainContent() {
    val navController = rememberNavController()
    val content = LocalContext.current
    val assignments = remember { mutableStateListOf<Assignment>() }
    val coroutineScope = rememberCoroutineScope()
    val db = MyDatabase.getDatabase(content)
    val repo = AssignmentRepository(
        db.assignmentDao(),
        motivationSource = MotivationRemoteSource(client))

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            assignments.clear()
            assignments.addAll(repo.getActiveAssignments())
        }
    }


    Scaffold(
        containerColor = Color(0xFFF1F0E8),
        topBar = {
            MyTopBar(navController, title = getTitle(navController))
        },
        bottomBar = {
            MyBottomNav(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen(navController, assignments, repo, coroutineScope)
            }
            composable("upcoming") {
                UpcomingScreen(repo)
            }

            composable("motivation") {
                MotivationScreen(repo)
            }

            composable("Completed") {
                CompletedScreen(repo)
            }
            composable("add") {
                AddScreen(
                    navController = navController,
                    onSave = { assignment ->
                        coroutineScope.launch {
                            if (assignment.id == 0) {
                                repo.addAssignment(assignment)
                            } else {
                                repo.updateAssignment(assignment)

                            }
                            assignments.clear()
                            assignments.addAll(repo.getActiveAssignments())
                            navController.navigate("home")
                        }
                    },
                    onDelete = {},
                    existing = null
                )
            }

            composable("edit/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                val assignment = assignments.find { it.id == id }

                assignment?.let {
                    AddScreen(
                        navController = navController,
                        existing = it,
                        onSave = { updated ->
                            coroutineScope.launch {
                                repo.updateAssignment(updated)
                                assignments.clear()
                                assignments.addAll(repo.getAllAssignments())
                                navController.navigate("home")
                            }
                        },
                        onDelete = { deleted ->
                            coroutineScope.launch {
                                repo.deleteAssignment(deleted)
                                assignments.clear()
                                assignments.addAll(repo.getAllAssignments())
                                navController.navigate("home")
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun getTitle(navController: NavController): String {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route
    return when (route) {
        "home" -> "Assignments"
        "upcoming" -> "Upcoming"
        "courseBy" -> "Course By"
        "add" -> "Add"
        "motivation" -> "Quotes"
        else -> "Assignments"
    }
}