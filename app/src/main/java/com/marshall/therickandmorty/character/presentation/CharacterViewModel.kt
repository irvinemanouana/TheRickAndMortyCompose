package com.marshall.therickandmorty.character.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.marshall.therickandmorty.character.domain.CharacterRepository


class CharacterViewModel(characterRepository: CharacterRepository): ViewModel() {

    val charactersPagingFlow = characterRepository
        .getAllCharacters()
        .cachedIn(viewModelScope)

}