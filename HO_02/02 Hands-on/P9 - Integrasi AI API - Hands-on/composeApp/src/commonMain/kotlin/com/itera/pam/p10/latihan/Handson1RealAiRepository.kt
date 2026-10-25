package com.itera.pam.p10.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p10.AiRepository
import com.itera.pam.p10.GEMINI_API_KEY
import com.itera.pam.p10.GEMINI_BASE_URL
import com.itera.pam.p10.GEMINI_MODEL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Hands-on 1: Panggil AI API Dasar (Gemini)
// Tugas: lengkapi RealAiRepository.ask() supaya benar-benar memanggil
// Gemini API lewat Ktor, lalu parsing responsnya menjadi teks jawaban.
//
// Bentuk request Gemini (POST, JSON):
//   { "contents": [ { "parts": [ { "text": "<prompt>" } ] } ] }
// Bentuk response (disederhanakan):
//   { "candidates": [ { "content": { "parts": [ { "text": "<jawaban>" } ] } } ] }

@Serializable
data class GeminiPart(val text: String)

@Serializable
data class GeminiContent(val parts: List<GeminiPart>)

@Serializable
data class GeminiRequest(val contents: List<GeminiContent>)

@Serializable
data class GeminiCandidate(val content: GeminiContent? = null)

@Serializable
data class GeminiResponse(val candidates: List<GeminiCandidate>? = null)

class RealAiRepository(
    private val client: HttpClient = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
) : AiRepository {

    override suspend fun ask(prompt: String): Result<String> {
        if (GEMINI_API_KEY.isBlank() || GEMINI_API_KEY == "YOUR_API_KEY_HERE") {
            return Result.failure(IllegalStateException("Isi GEMINI_API_KEY di ApiConfig.kt sebelum memanggil Gemini."))
        }

        return try {
            val requestBody = GeminiRequest(
                contents = listOf(
                    GeminiContent(parts = listOf(GeminiPart(text = prompt)))
                )
            )
            val geminiResponse = client.post(
                "$GEMINI_BASE_URL/$GEMINI_MODEL:generateContent"
            ) {
                parameter("key", GEMINI_API_KEY)
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.body<GeminiResponse>()
            val text = geminiResponse.candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text
                ?: return Result.failure(
                    IllegalStateException("Tidak ada jawaban dari Gemini (mungkin kena safety filter).")
                )
            Result.success(text)
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}

@Composable
fun Handson1Screen() {
    var input by remember { mutableStateOf("Apa itu Kotlin Multiplatform? Jawab singkat.") }
    var result by remember { mutableStateOf("(belum ada jawaban)") }
    val scope = rememberCoroutineScope()
    val repository = remember { RealAiRepository() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Panggil Gemini API")
        Text("Butuh GEMINI_API_KEY asli di ApiConfig.kt — sebelum itu diisi, tombol ini akan gagal (itu wajar, lihat README bagian Troubleshooting).")
        OutlinedTextField(value = input, onValueChange = { input = it }, label = { Text("Pertanyaan") })
        Button(onClick = {
            scope.launch {
                result = repository.ask(input).fold(
                    onSuccess = { it },
                    onFailure = { "Error: ${it.message}" }
                )
            }
        }) {
            Text("Tanya AI (Real)")
        }
        Text(result)
    }
}
