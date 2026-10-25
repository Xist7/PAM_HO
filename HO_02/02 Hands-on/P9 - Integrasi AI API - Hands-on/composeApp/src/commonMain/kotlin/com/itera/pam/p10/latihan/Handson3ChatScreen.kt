package com.itera.pam.p10.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p10.MockAiRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

// Hands-on 3: AI-Powered Chat UI
// Tugas: buat UI chat sederhana: TextField input, tombol "Kirim", daftar
// bubble pesan (isUser: Boolean, text: String) yang bisa di-scroll, dan
// indikator loading saat menunggu jawaban dari AiRepository.ask()
// (pakai MockAiRepository() secara default).
//
data class ChatMessage(val isUser: Boolean, val text: String)

@Composable
fun Handson3Screen() {
    var messages by remember { mutableStateOf(emptyList<ChatMessage>()) }
    var input by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val repository = remember { MockAiRepository() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Hands-on 3: AI-Powered Chat UI")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 480.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
                ) {
                    Surface(
                        color = if (message.isUser) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(message.text, modifier = Modifier.padding(12.dp))
                    }
                }
            }
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Pesan") },
                modifier = Modifier.weight(1f)
            )
            Button(
                enabled = input.isNotBlank() && !isLoading,
                onClick = {
                    val prompt = input.trim()
                    messages = messages + ChatMessage(isUser = true, text = prompt)
                    input = ""
                    isLoading = true
                    scope.launch {
                        try {
                            val answer = repository.ask(prompt).getOrElse {
                                "Error: ${it.message ?: "Tidak dapat memperoleh jawaban."}"
                            }
                            messages = messages + ChatMessage(isUser = false, text = answer)
                        } catch (exception: CancellationException) {
                            throw exception
                        } catch (exception: Exception) {
                            messages = messages + ChatMessage(
                                isUser = false,
                                text = "Error: ${exception.message ?: "Tidak dapat memperoleh jawaban."}"
                            )
                        } finally {
                            isLoading = false
                        }
                    }
                }
            ) {
                Text("Kirim")
            }
        }
    }
}
