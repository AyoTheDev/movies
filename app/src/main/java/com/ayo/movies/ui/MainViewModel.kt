package com.ayo.movies.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.usecase.FavouriteMoviesUseCase
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
    private val moviesUseCase: FavouriteMoviesUseCase
) : BaseViewModel(coroutineContextProvider) {

    init {
        loadPopularMovies()
    }

    val popularMoviesLiveData = MutableLiveData<List<MovieDomain>>()

    fun loadPopularMovies() = load(launch {
        try {
            val data = popularMoviesUseCase.getPopularMovies()
            println("")
            popularMoviesLiveData.postValue(data)
        } catch (e: Exception) {
            Timber.e(e)
            println(e.message)
        }
    })


}