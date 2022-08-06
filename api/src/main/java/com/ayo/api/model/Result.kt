package com.ayo.api.model

import com.google.gson.annotations.SerializedName

data class Result(
    val id: Int,
    val title: String,
    val poster_path: String)