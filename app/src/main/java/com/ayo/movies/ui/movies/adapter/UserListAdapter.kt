package com.ayo.movies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.UserDomain
import com.ayo.movies.R

class UserListAdapter(private val listener: Listener) : RecyclerView.Adapter<UserViewHolder>() {

    private var items = mutableListOf<UserDomain>()

    //notifyDataSetChanged is an expensive operation, should use a diff call back
    fun update(list: List<UserDomain>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface Listener {
        fun onClick(position: Int)
    }
}