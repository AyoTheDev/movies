package com.ayo.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CampusDomain(
    val name: String?,
    val imageUrl: String?
): Parcelable
