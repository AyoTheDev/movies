package com.ayo.movies.ui.movies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ayo.domain.model.MovieDomain
import com.ayo.movies.R
import com.ayo.movies.databinding.FragmentMovieDetailsBinding
import com.ayo.movies.ui.movies.activity.MainActivity
import com.ayo.movies.utils.ImageLoaderUtils
import com.ayo.movies.utils.Resource
import dagger.android.support.DaggerDialogFragment

class MovieDetailsDialogFragment : DaggerDialogFragment() {

    private val viewModel by lazy { (activity as MainActivity).viewModel }
    private lateinit var binding: FragmentMovieDetailsBinding


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
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpListener()
        loadData(movieId)
        observeViewModel()
    }

    private fun showLoading(loading: Boolean) {
        binding.loadingFlipper.displayedChild = if (loading) 0 else 1
    }

    private fun setUpView() {
        when (viewModel.isMovieFavourite(movieId)) {
            true -> {
                binding.favouriteSwitch.isChecked = true
                binding.favouriteSwitch.text = getString(R.string.remove_from_favourites)
            }
            false -> {
                binding.favouriteSwitch.isChecked = false
                binding.favouriteSwitch.text = getString(R.string.add_to_favourites)
            }
        }
    }

    private fun setUpListener() {
        binding.favouriteSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.apply {
                if (isChecked) {
                    addMovieToFavourites(movie)
                    binding.favouriteSwitch.text = getString(R.string.remove_from_favourites)
                } else {
                    removeMovieFromFavourites(movie.id)
                    binding.favouriteSwitch.text = getString(R.string.add_to_favourites)
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            movieDetails.observe(requireActivity(), Observer { handleMovieDetails(it) })
        }

    }

    private fun handleMovieDetails(resource: Resource<MovieDomain>) {
        when(resource){
            is Resource.Success -> {
                this.movie = resource.data
                ImageLoaderUtils.loadImage(context, movie.imgUrl, binding.image)
                binding.title.text = movie.title
                binding.details.text = movie.overview
                val runTimeText = "${movie.runtime} ${getString(R.string.minutes)}"
                binding.runtime.text = runTimeText
                showLoading(false)
            }
            is Resource.Loading -> showLoading(resource.loading)
            is Resource.Failure -> handleFailure(resource)
        }

    }

    private fun handleFailure(resource: Resource.Failure<*>) {
        //todo show snackbar for failure
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