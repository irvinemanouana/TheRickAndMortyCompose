package com.marshall.therickandmorty.character.data

import com.marshall.therickandmorty.character.data.entities.CharacterInfo
import com.marshall.therickandmorty.character.domain.CharacterRepository
import com.marshall.therickandmorty.core.data.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterRepositoryImpl : CharacterRepository, KoinComponent {

    private val client: HttpClient by inject()

    override suspend fun getAllCharacters(): Flow<Result<CharacterInfo>> = flow {
        try {
            val response = client.get("https://rickandmortyapi.com/api/character").body<CharacterInfo>()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error<Nothing>(e.message.toString()))
        }
        finally {
            client.close()
        }
    }

    override suspend fun getCharactersByPage(pageIndex: Int): Flow<Result<CharacterInfo>> {
        TODO("Not yet implemented")
    }
}