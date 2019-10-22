package com.ayo.movies.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(coroutineContextProvider: CoroutineContextProvider) : ViewModel(), CoroutineScope {

    private val jobs = mutableListOf<Job>()
    override val coroutineContext: CoroutineContext = coroutineContextProvider.main

    override fun onCleared() = jobs.forEach { it.cancel() }

    fun load(job: Job) = jobs.add(job)


}