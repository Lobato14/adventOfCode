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

fun main() {
    val espacioVacio = "."
    val galaxia = "#"
    val espacio = readInput("Day11")

    val numerosDeGalaxias = buscarGalaxias(espacio, espacioVacio, galaxia)
    val sumaDeLongitudes = calcularSumaDeLongitudes(espacio, numerosDeGalaxias)
    println(sumaDeLongitudes)
}

fun buscarGalaxias(espacio: List<String>, espacioVacio: String, galaxia: String): Map<Pair<Int, Int>, Int> {
    val numerosDeGalaxias = mutableMapOf<Pair<Int, Int>, Int>()
    var numeroGalaxia = 1

    for (i in espacio.indices) {
        for (j in espacio[i].indices) {
            if (espacio[i][j] == galaxia[0]) {
                numerosDeGalaxias[Pair(i, j)] = numeroGalaxia
                numeroGalaxia++
            }
        }
    }

    return numerosDeGalaxias
}

fun calcularSumaDeLongitudes(espacio: List<String>, numerosDeGalaxias: Map<Pair<Int, Int>, Int>): Int {
    val filas = espacio.size
    val columnas = espacio[0].length
    var sumaDeLongitudes = 0

    for ((posicion, numero) in numerosDeGalaxias) {
        for (i in 0 until filas) {
            for (j in 0 until columnas) {
                val otraGalaxia = Pair(i, j)
                val otraNumero = numerosDeGalaxias[otraGalaxia] ?: continue

                if (numero != otraNumero) {
                    val distancia = calcularDistancia(posicion, otraGalaxia)
                    sumaDeLongitudes += distancia
                }
            }
        }
    }

    return sumaDeLongitudes
}

fun calcularDistancia(galaxia1: Pair<Int, Int>, galaxia2: Pair<Int, Int>): Int {
    return kotlin.math.abs(galaxia1.first - galaxia2.first) + kotlin.math.abs(galaxia1.second - galaxia2.second)
}