package com.ayo.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ayo.domain.usecase.*
import com.ayo.movies.common.TestContextProvider
import com.ayo.movies.ui.breakingbad.viewmodel.MainViewModel
import com.ayo.movies.utils.Resource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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
    lateinit var useCase: CharacterUseCase

    @Before
    fun setUp() {
        underTest = MainViewModel(TestContextProvider(), useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getUsers(): Unit = runBlockingTest  {

        //given
        val observer: Observer<Resource<List<UserDomain>>> = mock()
        whenever(useCase.getCharacters()).doReturn(mockFlow)

        //when
        underTest.charactersLiveData.observeForever(observer)
        underTest.loadCharacters()

        //then
        verify(observer).onChanged(Resource.Success(emptyList()))
    }

    private val mockFlow: Flow<List<UserDomain>> = flow { emit(emptyList()) }

}

