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
import com.ayo.movies.databinding.FragmentMoviesBinding
import com.ayo.movies.ui.movies.activity.MainActivity
import com.ayo.movies.ui.movies.adapter.MovieListAdapter
import com.ayo.movies.utils.Resource
import dagger.android.support.DaggerFragment

class MoviesFragment : DaggerFragment() {

    private lateinit var pageType: PageType

    private val viewModel by lazy { (activity as MainActivity).viewModel }

    private val adapter: MovieListAdapter by lazy { MovieListAdapter(movieListListener) }

    private var detailsDialog: MovieDetailsDialogFragment? = null

    private lateinit var binding: FragmentMoviesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
        observeViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewModel() {
        viewModel.apply {
            when (pageType) {
                PageType.POPULAR ->
                    popularMovies.observe(requireActivity(), Observer { handleMovieData(it) })
                PageType.FAVOURITES ->
                    favouriteMovies.observe(requireActivity(), Observer { handleMovieData(it) })
            }
        }
    }

    private fun handleMovieData(resource: Resource<List<MovieDomain>>) {
        when(resource){
            is Resource.Success -> {
                adapter.update(resource.data)
                showLoading(false)
            }
            is Resource.Loading -> showLoading(resource.loading)
            is Resource.Failure -> handleFailure(resource)
        }

    }

    private fun handleFailure(resource: Resource.Failure<*>) {
        //todo show snack bar for error
    }

    private fun setUpView() {
        showLoading(true)
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration =
            DividerItemDecoration(binding.recyclerView.context, layoutManager.orientation)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        if (detailsDialog?.isVisible == true) detailsDialog?.dismiss()
        super.onDestroy()
    }

    private fun showLoading(loading: Boolean) {
        binding.loadingFlipper.displayedChild = if (loading) 0 else 1
    }

    private val movieListListener = object : MovieListAdapter.Listener {
        override fun onClick(position: Int) {
            val movieId = adapter.getItem(position).id
            detailsDialog = MovieDetailsDialogFragment.newInstance(movieId)
            fragmentManager?.let { fm ->
                detailsDialog?.show(fm, MovieDetailsDialogFragment.TAG)
            }
        }
    }

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
}