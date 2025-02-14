package com.marshall.therickandmorty.character.data.entities

import com.marshall.therickandmorty.core.data.remote.Info
import kotlinx.serialization.Serializable

@Serializable
data class CharacterInfo(
    val info: Info,
    val results: List<Character>
)