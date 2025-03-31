package com.bcit.assignmenttracker.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson

// entry point to all our http requests
val MyHttpClient = HttpClient{
    install(ContentNegotiation) {
        gson()
    }
}
