package com.itera.pam.p4.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Latihan 1: Counter App dengan State (slide P4 hal. 29)
// Tugas: Buat counter dengan increment, decrement, dan reset.
//
// Checklist:
// [x] State dengan remember
// [x] mutableStateOf(0)
// [x] Text untuk display
// [x] Button increment (+1)
// [x] Button decrement (-1)
// [x] Button reset (ke 0)
// [x] Disable decrement jika 0

@Composable
fun Handson1Screen() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Latihan 1: Counter App dengan State")

        Text("Count: $count")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { count-- }, enabled = count > 0) {
                Text("-")
            }
            Button(onClick = { count++ }) {
                Text("+")
            }
            Button(onClick = { count = 0 }) {
                Text("Reset")
            }
        }
    }
}
