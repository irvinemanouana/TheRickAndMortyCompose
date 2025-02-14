package com.marshall.therickandmorty.character.presentation


import androidx.lifecycle.viewModelScope
import com.marshall.therickandmorty.character.data.entities.CharacterInfo
import com.marshall.therickandmorty.character.domain.CharacterRepository

import com.marshall.therickandmorty.core.presentation.BaseViewModel
import com.marshall.therickandmorty.core.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(characterRepository: CharacterRepository): BaseViewModel() {

    private val _currentCharacterPage = MutableStateFlow(1)
    val currentCharacterPage = _currentCharacterPage.asStateFlow()



    private val _characterUiState = MutableStateFlow<UiState<CharacterInfo?>>(UiState.Loading)
    val characterUiState = _characterUiState.asStateFlow()

    init {
        viewModelScope.launch {
            characterRepository
                .getAllCharacters()
                .collect {
                    httpResponse ->
                    handleResult(
                        result = httpResponse,
                        onSuccess = {
                            _characterUiState.value = UiState.Success(it)
                        },
                        onError = {
                            _characterUiState.value = UiState.Error(it)
                        }
                    )
                }
        }
    }


}