package com.example.ierg4998.Page

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ierg4998.Dialog.CreateNoteDialog
import com.example.ierg4998.Dialog.EditNoteDialog


@Composable
fun NotePage(navController: NavHostController, sharedPreferences: SharedPreferences) {
    var context = LocalContext.current
    var notes by remember { mutableStateOf(listOf<String>()) }
    var showCreateNoteDialog by remember { mutableStateOf(false) }
    var showEditNoteDialog by remember { mutableStateOf(false) }
    var noteToEdit by remember { mutableStateOf("") }
    var noteIndexToEdit by remember { mutableStateOf(-1) }


    LaunchedEffect(Unit) {
        val noteSet = sharedPreferences.getStringSet("notes", emptySet())
        notes = noteSet?.toList() ?: emptyList()
    }

    if (showCreateNoteDialog) {
        CreateNoteDialog(
            onDismiss = { showCreateNoteDialog = false },
            onNoteAdded = { newNote ->
                notes = notes + newNote
                saveNotesToPreferences(sharedPreferences, notes) // Save notes
                Toast.makeText(context, "Added: $newNote", Toast.LENGTH_SHORT).show()
                showCreateNoteDialog = false
            }
        )
    }

    if (showEditNoteDialog) {
        EditNoteDialog(
            note = noteToEdit,
            onDismiss = { showEditNoteDialog = false },
            onNoteEdited = { editedNote ->
                notes = notes.toMutableList().apply {
                    this[noteIndexToEdit] = editedNote
                }
                saveNotesToPreferences(sharedPreferences, notes)
                Toast.makeText(context, "Edited: $editedNote", Toast.LENGTH_SHORT).show()
                showEditNoteDialog = false
            }
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Text(text = "Notes", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(20.dp))

        // Display the list of notes
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(notes) { note ->
                val index = notes.indexOf(note) // Get index for deletion
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Note text inside a Card
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp).clickable {
                                noteToEdit = note
                                noteIndexToEdit = index
                                showEditNoteDialog = true
                            },
                        shape = RoundedCornerShape(8.dp),

                    ) {
                        Text(
                            text = note,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }//end of card
                    Button(onClick = {
                        // Show a Toast message
                        Toast.makeText(context, "Deleted: $note", Toast.LENGTH_SHORT).show()

                        // Delete the note
                        notes = notes.filterIndexed { i, _ -> i != index }
                        saveNotesToPreferences(sharedPreferences, notes) // Save updated notes
                    }) {
                        Text(text = "Delete", fontSize = 16.sp)
                    }
                }// end of row
            }


        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { showCreateNoteDialog = true }) {
            Text(text = "Create Note", fontSize = 40.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("Main") {
                popUpTo("Main") {
                    inclusive = true
                }
            }
        }) {
            Text(text = "Go to Main", fontSize = 40.sp)
        }

    }// end of column

}

fun saveNotesToPreferences(sharedPreferences: SharedPreferences, notes: List<String>) {
    val editor = sharedPreferences.edit()
    editor.putStringSet("notes", notes.toSet())
    editor.apply()

}




