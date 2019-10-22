package com.ayo.movies.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPrefs(private val context: Context) {

    companion object {
        private const val FILE_NAME = "com.ayo.movies"
        private const val FAVOURITES = "favourites"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    var favourites: Set<String>?
        get() = prefs.getStringSet(FAVOURITES, emptySet())?.toSet()
        set(value) = prefs.edit().putStringSet(FAVOURITES, value).apply()
}