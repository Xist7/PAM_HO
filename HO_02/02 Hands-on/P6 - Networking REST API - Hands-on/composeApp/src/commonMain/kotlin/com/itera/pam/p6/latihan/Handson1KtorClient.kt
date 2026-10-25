package com.itera.pam.p6.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.call.body
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Hands-on 1: Ktor Client Dasar & GET Request
// Endpoint yang dipakai di seluruh hands-on Pertemuan 6:
// https://jsonplaceholder.typicode.com/users (fake REST API gratis untuk belajar,
// mengembalikan array JSON berisi data user).

@Serializable
data class RemoteUser(
    val id: Int,
    val name: String,
    val email: String
)

val client: HttpClient = HttpClient {
    expectSuccess = true
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

suspend fun fetchUsers(): List<RemoteUser> {
    return client.get("https://jsonplaceholder.typicode.com/users").body()
}

@Composable
fun Handson1Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Ktor Client Dasar & GET Request")
        Text("Handson1KtorClient menyediakan HttpClient dan GET request untuk mengambil daftar pengguna.")
    }
}
