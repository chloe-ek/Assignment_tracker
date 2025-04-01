package com.bcit.assignmenttracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bcit.assignmenttracker.data.AssignmentRepository
import kotlinx.coroutines.launch

@Composable
fun MotivationScreen(repo: AssignmentRepository) {
    val coroutineScope = rememberCoroutineScope()
    val quote = remember { mutableStateOf("Loading...") }
    val author = remember { mutableStateOf("") }

    // 최초 로딩 시 API 호출
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val motivation = repo.getMotivation()
                quote.value = motivation.q
                author.value = motivation.a
            } catch (e: Exception) {
                quote.value = "Failed to load quote."
                author.value = ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFFFF1F1))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = quote.value,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "- ${author.value}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val motivation = repo.getMotivation()
                        quote.value = motivation.q
                        author.value = motivation.a
                    } catch (e: Exception) {
                        quote.value = "Failed to load quote."
                        author.value = ""
                    }
                }
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFdde5b6))
        ) {
            Text("Get Motivated!")
        }
    }
}
