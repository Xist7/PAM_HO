package com.itera.pam.p11.latihan

import com.itera.pam.p11.ClockProvider
import com.itera.pam.p11.SessionLogger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TestTimeSource
import kotlin.time.TimeSource

// Hands-on 2: Test Double — Fake ClockProvider
// Masalah: jika SessionLogger diuji dengan waktu ASLI (TimeSource.Monotonic
// sungguhan), hasil elapsed time-nya tidak bisa diprediksi persis -> test
// jadi flaky (kadang lolos, kadang gagal, tergantung kecepatan mesin).
// Solusi: gunakan TestTimeSource dari kotlin.time — "jam palsu" yang hanya
// maju kalau kita suruh maju secara manual, sehingga hasilnya deterministik.

class FakeClockProvider(val source: TestTimeSource = TestTimeSource()) :
    ClockProvider,
    TimeSource by source

class SessionLoggerTest {

    @Test
    fun endSessionMengembalikanDurasiYangDimajukanPadaFakeClock() {
        val fakeClock = FakeClockProvider()
        val logger = SessionLogger(fakeClock)
        logger.startSession()
        fakeClock.source += 5.seconds

        assertEquals(5.seconds, logger.endSession())
    }
}
