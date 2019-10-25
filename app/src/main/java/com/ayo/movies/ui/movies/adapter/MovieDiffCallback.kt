package com.ayo.movies.ui.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ayo.domain.model.MovieDomain

class MovieDiffCallback(private val oldList: List<MovieDomain>, private val newList: List<MovieDomain>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.title == new.title
    }
}
