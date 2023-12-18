// --- Día 11: Expansión cósmica ---

// ---- Parte 1 -----

// Continúas siguiendo las señales hacia "Aguas Termales" y finalmente te encuentras
// con un observatorio. El elfo que está dentro resulta ser un investigador que estudia
// la expansión cósmica utilizando el telescopio gigante que hay aquí.

// No sabe nada de las piezas perdidas de la máquina; sólo está de visita para este
// proyecto de investigación. Sin embargo, confirma que las aguas termales son la zona
// más cercana en la que es probable que haya gente; incluso te llevará directamente
// allí cuando termine con el análisis de las observaciones de hoy.

// ¿Quizá puedas ayudarle con el análisis para acelerar las cosas?

// El investigador ha recogido un montón de datos y los ha compilado en una sola imagen
// gigante (la entrada de tu puzzle). La imagen incluye espacio vacío (.) y galaxias (#).
// Por ejemplo:

// ...#......
// .......#..
// #.........
// ..........
// ......#...
// .#........
// .........#
// ..........
// .......#..
// #...#.....

// El investigador intenta averiguar la suma de las longitudes del camino más corto entre
// cada par de galaxias. Sin embargo, hay una trampa: el universo se expandió en el tiempo
// que tardó la luz de esas galaxias en llegar al observatorio.

// Debido a algo relacionado con los efectos gravitatorios, sólo parte del espacio se
// expande. De hecho, el resultado es que cualquier fila o columna que no contenga
// galaxias debería ser el doble de grande.

// En el ejemplo anterior, tres columnas y dos filas no contienen galaxias:

//   v v v
// ...#......
// .......#..
// #.........
//>..........<
// ......#...
// .#........
// .........#
//>..........<
// .......#..
// #...#.....
//   ^ ^ ^

// Estas filas y columnas tienen que ser el doble de grandes; por tanto, el resultado
// de la expansión cósmica tiene este aspecto:

// ....#........
// .........#...
// #............
// .............
// .............
// ........#....
// .#...........
// ............#
// .............
// .............
// .........#...
// #....#.......

// Equipado con este universo expandido, se puede encontrar el camino más corto entre
// cada par de galaxias. Esto puede ayudar a asignar a cada galaxia un número único:

// ....1........
// .........2...
// 3............
// .............
// .............
// ........4....
// .5...........
// ............6
// .............
// .............
// .........7...
// 8....9.......

// En estas 9 galaxias hay 36 pares. Cuenta cada par sólo una vez; el orden dentro del par
// no importa. Para cada par, encontrar cualquier camino más corto entre las dos galaxias
// utilizando sólo los pasos que se mueven hacia arriba, abajo, izquierda o derecha
// exactamente una . o # a la vez. (Se permite que el camino más corto entre dos galaxias
// pase por otra galaxia).

// Por ejemplo, aquí se muestra uno de los caminos más cortos entre las galaxias 5 y 9:

// ....1........
// .........2...
// 3............
// .............
// .............
// ........4....
// .5...........
// .##.........6
// ..##.........
// ...##........
// ....##...7...
// 8....9.......

// Este camino tiene longitud 9 porque se necesitan como mínimo nueve pasos para ir de la
// galaxia 5 a la galaxia 9 (los ocho lugares marcados con # más el paso a la propia
// galaxia 9). He aquí otros ejemplos de caminos más cortos:

// Entre la galaxia 1 y la galaxia 7: 15
// Entre la galaxia 3 y la galaxia 6: 17
// nEntre la galaxia 8 y la galaxia 9: 5

// En este ejemplo, después de expandir el universo, la suma del camino más corto entre
// los 36 pares de galaxias es 374.

// Expande el universo y luego halla la longitud del camino más corto entre cada par de
// galaxias. ¿Cuál es la suma de estas longitudes?

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

fun main() {
    val input = readInput("Day11")

    val filasVacias = encontrarFilasVacias(input)
    val columnasVacias = encontrarColumnasVacias(input)
    val galaxias = encontrarGalaxias(input)

    val tamanoExpansion1 = 1L
    val tamanoExpansion2 = 1000000L - 1
    var sumaCaminoMasCorto1 = 0L
    var sumaCaminoMasCorto2 = 0L

    // Bucle para comparar todas las combinaciones de pares de galaxias
    for (i in galaxias.indices) {
        for (j in i + 1 until galaxias.size) {
            val columnasExpandidas = contarColumnasExpandidas(galaxias[i], galaxias[j], columnasVacias)
            val filasExpandidas = contarFilasExpandidas(galaxias[i], galaxias[j], filasVacias)
            // Calcular distancia entre galaxias
            val camino = calcularCamino(galaxias[i], galaxias[j])
            // Sumar distancias considerando expansiones y agregar al resultado final
            sumaCaminoMasCorto1 += camino + filasExpandidas * tamanoExpansion1 + columnasExpandidas * tamanoExpansion1
            sumaCaminoMasCorto2 += camino + filasExpandidas * tamanoExpansion2 + columnasExpandidas * tamanoExpansion2
        }
    }

    val solucion1 = sumaCaminoMasCorto1.toString()
    val solucion2 = sumaCaminoMasCorto2.toString()

    println("La suma de las longitudes es $solucion1")
    println("La suma de estas longitudes es $solucion2")
}

// Encontrar filas vacías en la entrada
fun encontrarFilasVacias(input: List<String>): List<Int> {
    val filasVacias = mutableListOf<Int>()
    for (y in input.indices) {
        if (input[y].all { it == '.' }) {
            filasVacias.add(y)
        }
    }
    return filasVacias
}

// Encontrar columnas vacías en la entrada
fun encontrarColumnasVacias(input: List<String>): List<Int> {
    val columnasVacias = mutableListOf<Int>()
    for (x in input[0].indices) {
        if (input.map { linea -> linea[x] }.all { it == '.' }) {
            columnasVacias.add(x)
        }
    }
    return columnasVacias
}

// Encontrar coordenadas de las galaxias en la entrada
fun encontrarGalaxias(input: List<String>): List<Pair<Int, Int>> {
    val galaxias = mutableListOf<Pair<Int, Int>>()
    for (y in input.indices) {
        for (x in input[0].indices) {
            if (input[y][x] == '#') galaxias.add(Pair(y, x))
        }
    }
    return galaxias
}

// Contar columnas expandidas entre dos galaxias
fun contarColumnasExpandidas(galaxia1: Pair<Int, Int>, galaxia2: Pair<Int, Int>, columnasVacias: List<Int>): Long {
    return columnasVacias.count {
        it > min(galaxia1.second, galaxia2.second)
                && it < max(galaxia1.second, galaxia2.second)
    }.toLong()
}

// Contar filas expandidas entre dos galaxias
fun contarFilasExpandidas(galaxia1: Pair<Int, Int>, galaxia2: Pair<Int, Int>, filasVacias: List<Int>): Long {
    return filasVacias.count {
        it > min(galaxia1.first, galaxia2.first)
                && it < max(galaxia1.first, galaxia2.first)
    }.toLong()
}

// Calcular la distancia entre dos galaxias
fun calcularCamino(galaxia1: Pair<Int, Int>, galaxia2: Pair<Int, Int>): Long {
    return (abs(galaxia1.second - galaxia2.second) + abs(galaxia1.first - galaxia2.first)).toLong()
}

// ---- Parte 2 -----

// Las galaxias son mucho más antiguas (y, por lo tanto, mucho más distantes) de lo que
// el investigador estimó inicialmente.

// Ahora, en lugar de la expansión que realizaste anteriormente, haz que cada fila o
// columna vacía sea un millón de veces más grande. Es decir, cada fila vacía debe
// reemplazarse con 1000000 filas vacías, y cada columna vacía debe reemplazarse con
// 1000000 columnas vacías.

// (En el ejemplo anterior, si cada fila o columna vacía fuera simplemente 10 veces
// más grande, la suma de las rutas más cortas entre cada par de galaxias sería 1030.
// Si cada fila o columna vacía fuera simplemente 100 veces más grande, la suma de las
// rutas más cortas entre cada par de galaxias sería 8410. Sin embargo, tu universo
// deberá expandirse mucho más allá de estos valores).

// Comenzando con la misma imagen inicial, expande el universo según estas nuevas
// reglas, luego encuentra la longitud de la ruta más corta entre cada par de galaxias.
// ¿Cuál es la suma de estas longitudes?