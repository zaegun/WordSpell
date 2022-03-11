package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)


        var testList = Global.getTestList()
        var apple = testList[0]
        var textBox = findViewById<TextView>(R.id.test_text_box)
        var score = Global.getScore(apple)
        var track = Global.getTrackNum()
        textBox.text = apple.toString() + score.toString() + track.toString()

//        create a loop to loop through each item in the list



    }
}

