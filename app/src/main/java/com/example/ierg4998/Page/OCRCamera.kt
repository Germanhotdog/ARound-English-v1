package com.example.ierg4998.Page


import android.content.SharedPreferences
import android.widget.Toast
import androidx.camera.view.LifecycleCameraController

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.example.ierg4998.tensorflowlite.domain.Classification
import com.example.ierg4998.tensorflowlite.presentation.CameraPreview


@Composable
fun OCRCamera(
    classification: List<Classification>,
    controller: LifecycleCameraController,
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    ) {
    var notes by remember { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current
    val noteSet = sharedPreferences.getStringSet("notes", emptySet())
    notes = noteSet?.toList() ?: emptyList()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CameraPreview(
            controller,
            Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {

            if (classification.isEmpty()) {
                Text(
                    text = "Scan the Object",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(50.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                classification.forEach {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(50.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Button(
                        modifier = Modifier.padding(20.dp),
                        onClick = {
                            notes = notes + it.name
                            saveNotesToPreferences(sharedPreferences,notes) // Add the last classification name
                            Toast.makeText(context,"Added " + it.name, Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Add Note", fontSize = 20.sp)
                    }


                }//
            }
        }//end coloumn
        Spacer(modifier = Modifier.height(20.dp).align(Alignment.BottomCenter))

        // Capture Button
        Row(modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp)){
            Button(
                modifier = Modifier.padding(20.dp),
                onClick = {
                    navController.navigate("Main"){
                        popUpTo("Main"){
                            inclusive = true
                        }
                    }
                }
            ) {
                Text("Home",fontSize = 20.sp)
            } // end of button

            Button(
                modifier = Modifier.padding(20.dp),
                onClick = {
                    navController.navigate("NotePage")
                }
            ) {
                Text("Note",fontSize = 20.sp)
            }




        }// end row


    }// end box


} // end of function









