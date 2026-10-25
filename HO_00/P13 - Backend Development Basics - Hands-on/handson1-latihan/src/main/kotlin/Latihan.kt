import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 1: HTTP Server Sederhana
// Tugas: Buat HTTP server (pakai HttpServer bawaan JDK, tanpa framework)
// yang listen di port 8080 dan merespon GET /hello dengan teks
// "Hello, Kotlin Backend!" serta status code 200 OK.

fun main() {
    // TODO 1: Buat instance HttpServer yang listen di 0.0.0.0 port 8080
    // val server = HttpServer.create(???, 0)
    val server =HttpServer.create(InetSocketAddress(0.0.0.0, 8080), 0)

    // TODO 2: Daftarkan context "/hello" dengan handler yang:
    //   - menyiapkan response body "Hello, Kotlin Backend!" (dalam bytes)
    //   - memanggil exchange.sendResponseHeaders(200, body.size.toLong())
    //   - menulis body ke exchange.responseBody, lalu menutup stream-nya
    server.createContext("/hello") { exchange ->
        val response = "Hello, Kotlin Backend!"
        val responseBytes = response.toByteArray()
        exchange.sendResponseHeaders(200, responseBytes.size.toLong())
        exchange.responseBody.use { outputStream ->
            outputStream.write(responseBytes)
        }
    }

    // TODO 3: Jalankan server dengan server.start(), lalu print pesan
    // bahwa server sudah berjalan di port 8080
    server.start()
    println("Server berjalan di http://localhost:8080/hello")

    // Server sengaja dibuat berhenti otomatis setelah 10 detik supaya
    // proses tidak menggantung saat dijalankan lewat tombol Run di IDE.
    // Setelah TODO di atas selesai, uncomment baris di bawah ini:
    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
    
}
