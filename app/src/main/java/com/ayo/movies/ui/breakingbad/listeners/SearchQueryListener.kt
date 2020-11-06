/*
 * Copyright (C) Fluidic Analytics Limited, 2020.
 * All rights reserved.
 */

package com.ayo.movies.ui.breakingbad.listeners

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchQueryListener(lifecycle: Lifecycle, private val submitQuery: (String) -> Unit,
                          private val reset: () -> Unit) : TextWatcher {

    private var debouncePeriod: Long = 500

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty())
            reset()
        else {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                delay(debouncePeriod)
                submitQuery(s.toString())
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    fun onSearchButtonClicked(query: String) = submitQuery(query)

}
