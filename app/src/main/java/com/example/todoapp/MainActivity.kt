package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoScreen()
                }
            }
        }
    }
}

@Composable
fun ToDoScreen() {
    var taskText by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                placeholder = { Text("Enter task...") },
                modifier = Modifier.weight(1f)
            )

            // Add Button
            Button(
                onClick = {
                    if (taskText.isNotBlank()) {
                        tasks = tasks + taskText  // Add new task
                        taskText = ""  // Clear input field
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }

            // Clear All Button (Deletes all tasks)
            Button(
                onClick = {
                    tasks = listOf()  // Clear the task list
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Clear All")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display list of tasks
        tasks.forEach { task ->
            Text(
                text = "• $task",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
    ToDoAppTheme {
        ToDoScreen()
    }
}
