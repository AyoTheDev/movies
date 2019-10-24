package com.ayo.movies.ui.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.BaseViewModel
import com.ayo.movies.common.CoroutineContextProvider
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase,
    private val addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val moviesUseCase: MovieUseCase
) : BaseViewModel(coroutineContextProvider) {

    init {
        loadPopularMovies()
        loadFavouriteMovies()
    }

    val errorStateLiveData = MutableLiveData<Exception>()
    val favouriteMoviesLiveData = MutableLiveData<List<MovieDomain>>()
    val popularMoviesLiveData = MutableLiveData<List<MovieDomain>>()
    val movieDetailsLiveData = MutableLiveData<MovieDomain>()

    fun addMovieToFavourites(movie: MovieDomain) {
        try {
            val data = addMovieToFavouritesUseCase.addMovie(movie)
            favouriteMoviesLiveData.postValue(data)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun removeMovieFromFavourites(id: Int) {
        try {
            val data = removeMovieFromFavouritesUseCase.removeMovie(id)
            favouriteMoviesLiveData.postValue(data)
        } catch (e: Exception) {
            errorStateLiveData.postValue(e)
            Timber.e(e)
        }
    }

    fun loadMovieDetails(id: Int) = load(launch {
        try {
            val data = moviesUseCase.getMovie(id)
            movieDetailsLiveData.postValue(data)
        } catch (e: Exception) {
            errorStateLiveData.postValue(e)
            Timber.e(e)
        }
    })

    fun isMovieFavourite(id: Int?): Boolean? {
        val currentFavourites = favouriteMoviesUseCase.getFavouriteMovies()
        return currentFavourites?.map { it.id }?.contains(id)
    }

    private fun loadFavouriteMovies() = load(launch {
        try {
            val data = favouriteMoviesUseCase.getFavouriteMovies()
            favouriteMoviesLiveData.postValue(data)
        } catch (e: Exception) {
            errorStateLiveData.postValue(e)
            Timber.e(e)
        }
    })

    private fun loadPopularMovies() = load(launch {
        try {
            val data = popularMoviesUseCase.getPopularMovies()
            popularMoviesLiveData.postValue(data)
        } catch (e: Exception) {
            errorStateLiveData.postValue(e)
            Timber.e(e)
        }
    })
}