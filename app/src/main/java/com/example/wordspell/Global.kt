package com.example.wordspell

import android.content.Context

object Global {
    // GLOBAL DATA
    private var wordData = mutableListOf<WordData>()    // Contains all word data
    var wordList = mutableListOf<String>()              // List of all the words
        private set             // Should only be set at startup

    // The working list is an array of words that are created based on the number of words selected
    var workingList = mutableListOf<String>()   // Smaller array of words for usage
    var numberOfWords = 0       // The amount of words the user wants to go through
    var resultsData = mutableListOf<WordData>()


    fun clearWordData() {
        // This clears the local data for refresh
        wordData.clear()
    }

    fun setGlobalData(dataList : ArrayList<WordData>) {
        // Inserts a whole list to wordData
        wordData = dataList
    }

    fun setCurrentDataList(data : WordData) {
        // Adds one word data to the list
        wordData.add(data)
    }

    fun clearWordList() {
        // Clears all strings
        wordList.clear()
    }

    fun generateWordList(){
        // Makes a list using the word names in the data
        for(wordData in wordData) {
            wordList.add(wordData.word)
        }
    }

    fun addOneToWordList(word : String) {
        // Adds one word to the word list
        wordList.add(word)
    }

    fun getScore(refWord : String) : Int {
        // This finds the data based on the reference word
        val foundWord = wordData.find {it.word == refWord}
        //  Return the score
        return foundWord?.score!!
    }

    fun setScore(refWord : String, newScore : Int, context : Context) {
        // This updates the score and the date the score was changed
        // This finds the data based on the reference word
        val foundWord = wordData.find {it.word == refWord}
        // First check if the the score needs to be updated
        if (foundWord?.score != newScore) {
            // If it needs updating, update with the new score and date
            foundWord?.score = newScore
        }
        // Update DB
        val db = DBHelper(context, null)
        db.updateWord(foundWord!!)
    }

    fun setWorkingList(word : String) {
        // Adds the word to the working list
        workingList.add(word)
    }

    fun resetWorkingList(){
        // Resets the working list to a blank array
        workingList.clear()
    }

    fun getTrackNum() : Int {
        // This returns the number of words the user wants to be tested on
        return numberOfWords
    }

    fun setTrackNum(amount : Int) {
        // This sets the number of words the user wants to be tested on
        numberOfWords = amount
    }

    fun resetTrackNum() {
        // Sets the number of words counter to 0
        setTrackNum(0)
    }

    fun getResultsData(list: MutableList<String>) : MutableList<WordData> {
        // For the Results screen the Recycle View needs access to the word name and score.
        // As the working list only contains the word names, we need to create a list with
        // Word Data objects in them using the supplied working list without effecting
        // The main data.

        // Cycle through the supplied list
        for (reference in list) {
            // Find the correct word data object
            val foundWord = wordData.find { it.word == reference }
            // Add the object to the resultsData list
            if (foundWord != null) {
                resultsData.add(foundWord)
            }
        }
        // Return the list which has the word data
        return resultsData
    }

    fun getTestList(): MutableList<String> {
        // Creates a list for testing, updates their scores, and applies the correct tracking number
        val testList = mutableListOf<String>()
        testList.add("apple")
        testList.add("truck")
        testList.add("school")

        setTrackNum(testList.size)
        for(word in testList) {
            // This finds the data based on the reference word
            val foundWord = wordData.find { it.word == word}
            // If it needs updating, update with the new score and date
            foundWord?.score = 0
        }

        return testList
    }

}