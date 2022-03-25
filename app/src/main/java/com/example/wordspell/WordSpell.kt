package com.example.wordspell

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class WordSpell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_spell)
    }
    // Save the total word list
    val wordList = Global.getTestList()
    // The word currently being spelled
    var currentWord = "apple"
    // Number of the current word
    var currentWordNum = 0
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
        // Save the value from the button
        val buttonValue = button.text as String
        // Make sure the word isn't complete
        // and check if the selection is correct
        if (currentLetterNum < currentWord.length)
            if (buttonValue.compareTo(currentWord[currentLetterNum].toString()) == 0) {
                this.currentLetters += buttonValue
                button.text = ""
                currentLetterNum++
            }
        wordDisplay.text = currentLetters
    }

    private fun initializePage() {
        TODO("Not yet implemented")
    }

    // ADDITIONAL FUNCTIONS
    private fun setImage(word : String) {
        // This sets the image view in based on what word is being worked on
        // Get the image view
        val img = findViewById<ImageView>(R.id.imageDisplay)

        // Sets the image based on the supplied word
        img.setImageResource(resources.getIdentifier(
            "img_$word",
            "drawable",
            packageName))
    }

    private fun setAudio(word : String) {
        // This sets the listener for the audio button and tells it what to play
        // Get the audio button
        val btn = findViewById<ImageButton>(R.id.audioBtn)

        // Set the listener for the button
        btn.setOnClickListener {
            // Get the audio file based on the supplied word
            val mediaPlayer = MediaPlayer.create(
                this,
                resources.getIdentifier(
                    word,
                    "raw",
                    packageName
                ))
            // Start the audio file in the button
            mediaPlayer?.start()
        }
    }

    private fun setBtnVisibility(btn : Button) {
        // Toggles the buttons visibility
        if(btn.visibility == View.VISIBLE){
            btn.visibility = View.INVISIBLE
        }
        else {
            btn.visibility = View.VISIBLE
        }
    }

    private fun goToResults() {
        // Goes to the result screen
        val intent = Intent(this@WordSpell, Results::class.java)
        startActivity(intent)
        finish()
    }
}
