// Hands-on 1: Class & Inheritance
// Tugas: Buat hierarki class kendaraan menggunakan open class, primary constructor,
// dan override fungsi. Vehicle adalah base class, Car dan Motorcycle adalah turunannya.

// TODO 1: Jadikan class ini "open" agar bisa diturunkan (inherited).
open class Vehicle(val name: String, val maxSpeed: Int) {

    // TODO 2: Jadikan fungsi ini "open" agar bisa di-override oleh subclass.
    open fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h"
    }
}

// TODO 3: Buat class Car sebagai turunan dari Vehicle.
class Car(name: String, val numberOfDoors: Int) : Vehicle(name, maxSpeed = 180) {
    override fun describe(): String {
        return "${super.describe()} dan punya $numberOfDoors pintu"
    }
}

// TODO 4: Buat class Motorcycle sebagai turunan dari Vehicle.
class Motorcycle(name: String, val hasSidecar: Boolean) : Vehicle(name, maxSpeed = 220) {
    override fun describe(): String {
        val sidecarText = if (hasSidecar) "dengan sidecar" else "tanpa sidecar"
        return "${super.describe()} ($sidecarText)"
    }
}

fun main() {
    val vehicles = listOf<Vehicle>(
        // TODO 5: Buat 1 instance Car dan 1 instance Motorcycle, masukkan ke list ini.
        Car("Toyota", numberOfDoors = 4),
        Motorcycle("Ninja", hasSidecar = false)
    )

    // Polymorphism: setiap elemen dipanggil lewat interface Vehicle,
    // tapi describe() yang jalan adalah versi milik subclass masing-masing.
    vehicles.forEach { println(it.describe()) }
}
