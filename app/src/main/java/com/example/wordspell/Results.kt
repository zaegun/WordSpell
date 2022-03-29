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
        val rcv:RecyclerView = findViewById(R.id.recyclerView)


        var scoreList = Global.getResultsData(Global.getTestList())


        rcv.apply {
            layoutManager = LinearLayoutManager(this@Results)
            adapter = Adapter(scoreList)
        }



    }
}

