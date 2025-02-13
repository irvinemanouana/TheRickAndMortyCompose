package com.marshall.therickandmorty.core.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType

private val client = HttpClient(CIO) {

    install(HttpCache)

    install(HttpTimeout) {
        requestTimeoutMillis = 1000L
    }
    install(DefaultRequest) {
        header("Accept", "application/json")
        header("content-type", "application/json")
        contentType(ContentType.Application.Json)
    }

}