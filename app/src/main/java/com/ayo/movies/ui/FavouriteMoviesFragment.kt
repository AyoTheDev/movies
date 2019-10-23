package com.ayo.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.adapter.MovieListAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favourite_movies.*
import javax.inject.Inject

class FavouriteMoviesFragment: DaggerFragment() {

    private val viewModel by lazy { (activity as MainActivity).viewModel }

    private val adapter: MovieListAdapter by lazy { MovieListAdapter(movieListListener) }

    private var detailsDialog: MovieDetailsDialogFragment? = null

    private val movieListListener = object : MovieListAdapter.Listener {
        override fun onClick(position: Int) {
            val movieId = adapter.getItem(position).id
            detailsDialog = MovieDetailsDialogFragment.newInstance(movieId)
            fragmentManager?.let { fm ->
                detailsDialog?.show(fm, MovieDetailsDialogFragment.TAG)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
        viewModel.loadFavouriteMovies()
        observeViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewModel() {
        viewModel.favouriteMoviesLiveData.observe(this, Observer { handleMovieData(it) })
    }

    private fun handleMovieData(movieList: List<MovieDomain>?) {
        adapter.update(movieList)
    }

    private fun setUpView() {
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(recyclerView?.context, layoutManager.orientation)
        recyclerView?.addItemDecoration(dividerItemDecoration)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_movies, container, false)
    }

}