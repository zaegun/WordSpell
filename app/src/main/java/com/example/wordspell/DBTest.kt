package com.example.wordspell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class DBTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbtest)

        // Setup DB
        val db = DBHelper(this, null)
        // Initialize DB and load data
        loadData(db)
        crossCheckList(db)

        val text1 = findViewById<TextView>(R.id.testView)
        text1.text = Global.wordList.toString()

        val text2 = findViewById<TextView>(R.id.testView2)
        textViewRefresh(text2)

        val btn0 = findViewById<Button>(R.id.button5)
        val btn1 = findViewById<Button>(R.id.button6)
        val btn2 = findViewById<Button>(R.id.button8)
        val btn3 = findViewById<Button>(R.id.button9)

        btn0.setOnClickListener() {
            Global.setScore("apple", 0, this)
            textViewRefresh(text2)
        }
        btn1.setOnClickListener() {
            Global.setScore("apple", 1, this)
            textViewRefresh(text2)
        }
        btn2.setOnClickListener() {
            Global.setScore("apple", 2, this)
            textViewRefresh(text2)
        }
        btn3.setOnClickListener() {
            Global.setScore("apple", 3, this)
            textViewRefresh(text2)
        }

    }

    fun textViewRefresh(textBox : TextView) {
        textBox.text = Global.getScore("apple").toString()

    }

    // Load Data list
    // Get the JSON List
    // Check if each of the words is in the Data list
    // If not add the JSON word to the data list
    // else move on
    fun loadData(db : DBHelper) {
        // Clears the Global local data for refresh from db
        Global.clearWordData()
        Global.clearWordList()

        // Get the DB data
        val readList = db.readData()

        // Put the contents into the Global local data
        Global.setGlobalData(readList)
        Global.generateWordList()
    }

    fun crossCheckList(db : DBHelper) {
        // Get the hard coded word list
        val allWordsList = WordList.allWordsList

        // Check if it exists in the db loaded data
        for(refWord in allWordsList) {
            var check = Global.wordList.find { word -> word == refWord}
            // If the word doesn't exist, add it to the database with parameters
            if(check == null) {
                // Set the parameters
                val word = refWord
                val score = 0
                val date = "2022.02.16 at 2:27:00"

                // Create the WordData object
                val newWord = WordData(word, score, date)

                // Put into the db
                db.insertData(newWord)

                // Add to Global
                Global.setCurrentDataList(newWord)
                Global.addOneToWordList(newWord.word)
            }
        }

    }
}
