package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button



class Startup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val wordList = arrayOf("apple", "truck", "toy")
        val scoreList = arrayOf(2,4,1)
        val scoreDateTime  = "2022.02.16 at 2:27:00"

        for (i in wordList.indices) {
            val wordDataObject = WordData(wordList[i], scoreList[i], scoreDateTime)
            Global.setCurrentDataList(wordDataObject)
        }

        val startUpText = findViewById<TextView>(R.id.startUpLabel)
        //val scoreText = Global.wordData[Global.currentInt].score
        //val wordText = Global.wordData[Global.currentInt].word
        Global.generateWordList()
        startUpText.text = Global.getScore(Global.wordList[Global.currentInt]).toString()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            //Global.cyclePos()
            Global.setScore("apple", 3)
            startUpText.text = Global.getScore(Global.wordList[Global.currentInt]).toString()
            //val intent = Intent(this@Startup, MainMenu::class.java)
            //startActivity(intent)
            //finish()
        }
    }
}

// Class for creating a word
class WordData(newWord : String, newScore : Int, newDate : String ){
    // The object contains the following pieces of data
    var word = "default"        // Word value
        private set             // Word can only be set on creation
    var score = 0               // Score from 0-3
        set(value) {      // Sets the score if between 0-3
            field = if(value in 0..3) {value} else {0}
        }
    var scoreDate = "default"   // String that has the time and date

    init {
        // Set the word, score, and the recorded date of that score
        word = newWord
        score = newScore
        scoreDate = newDate
    }
}

object Global {
    var wordData = mutableListOf<WordData>()
    var wordList = mutableListOf<String>()
    var currentInt = 0

    fun setCurrentDataList(wordObject : WordData) {
        wordData.add(wordObject)
    }

    fun generateWordList(){
        for(wordData in wordData) {
            wordList.add(wordData.word)
        }
    }

    fun getScore(refWord : String) : Int {
        // This finds the data based on the reference word
        val foundWord = wordData.find {it.word == refWord}

        //  Return the score
        return foundWord?.score!!
    }

    fun setScore(refWord : String, newScore : Int) {
        // This updates the score and the date the score was changed
        // This finds the data based on the reference word
        val foundWord = wordData.find {it.word == refWord}
        // First check if the the score needs to be updated
        if (foundWord?.score != newScore) {
            // If it needs updating, update with the new score and date
            foundWord?.score = newScore
        }


    }

    fun cyclePos() {
        if (currentInt < 3) {
            currentInt += 1
        }
        else {
            currentInt = 0
        }
    }
}