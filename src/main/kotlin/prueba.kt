import java.io.File

fun main() {
    val platform = readInput().lines().map { it.toCharArray().toTypedArray() }.toTypedArray()
    var weight = 0

    for (y in platform.indices) {
        for (x in platform[y].indices) {
            if (platform[y][x] == 'O') {
                platform[y][x] = '.'
                var i = y - 1
                while (i >= 0 && platform[i][x] == '.') {
                    i--
                }
                platform[i + 1][x] = 'O'
                weight += platform.size - i - 1
            }
        }
    }

    println(weight)
}

fun readInput(): String {
    // Ajusta la ruta del archivo según sea necesario
    return File("src/Day14.txt").readText().trim()
}




