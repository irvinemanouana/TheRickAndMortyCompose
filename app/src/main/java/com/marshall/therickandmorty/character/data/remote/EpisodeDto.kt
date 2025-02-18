package com.marshall.therickandmorty.character.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val air_date: String,
    val episode: String,
    val id: Int,
    val name: String,
    val characters : List<String>,
)