package com.example.wordspell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class MainMenu : AppCompatActivity() {
    // Set view data
    var trackNum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Sets the initial view
        setTrackView()

        // Get buttons in the view
        val subBtn = findViewById<Button>(R.id.subBtn)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val scoreBtn = findViewById<Button>(R.id.scoreBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)

        //Reset working data
        resetWorkingData()

        // Set listeners for the buttons
        subBtn.setOnClickListener(){
            // Listener to subtract 1 from the tracking number
            subTrack()
        }

        addBtn.setOnClickListener(){
            // Listener to add 1 to the tracking number
            addTrack()
        }

        scoreBtn.setOnClickListener() {
            // Listener so the user can go to the score screen
            goToScoreView()
        }

        startBtn.setOnClickListener() {
            // Listener so the user can go to the score screen
            goToWordSpellView()
        }
    }

    fun subTrack() {
        // Subtracts from the tracking number until it hits 1
        if(trackNum > 1){ trackNum -= 1 }
        else { trackNum = 1 }

        // Sets the text view
        setTrackView()
    }

    fun addTrack() {
        // Adds to the number until it hits 9
        if(trackNum < 9){ trackNum += 1 }
        else { trackNum = 9 }

        // Set the text view
        setTrackView()
    }

    fun setTrackView() {
        // Sets the text view
        val numText = findViewById<TextView>(R.id.numView)
        numText.text = trackNum.toString()
    }

    fun goToScoreView() {
        // Make sure working data is reset
        resetWorkingData()
        // Load the Results Activity
        val intent = Intent(this@MainMenu, Results::class.java)
        startActivity(intent)
        finish()

    }

    fun goToWordSpellView() {
        // Set the tracking number
        Global.setTrackNum(trackNum)
        // Set the working list
        createWorkingList()
        // Load the Word Spell Activity
        val intent = Intent(this@MainMenu, WordSpell::class.java)
        startActivity(intent)
        finish()
    }

    fun resetWorkingData() {
        // Reset working data
        Global.resetWorkingList()
        Global.resetTrackNum()
    }

    fun createWorkingList() {
        // Generate a list of non-duplicate numbers ranging from 0 to the length of the word list
        // The random numbers are the positions. Use those positions to get the word from the list.
        // Add the words to the working list.

    }
}