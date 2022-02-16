package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        var startUpText : TextView = findViewById<TextView>(R.id.main_menu_header)
        startUpText.text = WordData.name
    }
}