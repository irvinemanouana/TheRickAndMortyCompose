package com.marshall.therickandmorty.character.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marshall.therickandmorty.character.data.entities.CharacterInfo
import com.marshall.therickandmorty.core.presentation.ErrorMessageScreen
import com.marshall.therickandmorty.core.presentation.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(modifier: Modifier = Modifier, viewModel: CharacterViewModel = koinViewModel()) {

    val charactersInfo by viewModel.characterUiState.collectAsState()

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
    Column(modifier = modifier
        .fillMaxSize())
    {
        Text(text = characterInfo.toString())
    }
}

@Composable
@Preview(showBackground = true, name = "Character List Screen")
fun CharacterListScreenPreview() {

    CharacterListScreenContent()

}



