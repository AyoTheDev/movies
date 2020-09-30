package com.ayo.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ayo.domain.model.UserDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.TestContextProvider
import com.ayo.movies.ui.movies.viewmodel.MainViewModel
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
    lateinit var useCase: UserUseCase

    @Before
    fun setUp() {
        underTest = MainViewModel(TestContextProvider(), useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getUsers(): Unit = runBlockingTest  {

        //given
        val observer: Observer<Resource<List<UserDomain>>> = mock()
        whenever(useCase.getUsers()).doReturn(mockFlow)

        //when
        underTest.usersLiveData.observeForever(observer)
        underTest.loadUsers()

        //then
        verify(observer).onChanged(Resource.Success(emptyList()))
    }

    private val mockFlow: Flow<List<UserDomain>> = flow { emit(emptyList()) }

}

