package com.ayo.domain.model

import com.ayo.api.model.Item

fun Item.toDomain(): CharacterDomain {
    return CharacterDomain(
        name = this.name,
        imageUrl = this.img,
        occupation = this.occupation,
        status = this.status,
        nickname = this.nickname,
        appearance = this.appearance
    )
}

fun List<Item>.toDomain(): List<CharacterDomain> {
    return this.map {
        it.toDomain()
    }
}
