package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent

class Startup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        WordData.setName("Billy")
        val startUpText : TextView = findViewById<TextView>(R.id.startUpLabel)
        startUpText.text = WordData.name

        val button : Button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this@Startup, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
    }
}

object WordData {
    var name = ""

    // Sets the Singleton name for testing
    @JvmName("setName1")
    fun setName(newName : String) {
        name = newName
    }
}