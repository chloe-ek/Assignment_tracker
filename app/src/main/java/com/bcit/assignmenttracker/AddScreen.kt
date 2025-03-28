package com.bcit.assignmenttracker

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults



@Composable
fun AddScreen(navController: NavController,
              onSave: (title: String, course: String, dueDate: String, courseLevel: Int) -> Unit ) {
    var title by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var courseLevel by remember { mutableIntStateOf(3) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color(0xFFFFF1F1)
            )
        ) {
            Column {

                Spacer(modifier = Modifier.height(23.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Assignment Title") },
                    modifier = Modifier.fillMaxWidth(),
                )


                Spacer(modifier = Modifier.height(23.dp))

                TextField(
                    value = course,
                    onValueChange = { course = it },
                    label = { Text("Course Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(23.dp))

                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Course Level:")
                Row {
                    repeat(5) { index ->
                        IconButton(onClick = { courseLevel = index + 1 }) {
                            Icon (
                                imageVector = Icons.Default.Star,
                                contentDescription = "Set level ${index + 1}",
                                tint = if (index < courseLevel) Color(0xFFFFC107) else Color.LightGray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onSave(title, course, dueDate, courseLevel)
                        navController.navigate("home")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFffc2d1),   // 버튼 배경색 (핑크톤 예시)
                        contentColor = Color.White            // 버튼 안 텍스트 색
                    ),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save")


                }
            }
        }
    }
}


