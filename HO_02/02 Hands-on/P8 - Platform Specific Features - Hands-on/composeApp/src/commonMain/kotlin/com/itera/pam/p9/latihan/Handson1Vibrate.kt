package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p9.getPlatformName
import com.itera.pam.p9.vibrate

// Hands-on 1: expect/actual Lanjutan — Getar Perangkat
// `vibrate()` sudah diimplementasikan lewat expect/actual (lihat Platform.kt
// dan actual-nya di androidMain/iosMain/desktopMain) — tugasmu di sini hanya
// memanggilnya dari tombol di bawah.
//
@Composable
fun Handson1Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Getar Perangkat (expect/actual)")
        Button(onClick = { vibrate() }) {
            Text("Getar!")
        }
        Text(
            "Platform: ${getPlatformName()}. Android dan iOS bergetar; " +
                "Desktop hanya mencatat log."
        )
    }
}
