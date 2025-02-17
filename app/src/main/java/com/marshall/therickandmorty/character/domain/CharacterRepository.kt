package com.marshall.therickandmorty.character.domain

import androidx.paging.PagingData
import com.marshall.therickandmorty.character.data.remote.CharacterInfoDto
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<PagingData<Character>>
}