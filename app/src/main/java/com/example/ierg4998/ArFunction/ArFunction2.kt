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

class ArFunction2 : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var Worker: ArModelNode
    private lateinit var Man: ArModelNode
    private lateinit var House: ArModelNode
    private lateinit var Fountain: ArModelNode
    private lateinit var Pond: ArModelNode
    private lateinit var Trophy: ArModelNode



    private lateinit var questionText: TextView
    private lateinit var messageText: TextView
    private lateinit var answerOptions: RadioGroup
    private lateinit var answerButton: Button
    private lateinit var placeButton: Button

    private lateinit var mediaPlayer: MediaPlayer //for background music


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_function2)

        // Initialize MediaPlayer and start playing background music
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
        Worker = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Worker.glb",
                scaleToUnits = 0.7f,
                centerOrigin = Position(-1f)

            )
            {
                sceneView.planeRenderer.isVisible = false


            }

//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }

        Man = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Man.glb",
                scaleToUnits = 0.7f,
                centerOrigin = Position(x  =1f),

            )
            {
                sceneView.planeRenderer.isVisible = false


            }

//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }

        House = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/House.glb",
                scaleToUnits = 0.7f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false


            }

//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }


        Fountain = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Fountain.glb",
                scaleToUnits = 0.5f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false

            }
//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }

        Pond = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Pond.glb",
                scaleToUnits = 0.5f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false

            }
//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }

        Trophy = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Trophy.glb",
                scaleToUnits = 0.3f,
                centerOrigin = Position(-0.5f),

            )
            {
                sceneView.planeRenderer.isVisible = false

            }
//            onAnchorChanged = {
//                placeButton.isGone = it != null
//            }

        }


        sceneView.addChild(Worker)
        sceneView.addChild(Man)
        sceneView.addChild(House)
        sceneView.addChild(Fountain)
        sceneView.addChild(Pond)
        sceneView.addChild(Trophy)

        questionText = findViewById(R.id.questionText)
        answerOptions = findViewById(R.id.answerOptions)
        answerButton = findViewById(R.id.submitAnswerButton)
        messageText = findViewById(R.id.Message)
        placeButton = findViewById(R.id.placeButton)


        //first question
        Worker.isVisible =true
        Man.isVisible =true
        House.isVisible = false
        Fountain.isVisible = false
        Pond.isVisible =  false
        Trophy.isVisible =  false


        val option1: RadioButton = findViewById(R.id.option1)
        val option2: RadioButton = findViewById(R.id.option2)
        val option3: RadioButton = findViewById(R.id.option3)
        val option4: RadioButton = findViewById(R.id.option4)

        questionText.visibility = View.VISIBLE
        answerOptions.visibility = View.INVISIBLE
        answerButton.visibility = View.VISIBLE
        //option Invisible
        option1.visibility = View.INVISIBLE
        option2.visibility = View.INVISIBLE
        option3.visibility = View.INVISIBLE
        option4.visibility = View.INVISIBLE
        //messageText invisible
        messageText.visibility = View.INVISIBLE
        //placeButton invisible
        placeButton.visibility = View.INVISIBLE

        var questionNo: Int = 0
        var page: Int = 1



        answerButton.setOnClickListener {
            // Get the selected answer from the RadioGroup
            val selectedId = answerOptions.checkedRadioButtonId
            // Check if the selected answer is the correct one (assuming it's "square")
            //if (selectedId == R.id.option2) {
            if(page == 1){
                questionText.visibility = View.VISIBLE
                answerOptions.visibility = View.VISIBLE
                Worker.isVisible = false
                Man.isVisible = false
                House.isVisible = true
                placeButton.visibility = View.INVISIBLE

                questionText.text = "屋子的英文名字是什麼?"
                option1.visibility = View.VISIBLE
                option2.visibility = View.VISIBLE
                option3.visibility = View.VISIBLE
                option4.visibility = View.VISIBLE

                messageText.visibility = View.VISIBLE
                messageText.text = "Mr Kim: Where do you want to build your _____?"
                answerButton.text = "Submit Answer"

                questionNo = 1;
                page = 2;

            }
            else if(questionNo == 1 && selectedId == R.id.option1 && page == 2){
            questionText.text = "把屋子(House)放在平面上面"
            answerOptions.visibility = View.INVISIBLE
            messageText.visibility = View.INVISIBLE
            placeButton.visibility = View.VISIBLE
            answerButton.visibility = View.INVISIBLE
            sceneView.planeRenderer.isVisible = true

            }
            else if(page == 3){
                questionText.visibility = View.VISIBLE
                answerOptions.visibility = View.VISIBLE
                Fountain.isVisible = true
                placeButton.visibility = View.INVISIBLE

                questionText.text = "噴水池的英文名字是什麼?"

                option1.text = "Ocean"
                option2.text = "Waterblow"
                option3.text = "Waterpool"
                option4.text = "Fountain"
                messageText.visibility = View.VISIBLE
                messageText.text = "Mr Kim: Do you want to build a gorgeous(華麗的) _____?"
                answerButton.text = "Submit Answer"
                questionNo = 2;
                page = 4;

            }
            else if(questionNo == 2 && selectedId == R.id.option4 && page == 4){
                questionText.text = "把噴水池(Fountain)放在平面上面"
                answerOptions.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE
                placeButton.visibility = View.VISIBLE
                answerButton.visibility = View.INVISIBLE
                sceneView.planeRenderer.isVisible = true



            }

            else if (page == 5)
            {
                questionText.visibility = View.VISIBLE
                answerOptions.visibility = View.VISIBLE
                Pond.isVisible = true
                questionText.text = "池塘的英文名字是什麼?"
                placeButton.visibility = View.INVISIBLE

                option1.text = "Lake"
                option2.text = "Waterpool"
                option3.text = "Pond"
                option4.text = "River"
                messageText.visibility = View.VISIBLE
                messageText.text = "Mr Kim: It is better to have a ______ for your goldfish(金魚) to swim!"
                answerButton.text = "Submit Answer"
                questionNo = 3
                page = 6
            }
            else if (questionNo == 3 && selectedId == R.id.option3 && page == 6){
                questionText.text = "把池塘(Pond)放在平面上面"
                answerOptions.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE
                placeButton.visibility = View.VISIBLE
                answerButton.visibility = View.INVISIBLE
                sceneView.planeRenderer.isVisible = true

            }
            else if (page == 7)
            {

                Trophy.isVisible =  true
                placeButton.visibility = View.INVISIBLE

                val sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("trophy2", 1)
                editor.apply() // Commit changes

                questionText.text = "恭喜你通過了第二關, 你獲得了一個獎杯!\n你可以按Place Trophy 來放置你的trophy或按Exit退出"

                option1.visibility = View.INVISIBLE
                option2.visibility = View.INVISIBLE
                option3.visibility = View.INVISIBLE
                option4.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE
                placeButton.visibility = View.INVISIBLE
                sceneView.planeRenderer.isVisible = true

                if (answerButton.text == "Place trophy"){
                    placeModel(Trophy)
                }

                answerButton.text = "Place trophy"

            }
            else{ //Wrong Answer
                messageText.visibility = View.VISIBLE
                messageText.text="這個答案不太對，再試一次！加油！"
            }

        }

        placeButton.setOnClickListener {
            when (questionNo) {
                1 -> {
                    placeModel(House)
                    page = 3
                    answerButton.text = "Continue"
                    answerButton.visibility = View.VISIBLE
                }
                2 -> {
                    placeModel(Fountain)
                    page = 5
                    answerButton.text = "Continue"
                    answerButton.visibility = View.VISIBLE
                }
                3 -> {
                    placeModel(Pond)
                    page = 7
                    answerButton.text = "Continue"
                    answerButton.visibility = View.VISIBLE
                }
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