package com.marshall.therickandmorty.core.data

sealed class Result <out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val message: String) : Result<Nothing>()
}