package com.ayo.movies.ui.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayo.api.exceptions.NoNetworkException
import com.ayo.api.exceptions.ServerException
import com.ayo.api.interceptors.NetworkResponseInterceptor
import com.ayo.api.utils.SchedulerProvider
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.BaseViewModel
import com.ayo.movies.common.CoroutineContextProvider
import com.ayo.movies.utils.Resource
import com.ayo.movies.utils.Resource.Success
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase,
    private val addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val moviesUseCase: MovieUseCase
) : BaseViewModel(coroutineContextProvider, compositeDisposable, schedulerProvider) {

    val favouriteMovies: LiveData<Resource<List<MovieDomain>>>
        get() = _favouriteMovies

    val popularMovies: LiveData<Resource<List<MovieDomain>>>
        get() = _popularMovies

    val movieDetails: LiveData<Resource<MovieDomain>>
        get() = _movieDetails

    private val _favouriteMovies = MutableLiveData<Resource<List<MovieDomain>>>()
    private val _popularMovies = MutableLiveData<Resource<List<MovieDomain>>>()
    private val _movieDetails = MutableLiveData<Resource<MovieDomain>>()

    init {
        _favouriteMovies.postValue(Resource.Loading(true))
        _popularMovies.postValue(Resource.Loading(true))
    }

    fun addMovieToFavourites(movie: MovieDomain) {
        try {
            val data = addMovieToFavouritesUseCase.addMovie(movie)
            _favouriteMovies.postValue(Success(data))
        } catch (e: NoNetworkException) {
            _favouriteMovies
                .postValue(Resource.Failure("Please connect to the internet", e))
            Timber.e(e)
        } catch (e: ServerException) {
            _favouriteMovies
                .postValue(Resource.Failure("MovieDb is currently down", e))
            Timber.e(e)
        } catch (e: Exception) {
            _favouriteMovies
                .postValue(Resource.Failure("Problem fetching movies", e))
            Timber.e(e)
        }
    }

    fun removeMovieFromFavourites(id: Int) {
        try {
            val data = removeMovieFromFavouritesUseCase.removeMovie(id)
            _favouriteMovies.postValue(Success(data))
        } catch (e: NoNetworkException) {
            _favouriteMovies
                .postValue(Resource.Failure("Please connect to the internet", e))
            Timber.e(e)
        } catch (e: ServerException) {
            _favouriteMovies
                .postValue(Resource.Failure("MovieDb is currently down", e))
            Timber.e(e)
        } catch (e: Exception) {
            _favouriteMovies
                .postValue(Resource.Failure("Problem fetching movies", e))
            Timber.e(e)
        }
    }

    fun loadMovieDetails(id: Int) = load(viewModelScope.launch {

    })

    fun isMovieFavourite(id: Int?): Boolean? {
        val currentFavourites = favouriteMoviesUseCase.getFavouriteMovies()
        return currentFavourites?.map { it.id }?.contains(id)
    }

    fun loadFavouriteMovies() = load(viewModelScope.launch {
        try {
            favouriteMoviesUseCase.getFavouriteMovies()?.let { data ->
                _favouriteMovies.postValue(Success(data))
            }
        } catch (e: NoNetworkException) {
            _favouriteMovies
                .postValue(Resource.Failure("Please connect to the internet", e))
            Timber.e(e)
        } catch (e: ServerException) {
            _favouriteMovies
                .postValue(Resource.Failure("Please connect to the internet", e))
            Timber.e(e)
        } catch (e: Exception) {
            _favouriteMovies
                .postValue(Resource.Failure("Please connect to the internet", e))
            Timber.e(e)
        }
    })

    fun loadPopularMovies() = load(viewModelScope.launch {
        callInteract(popularMoviesUseCase.getPopularMovies()) { it ->

        }
    })
}