package com.ayo.movies.ui.breakingbad.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.CharacterDomain
import com.ayo.movies.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.character_item.view.*


class CharacterViewHolder(itemView: View, private val listener: CharacterListAdapter.Listener):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bind(character: CharacterDomain){
        val context = itemView.context
        itemView.apply {
            setOnClickListener(this@CharacterViewHolder)
            ImageLoaderUtils.loadImage(context, character.imageUrl, img)
            name.text = character.name
        }
    }

    override fun onClick(v: View?) {
        listener.onClick(layoutPosition)
    }
}
