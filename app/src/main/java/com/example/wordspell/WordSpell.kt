package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class WordSpell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_spell)
    }
    // Save the total word list
    val wordList = Global.getTestList()
    // The word currently being spelled
    var currentWord = ""
    // Number of the current word
    var currentWordNum = 0
    // Letters of the current word
    val wordLetters = mutableListOf<String>()
    // Letters to be chosen from
    val letterOptions = mutableListOf<String>()
    // Letters currently selected
    val currentLetters = mutableListOf<String>()
    // Track the current letter in the word
    var currentLetterNum = 0
    // To keep track of what word we are on and how many words we have
    val numWords = wordList.size

    // Grab text box elements
//        var textBox = findViewById<TextView>(R.id.test_box)
//        // textBox.text = wordList[0]
//        val apple = wordList[0]
//        Global.setScore(apple, 2)
//        var score = Global.getScore(apple)
//        var track = Global.getTrackNum()

    // Iterate through the word list as we finish each word
    private fun progressList() {
        if (currentWordNum < numWords)
            // Save the current word from the list
            currentWord = wordList[currentWordNum]
            // Clear the letter list and the selected letters
            wordLetters.clear()
            currentLetters.clear()
            // Reset letter count
            currentLetterNum = 0
            // Fill letter list with letters in current
            currentWord.forEach { j -> wordLetters.add(j.toString()) }
            initializePage()
            // Progress to next word
            currentWordNum++
        }

    // Take inputs from the buttons to spell out the word
    fun spellWord(view: View) {
        // Make sure the word isn't complete
        if (currentLetterNum < currentWord.length)
//            // Check it the selection is correct
//            if (buttonValue == wordLetters[currentLetterNum])
//                // Add the button value to the letter list
//                currentLetters.add(buttonValue)
            TODO("Figure out how to pass the button value")
    }

    private fun initializePage() {
        TODO("Not yet implemented")
    }
}
