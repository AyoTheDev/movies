package com.ayo.movies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R

class MovieListAdapter(private val listener: Listener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var items = mutableListOf<MovieDomain>()

    fun update(list: List<MovieDomain>) {
        val diffCallBack = MovieDiffCallback(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface Listener {
        fun onClick(position: Int)
    }
}