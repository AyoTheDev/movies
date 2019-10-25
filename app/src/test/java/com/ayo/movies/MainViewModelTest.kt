package com.ayo.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.TestContextProvider
import com.ayo.movies.ui.movies.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var underTest: MainViewModel

    @Mock
    lateinit var favouriteMoviesUseCase: FavouriteMoviesUseCase

    @Mock
    lateinit var addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase

    @Mock
    lateinit var removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase

    @Mock
    lateinit var popularMoviesUseCase: PopularMoviesUseCase

    @Mock
    lateinit var moviesUseCase: MovieUseCase

    @Before
    fun setUp() {
        underTest = MainViewModel(
            TestContextProvider(), favouriteMoviesUseCase, addMovieToFavouritesUseCase,
            removeMovieFromFavouritesUseCase, popularMoviesUseCase, moviesUseCase
        )

    }

    @Test
    fun addMovieToFavouritesSuccess() {
        //given
        val movie = dummyMovie
        val observer: Observer<List<MovieDomain>> = mock()
        whenever(addMovieToFavouritesUseCase.addMovie(movie)).doReturn(listOf(movie))

        //when
        underTest.favouriteMoviesLiveData.observeForever(observer)
        underTest.addMovieToFavourites(movie)

        //then
        verify(addMovieToFavouritesUseCase).addMovie(movie)
        verify(observer).onChanged(listOf(movie))
    }

    @Test(expected = Exception::class)
    fun addMovieToFavouritesError() {
        //given
        val error = Exception()
        val observer: Observer<String> = mock()
        whenever(addMovieToFavouritesUseCase.addMovie(dummyMovie)).thenThrow(error)

        underTest.errorStateLiveData.observeForever(observer)
        underTest.addMovieToFavourites(dummyMovie)

        verify(observer).onChanged("Problem fetching movies")
    }

    private val dummyMovie = MovieDomain(1, "movie", "url", "overview", 1)

}