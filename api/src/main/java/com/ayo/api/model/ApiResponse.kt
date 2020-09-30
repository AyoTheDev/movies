package com.ayo.api.model

data class ApiResponse(
    val has_more: Boolean,
    val items: List<UserApi>,
    val quota_max: Int,
    val quota_remaining: Int
)