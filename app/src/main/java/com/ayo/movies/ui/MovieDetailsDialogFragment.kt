package com.ayo.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.ayo.movies.di.ViewModelFactory
import com.bumptech.glide.Glide
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*
import javax.inject.Inject

class MovieDetailsDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private var movieId: Int? = null

    companion object {
        const val TAG = "MovieDetailsDialogFragment"
        fun newInstance(id: Int): MovieDetailsDialogFragment {
            val fragment = MovieDetailsDialogFragment()
            fragment.movieId = id
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movieDetailsLiveData.observe(this, Observer { handleMovieDetails(it) })
    }

    private fun handleMovieDetails(movie: MovieDomain) {
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie?.imgUrl}"
        Glide
            .with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(image)

        title.text = movie.title
        details.text = movie.overview
        runtime.text = movie.runtime

    }

    private fun loadData() {
        movieId?.let { id -> viewModel.loadMovieDetails(id) }
    }
}