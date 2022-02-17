package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent



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
    // GLOBAL DATA
    private var wordData = mutableListOf<WordData>()    // Contains all word data
    var wordList = mutableListOf<String>()              // List of all the words
        private set             // Should only be set at startup

    // The working list is an array of words that are created based on the number of words selected
    var workingList = mutableListOf<String>()   // Smaller array of words for usage
    var numberOfWords = 0       // The amount of words the user wants to go through

    fun setCurrentDataList(data : WordData) {
        // Adds the word data to the list
        wordData.add(data)
    }

    fun generateWordList(){
        // Makes a list using the word names in the data
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

    fun setWorkingList(word : String) {
        // Adds the word to the working list
        workingList.add(word)
    }

    fun resetWorkingList(){
        // Resets the working list to a blank array
        workingList.clear()
    }

    fun getNumOfWords() : Int {
        // This returns the number of words the user wants to be tested on
        return numberOfWords
    }

    fun setNumOfWords(amount : Int) {
        // This sets the number of words the user wants to be tested on
        numberOfWords = amount
    }

    fun resetNumOfWords() {
        // Sets the number of words counter to 0
        setNumOfWords(0)
    }

    // TESTING ONLY
    fun cyclePos() {
        if (numberOfWords < 3) {
            setNumOfWords(numberOfWords + 1)
        }
        else {
            resetNumOfWords()
        }
    }
}