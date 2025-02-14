package com.marshall.therickandmorty.core.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)