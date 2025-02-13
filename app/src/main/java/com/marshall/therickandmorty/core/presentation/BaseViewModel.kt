package com.marshall.therickandmorty.core.presentation

import com.marshall.therickandmorty.core.data.Result
import androidx.lifecycle.ViewModel

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}


abstract class BaseViewModel: ViewModel() {
    protected fun <T> handleResult(
        result: Result<T>,
        onSuccess:(T) -> Unit,
        onError:(String) -> Unit
    ) {
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Error<*> -> onError(result.message)
        }
    }
}