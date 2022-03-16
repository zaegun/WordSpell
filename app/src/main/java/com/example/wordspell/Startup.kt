package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.TextView


class Startup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Created the instance and the layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        // Setup DB
        val db = DBHelper(this, null)
        // Initialize DB and load data
        loadData(db)
        crossCheckList(db)


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
            val intent = Intent(this@Startup, HighScore::class.java)
            startActivity(intent)
            finish()
        }

        val testView = findViewById<TextView>(R.id.textView2)
        Global.setScore("apple", 0, this)
        testView.text = Global.getScore("apple").toString()
    }


    fun loadData(db : DBHelper) {
        // Clears the Global local data for refresh from db
        Global.clearWordData()
        Global.clearWordList()

        // Get the DB data
        val readList = db.readData()

        // Put the contents into the Global local data
        Global.setGlobalData(readList)
        Global.generateWordList()
    }

    fun crossCheckList(db : DBHelper) {
        // Get the hard coded word list
        val allWordsList = WordList.allWordsList

        // Check if it exists in the db loaded data
        for(refWord in allWordsList) {
            val check = Global.wordList.find { word -> word == refWord}
            // If the word doesn't exist, add it to the database with parameters
            if(check == null) {
                // Set the parameters
                val word = refWord
                val score = 0
                val date = "2022.02.16 at 2:27:00"

                // Create the WordData object
                val newWord = WordData(word, score, date)

                // Put into the db
                db.insertData(newWord)

                // Add to Global
                Global.setCurrentDataList(newWord)
                Global.addOneToWordList(newWord.word)
            }
        }

    }

    fun goToMainMenu() {
        // This function will redirect the page to the main menu
        val intent = Intent(this@Startup, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}

