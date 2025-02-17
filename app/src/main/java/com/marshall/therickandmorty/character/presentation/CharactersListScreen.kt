package com.marshall.therickandmorty.character.presentation


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marshall.therickandmorty.character.data.entities.CharacterInfo
import com.marshall.therickandmorty.core.presentation.ErrorMessageScreen
import com.marshall.therickandmorty.core.presentation.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(modifier: Modifier = Modifier, viewModel: CharacterViewModel = koinViewModel()) {

    val charactersInfo by viewModel.characterUiState.collectAsStateWithLifecycle()

    when (charactersInfo) {
        is UiState.Success -> {
            CharacterListScreenContent(modifier = modifier, characterInfo = (charactersInfo as UiState.Success).data)
        }

        is UiState.Error -> {
            ErrorMessageScreen(message = (charactersInfo as UiState.Error).message, action = {})
        }
        is UiState.Loading -> {
        }
    }


}

@Composable
fun CharacterListScreenContent(modifier: Modifier = Modifier, characterInfo: CharacterInfo? = null) {
    LazyColumn(modifier = modifier) {
        if (characterInfo != null) {
            items(characterInfo.results) {
                character ->
                CharacterCard(character = character)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Character List Screen")
fun CharacterListScreenPreview() {

    CharacterListScreenContent()

}



