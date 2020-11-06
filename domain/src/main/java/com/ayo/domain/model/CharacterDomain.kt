package com.ayo.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDomain(
    val name: String?,
    val imageUrl: String?,
    val occupation: List<String>?,
    val status: String?,
    val nickname: String?,
    val appearance: List<Int>?
): Parcelable
