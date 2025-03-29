package com.bcit.assignmenttracker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bcit.assignmenttracker.component.AssignmentCard
import com.bcit.assignmenttracker.data.Assignment
import com.bcit.assignmenttracker.data.AssignmentRepository
import kotlinx.coroutines.launch
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

@Composable
fun CompletedScreen (
    navController: NavController,
    repo: AssignmentRepository
) {
    val completedAssignments = remember { mutableStateListOf<Assignment>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            completedAssignments.clear()
            completedAssignments.addAll(repo.getCompletedAssignments())
        }
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(completedAssignments) { assignment ->
            AssignmentCard(
                title = assignment.title,
                course = assignment.course,
                courseLevel = assignment.courseLevel,
                dueDate = assignment.deadline,
                note = assignment.note,
                onEditClick = {},
                onCompleteClick = {},
                isCompleted = true
            )
        }
    }
}