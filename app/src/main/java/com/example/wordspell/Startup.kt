package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

class Startup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val wordList = arrayOf("apple", "truck", "toy")
        val scoreList = arrayOf(2,1,0)
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateAndTime: String = simpleDateFormat.format(Date())

        for (i in wordList.indices) {
            val wordDataObject = WordData(wordList[i], scoreList[i], currentDateAndTime)
            Global.setCurrentDataList(wordDataObject)
        }

        Global.setName("Billy")
        val startUpText : TextView = findViewById<TextView>(R.id.startUpLabel)
        startUpText.text = Global.currentData[Global.currentInt].getWord()

        val button : Button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Global.cyclePos()
            val intent = Intent(this@Startup, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
    }
}

// Class for creating word
class WordData(newWord : String, newScore : Int, newDate : String ){
    // The object contains the following pieces of data
    private var word = "default"        // Word value
    private var score = 0               // Score from 0-3
    private var scoreDate = "default"   // String that has the time and date

    init {
        // Set the word, score, and the recorded date of that score
        word = newWord
        score = newScore
        scoreDate = newDate
    }

    @JvmName("getWord1")
    fun getWord(): String {
        return word
    }

}

object Global {
    var name = ""
    var currentData = mutableListOf<WordData>()
    var currentInt = 0

    // Sets the Singleton name for testing
    @JvmName("setName1")
    fun setName(newName : String) {
        name = newName
    }

    fun setCurrentDataList(wordObject : WordData) {
        currentData.add(wordObject)
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