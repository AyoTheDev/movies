package com.ayo.movies.utils

import java.lang.StringBuilder

fun List<String>.formatOccupationList(): String {
    val sb = StringBuilder()
    this.forEach {
        if (it == this.last()) sb.append(it)
        else sb.append("$it, ")
    }
    return sb.toString()
}

fun List<Int>.formatAppearanceList(): String {
    val sb = StringBuilder()
    this.forEach {
        if (it == this.last()) sb.append(it)
        else sb.append("$it, ")
    }
    return sb.toString()
}
