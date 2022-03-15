package com.example.wordspell

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(var context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

        companion object {
            private val DATABASE_NAME = "WordSpellDB"
            val TABLE_NAME = "WordData"
            val COL_WORD = "word"
            val COL_SCORE = "score"
            val COL_DATE = "date"

        }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create the SQL table
        val createTable = ("CREATE TABLE " + TABLE_NAME + " (" +
                COL_WORD + " TEXT PRIMARY KEY," +
                COL_SCORE + " INTEGER," +
                COL_DATE + " TEXT" + ")")

        // Execute calling sqlite
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        // This checks if the table already exists
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(word : WordData) : Long {
        // Function is used to insert data into the SQL table
        // Get the database and the initiate the values
        val db = this.writableDatabase
        val cv = ContentValues()

        // Populate the values
        cv.put(COL_WORD, word.word)
        cv.put(COL_SCORE, word.score)
        cv.put(COL_DATE, word.scoreDate)

        // Inert values into the db
        val success = db.insert(TABLE_NAME, null, cv)
        db.close()
        return success
    }

    fun readData() : ArrayList<WordData> {
        // Get a list of all items
        // set the list variable and the query
        val wordList : ArrayList<WordData> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"

        // readable variable of the database
        val db = this.readableDatabase

        // Get the cursor
        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        // Store the values we will be using
        var word : String
        var score : Int
        var scoreDate : String

        // Get the values and apply it to the list
        if (cursor!!.moveToFirst()) {
            do {
                word = cursor.getString(cursor.getColumnIndexOrThrow(COL_WORD))
                score = cursor.getInt(cursor.getColumnIndexOrThrow(COL_SCORE))
                scoreDate = cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE))

                val data = WordData(word, score, scoreDate)
                wordList.add(data)
            } while (cursor.moveToNext())
        }
        // Close the cursor
        cursor.close()
        // return the list
        return wordList
    }

    fun updateWord (word : WordData) : Int {
        // Get the database
        val db = this.writableDatabase
        val cv = ContentValues()
        val wordName = word.word

        // Populate the values
        cv.put(COL_WORD, word.word)
        cv.put(COL_SCORE, word.score)
        cv.put(COL_DATE, word.scoreDate)

        // Update the item in the db and report the success or not
        val success = db.update(TABLE_NAME, cv,"$COL_WORD=?", arrayOf(wordName))
        db.close()
        return success
    }

    fun deleteWord(word : WordData) : Int {
        // Get the database
        val db = this.writableDatabase
        val wordName = word.word

        // Delete the word
        val success = db.delete(TABLE_NAME, "$COL_WORD=?", arrayOf(wordName))
        db.close()
        return success
    }
}