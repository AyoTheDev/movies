package com.ayo.movies.ui.breakingbadcampustours.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.CampusDomain
import com.ayo.movies.R

class CampusListAdapter : RecyclerView.Adapter<CampusViewHolder>() {

    private var items = mutableListOf<CampusDomain>()

    //notifyDataSetChanged is an expensive operation, should use a diff call back
    fun update(list: List<CampusDomain>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampusViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.campus_item, parent, false)
        return CampusViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CampusViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
