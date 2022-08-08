package com.ayo.movies

import com.ayo.api.model.MovieApi
import com.ayo.api.services.MovieDbService
import com.ayo.data.repository.MovieDbRepository
import com.ayo.data.SharedPrefs
import com.ayo.data.model.MovieData
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doReturn
import io.reactivex.Observable
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.internal.matchers.Any
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    private lateinit var underTest: MovieDbRepository

    @Mock
    lateinit var service: MovieDbService

    @Mock
    lateinit var sharedPrefs: SharedPrefs

    private val gson = Gson()

    @Before
    fun setUp() {
        underTest = MovieDbRepository(service, sharedPrefs, gson)
    }

    @Test
    fun addMovieToFavourites() {
        //given
        val movie = dummyMovie

        //when
        val result = underTest.addMovieToFavourites(movie)

        //then
        assert(result.isNotEmpty())
    }

    @Test
    fun removeMovieFromFavourites() {
        //given
        doReturn(setOf(dummyMovieJson)).`when`(sharedPrefs).favourites

        //when
        val result = underTest.removeMovieFromFavourites(290859)

        //then
        assert(result.isEmpty())
    }

    @Test
    fun getFavouriteMovies() {
        //given
        doReturn(setOf(dummyMovieJson)).`when`(sharedPrefs).favourites

        //when
        val result = underTest.getFavouriteMovies()

        //then
        assertNotNull(result)
        assert(result?.isNotEmpty() == true)
    }

    @Test
    fun getMovieDetails() = runBlocking {
        //given
        `when`(service.getMovie(1)).thenReturn(Observable.just(dummyMovieApi))

        //when
        val result = underTest.getMovieDetails(1)

        //then
        assertNotNull(result)
    }

    private val dummyMovieApi = MovieApi(1, "movie", "url", "overview", 1)

    private val dummyMovie = MovieData(1, "movie", "url", "overview", 1)

    private val dummyMovieJson = "{\"id\":290859,\"imgUrl\":\"/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg\",\"overview\":\"" +
            "More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, " +
            "and re-wrote the fate of the human race. Dani Ramos is living a simple life in " +
            "Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – " +
            "travels back through time to hunt and kill her. Dani\\u0027s survival depends on her joining forces " +
            "with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. " +
            "As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are " +
            "led to a T-800 from Sarah’s past that may be their last best hope.\",\"runtime\":128,\"title\":\"Terminator: Dark Fate\"}"
}
