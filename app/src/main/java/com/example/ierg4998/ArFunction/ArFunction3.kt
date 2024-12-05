package com.example.ierg4998.ArFunction

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import android.view.View
import android.widget.EditText
import com.example.ierg4998.MainActivity
import com.example.ierg4998.R

import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position


class ArFunction3 : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var Lady: ArModelNode
    private lateinit var Statue: ArModelNode
    private lateinit var Mountain: ArModelNode
    private lateinit var Sitting: ArModelNode
    private lateinit var Trophy: ArModelNode


    private lateinit var questionText: TextView
    private lateinit var messageText: TextView
    private lateinit var answerButton: Button
    private lateinit var placeButton: Button
    private lateinit var inputText: EditText

    private lateinit var mediaPlayer: MediaPlayer //for background music



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_function3)

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
        Lady = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Lady.glb",
                scaleToUnits = 0.7f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false
            }
        }

        Statue = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Statue.glb",
                scaleToUnits = 0.7f,
                centerOrigin = Position()

            )
            {
                sceneView.planeRenderer.isVisible = false
            }
        }

        Mountain = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Mountain.glb",
                scaleToUnits = 1f,
                centerOrigin = Position(z=-0.2f)

            )
            {
                sceneView.planeRenderer.isVisible = false
            }
        }

        Sitting = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/Sitting.glb",
                scaleToUnits = 0.7f,
                centerOrigin = Position(-0.5f)

            )
            {
                sceneView.planeRenderer.isVisible = false
            }
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


        }

        sceneView.addChild(Lady)
        sceneView.addChild(Statue)
        sceneView.addChild(Mountain)
        sceneView.addChild(Sitting)
        sceneView.addChild(Trophy)

        questionText = findViewById(R.id.questionText)
        messageText = findViewById(R.id.Message)
        answerButton = findViewById(R.id.submitAnswerButton)
        placeButton = findViewById(R.id.placeButton)
        //Input Text
        inputText = findViewById(R.id.inputText)


        //first question
        Lady.isVisible =true
        Statue.isVisible =false
        Sitting.isVisible = false
        Mountain.isVisible = false
        Trophy.isVisible =  false



        questionText.visibility = View.VISIBLE
        answerButton.visibility = View.VISIBLE
        //messageText invisible
        messageText.visibility = View.INVISIBLE
        //placeButton invisible
        placeButton.visibility = View.INVISIBLE
        //Input Text invisible
        inputText.visibility = View.INVISIBLE

        var questionNo: Int = 0



        answerButton.setOnClickListener {
            val userAnswer = inputText.text.toString()

            if(questionNo == 0){
                questionText.visibility = View.VISIBLE
                inputText.visibility = View.VISIBLE
                Lady.isVisible = false
                Mountain.isVisible = true
                placeButton.visibility = View.INVISIBLE

                questionText.text = "描述一下你看到什麼?\n" +
                        "- A road to the top of a mountain\n" +
                        "- A railway to the top of a mountain\n" +
                        "- A mountain with a waterfall\n" +
                        "- A lake on the top of a hill"


                answerButton.text = "Submit Answer"

                questionNo = 1;


            }
            else if(questionNo == 1 && userAnswer.equals("A mountain with a waterfall", ignoreCase = true)){

                questionText.visibility = View.VISIBLE
                inputText.visibility = View.VISIBLE
                Mountain.isVisible = false
                Statue.isVisible = true
                placeButton.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE

                questionText.text = "很好很好...那描述一下你現在看到什麼?\n" +
                        "- A dancing wooden man\n" +
                        "- A running iron man\n" +
                        "- A crying wooden man\n" +
                        "- A dancing iron man"

                answerButton.text = "Submit Answer"

                questionNo = 2;



            }
            else if(questionNo == 2 && userAnswer.equals("A dancing wooden man", ignoreCase = true)){

                questionText.visibility = View.VISIBLE
                inputText.visibility = View.VISIBLE
                Statue.isVisible = false
                Sitting.isVisible = true
                placeButton.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE

                questionText.text = "很好很好...那描述一下你現在看到什麼?\n" +
                        "- A man crying\n" +
                        "- A man crawling\n" +
                        "- A man kneeling\n" +
                        "- A man sitting"

                answerButton.text = "Submit Answer"

                questionNo = 3;

            }
            else if(questionNo == 3 && userAnswer.equals("A man sitting", ignoreCase = true)){
                inputText.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE
                placeButton.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE

                Sitting.isVisible = false
                Lady.isVisible =true
                answerButton.text = "Next"

                questionText.text = "Ummm.. 都答得不錯...\n" +
                        "今天的測驗先到這裏吧，送你一個Trophy\n" +
                        "(按下Next)"

                questionNo = 4
            }
            else if (questionNo == 4){
                Lady.isVisible =false
                Trophy.isVisible =  true
                placeButton.visibility = View.INVISIBLE
                messageText.visibility = View.INVISIBLE

                val sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("trophy3", 1)
                editor.apply() // Commit changes

                questionText.text = "恭喜你通過了第三關, 你獲得了一個獎杯!\n你可以按Place Trophy 來放置你的trophy或按Exit退出"

                inputText.visibility = View.INVISIBLE

                messageText.visibility = View.INVISIBLE
                placeButton.visibility = View.INVISIBLE
                sceneView.planeRenderer.isVisible = true

                if (answerButton.text == "Place trophy"){
                    placeModel(Trophy)
                }

                sceneView.planeRenderer.isVisible = true
                answerButton.text = "Place trophy"
            }
            else{ //Wrong Answer
                messageText.visibility = View.VISIBLE
                messageText.text="這個答案不太對，再試一次！加油！"
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