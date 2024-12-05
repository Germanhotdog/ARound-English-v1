package com.example.ierg4998.ArFunction


import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.view.View
import android.widget.RadioButton
import com.example.ierg4998.MainActivity
import com.example.ierg4998.R

import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class ArFunction : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var Apple: ArModelNode
    private lateinit var Orange: ArModelNode
    private lateinit var pear: ArModelNode
    private lateinit var Trophy: ArModelNode

    private lateinit var questionText: TextView
    private lateinit var messageText: TextView
    private lateinit var answerOptions: RadioGroup
    private lateinit var answerButton: Button

    private lateinit var mediaPlayer: MediaPlayer //for background music


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_function)

        mediaPlayer = MediaPlayer.create(this, R.raw.jazz_bgm) // Add your audio file in res/raw folder
        mediaPlayer.setVolume(0.3f, 0.3f)
        mediaPlayer.isLooping = true // Optional: loop the background music
        mediaPlayer.start()

        sceneView = findViewById<ArSceneView?>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        val exit = findViewById<Button>(R.id.button)
        exit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        

        //add new model
        Apple = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Apple.glb",
                scaleToUnits = 0.4f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false
                val materialInstance = it.materialInstances[0]

            }

//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }


        Orange = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Orange.glb",
                scaleToUnits = 0.4f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false
                val materialInstance = it.materialInstances[1]
            }
//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }

        pear = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/pear.glb",
                scaleToUnits = 0.4f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false
                val materialInstance = it.materialInstances[2]
            }
//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }

        Trophy = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Trophy.glb",
                scaleToUnits = 0.6f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false
                val materialInstance = it.materialInstances[2]
            }
//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }
        sceneView.addChild(Apple)
        sceneView.addChild(Orange)
        sceneView.addChild(pear)
        sceneView.addChild(Trophy)

        questionText = findViewById(R.id.questionText)
        answerOptions = findViewById(R.id.answerOptions)
        answerButton = findViewById(R.id.submitAnswerButton)
        messageText = findViewById(R.id.Message)


        //first question
        Apple.isVisible = true
        Orange.isVisible = false
        pear.isVisible =  false
        Trophy.isVisible =  false

        questionText.visibility = View.VISIBLE
        answerOptions.visibility = View.VISIBLE
        answerButton.visibility = View.VISIBLE
        //messageText invisible
        messageText.visibility = View.INVISIBLE


        val option1: RadioButton = findViewById(R.id.option1)
        val option2: RadioButton = findViewById(R.id.option2)
        val option3: RadioButton = findViewById(R.id.option3)
        val option4: RadioButton = findViewById(R.id.option4)

        var QuestionNo: Int = 1

        answerButton.setOnClickListener {
            // Get the selected answer from the RadioGroup
            val selectedId = answerOptions.checkedRadioButtonId
            // Check if the selected answer is the correct one (assuming it's "square")
            //if (selectedId == R.id.option2) {
            if(QuestionNo == 1 && selectedId == R.id.option2){
                // Hide the question and answer options
                Apple.isVisible = false
                Orange.isVisible = true
                pear.isVisible =  false
                questionText.text = "橙的顏色是什麼?"

                option1.text = "Orange"
                option2.text = "Yellow"
                option3.text = "Red"
                option4.text = "Green"
                messageText.visibility = View.INVISIBLE

                QuestionNo = 2

            }
            else if (QuestionNo == 2 && selectedId == R.id.option1)
            {
                Apple.isVisible = false
                Orange.isVisible = false
                pear.isVisible =  true
                questionText.text = "這個水果的英文名字是什麼?"

                option1.text = "Pear"
                option2.text = "Coconut"
                option3.text = "Banana"
                option4.text = "Potato"
                messageText.visibility = View.INVISIBLE

                QuestionNo = 3
            }
            else if (QuestionNo == 3 && selectedId == R.id.option1)
            {
                Apple.isVisible = false
                Orange.isVisible = false
                pear.isVisible =  false
                Trophy.isVisible =  true

                val sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("trophy1", 1)
                editor.apply() // Commit changes

                questionText.text = "恭喜你通過了第一關, 你獲得了一個獎杯!\n你可以按Place Trophy 來放置你的trophy或按Exit退出"

                option1.visibility = View.INVISIBLE
                option2.visibility = View.INVISIBLE
                option3.visibility = View.INVISIBLE
                option4.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE

                if (answerButton.text == "Place trophy"){
                    placeModel(Trophy)
                }

                answerButton.text = "Place trophy"
            }
            else{ //Wrong Answer
                messageText.visibility = View.VISIBLE
            }

        }






    }

    private fun placeModel(modelNode: ArModelNode) {
        modelNode.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayer resources
        mediaPlayer.release()
    }



}