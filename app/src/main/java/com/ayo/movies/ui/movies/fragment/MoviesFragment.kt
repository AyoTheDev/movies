package com.ayo.movies.ui.movies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.ayo.movies.ui.movies.activity.MainActivity
import com.ayo.movies.ui.movies.adapter.MovieListAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class MoviesFragment : DaggerFragment() {

    companion object {
        fun newInstance(pageType: Int): MoviesFragment {
            val frag = MoviesFragment()
            frag.pageType = pageType
            return frag
        }
    }

    private var pageType: Int? = null

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
        observeViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewModel() {
        when(pageType){
            0 -> viewModel.popularMoviesLiveData.observe(this, Observer { handleMovieData(it) })
            1 -> viewModel.favouriteMoviesLiveData.observe(this, Observer { handleMovieData(it) })
        }
        viewModel.errorStateLiveData.observe(this, Observer {
            Toast.makeText(context, context?.getString(R.string.error_msg_1), Toast.LENGTH_LONG).show()
        })
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

    override fun onDestroy() {
        super.onDestroy()
        if(detailsDialog?.isVisible == true) detailsDialog?.dismiss()
    }
}