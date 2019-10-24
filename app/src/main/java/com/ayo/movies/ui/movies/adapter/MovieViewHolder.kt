package com.ayo.movies.ui.movies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.utils.ImageLoaderUtils
import com.ayo.domain.model.prependMovieImageUrl
import com.ayo.movies.utils.AnimationUtils.setFadeAnimation
import kotlinx.android.synthetic.main.item_movie.view.*

private const val ANIMATION_DURATION = 1000L

class MovieViewHolder(itemView: View, private val listener: MovieListAdapter.Listener):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bind(movie: MovieDomain){
        itemView.apply {
            ImageLoaderUtils.loadImage(context, movie.imgUrl.prependMovieImageUrl(), image)
            setOnClickListener(this@MovieViewHolder)
            title.text = movie.title
            setFadeAnimation(this, ANIMATION_DURATION)
        }
    }

    override fun onClick(v: View?) {
        listener.onClick(layoutPosition)
    }
}