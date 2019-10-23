package com.ayo.movies.ui.adapter

import android.view.View
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieViewHolder(itemView: View, private val listener: MovieListAdapter.Listener):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bind(movie: MovieDomain){
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.imgUrl}"
        itemView.apply {
            setOnClickListener(this@MovieViewHolder)
            title.text = movie.title
            Glide
                .with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(itemView.image)
            setFadeAnimation(this)
        }
    }

    override fun onClick(v: View?) {
        listener.onClick(layoutPosition)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = ANIMATION_DURATION
        view.startAnimation(anim)
    }

    companion object {
        private const val ANIMATION_DURATION = 1000L
    }
}