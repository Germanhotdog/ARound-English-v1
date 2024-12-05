package com.example.ierg4998.tensorflowlite.domain

import android.graphics.Bitmap
import io.github.sceneview.math.Rotation

interface LandmarkClassifier {

    fun classify(bitmap: Bitmap, rotation: Int):List<Classification>
}