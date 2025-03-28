package com.bcit.assignmenttracker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AssignmentCard(
    title: String,
    course: String,
    courseLevel: Int,
    dueDate: String,
    onEditClick: () -> Unit,
    onCompleteClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth().
            padding(vertical = 10.dp, horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.elevatedCardColors(
                containerColor = Color(0xFFFEF9D9)
                )
    )
    {
        Column(modifier = Modifier.padding(8.dp)) {
            // title, edit icons
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                }
            }

            //course name
            Text(text = "Course: $course", style = MaterialTheme.typography.bodyMedium)
            // Due date
            Text(text = "Due: $dueDate", style = MaterialTheme.typography.bodySmall)

            // course level, complete button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row { repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < courseLevel) Color(0xFFFFD700) else Color.LightGray
                    )
                } }

                Button(
                    onClick = onCompleteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFfde2e4))
                ) {
                    Text("Complete")
                }


            }
        }
    }


}


