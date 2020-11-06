package com.ayo.movies.ui.breakingbad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.CharacterDomain
import com.ayo.movies.R

class CharacterListAdapter(private val listener: Listener) : RecyclerView.Adapter<CharacterViewHolder>() {

    private var items = mutableListOf<CharacterDomain>()

    //notifyDataSetChanged is an expensive operation, should use a diff call back
    fun update(list: List<CharacterDomain>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface Listener {
        fun onClick(position: Int)
    }
}
