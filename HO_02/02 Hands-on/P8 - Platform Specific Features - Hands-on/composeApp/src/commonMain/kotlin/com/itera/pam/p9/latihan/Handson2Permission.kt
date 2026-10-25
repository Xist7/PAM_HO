package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p9.hasCameraPermission

// Hands-on 2: Permission Check (Platform-Aware)
// `hasCameraPermission()` sudah diimplementasikan lewat expect/actual: cek
// ASLI di Android (ContextCompat.checkSelfPermission), stub `true` di
// iOS/Desktop karena model permission-nya berbeda dan di luar cakupan
// hands-on ini.
//
@Composable
fun Handson2Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: Permission Check")
        val hasPermission = hasCameraPermission()
        Text(
            if (hasPermission) {
                "Status izin kamera: diberikan."
            } else {
                "Status izin kamera: belum diberikan."
            }
        )
        Button(onClick = { /* simulasi tombol minta izin, di luar cakupan hands-on ini */ }) {
            Text("Simulasikan Minta Izin")
        }
    }
}
