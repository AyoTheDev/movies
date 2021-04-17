package com.ayo.movies.ui.breakingbadcampustours.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.CampusDomain
import com.ayo.movies.R
import com.ayo.movies.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.campus_item.view.*

class CampusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(campus: CampusDomain){
        val context = itemView.context
        itemView.apply {
            ImageLoaderUtils.loadImage(context, campus.imageUrl, img)
            campus_name.text = campus.name
            tour_count.text = context.getString(R.string.tour_count)
        }
    }
}
