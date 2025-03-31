package com.bcit.assignmenttracker

import androidx.compose.material3.*
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bcit.assignmenttracker.data.local.Assignment
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Icon



@Composable
fun AddScreen(navController: NavController,
              onSave: (Assignment) -> Unit,
              existing: Assignment? = null) {
    var title by remember { mutableStateOf(existing?.title ?: "") }
    var course by remember { mutableStateOf(existing?.course ?: "") }
    var dueDate by remember { mutableStateOf(existing?.deadline ?: "") }
    var courseLevel by remember { mutableIntStateOf(existing?.courseLevel ?: 3) }
    var note by remember { mutableStateOf(existing?.note ?: "") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            R.style.MyPastelDatePicker,
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                dueDate = formatter.format(selectedDate.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

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

                OutlinedTextField(
                    value = dueDate,
                    onValueChange = {},
                    label = { Text("Due Date") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Pick Date")
                        }
                    }
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

                TextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Note") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onSave(
                            Assignment(
                                id = existing?.id ?:0,
                                title = title,
                                course = course,
                                deadline = dueDate,
                                courseLevel = courseLevel,
                                note = note)
                        )
                        navController.navigate("home")
                    },


                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFffc2d1),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save")

                }
                Button(
                    onClick = {
                        onSave(
                            Assignment(
                                id = existing?.id ?:0,
                                title = title,
                                course = course,
                                deadline = dueDate,
                                courseLevel = courseLevel,
                                note = note)
                        )
                        navController.navigate("home")
                    },


                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFffc2d1),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Delete")

                }
            }
        }
    }
}


