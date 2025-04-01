package com.bcit.assignmenttracker.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MotivationRemoteSource(private val client: HttpClient) {

    suspend fun getMotivation(): Motivation {
        val response: List<Motivation> = client.get(QUOTE).body()
        return response.first()
    }
}