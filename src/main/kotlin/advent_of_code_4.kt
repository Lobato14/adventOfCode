
// --- Día 4: Raspaditos ---

// Parte 1:

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


// Parte 2

// Justo cuando estás a punto de informar tus hallazgos al Elfo, uno de ustedes se da cuenta de que las reglas
// han sido impresas en la parte posterior de cada tarjeta todo este tiempo.

// No hay tal cosa como "puntos". En cambio, las tarjetas rasca y gana solo te hacen ganar más tarjetas rasca y
// gana, igual al número de números ganadores que tengas.

// Específicamente, ganas copias de las tarjetas rasca y gana debajo de la tarjeta ganadora igual al número de
// coincidencias. Entonces, si la tarjeta 10 tuviera 5 números coincidentes, ganarías una copia cada una de las
// tarjetas 11, 12, 13, 14 y 15.

// Las copias de las tarjetas rasca y gana se puntúan como las tarjetas rasca y gana normales y tienen el mismo
// número de tarjeta que la tarjeta que copiaron. Entonces, si ganas una copia de la tarjeta 10 y tiene 5
// números coincidentes, ganaría una copia de las mismas tarjetas que la tarjeta original 10 ganó: tarjetas 11,
// 12, 13, 14 y 15. Este proceso se repite hasta que ninguna de las copias te hace ganar más tarjetas.
// (Las tarjetas nunca te obligarán a copiar una tarjeta más allá del final de la mesa).

// Esta vez, el ejemplo anterior se desarrolla de manera diferente:

// Tarjeta 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53
// Tarjeta 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
// Tarjeta 3: 1 21 53 59 44 | 69 82 63 72 16 21 14 1
// Tarjeta 4: 41 92 73 84 69 | 59 84 76 51 58 5 54 83
// Tarjeta 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
// Tarjeta 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11

// La Tarjeta 1 tiene cuatro números coincidentes, por lo que ganas una copia cada una de las siguientes
// cuatro tarjetas: tarjetas 2, 3, 4 y 5.

// Tu tarjeta original 2 tiene dos números coincidentes, por lo que ganas una copia cada una de las tarjetas
// 3 y 4.

// Tu copia de la tarjeta 2 también gana una copia cada una de las tarjetas 3 y 4.

// Tus cuatro instancias de la tarjeta 3 (una original y tres copias) tienen dos números coincidentes,
// por lo que ganas cuatro copias cada una de las tarjetas 4 y 5.

// Tus ocho instancias de la tarjeta 4 (una original y siete copias) tienen un número coincidente, por lo que
// ganas ocho copias de la tarjeta 5.

// Tus catorce instancias de la tarjeta 5 (una original y trece copias) no tienen números coincidentes y no
// ganan más tarjetas.

// Tu única instancia de la tarjeta 6 (una original) no tiene números coincidentes y no gana más tarjetas.

// Una vez que se han procesado todas las originales y copias, terminas con 1 instancia de la tarjeta 1, 2
// instancias de la tarjeta 2, 4 instancias de la tarjeta 3, 8 instancias de la tarjeta 4, 14 instancias de la
// tarjeta 5 y 1 instancia de la tarjeta 6. En total, este montón de tarjetas rasca y gana te hace tener 30
// tarjetas rasca y gana en última instancia


import kotlin.math.pow

// kotlin.math.pow es parte de la biblioteca estándar de Kotlin y se utiliza para
// calcular la potencia de un número.

fun main() {

    val puzleParte1 = calcularPuntosParte1(readInput("Day04"))
    println("En total sus puntos valen $puzleParte1")
    val puzleParte2 = calcularPuntosParte2(readInput("Day04"))
    println("En total sus puntos valen $puzleParte2")
}


fun calcularPuntosParte1(cartas: List<String>): Int {

    val datos: MutableList<MutableList<Int>> = mutableListOf()
    var suma = 0
    for (carta in cartas) {
        // Dividir la cadena para obtener los números ganadores y los números que tienes
        val partes = carta.replace("  ", " ").split(": ")[1].split(" | ")

        // Agregar una lista vacía a los datos
        datos.add(mutableListOf())

        // Convertir los números de las cartas a listas de enteros
        val numerosGanadores = partes[0].split(" ").map { it.toInt() }
        val misNumeros = partes[1].split(" ").map { it.toInt() }

        // Calcular el puntaje para esta carta según las reglas de la parte 1
        var puntaje = 0
        for (numero in numerosGanadores) {
            if (misNumeros.contains(numero)) {
                puntaje += 1
            }
        }
        puntaje = 2.0.pow(puntaje - 1).toInt()

        // Agregar el puntaje a la suma total
        suma += puntaje
    }
    return suma
}


fun calcularPuntosParte2(cartas: List<String>): Int {
    // Lista de tripletes para almacenar los números de las cartas, mis números y el recuento
    val cartasConRecuento: MutableList<Triple<List<Int>, List<Int>, Int>> = mutableListOf()

    // Iterar sobre cada línea de entrada
    for (linea in cartas) {
        // Dividir la cadena para obtener los números ganadores y los números que tienes
        val partes = linea.replace("  ", " ").split(": ")[1].split(" | ")

        // Convertir los números de las cartas a listas de enteros
        val numerosGanadores = partes[0].split(" ").map { it.toInt() }
        val misNumeros = partes[1].split(" ").map { it.toInt() }

        // Agregar un nuevo triple a la lista con recuento inicializado en 1
        cartasConRecuento.add(Triple(numerosGanadores, misNumeros, 1))
    }

    // Iterar sobre cada carta con recuento
    for ((indice, carta) in cartasConRecuento.withIndex()) {
        // Calcular el puntaje para esta carta según las reglas de la parte 2
        var puntaje = 0
        for (numero in carta.first) {
            if (carta.second.contains(numero)) {
                puntaje += 1
            }
        }

        // Actualizar los recuentos de las cartas siguientes en la lista
        for (i in 1..puntaje) {
            // Copiar el triple actual y actualizar el tercer elemento (recuento)
            cartasConRecuento[i + indice] = cartasConRecuento[i + indice].copy(third = carta.third + cartasConRecuento[i + indice].third)
        }
    }

    // Calcular la suma total de recuentos
    var suma = 0
    cartasConRecuento.forEach { carta ->
        suma += carta.third
    }
    return suma
}