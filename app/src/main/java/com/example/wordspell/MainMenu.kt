package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val startUpText : TextView = findViewById<TextView>(R.id.main_menu_header)
        //val scoreText = Global.wordData[Global.currentInt].score
        //val wordText = Global.wordData[Global.currentInt].word
        startUpText.text = Global.wordList.toString()
    }
}