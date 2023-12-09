//
// --- Día 4: Raspaditos ---
// La góndola te lleva hacia arriba. Extrañamente, sin embargo, el suelo no parece estar subiendo contigo; no estás
// escalando una montaña. Mientras el círculo de la Isla de la Nieve se aleja debajo de ti, ¡de repente aparece una
// nueva masa de tierra sobre ti! La góndola te lleva a la superficie de la nueva isla y se detiene bruscamente en
// la estación.

// Al salir de la góndola, lo primero que notas es que el aire aquí es mucho más cálido que en la Isla de la Nieve.
// También es bastante húmedo. ¿Es aquí donde está la fuente de agua?

// Lo siguiente que notas es a un Elfo sentado en el suelo al otro lado de la estación en lo que parece ser una
// pila de coloridas tarjetas cuadradas.

// "¡Oh! ¡Hola!" El Elfo corre hacia ti emocionado. "¿En qué puedo ayudarte?" Preguntas acerca de las fuentes de
// agua.

// "No estoy seguro; solo opero el ascensor de la góndola. Suena como algo que podríamos tener, después de
// todo, ¡esto es Isla Isla! Apuesto a que el jardinero lo sabría. Sin embargo, está en otra isla, la pequeña
// rodeada de agua, no la flotante. Realmente necesitamos idear un mejor esquema de nombres. Te diré qué: si
// puedes ayudarme con algo rápido, te prestaré mi bote y podrás visitar al jardinero. Recibí todas estas
// tarjetas de rasca y gana como regalo, pero no puedo entender qué he ganado."

// El Elfo te lleva hacia la pila de tarjetas coloridas. Allí descubres docenas de tarjetas de rasca y gana,
// todas con su cobertura opaca ya raspada. Al recoger una, parece que cada tarjeta tiene dos listas de números
// separadas por una barra vertical (|): una lista de números ganadores y luego una lista de números que tienes.
// Organizas la información en una tabla (tu entrada de rompecabezas).

// Según lo que el Elfo ha podido entender, debes averiguar cuáles de los números que tienes aparecen en la lista de
// números ganadores. La primera coincidencia hace que la tarjeta valga un punto y cada coincidencia después de la
// primera duplica el valor de puntos de esa tarjeta.

// Por ejemplo:

// Tarjeta 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53
// Tarjeta 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
// Tarjeta 3: 1 21 53 59 44 | 69 82 63 72 16 21 14 1
// Tarjeta 4: 41 92 73 84 69 | 59 84 76 51 58 5 54 83
// Tarjeta 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
// Tarjeta 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11

/// En el ejemplo anterior, la tarjeta 1 tiene cinco números ganadores (41, 48, 83, 86 y 17) y ocho números que
// tienes (83, 86, 6, 31, 17, 9, 48 y 53). ¡De los números que tienes, cuatro de ellos (48, 83, 17 y 86) son números
// ganadores! Esto significa que la tarjeta 1 vale 8 puntos (1 por la primera coincidencia, luego duplicado tres
// veces por cada una de las tres coincidencias después de la primera).

// La tarjeta 2 tiene dos números ganadores (32 y 61), por lo que vale 2 puntos.
// La tarjeta 3 tiene dos números ganadores (1 y 21), por lo que vale 2 puntos.
// La tarjeta 4 tiene un número ganador (84), por lo que vale 1 punto.
// La tarjeta 5 no tiene números ganadores, por lo que no vale ningún punto.
// La tarjeta 6 no tiene números ganadores, por lo que no vale ningún punto.
// Entonces, en este ejemplo, la pila de tarjetas de rasca y gana del Elfo vale 13 puntos.

// Siéntate en la gran pila de coloridas tarjetas. ¿Cuántos puntos valen en total?

fun main() {
    // Definir las cartas con los números ganadores y los números que tienes
    val cartas = listOf(
        "41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        " 1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    )

    // Calcular el total de puntos
    val puntosTotales = calcularPuntosTotales(cartas)

    // Mostrar el resultado
    println("El total de puntos de las cartas es: $puntosTotales")
}

// Función para calcular el total de puntos de las cartas
fun calcularPuntosTotales(cartas: List<String>): Int {
    var contadorPuntosTotales = 0

    // Iterar sobre cada carta
    for (carta in cartas) {
        // Dividir la carta en números ganadores y números que tienes
        println("Carta actual: $carta")
        val partes = carta.trim().split(" | ")
        if (partes.size == 2) {
            val numerosGanadores = partes[0].split(" ").map { it.toInt() }
            val misNumeros = partes[1].split(" ").map { it.toInt() }

            // Calcular los puntos para esta carta
            val puntajeCarta = calcularPuntoDeLaCarta(numerosGanadores, misNumeros)

            // Agregar los puntos al total
            contadorPuntosTotales += puntajeCarta
        }
    }

    return contadorPuntosTotales
}


// Función para calcular los puntos de una carta
fun calcularPuntoDeLaCarta(numerosGanadores: List<Int>, misNumeros: List<Int>): Int {
    var puntosDelaCarta = 0
    // Iterar sobre cada número que tienes
    for (numero in misNumeros) {
        // Verificar si el número está en la lista de números ganadores
        if (numero in numerosGanadores) {
            // Calcular los puntos para este número y agregarlos al total de la carta
            puntosDelaCarta += 1 shl numerosGanadores.indexOf(numero)
        }
    }

    return puntosDelaCarta
}