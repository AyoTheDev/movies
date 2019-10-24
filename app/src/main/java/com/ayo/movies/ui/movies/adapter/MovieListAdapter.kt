package com.ayo.movies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R

class MovieListAdapter(private val listener: Listener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = emptyList<MovieDomain>()

    fun update(list: List<MovieDomain>?){
        list?.let { MovieDomainList ->
            items = MovieDomainList
            notifyDataSetChanged()
        }
    }

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(items[position])
    }

    interface Listener {
        fun onClick(position: Int)
    }
}