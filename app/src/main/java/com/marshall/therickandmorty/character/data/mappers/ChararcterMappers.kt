package com.marshall.therickandmorty.character.data.mappers

import com.marshall.therickandmorty.character.data.remote.CharacterDto
import com.marshall.therickandmorty.character.domain.Character

fun CharacterDto.toCharacter(): Character {
    return Character(
        created = created,
        gender = gender,
        id = id,
        image = image,
        name = name,
        origin = origin.name,
        species = species,
        status = status,
        type = type,
        location = location.name,
        url = url
    )
}