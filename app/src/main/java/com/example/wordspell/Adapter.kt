package com.example.wordspell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(
    val list : List<WordData>
) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Gets the object in the view for the word and the score
        val tvWord: TextView = itemView.findViewById(R.id.tvCurrentWord)
        val tvScore: TextView = itemView.findViewById(R.id.tvCurrentScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflates the list
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_score_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Populate the view objects and applies the data
        val item = list[position]
        holder.apply{
            tvWord.text = item.word
            tvScore.text = item.score.toString()
        }
    }

    override fun getItemCount(): Int {
        // Get the size of the list
        return list.size
    }
}