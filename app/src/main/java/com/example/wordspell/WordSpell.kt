package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WordSpell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_spell)
    }
    // Save the total word list
    val wordList = Global.getTestList()
    // The word currently being spelled
    var currentWord = ""
    // Letters of the current word
    val wordLetters = mutableListOf<String>()
    // Letters to be chosen from
    val letterOptions = mutableListOf<String>()
    // Letters currently selected
    val currentLetters = mutableListOf<String>()

    // Grab text box elements
//        var textBox = findViewById<TextView>(R.id.test_box)
//        // textBox.text = wordList[0]
//        val apple = wordList[0]
//        Global.setScore(apple, 2)
//        var score = Global.getScore(apple)
//        var track = Global.getTrackNum()

    // To keep track of what word we are on and how many words we have
    val numWords = wordList.size
    private fun progressList() {
        for (i in 0..numWords) {
            currentWord = wordList[i]
            currentWord.forEach { j -> wordLetters.add(j.toString()) }
            initializePage(currentWord,letterOptions,wordLetters)
            spellWord(letterOptions,wordLetters)
        }
    }

    private fun spellWord(letterOptions: MutableList<String>, wordLetters: MutableList<String>) {
        TODO("Not yet implemented")
    }

    private fun initializePage(
        currentWord: String,
        letterOptions: MutableList<String>,
        wordLetters: MutableList<String>
    ) {
        TODO("Not yet implemented")
    }
}