package com.bcit.assignmenttracker


import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bcit.assignmenttracker.component.AssignmentCard
import com.bcit.assignmenttracker.data.AssignmentRepository
import kotlinx.coroutines.launch
import com.bcit.assignmenttracker.data.Assignment
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private fun getDateAfter(days: Int): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().plusDays(days.toLong()).format(formatter)
}

@Composable
fun UpcomingScreen(navController: NavController, repo: AssignmentRepository) {
    val assignments = remember { mutableStateListOf<Assignment>()}
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            val dates = listOf(
                getDateAfter(0),
                getDateAfter(1),
                getDateAfter(2)
            )
            assignments.clear()
            assignments.addAll(repo.getUpcomingAssignments(dates))
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        LazyColumn {
            items(assignments) { assignment ->
                AssignmentCard(
                    title = assignment.title,
                    course = assignment.course,
                    courseLevel = assignment.courseLevel,
                    dueDate = assignment.deadline,
                    note = assignment.note,
                    onEditClick = {},
                    onCompleteClick = {

                    },
                    isCompleted = assignment.isCompleted
                )
            }
        }
    }


}