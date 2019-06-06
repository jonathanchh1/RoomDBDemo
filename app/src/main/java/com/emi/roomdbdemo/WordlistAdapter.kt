package com.emi.roomdbdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordlistAdapter(context : Context) : RecyclerView.Adapter<WordlistAdapter.WordViewHolder>() {


    private var wordList = emptyList<Word>()
    private val Inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val wordItemView = Inflater.inflate(R.layout.content_items, parent, false)
        return WordViewHolder(wordItemView)
    }


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = wordList[position]
        holder.wordTextItemView.text = current.word
    }


    override fun getItemCount(): Int {
        return wordList.size
    }


    internal fun setWord(word : List<Word>){
        this.wordList = word
        notifyDataSetChanged()
    }


    inner class WordViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val wordTextItemView = itemView.findViewById<TextView>(R.id.textView)
    }
}