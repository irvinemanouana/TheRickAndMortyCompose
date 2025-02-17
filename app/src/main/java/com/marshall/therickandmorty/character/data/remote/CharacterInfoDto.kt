package com.marshall.therickandmorty.character.data.remote

import com.marshall.therickandmorty.core.data.remote.InfoDto
import kotlinx.serialization.Serializable

@Serializable
data class CharacterInfoDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)

@Serializable
data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterLocationDto,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

@Serializable
data class OriginDto(
    val name: String,
    val url: String
)

@Serializable
data class CharacterLocationDto(
    val name: String,
    val url: String
)