package com.ayo.movies.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ayo.api.exceptions.NoNetworkException
import com.ayo.api.exceptions.ServerException
import com.ayo.domain.model.UserDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.BaseViewModel
import com.ayo.movies.common.CoroutineContextProvider
import com.ayo.movies.utils.Resource
import com.ayo.movies.utils.Resource.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val userUseCase: UserUseCase
) : BaseViewModel(coroutineContextProvider) {

    val usersLiveData: LiveData<Resource<List<UserDomain>>>
        get() = _usersListLiveData

    private val _usersListLiveData = MutableLiveData<Resource<List<UserDomain>>>()

    init {
        _usersListLiveData.postValue(Resource.Loading(true))
        loadUsers()
    }

    fun loadUsers(name: String? = null) {
        load(launch {
            try {
                val data = when (name) {
                    null -> userUseCase.getUsers()
                    else -> userUseCase.getUsersByName(name)
                }
                data.collect { users -> _usersListLiveData.postValue(Success(users)) }
            } catch (e: NoNetworkException) {
                _usersListLiveData
                    .postValue(Resource.Failure("Please connect to the internet", e))
                Timber.e(e)
            } catch (e: ServerException) {
                _usersListLiveData
                    .postValue(Resource.Failure("Stack API is currently down", e))
                Timber.e(e)
            } catch (e: Exception) {
                _usersListLiveData
                    .postValue(Resource.Failure("Problem fetching users", e))
                Timber.e(e)
            }
        })
    }

}