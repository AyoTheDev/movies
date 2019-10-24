package com.ayo.movies.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(coroutineContextProvider: CoroutineContextProvider) : ViewModel(), CoroutineScope {

    private val jobs = mutableListOf<Job>()
    override val coroutineContext: CoroutineContext = coroutineContextProvider.io

    override fun onCleared() = jobs.forEach {
        it.cancel()
        jobs.remove(it)
    }

    fun load(job: Job) {
        job.apply {
            jobs.add(this)
            this.invokeOnCompletion { jobs.remove(this) }
        }
    }

}