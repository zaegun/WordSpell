package com.example.wordspell

object WordList {
    var allWordsList = ArrayList<String>()

    init {
        allWordsList.add("apple")
        allWordsList.add("bath")
        allWordsList.add("boat")
        allWordsList.add("child")
        allWordsList.add("clock")
        allWordsList.add("dress")
        allWordsList.add("eight")
        allWordsList.add("eleven")
        allWordsList.add("gray")
        allWordsList.add("happy")
        allWordsList.add("house")
        allWordsList.add("lock")
        allWordsList.add("lunch")
        allWordsList.add("night")
        allWordsList.add("pool")
        allWordsList.add("school")
        allWordsList.add("sea")
        allWordsList.add("shape")
        allWordsList.add("sky")
        allWordsList.add("slide")
        allWordsList.add("stone")
        allWordsList.add("truck")
        allWordsList.add("tune")
        allWordsList.add("winter")
    }

    fun getWordList() : ArrayList<String> {
        return allWordsList
    }
}