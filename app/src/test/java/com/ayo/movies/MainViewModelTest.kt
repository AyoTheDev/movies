package com.ayo.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.TestContextProvider
import com.ayo.movies.ui.movies.viewmodel.MainViewModel
import com.ayo.movies.utils.Resource
import com.ayo.movies.utils.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private val compositeDisposable = CompositeDisposable()

    private val testScheduler = TestScheduler()
    private val testSchedulerProvider = TestSchedulerProvider(testScheduler)

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
            testSchedulerProvider, compositeDisposable, favouriteMoviesUseCase, addMovieToFavouritesUseCase,
            removeMovieFromFavouritesUseCase, popularMoviesUseCase, moviesUseCase
        )
    }

    @Test
    fun addMovieToFavouritesSuccess() {
        //given
        val movie = dummyMovie
        val observer: Observer<Resource<List<MovieDomain>>> = mock()
        whenever(addMovieToFavouritesUseCase.addMovie(movie)).doReturn(listOf(movie))

        //when
        underTest.favouriteMovies.observeForever(observer)
        underTest.addMovieToFavourites(movie)

        //then
        verify(addMovieToFavouritesUseCase).addMovie(movie)
        verify(observer).onChanged(Resource.Success(listOf(movie)))
    }

    @Test(expected = Exception::class)
    fun addMovieToFavouritesError() {
        //given
        val error = Exception()
        val observer: Observer<Resource<List<MovieDomain>>> = mock()

        whenever(addMovieToFavouritesUseCase.addMovie(dummyMovie)).thenThrow(error)

        underTest.favouriteMovies.observeForever(observer)
        underTest.addMovieToFavourites(dummyMovie)

        verify(observer).onChanged(Resource.Failure("test fail"))
    }

    private val dummyMovie = MovieDomain(1, "movie", "url", "overview", 1)

}

