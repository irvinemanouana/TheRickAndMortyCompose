package com.marshall.therickandmorty.character.presentation


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marshall.therickandmorty.character.domain.Character
import com.marshall.therickandmorty.core.presentation.ErrorMessageScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = koinViewModel()
) {
    val characters = viewModel.charactersPagingFlow.collectAsLazyPagingItems()
    CharacterListScreenContent(modifier = modifier, charactersPagingItems = characters)

}

@Composable
fun CharacterListScreenContent(
    modifier: Modifier = Modifier,
    charactersPagingItems: LazyPagingItems<Character>
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = charactersPagingItems.loadState) {
        if (charactersPagingItems.loadState.refresh is LoadState.Error) {
            val error = charactersPagingItems.loadState.refresh as LoadState.Error
            Toast.makeText(context, error.error.message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(
        )
    ) {

        when (charactersPagingItems.loadState.refresh) {
            is LoadState.Error -> ErrorMessageScreen(message = "Error loading characters", action = {
                charactersPagingItems.retry()
            })
            is LoadState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(charactersPagingItems.itemCount) { index ->
                        val character = charactersPagingItems[index]
                        if (character != null) {
                            CharacterCard(character = character)
                        }
                    }
                    item {
                        if (charactersPagingItems.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                        if (charactersPagingItems.loadState.append is LoadState.Error) {
                            Button(onClick = { charactersPagingItems.retry() }) { Text("Retry") }
                        }
                    }

                }
            }

        }
    }
}




