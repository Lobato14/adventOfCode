// --- Día 13: Punto de Incidencia ---

// -- Parte 1 -----

// Con tu ayuda, el equipo de aguas termales encuentra un manantial adecuado que te lanza de manera
// ordenada y precisa hasta el borde de la Isla de Lava.

// Solo hay un problema: no ves lava.

// Ves mucha ceniza y roca ígnea; incluso hay lo que parecen ser montañas grises dispersas por todas
// partes. Después de un tiempo, te diriges hacia un grupo cercano de montañas solo para descubrir que
// el valle entre ellas está completamente lleno de grandes espejos. La mayoría de los espejos parecen
// estar alineados de manera consistente; ¿quizás deberías dirigirte en esa dirección?

// A medida que avanzas por el valle de espejos, descubres que varios de ellos han caído de los grandes
// marcos de metal que los mantienen en su lugar. Los espejos son extremadamente planos y brillantes, y
// muchos de los espejos caídos se han alojado en la ceniza a ángulos extraños. Debido a que el terreno
// es de un solo color, es difícil decir dónde es seguro caminar o dónde te vas a encontrar con un
// espejo.

// Registra los patrones de ceniza (.) y rocas (#) que ves mientras caminas (tu entrada de rompecabezas)
// ; tal vez al analizar cuidadosamente estos patrones, ¡puedas descubrir dónde están los espejos!

// Por ejemplo:

// #.##..##.
// ..#.##.#.
// ##......#
// ##......#
// ..#.##.#.
// ..##..##.
// #.#.##.#.

// #...##..#
// #....#..#
// ..##..###
// #####.##.
// #####.##.
// ..##..###
// #....#..#

// Para encontrar el reflejo en cada patrón, necesitas encontrar una reflexión perfecta ya sea a través
// de una línea horizontal entre dos filas o a través de una línea vertical entre dos columnas.

// En el primer patrón, la reflexión es a través de una línea vertical entre dos columnas; las flechas
// en cada una de las dos columnas apuntan a la línea entre las columnas:

// 123456789
// ><
// #.##..##.
// ..#.##.#.
// ##......#
// ##......#
// ..#.##.#.
// ..##..##.
// #.#.##.#.
// ><
// 123456789

// En este patrón, la línea de reflexión es la línea vertical entre las columnas 5 y 6. Como la línea
// vertical no está perfectamente en el medio del patrón, parte del patrón (columna 1) no tiene a dónde
// reflejarse y se puede ignorar; cada otra columna tiene una columna reflejada dentro del patrón y debe
// coincidir exactamente: la columna 2 coincide con la columna 9, la columna 3 coincide con la 8, la 4
// coincide con la 7 y la 5 coincide con la 6.

// El segundo patrón se refleja a través de una línea horizontal en su lugar:

//1 #...##..# 1
//2 #....#..# 2
//3 ..##..### 3
//4v#####.##.v4
//5^#####.##.^5
//6 ..##..### 6
//7 #....#..# 7

// Este patrón se refleja a través de la línea horizontal entre las filas 4 y 5. La fila 1 se
// reflejaría con una fila hipotética 8, pero como eso no está en el patrón, la fila 1 no necesita
// coincidir con nada. Las filas restantes coinciden: la fila 2 coincide con la fila 7, la fila 3
// coincide con la fila 6 y la fila 4 coincide con la fila 5.

// Para resumir tus notas de patrones, suma el número de columnas a la izquierda de cada línea de
// reflexión vertical; a eso, también suma 100 multiplicado por el número de filas por encima de
// cada línea de reflexión horizontal. En el ejemplo anterior, la línea vertical del primer patrón
// tiene 5 columnas a su izquierda y la línea horizontal del segundo patrón tiene 4 filas por encima
// de ella, un total de 405.

// Encuentra la línea de reflexión en cada uno de los patrones en tus notas. ¿Qué número obtienes
// después de resumir todas tus notas?

import kotlin.math.min

fun main() {
    // Entrada
    val input = readInput("Day13").joinToString("\n")
    // Procesamiento
    val resultadoParte1 = calcularParte1(input)
    val resultadoParte2 = calcularParte2(input)
    // Salida
    println("Se obtiene el $resultadoParte1")
    println("Se obtiene el $resultadoParte2")
}

fun calcularParte1(input: String): Long {
    val seccionesDivididas = input.split("\n\n")
    val transpuestas = mutableListOf<String>()
    var total = 0L

    for (seccion in seccionesDivididas) {
        val listaSeccion = seccion.split("\n")
        total += obtenerPuntoDeSeparacion(listaSeccion)
        transpuestas.add(transponer(listaSeccion))
    }

    for (seccion in transpuestas) {
        val listaSeccion = seccion.split("\n")
        total += obtenerPuntoDeSeparacion(listaSeccion) * 100
    }

    return total
}

// --- Parte 2 ----

// Continuas caminando por el valle de los espejos y, ¡ZAS! Chocas directamente con uno. Con suerte,
// nadie estaba mirando, porque eso debe haber sido bastante vergonzoso.

// Tras una inspección más cercana, descubres que cada espejo tiene exactamente una mancha: exactamente
// un . o # debería ser del tipo opuesto.

// En cada patrón, deberás localizar y corregir la mancha que provoca que una línea de reflexión
// diferente sea válida. (La antigua línea de reflexión no necesariamente seguirá siendo válida después
// de corregir la mancha).

// Aquí está el ejemplo nuevamente:

// #.##..##.
// ..#.##.#.
// ##......#
// ##......#
// ..#.##.#.
// ..##..##.
// #.#.##.#.

// #...##..#
// #....#..#
// ..##..###
// #####.##.
// #####.##.
// ..##..###
// #....#..#

// La mancha en el primer patrón está en la esquina superior izquierda. Si el # en la esquina superior
// izquierda fuera en su lugar un ., tendría una línea de reflexión horizontal diferente:

// 1 ..##..##. 1
// 2 ..#.##.#. 2
// 3v##......#v3
// 4^##......#^4
// 5 ..#.##.#. 5
// 6 ..##..##. 6
// 7 #.#.##.#. 7

// Con la mancha corregida en la esquina superior izquierda, ahora existe una nueva línea de
// reflexión horizontal entre las filas 3 y 4. La fila 7 no tiene una fila reflejada correspondiente
// y se puede ignorar, pero todas las demás filas coinciden exactamente: la fila 1 coincide con la
// fila 6, la fila 2 coincide con la fila 5, y la fila 3 coincide con la fila 4.

// En el segundo patrón, la mancha puede corregirse cambiando el quinto símbolo en la fila 2 de .
// a #:

// 1v#...##..#v1
// 2^#...##..#^2
// 3 ..##..### 3
// 4 #####.##. 4
// 5 #####.##. 5
// 6 ..##..### 6
// 7 #....#..# 7

// Ahora, el patrón tiene una línea de reflexión horizontal diferente entre las filas 1 y 2.

// Resume tus notas como antes, pero utiliza las nuevas líneas de reflexión diferentes. En este ejemplo,
// la nueva línea horizontal del primer patrón tiene 3 filas por encima y la nueva línea horizontal del
// segundo patrón tiene 1 fila por encima, resumiendo en el valor 400.

// En cada patrón, corrige la mancha y encuentra la línea de reflexión diferente. ¿Qué número obtienes
// después de resumir la nueva línea de reflexión en cada patrón en tus notas?

fun calcularParte2(input: String): Long {

    val seccionesDivididas = input.split("\n\n")
    val transpuestas = mutableListOf<String>()
    var total = 0L

    for (seccion in seccionesDivididas) {
        val listaSeccion = seccion.split("\n")
        total += obtenerPuntoDeSeparacionParte2(listaSeccion) * 100
        transpuestas.add(transponer(listaSeccion))
    }

    for (seccion in transpuestas) {
        val listaSeccion = seccion.split("\n")
        total += obtenerPuntoDeSeparacionParte2(listaSeccion)
    }

    return total
}

fun obtenerPuntoDeSeparacionParte2(seccion: List<String>): Int {
    seccion.indices.forEach {
        val izquierda = seccion.subList(0, it).reversed()
        val derecha = seccion.subList(it, seccion.size)

        val combinadas = izquierda.zip(derecha)
        val diferenciasTotales = combinadas.sumOf { (b, a) ->
            if (a == b) 0
            else a.obtenerDiferencias(b) }

        if (diferenciasTotales == 1) return it
    }
    return 0
}

fun obtenerPuntoDeSeparacion(seccion: List<String>): Long {
    val puntosDeSeparacion = (0..seccion[0].length).toMutableList()

    for (linea in seccion) {
        val iterador = puntosDeSeparacion.iterator()

        while (iterador.hasNext()) {

            val punto = iterador.next()
            var izquierda = linea.substring(0, punto)
            var derecha = linea.substring(punto)
            val ladoMasCorto = min(izquierda.length, derecha.length)

            if (ladoMasCorto == 0) {
                iterador.remove()
                continue
            }

            izquierda = izquierda.reversed().take(ladoMasCorto)
            derecha = derecha.take(ladoMasCorto)

            if (!izquierda.equals(derecha)) {
                iterador.remove()
            }
        }
    }

    if (puntosDeSeparacion.isEmpty()) {
        return 0L
    }

    return puntosDeSeparacion.sum().toLong()
}

fun transponer(seccion: List<String>): String {
    val nuevaLista = mutableListOf<String>()

    for (i in seccion[0].indices) {
        var fila = ""
        for (j in seccion.indices) {
            fila += seccion[j][i]
        }
        nuevaLista.add(fila)
    }

    return nuevaLista.joinToString("\n")
}

fun String.obtenerDiferencias(otra: String): Int {
    return this.zip(otra).count { it.first != it.second }
}