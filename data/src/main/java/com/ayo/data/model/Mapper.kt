package com.ayo.data.model

import com.google.gson.Gson

fun Set<String>.toDomain(gson: Gson): List<MovieData> {
    return this.map { gson.fromJson(it, MovieData::class.java) }
}
