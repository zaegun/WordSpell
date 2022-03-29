package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // Set variables
        val rcv:RecyclerView = findViewById(R.id.recyclerView)
        val mmBtn : Button = findViewById(R.id.main_menu_btn)

        // Sets which list will be displayed
        // If tracking number is 0, displays everything, otherwise display the wordlist
        val scoreList = when(Global.getTrackNum() == 0) {
            true -> Global.getResultsData(Global.wordList)
            false -> Global.getResultsData(Global.workingList)
        }

        // Apply the list to the Recycler View
        rcv.apply {
            layoutManager = LinearLayoutManager(this@Results)
            adapter = Adapter(scoreList)
        }

        mmBtn.setOnClickListener{
            // Navigate to the main menu
            goToMainMenu()
        }

    }

    fun goToMainMenu() {
        // Set the intent and prevent back button usage
        val intent = Intent(this@Results, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}

