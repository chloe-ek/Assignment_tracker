package com.bcit.assignmenttracker.data.remote


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import com.google.gson.Gson
import com.google.gson.JsonObject

class MotivationRemoteSource(private val client: HttpClient) {

    suspend fun getMotivation(): Motivation {
        val response = client.get(QUOTE)
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Motivation::class.java)
    }
}