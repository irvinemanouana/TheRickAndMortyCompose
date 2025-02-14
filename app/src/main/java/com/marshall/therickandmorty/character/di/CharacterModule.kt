package com.marshall.therickandmorty.character.di

import com.marshall.therickandmorty.character.data.CharacterRepositoryImpl
import com.marshall.therickandmorty.character.domain.CharacterRepository
import com.marshall.therickandmorty.character.presentation.CharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl() }
    viewModel { CharacterViewModel(get()) }
}