fun main() {
    // Definir los datos de entrada
    val juegos = listOf(
        listOf(3 to "blue", 4 to "red"),
        listOf(1 to "blue", 2 to "green", 6 to "blue"),
        listOf(2 to "green"),
        listOf(1 to "red", 2 to "green"),
        listOf(3 to "green", 4 to "blue", 1 to "red"),
        listOf(1 to "green", 1 to "blue"),
        listOf(8 to "green", 6 to "blue", 20 to "red"),
        listOf(5 to "blue", 4 to "red", 13 to "green"),
        listOf(5 to "green", 1 to "red"),
        listOf(1 to "green", 3 to "red", 6 to "blue"),
        listOf(3 to "green", 6 to "red"),
        listOf(3 to "green", 15 to "blue", 14 to "red"),
        listOf(6 to "red", 1 to "blue", 3 to "green"),
        listOf(2 to "blue", 1 to "red", 2 to "green")
    )

    // Definir los cubos requeridos
    val rojosRequeridos = 12
    val verdesRequeridos = 13
    val azulesRequeridos = 14

    // Calcular la suma de IDs de juegos posibles
    var sumaIDs = 0

    for ((index, conjunto) in juegos.withIndex()) {
        val id = index + 1
        if (esPosible(conjunto, rojosRequeridos, verdesRequeridos, azulesRequeridos)) {
            sumaIDs += id
        }
    }

    // Imprimir el resultado
    println("La suma de los IDs de los juegos posibles es: $sumaIDs")
}

fun esPosible(conjunto: List<Pair<Int, String>>, rojos: Int, verdes: Int, azules: Int): Boolean {
    val cubosRequeridos = mapOf("red" to rojos, "green" to verdes, "blue" to azules)
    val cubosRevelados = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)

    for ((cantidad, color) in conjunto) {
        cubosRevelados[color] = cubosRevelados[color]!! + cantidad
        if (cubosRevelados[color]!! > cubosRequeridos[color]!!) {
            return false
        }
    }

    return true
}