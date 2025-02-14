package com.marshall.therickandmorty.character.domain

import com.marshall.therickandmorty.character.data.entities.CharacterInfo
import com.marshall.therickandmorty.core.data.Result
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacters(): Flow<Result<CharacterInfo>>
    suspend fun getCharactersByPage(pageIndex: Int): Flow<Result<CharacterInfo>>
}