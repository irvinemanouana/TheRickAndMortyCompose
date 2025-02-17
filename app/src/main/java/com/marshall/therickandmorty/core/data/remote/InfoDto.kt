package com.marshall.therickandmorty.core.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)