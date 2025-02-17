package com.marshall.therickandmorty.character.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marshall.therickandmorty.character.data.paging.CharacterPagingSource
import com.marshall.therickandmorty.character.domain.Character
import com.marshall.therickandmorty.character.domain.CharacterRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterRepositoryImpl : CharacterRepository, KoinComponent {

    private val client: HttpClient by inject()
    override fun getAllCharacters(): Flow<PagingData<Character>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(client) }
        ).flow
    }
}