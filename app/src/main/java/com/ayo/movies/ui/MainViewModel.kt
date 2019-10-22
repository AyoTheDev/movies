package com.ayo.movies.ui

import com.ayo.domain.usecase.FavouriteMoviesUseCase
import com.ayo.domain.usecase.PopularMoviesUseCase
import com.ayo.movies.common.BaseViewModel
import com.ayo.movies.common.CoroutineContextProvider
import dagger.Module
import javax.inject.Inject

@Module
class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val moviesUseCase: FavouriteMoviesUseCase
) : BaseViewModel(coroutineContextProvider) {


}