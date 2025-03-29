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
import com.bcit.assignmenttracker.data.Assignment
import com.bcit.assignmenttracker.data.AssignmentRepository
import com.bcit.assignmenttracker.data.MyDatabase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainContent() {
    val navController = rememberNavController()
    val content = LocalContext.current
    val assignments = remember { mutableStateListOf<Assignment>() }
    val coroutineScope = rememberCoroutineScope()
    val db = MyDatabase.getDatabase(content)
    val repo = AssignmentRepository(db.assignmentDao())

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
                UpcomingScreen(navController)
            }
            composable("courseBy") {
                CourseByScreen(navController)
            }

            composable("Completed") {
                CompletedScreen(navController, repo)
            }
            composable("add") {
                AddScreen(navController) { title, course, dueDate, courseLevel, note ->
                    coroutineScope.launch {
                        val newAssignment = Assignment(
                            title = title,
                            course = course,
                            courseLevel = courseLevel,
                            deadline = dueDate,
                            note = note
                        )
                        repo.addAssignment(newAssignment)
                        assignments.clear()
                        assignments.addAll(repo.getActiveAssignments())
                    }

                    navController.navigate("home")
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
        else -> "Assignments"
    }
}