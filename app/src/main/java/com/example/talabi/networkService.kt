package com.example.talabi

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object NetworkService {
    private val client = HttpClient()

    suspend fun fetchMessage(): String {
        return try {
            client.get("http://192.168.1.5/api/message").bodyAsText()
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}