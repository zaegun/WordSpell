package com.example.wordspell

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import android.util.Log



class Startup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Created the instance and the layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        // Load the user's data
        loadData()


        // Gets the button and listens for the click to go to the next Activity
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            goToMainMenu()
        }

        // FOR TESTING
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this@Startup, WordSpell::class.java)
            startActivity(intent)
            finish()
        }

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this@Startup, Results::class.java)
            startActivity(intent)
            finish()
        }

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this@Startup, DBTest::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun loadData() {
        // Current implementation is hard coded.
        // This will be altered according to database.
        val wordList = arrayOf(
            "apple",
            "bath",
            "boat",
            "child",
            "clock",
            "dress",
            "eight",
            "eleven",
            "gray",
            "happy",
            "house",
            "lock",
            "lunch",
            "night",
            "pool",
            "school",
            "sea",
            "shape",
            "sky",
            "slide",
            "stone",
            "truck",
            "tune",
            "winter"
        )
        val scoreList = 0
        val scoreDateTime  = "2022.02.16 at 2:27:00"

        for (i in wordList.indices) {
            val wordDataObject = WordData(wordList[i], scoreList, scoreDateTime)
            Global.setCurrentDataList(wordDataObject)
        }

        Global.generateWordList()
    }

    fun goToMainMenu() {
        // This function will redirect the page to the main menu
        val intent = Intent(this@Startup, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}

