package com.ayo.movies.ui

import androidx.lifecycle.MutableLiveData
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.usecase.FavouriteMoviesUseCase
import com.ayo.domain.usecase.MovieUseCase
import com.ayo.domain.usecase.PopularMoviesUseCase
import com.ayo.movies.common.BaseViewModel
import com.ayo.movies.common.CoroutineContextProvider
import dagger.Module
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@Module
class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val moviesUseCase: MovieUseCase
) : BaseViewModel(coroutineContextProvider) {

    init {
        loadPopularMovies()
    }

    val popularMoviesLiveData = MutableLiveData<List<MovieDomain>>()
    val movieDetailsLiveData = MutableLiveData<MovieDomain>()

    fun loadMovieDetails(id: Int) = load(launch {
        try {
            val data = moviesUseCase.getMovie(id)
            movieDetailsLiveData.postValue(data)
        } catch (e: Exception) {
            Timber.e(e)
        }
    })

    private fun loadPopularMovies() = load(launch {
        try {
            val data = popularMoviesUseCase.getPopularMovies()
            popularMoviesLiveData.postValue(data)
        } catch (e: Exception) {
            Timber.e(e)
        }
    })


}