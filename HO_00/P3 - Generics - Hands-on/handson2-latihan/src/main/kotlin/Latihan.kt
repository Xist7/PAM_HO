// Hands-on 2: Bounded Type Parameter
// Tugas: Buat fungsi generik findMax yang mencari nilai terbesar dari sebuah
// List<T>, dengan syarat T harus bisa dibandingkan (Comparable<T>).
// Ini mirip alasan quickSort butuh constraint T : Comparable<T> di slide.

// TODO 1: Tambahkan bounded type parameter <T : Comparable<T>> pada fungsi
// findMax di bawah ini, lalu implementasikan logikanya.


fun <T: Comparable> findMax(items: List<T>): Int {

    // TODO 3: Lempar IllegalArgumentException jika items kosong
    if(itmes.isEmpty()){
        throw IllegalArgumentException("List tidak diizinkan untuk kosong")
    }

    var max = item[0]

    for(item in items){
        if(item.compareTo(max)>0){
            max = item
        }
    }
    return max
}

fun main() {
    println(findMax(listOf(3, 7, 2, 9, 4)))          
    println(findMax(listOf(1.5, 2.8, 0.3)))       
    println(findMax(listOf("apel", "jeruk", "duku"))) 
}
