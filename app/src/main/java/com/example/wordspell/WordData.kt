package com.example.wordspell

class WordData(newWord : String, newScore : Int, newDate : String ){
    // The object contains the following pieces of data
    var word = "default"        // Word value
        private set             // Word can only be set on creation
    var score = 0               // Score from 0-3
        set(value) {      // Sets the score if between 0-3
            field = if(value in 0..3) {value} else {0}
        }
    var scoreDate = "default"   // String that has the time and date

    init {
        // Set the word, score, and the recorded date of that score
        word = newWord
        score = newScore
        scoreDate = newDate
    }
}