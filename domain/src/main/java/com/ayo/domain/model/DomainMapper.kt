package com.ayo.domain.model

import com.ayo.api.model.Item

fun Item.toDomain(): CampusDomain {
    return CampusDomain(
        name = this.name,
        imageUrl = this.img)
}

fun List<Item>.toDomain(): List<CampusDomain> {
    return this.map {
        it.toDomain()
    }
}
