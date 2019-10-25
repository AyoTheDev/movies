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
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : DaggerFragment() {

    enum class PageType(val value: Int) {
        POPULAR(0),
        FAVOURITES(1)
    }

    companion object {
        fun newInstance(page: Int): MoviesFragment {
            val frag = MoviesFragment()
            frag.pageType = when (page) {
                0 -> PageType.POPULAR
                else -> PageType.FAVOURITES
            }
            return frag
        }
    }

    private lateinit var pageType: PageType

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
        when (pageType) {
            PageType.POPULAR ->
                viewModel.popularMoviesLiveData.observe(this, Observer { handleMovieData(it) })
            PageType.FAVOURITES ->
                viewModel.favouriteMoviesLiveData.observe(this, Observer { handleMovieData(it) })
        }
        viewModel.errorStateLiveData.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun handleMovieData(movieList: List<MovieDomain>) {
        adapter.update(movieList)
        showLoading(false)
    }

    private fun setUpView() {
        showLoading(true)
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(recyclerView?.context, layoutManager.orientation)
        recyclerView?.addItemDecoration(dividerItemDecoration)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onDestroy() {
        if (detailsDialog?.isVisible == true) detailsDialog?.dismiss()
        super.onDestroy()
    }

    private fun showLoading(loading: Boolean){
        loading_flipper.displayedChild = if (loading) 0 else 1
    }
}