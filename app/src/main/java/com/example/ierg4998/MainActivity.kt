package com.example.ierg4998

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import com.example.ierg4998.tensorflowlite.data.TfLiteLandmakeClassifier
import com.example.ierg4998.tensorflowlite.domain.Classification
import com.example.ierg4998.tensorflowlite.presentation.LandmarkImageAnalyzer
import com.example.ierg4998.ui.theme.IERG4998Theme
import com.example.ierg4998.ui.theme.Nav

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Handle the permissions result
            if (permissions[Manifest.permission.CAMERA] == true &&
                permissions[Manifest.permission.READ_MEDIA_AUDIO] == true) {
                // Permissions granted, proceed with the camera functionality
            } else {
                // Permissions denied, show a message to the user
            }
        }

        // Check permissions and request if necessary
        checkPermissions()

    setContent {
            IERG4998Theme {
                var classification by remember {
                    mutableStateOf(emptyList<Classification>())
                }
                val analyzer = remember {
                    LandmarkImageAnalyzer(
                        classifier = TfLiteLandmakeClassifier(context = applicationContext)
                        , onResults = { classification = it}
                    )
                }
                val controller = remember {
                    LifecycleCameraController(applicationContext).apply {
                        setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                        //setEnabledUseCases(CameraController.IMAGE_CAPTURE)
                        setImageAnalysisAnalyzer(
                            ContextCompat.getMainExecutor(applicationContext), analyzer
                        )
                    }
                }

                val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
                //showCustomToast(LocalContext.current,"Testing")
                //var context = applicationContext
                Nav(classification, controller, sharedPreferences)


            }
        } //end of set Content
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermissions() {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val mediaAudioPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)

        if (cameraPermission != PackageManager.PERMISSION_GRANTED || mediaAudioPermission != PackageManager.PERMISSION_GRANTED) {
            // Request permissions if not granted
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_AUDIO
                )
            )
        }
    }








}









