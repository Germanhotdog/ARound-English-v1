package com.example.ierg4998.Dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun CreateNoteDialog(onDismiss: () -> Unit, onNoteAdded: (String) -> Unit) {
    var noteText by remember { mutableStateOf("") }

    // Create a blurred background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(30.dp) // Adjust the blur radius
            .background(Color.Transparent)

    ) {
        // Your dialog
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Create Note") },
            text = {
                Column {
                    TextField(
                        value = noteText,
                        onValueChange = { noteText = it },
                        label = { Text("Enter note") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (noteText.isNotBlank()) {
                                    onNoteAdded(noteText)
                                    noteText = ""
                                }
                            }
                        )
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (noteText.isNotBlank()) {
                        onNoteAdded(noteText)
                        noteText = ""
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}