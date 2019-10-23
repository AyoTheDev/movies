package com.ayo.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.adapter.MovieListAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val adapter by lazy { MovieListAdapter(movieListListener) }

    private val movieListListener = object : MovieListAdapter.Listener {
        override fun onClick(position: Int) {

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
        loadData()
        observeViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadData() {
        //viewModel.loadPopularMovies()
    }

    private fun observeViewModel() {
        viewModel.popularMoviesLiveData.observe(this, Observer { handleMovieData(it) })
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
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }
}