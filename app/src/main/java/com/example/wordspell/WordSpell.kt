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
import kotlin.random.Random

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
    var currentWord = ""

    // Letters of the current word
    val wordLetters = mutableListOf<String>()

    // Letters to be chosen from
    var letterOptions = mutableListOf<String>()

    // Letter list
    val letters = arrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

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

            // Initialize the page
            initializePage(currentWord)

            // Progress to next word
            currentWordNum++
        }
    }

    // Set up the default page
    private fun initializePage(word: String) {

        // Access the word display
        val wordDisplay = findViewById<TextView>(R.id.answerDisplay)

        // Display blank spaces
        wordDisplay.text = formatSpellText(word, "")

        // Set image and audio for the given word
        setImage(word)
        setAudio(word)

        // Fill letter list with letters in current
        for (letter in currentWord)
            wordLetters.add(letter.toString())

        // Prepare letter options
        prepareOptions(wordLetters)

        // Show progress-related text boxes as empty
        val completeText = findViewById<TextView>(R.id.completeText)
        completeText.text = ""
        val continuePrompt = findViewById<TextView>(R.id.continuePrompt)
        continuePrompt.text = ""

        // Clear the letter list and the selected letters
        wordLetters.clear()
        currentLetters = ""

        // Reset letter count
        currentLetterNum = 0

        // Make sure we don't think the word is spelled from the get-go
        wordSpelled = false

        // Set our score to max to start out each new word
        score = 3

        // ...and make sure it displays.
        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)
        scoreDisplay.text = score.toString()
    }

    // Prepare the letter options for spelling.
    private fun prepareOptions(wordLetters: MutableList<String>) {

        // Save the letters for the word we are spelling.
        letterOptions = wordLetters

        // For all of the remaining spaces...
        for (i in letterOptions.size..8) {

            // ...generate random letters...
            val letter = letters[Random.nextInt(0,25)]

            // ...and save them as well.
            letterOptions.add(letter.toString())
        }

        // Now we make a list of buttons so we can save a letter to each
        val button1 = findViewById<Button>(R.id.letter1)
        val button2 = findViewById<Button>(R.id.letter2)
        val button3 = findViewById<Button>(R.id.letter3)
        val button4 = findViewById<Button>(R.id.letter4)
        val button5 = findViewById<Button>(R.id.letter5)
        val button6 = findViewById<Button>(R.id.letter6)
        val button7 = findViewById<Button>(R.id.letter7)
        val button8 = findViewById<Button>(R.id.letter8)
        val buttons = arrayOf(button1,button2,button3,button4,button5,button6,button7,button8)

        // Now a loop to add the letters
        for (button in buttons) {

            // First, pull a random letter from our options...
            val letter = letterOptions[Random.nextInt(0,letterOptions.size - 1)]

            // ...add it to the current button...
            button.text = letter

            // .. then remove it from the list
            letterOptions.remove(letter)
        }
    }

    // All of the useButton functions allow us to track which button is pressed
    fun useButton1(view: View) {
        val button = findViewById<Button>(R.id.letter1)
        spellWord(button,view)
    }

    fun useButton2(view: View) {
        val button = findViewById<Button>(R.id.letter2)
        spellWord(button,view)
    }

    fun useButton3(view: View) {
        val button = findViewById<Button>(R.id.letter3)
        spellWord(button,view)
    }

    fun useButton4(view: View) {
        val button = findViewById<Button>(R.id.letter4)
        spellWord(button,view)
    }

    fun useButton5(view: View) {
        val button = findViewById<Button>(R.id.letter5)
        spellWord(button,view)
    }

    fun useButton6(view: View) {
        val button = findViewById<Button>(R.id.letter6)
        spellWord(button,view)
    }

    fun useButton7(view: View) {
        val button = findViewById<Button>(R.id.letter7)
        spellWord(button,view)
    }

    fun useButton8(view: View) {
        val button = findViewById<Button>(R.id.letter8)
        spellWord(button,view)
    }

    // Take inputs from the buttons to spell out the word
    fun spellWord(button: Button, view: View) {

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

                // Add pressed button's value to the list
                this.currentLetters += buttonValue

                // Clear the button
                button.text = ""

                // We're on the next letter now
                currentLetterNum++
            }

            // If we still have a score and make a wrong guess...
            else if (score > 0 && buttonValue.compareTo("") != 0) {

                // ...lower the score
                score--

                // Display new score
                scoreDisplay.text = score.toString()
            }

            // Display the string of selected letters
            wordDisplay.text = formatSpellText(currentWord,currentLetters)
        }

        // If it is spelled correctly, we want to go to the next word,
        // but only after the user gets to look at it.
        // We'll have them push the button again when they're ready.
        if (wordSpelled){

            // Save the new score
            Global.setScore(currentWord,score,view.context)

            // We'll progress the list now
            progressList()

            // ...and clear the letters we already have
            wordDisplay.text = formatSpellText(currentWord,"")
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

    private fun goToResults() {
        // Goes to the result screen
        val intent = Intent(this@WordSpell, Results::class.java)
        startActivity(intent)
        finish()
    }

    private fun formatSpellText(currWord: String, currSpell : String) : String {
        // This function will format the text view of the spelling word
        // Get the remaining spaces that hasn't been spelled
        var blankSpaces = currWord.length - currSpell.length

        // Set the formatted word to the current spelling
        var formattedWord = currSpell

        // For each space that hasn't been spelled at an underscore
        for(num in 1..blankSpaces){
            formattedWord += "_"
        }

        // Return the current spelling with the underscores (UPPERCASE)
        return formattedWord.uppercase()
    }

}
