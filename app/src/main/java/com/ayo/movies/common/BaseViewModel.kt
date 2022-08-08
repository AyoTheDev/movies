package com.ayo.movies.common

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayo.api.exceptions.NetworkException
import com.ayo.api.exceptions.NoNetworkException
import com.ayo.api.exceptions.ServerException
import com.ayo.api.interceptors.NetworkResponseInterceptor
import com.ayo.api.utils.SchedulerProvider
import com.ayo.movies.utils.Event
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.Job

abstract class BaseViewModel(
    private val mCompositeDisposable: CompositeDisposable,
    private val mSchedulerProvider : SchedulerProvider
) :
    ViewModel() {

    private val jobs = mutableListOf<Job>()

    val loadingViewVisible = MutableLiveData<Event<Boolean>>()
    val errorMessage = MutableLiveData<Event<String>>()

    private fun showLoading() {loadingViewVisible.postValue(Event(true))}
    private fun hideLoading(){loadingViewVisible.postValue(Event(false))}
    private fun showError(message : String) {errorMessage.postValue(Event(message))}

    fun load(job: Job) {
        job.apply {
            jobs.add(this)
            this.invokeOnCompletion { jobs.remove(this) }
        }
    }

    protected open fun <T> callInteract(
        interactorObservableMethod : Observable<T>,
        networkResponseInterceptor: NetworkResponseInterceptor = NetworkResponseInterceptor(),
        errorCallback: ((Throwable) -> Unit)? = null,
        consumer : (T) -> Unit) {

        showLoading()
        mCompositeDisposable.add(
            interactorObservableMethod
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .doFinally{hideLoading()}
                .subscribe({
                    consumer(it)
                }, errorConsumer(null, networkResponseInterceptor, errorCallback))
        )
    }

    private fun errorConsumer(@StringRes errorTitle: Int?,
                              networkResponseInterceptor: NetworkResponseInterceptor,
                              errorCallback: ((Throwable) -> Unit)? = null
    ) : Consumer<in Throwable> {
        return Consumer{ throwable : Throwable ->
            if (throwable is NetworkException) {
                showError(throwable.localizedMessage)
            }else if (throwable is NoNetworkException){
                showError(throwable.localizedMessage)
            }else if (throwable is ServerException){
                showError(throwable.localizedMessage)
            }

            errorCallback?.let { it(throwable) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        hideLoading()
        mCompositeDisposable.dispose()
    }
}