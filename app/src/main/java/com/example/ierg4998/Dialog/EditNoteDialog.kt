package com.example.ierg4998.Dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun EditNoteDialog(note: String, onDismiss: () -> Unit, onNoteEdited: (String) -> Unit) {
    var editedNote by remember { mutableStateOf(note) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Note") },
        text = {
            TextField(
                value = editedNote,
                onValueChange = { editedNote = it },
                label = { Text("Note") }
            )
        },
        confirmButton = {
            Button(onClick = { onNoteEdited(editedNote) }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}