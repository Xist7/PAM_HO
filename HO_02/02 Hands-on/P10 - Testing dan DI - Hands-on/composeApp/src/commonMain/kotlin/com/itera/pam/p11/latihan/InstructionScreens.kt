package com.itera.pam.p11.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Hands-on Pertemuan 11 berbeda dari pertemuan lain: latihan sungguhannya
// berupa FILE TEST di commonTest/ (topiknya adalah unit testing), bukan
// layar UI. Layar-layar di bawah ini murni instruksional — mengarahkan kamu
// ke file test yang harus dilengkapi.

@Composable
fun Handson1Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Unit Test Dasar — Counter")
        Text("Buka: composeApp/src/commonTest/kotlin/.../p11/latihan/CounterTest.kt")
        Text("Tiga test Counter menerapkan Arrange-Act-Assert; jalankan lewat IDE atau `./gradlew :composeApp:desktopTest`.")
    }
}

@Composable
fun Handson2Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: Test Double — Fake ClockProvider")
        Text("Buka: composeApp/src/commonTest/kotlin/.../p11/latihan/SessionLoggerTest.kt")
        Text("FakeClockProvider memakai TestTimeSource agar durasi sesi dapat diuji deterministik.")
    }
}

@Composable
fun Handson3Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 3: Koin DI Module")
        Text("Buka: composeApp/src/commonTest/kotlin/.../p11/latihan/KoinModuleTest.kt")
        Text("Test memulai Koin dengan appModule dan memverifikasi resolusi dependency.")
    }
}
