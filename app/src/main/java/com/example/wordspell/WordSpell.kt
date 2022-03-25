package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class WordSpell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_spell)
        progressList()
    }
    // Save the total word list
    val wordList = Global.getTestList()
    // Number of the current word
    var currentWordNum = 0
    // The word currently being spelled
    var currentWord = "" //wordList[currentWordNum]
    // Letters of the current word
    val wordLetters = mutableListOf<String>()
    // Letters to be chosen from
    val letterOptions = mutableListOf<String>()
    // Letters currently selected
    var currentLetters = ""
    // Track the current letter in the word
    var currentLetterNum = 0
    // To keep track of what word we are on and how many words we have
    val numWords = wordList.size
    // Check if the current word is spelled correctly
    var wordSpelled = false
    // Keep track of score for the current word.
    // We want it to start at max.
    var score = 3


    // Grab text box elements
//        var textBox = findViewById<TextView>(R.id.test_box)
//        // textBox.text = wordList[0]
//        val apple = wordList[0]
//        Global.setScore(apple, 2)
//        var score = Global.getScore(apple)
//        var track = Global.getTrackNum()

    // Iterate through the word list as we finish each word
    private fun progressList() {
        if (currentWordNum < numWords) {
            // Save the current word from the list
            currentWord = wordList[currentWordNum]
            // Clear the letter list and the selected letters
            wordLetters.clear()
            currentLetters = ""
            // Reset letter count
            currentLetterNum = 0
            // Fill letter list with letters in current
            //currentWord.forEach { j -> wordLetters.add(j.toString()) }
            for (letter in currentWord)
                wordLetters.add(letter.toString())
            initializePage()
            // Progress to next word
            currentWordNum++
        }
    }

    fun useButton1(view: View) {
        val button = findViewById<Button>(R.id.letter1)
        spellWord(button)
    }

    fun useButton2(view: View) {
        val button = findViewById<Button>(R.id.letter2)
        spellWord(button)
    }

    fun useButton3(view: View) {
        val button = findViewById<Button>(R.id.letter3)
        spellWord(button)
    }

    fun useButton4(view: View) {
        val button = findViewById<Button>(R.id.letter4)
        spellWord(button)
    }

    fun useButton5(view: View) {
        val button = findViewById<Button>(R.id.letter5)
        spellWord(button)
    }

    fun useButton6(view: View) {
        val button = findViewById<Button>(R.id.letter6)
        spellWord(button)
    }

    fun useButton7(view: View) {
        val button = findViewById<Button>(R.id.letter7)
        spellWord(button)
    }

    fun useButton8(view: View) {
        val button = findViewById<Button>(R.id.letter8)
        spellWord(button)
    }

    // Take inputs from the buttons to spell out the word
    fun spellWord(button: Button) {
        // Here we hold the display letters
        val wordDisplay = findViewById<TextView>(R.id.answerDisplay)
        // ...and here we display the score
        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)
        // Save the value from the button
        val buttonValue = button.text as String
        // Make sure the word isn't complete
        // and check if the selection is correct
        if (currentLetterNum < currentWord.length) {
            if (buttonValue.compareTo(currentWord[currentLetterNum].toString()) == 0) {
                this.currentLetters += buttonValue
                button.text = ""
                currentLetterNum++
            }
            else if (score > 0 && buttonValue.compareTo("") != 0) {
                score--
                scoreDisplay.text = score.toString()
            }

            wordDisplay.text = currentLetters
        }

        // If it is spelled correctly, we want to go to the next word,
        // but only after the user gets to look at it.
        // We'll have them push the button again when they're ready.
        if (wordSpelled){
            // We'll progress the list now
            progressList()
            // ...and clear the letters we already have
            wordDisplay.text = ""
        }

        // We also want to see if the word is spelled correctly
        if (currentLetters.compareTo(currentWord) == 0) {
            // Display progress-related text
            val completeText = findViewById<TextView>(R.id.completeText)
            completeText.text = "You did it!"
            val continuePrompt = findViewById<TextView>(R.id.continuePrompt)
            continuePrompt.text = "Press any button to continue"
            // Make sure we know the word is finished
            wordSpelled = true
        }
    }

    // Set up the default page
    private fun initializePage() {
        // Show progress-related text boxes as empty
        val completeText = findViewById<TextView>(R.id.completeText)
        completeText.text = ""
        val continuePrompt = findViewById<TextView>(R.id.continuePrompt)
        continuePrompt.text = ""
        // Make sure we don't think the word is spelled from the get-go
        wordSpelled = false
        // Set our score to max to start out each new word
        score = 3
        // ...and make sure it displays
        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)
        scoreDisplay.text = score.toString()
    }
}
