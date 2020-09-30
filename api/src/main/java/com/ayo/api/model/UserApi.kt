package com.ayo.api.model

data class UserApi(
    val account_id: Int,
    val badge_counts: BadgeCount,
    val creation_date: Int,
    val display_name: String,
    val is_employee: Boolean,
    val link: String,
    val location: String?= null,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String,
    val website_url: String,
    val age: Int? = null
)