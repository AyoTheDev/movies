package com.ayo.movies.ui.movies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.ayo.movies.ui.movies.activity.MainActivity
import com.ayo.movies.utils.ImageLoaderUtils
import com.ayo.domain.model.prependMovieImageUrl
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsDialogFragment : DaggerDialogFragment() {

    private val viewModel by lazy { (activity as MainActivity).viewModel }

    private var movieId: Int? = null
    private lateinit var movie: MovieDomain

    companion object {
        const val TAG = "MovieDetailsDialogFragment"
        fun newInstance(id: Int): MovieDetailsDialogFragment {
            val fragment = MovieDetailsDialogFragment()
            fragment.movieId = id
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        showLoading(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpListener()
        loadData(movieId)
        observeViewModel()
    }

    private fun showLoading(loading: Boolean) {
        loading_flipper.displayedChild = if (loading) 0 else 1
    }

    private fun setUpView() {
        when (viewModel.isMovieFavourite(movieId)) {
            true -> {
                favourite_switch.isChecked = true
                favourite_switch.text = getString(R.string.remove_from_favourites)
            }
            false -> {
                favourite_switch.isChecked = false
                favourite_switch.text = getString(R.string.add_to_favourites)
            }
        }
    }

    private fun setUpListener() {
        favourite_switch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.apply {
                if (isChecked) {
                    addMovieToFavourites(movie)
                    favourite_switch.text = getString(R.string.remove_from_favourites)
                } else {
                    removeMovieFromFavourites(movie.id)
                    favourite_switch.text = getString(R.string.add_to_favourites)
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.movieDetailsLiveData.observe(this, Observer { handleMovieDetails(it) })
    }

    private fun handleMovieDetails(movie: MovieDomain) {
        this.movie = movie
        ImageLoaderUtils.loadImage(context, movie.imgUrl.prependMovieImageUrl(), image)
        title.text = movie.title
        details.text = movie.overview
        val runTimeText = "${movie.runtime} ${getString(R.string.minutes)}"
        runtime.text = runTimeText
        showLoading(false)
    }

    private fun loadData(movieId: Int?) {
        if (movieId != null) {
            viewModel.loadMovieDetails(movieId)
        } else {
            Toast.makeText(context, context?.getString(R.string.error_msg_2), Toast.LENGTH_LONG).show()
            dismiss()
        }
    }
}