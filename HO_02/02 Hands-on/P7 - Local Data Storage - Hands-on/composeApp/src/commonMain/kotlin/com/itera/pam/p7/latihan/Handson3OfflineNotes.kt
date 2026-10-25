package com.itera.pam.p7.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p7.db.AppDatabase

// Hands-on 3: Offline-First — Tampilkan & Hapus Catatan
// Gunakan CatatanRepository dari Handson2Repository.kt (yang sudah kamu
// lengkapi) untuk menampilkan daftar catatan dalam LazyColumn, form untuk
// menambah catatan baru, dan tombol hapus per item.
//
@Composable
fun Handson3Screen(database: AppDatabase) {
    val repository = remember(database) { CatatanRepository(database) }
    val catatanList by repository.getAll().collectAsState(initial = emptyList())
    var judul by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Hands-on 3: Offline Notes")
        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = isi,
            onValueChange = { isi = it },
            label = { Text("Isi catatan") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                repository.tambah(judul.trim(), isi.trim())
                judul = ""
                isi = ""
            },
            enabled = judul.isNotBlank() && isi.isNotBlank()
        ) {
            Text("Tambah")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 360.dp)
        ) {
            items(catatanList, key = { it.id }) { catatan ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(catatan.judul)
                        Text(catatan.isi)
                    }
                    Button(onClick = { repository.hapus(catatan.id) }) {
                        Text("Hapus")
                    }
                }
            }
        }
    }
}
