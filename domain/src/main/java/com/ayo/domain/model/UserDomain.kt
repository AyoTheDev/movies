package com.ayo.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDomain(
    var age: Int? = null,
    var creationDate: Int,
    var displayName: String,
    var location: String? = null,
    var profileImageURL: String,
    var reputation: Int,
    var userId: Int,
    var userType: String,
    var bronzeBadges: Int,
    var goldBadges: Int,
    var silverBadges: Int
) : Parcelable