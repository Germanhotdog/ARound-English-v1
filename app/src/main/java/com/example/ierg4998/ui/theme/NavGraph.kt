package com.example.ierg4998.ui.theme

import android.content.SharedPreferences
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ierg4998.ArPage

import com.example.ierg4998.Page.MainPage
import com.example.ierg4998.Page.NotePage
import com.example.ierg4998.Page.OCRCamera
import com.example.ierg4998.tensorflowlite.domain.Classification

@Composable
fun Nav(
    classification: List<Classification>,
    controller: LifecycleCameraController,
    sharedPreferences: SharedPreferences,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main") {
        composable(route = "Main") {
            MainPage(navController)
        }

        composable(route = "AR") {
            ArPage(navController)
        }

        composable(route = "NotePage") {
            NotePage(navController,sharedPreferences)
        }

        composable(route = "OCRCamera") {
            OCRCamera(classification, controller, navController, sharedPreferences)
        }


    }
}