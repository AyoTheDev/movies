package com.ayo.domain.model

import com.ayo.api.model.UserApi

fun UserApi.toDomain(): UserDomain {
    return UserDomain(
        userId = this.user_id,
        creationDate = this.creation_date,
        displayName = this.display_name,
        location = this.location,
        profileImageURL = this.profile_image,
        reputation = this.reputation,
        userType = this.user_type,
        bronzeBadges = this.badge_counts.bronze,
        silverBadges = this.badge_counts.silver,
        goldBadges = this.badge_counts.gold,
        age = this.age
    )
}

fun List<UserApi>.toDomain(): List<UserDomain> {
    return this.map {
        it.toDomain()
    }
}