package com.bcit.assignmenttracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bcit.assignmenttracker.component.AssignmentCard
import com.bcit.assignmenttracker.data.Assignment
import androidx.compose.foundation.lazy.items
import com.bcit.assignmenttracker.data.AssignmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen(
    navController: NavController,
    assignments: MutableList<Assignment>,
    repo: AssignmentRepository,
    coroutineScope: CoroutineScope
) {
    val inCompleteAssignments = assignments.filter { !it.isCompleted }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(inCompleteAssignments) { assignment ->
                AssignmentCard(
                    title = assignment.title,
                    course = assignment.course,
                    courseLevel = assignment.courseLevel,
                    dueDate = assignment.deadline,
                    note = assignment.note,
                    onEditClick = {
                        navController.navigate("edit/${assignment.id}")
                    },
                    onCompleteClick = {
                        coroutineScope.launch {
                            val updated = assignment.copy(isCompleted = true)
                            repo.updateAssignment(updated)
                            assignments.clear()
                            assignments.addAll(repo.getAllAssignments())

                        }
                    }


                )
            }
        }
        Button(
            onClick = { navController.navigate("completed") },
            modifier = Modifier
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFdde5b6))
        ) {
            Text("Completed")
        }
    }
}
