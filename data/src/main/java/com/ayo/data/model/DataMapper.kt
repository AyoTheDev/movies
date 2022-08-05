package com.ayo.data.model

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.IllegalStateException

fun Set<String>.toDomain(gson: Gson): List<MovieData> {
    return try {
        this.map { gson.fromJson(it, MovieData::class.java) }
    } catch (e: JsonSyntaxException) {
        throw IllegalStateException("Cannot parse data")
    }
}
