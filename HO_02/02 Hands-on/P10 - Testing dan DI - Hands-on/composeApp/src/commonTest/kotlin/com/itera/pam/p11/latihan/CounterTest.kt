package com.itera.pam.p11.latihan

import com.itera.pam.p11.Counter
import kotlin.test.Test
import kotlin.test.assertEquals

// Hands-on 1: Unit Test Dasar — Counter (Arrange-Act-Assert)
// Tugas: lengkapi 3 test di bawah mengikuti pola AAA.
// Jalankan lewat IDE (klik ikon ▶️ di sebelah class/fun) atau
// `./gradlew :composeApp:desktopTest`.
class CounterTest {

    @Test
    fun incrementMenambahNilaiCounter() {
        // Arrange
        val counter = Counter()

        // Act
        counter.increment()
        counter.increment()

        // Assert
        assertEquals(2, counter.value.value)
    }

    @Test
    fun decrementTidakBolehMembuatNilaiDiBawahNol() {
        // Arrange
        val counter = Counter()

        // Act
        counter.decrement()

        // Assert
        assertEquals(0, counter.value.value)
    }

    @Test
    fun resetMengembalikanNilaiKeNol() {
        // Arrange
        val counter = Counter()
        counter.increment()
        counter.increment()
        counter.increment()

        // Act
        counter.reset()

        // Assert
        assertEquals(0, counter.value.value)
    }
}
