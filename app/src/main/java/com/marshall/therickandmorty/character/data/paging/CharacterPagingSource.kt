package com.marshall.therickandmorty.character.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil3.network.HttpException
import com.marshall.therickandmorty.character.data.mappers.toCharacter
import com.marshall.therickandmorty.character.data.remote.CharacterInfoDto
import com.marshall.therickandmorty.character.data.remote.EpisodeDto
import com.marshall.therickandmorty.character.domain.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import okio.IOException

class CharacterPagingSource(
    private val apiClient: HttpClient
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val startingPageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiClient.get("${BASE_URL}/character") {
                url {
                    parameters.append("page", startingPageIndex.toString())
                }
            }.body<CharacterInfoDto>()


            val nextKey = if (response.info.next == null) null else startingPageIndex.plus(1)

            val returnedData = response.results.map { characterDto ->
                val from = apiClient.get(characterDto.episode[0]).body<EpisodeDto>()

                characterDto.toCharacter().apply {
                    location = "${from.episode} - ${from.name}"
                }

            }
            LoadResult.Page(
                data = returnedData,
                prevKey = if (startingPageIndex == STARTING_PAGE_INDEX) null else startingPageIndex.minus(
                    1
                ),
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)

        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        const val BASE_URL = "https://rickandmortyapi.com/api"

    }
}