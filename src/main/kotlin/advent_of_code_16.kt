// --- Día 16: El Suelo Será Lava ---

// --- Parte 1 ---

// Con el haz de luz completamente enfocado en algún lugar, el reno te guía aún más hacia el interior de la
// Instalación de Producción de Lava. En algún momento, te das cuenta de que las paredes de acero de la instalación
// han sido reemplazadas por cuevas, y las puertas son simplemente cuevas, y el suelo es una cueva, y estás bastante
// seguro de que esto es en realidad solo una cueva gigante.

// Finalmente, al acercarte a lo que debe ser el corazón de la montaña, ves una luz brillante en una caverna más
// adelante. Allí descubres que el haz de luz que enfocaste con tanto cuidado emerge de la pared de la caverna más
// cercana a la instalación y vierte toda su energía en un artefacto en el lado opuesto.

// Al inspeccionarlo más de cerca, el artefacto parece ser una cuadrícula cuadrada bidimensional plana que contiene
// espacio vacío (.), espejos (/ y ) y divisores (| y -).

// El artefacto está alineado de manera que la mayor parte del haz rebota alrededor de la cuadrícula, pero cada
// baldosa en la cuadrícula convierte parte de la luz del haz en calor para derretir la roca en la caverna.

// Observas el diseño del artefacto (tu entrada de rompecabezas). Por ejemplo:

// .|...\....
// |.-.\.....
// .....|-...
// ........|.
// ..........
// .........\
// ..../.\\..
// .-.-/..|..
// .|....-|.\
// ..//.|....

// El haz entra en la esquina superior izquierda desde la izquierda y se dirige hacia la derecha. Luego, su
// comportamiento depende de lo que encuentre a medida que se mueve:

// - Si el haz encuentra un espacio vacío (.), continúa en la misma dirección.

// - Si el haz encuentra un espejo (/ o ), se refleja 90 grados dependiendo del ángulo del espejo. Por ejemplo,
// un haz que se mueve hacia la derecha y encuentra un espejo / continuaría hacia arriba en la columna del espejo,
// mientras que un haz que se mueve hacia la derecha y encuentra un espejo \ continuaría hacia abajo desde la columna
// del espejo.

// - Si el haz encuentra el extremo puntiagudo de un divisor (| o -), el haz atraviesa el divisor como si fuera
// espacio vacío. Por ejemplo, un haz que se mueve hacia la derecha y encuentra un divisor - continuaría en la
// misma dirección.

// - Si el haz encuentra el lado plano de un divisor (| o -), se divide en dos haces que van en cada una de las dos
// direcciones en las que apuntan los extremos puntiagudos del divisor. Por ejemplo, un haz que se mueve hacia la
// derecha y encuentra un divisor | se dividiría en dos haces: uno que continúa hacia arriba desde la columna del
// divisor y otro que continúa hacia abajo desde la columna del divisor.

// Los haces no interactúan entre sí; una baldosa puede tener muchos haces pasando a través de ella al mismo tiempo.
// Una baldosa se energiza si tiene al menos un haz que pasa a través de ella, se refleja en ella o se divide en ella.

// En el ejemplo anterior, así es como el haz de luz rebota alrededor del artefacto:

// >|<<<\....
// |v-.\^....
// .v...|->>>
// .v...v^.|.
// .v...v^...
// .v...v^..\
// .v../2\\..
// <->-/vv|..
// .|<<<2-|.\
// .v//.|.v..

// Solo se muestran haces en las baldosas vacías; las flechas indican la dirección de los haces. Si una baldosa
// contiene haces que se mueven en múltiples direcciones, se muestra el número de direcciones distintas en su lugar.
// Aquí está el mismo diagrama, pero solo mostrando si una baldosa está energizada (#) o no (.):

// ######....
// .#...#....
// .#...#####
// .#...##...
// .#...##...
// .#...##...
// .#..####..
// ########..
// .#######..
// .#...#.#..

// En última instancia, en este ejemplo, 46 baldosas se energizan.

// La luz no está energizando suficientes baldosas para producir lava; para depurar el artefacto, necesitas comenzar
// analizando la situación actual. Con el haz comenzando en la esquina superior izquierda y dirigiéndose hacia la
// derecha, ¿cuántas baldosas terminan siendo energizadas?


fun main() {
    // Variables de los movimientos posibles
    val derecha = Pair(1, 0)
    val izquierda = Pair(-1, 0)
    val arriba = Pair(0, -1)
    val abajo = Pair(0, 1)

    // Mapa con todos los movimientos disponibles
    val movimientos = mapOf(
        Pair(".", derecha) to listOf(derecha),
        Pair(".", izquierda) to listOf(izquierda),
        Pair(".", arriba) to listOf(arriba),
        Pair(".", abajo) to listOf(abajo),

        Pair("-", derecha) to listOf(derecha),
        Pair("-", izquierda) to listOf(izquierda),
        Pair("-", arriba) to listOf(izquierda, derecha),
        Pair("-", abajo) to listOf(izquierda, derecha),

        Pair("|", derecha) to listOf(arriba, abajo),
        Pair("|", izquierda) to listOf(arriba, abajo),
        Pair("|", arriba) to listOf(arriba),
        Pair("|", abajo) to listOf(abajo),

        Pair("\\", derecha) to listOf(abajo),
        Pair("\\", izquierda) to listOf(arriba),
        Pair("\\", arriba) to listOf(izquierda),
        Pair("\\", abajo) to listOf(derecha),

        Pair("/", derecha) to listOf(arriba),
        Pair("/", izquierda) to listOf(abajo),
        Pair("/", arriba) to listOf(derecha),
        Pair("/", abajo) to listOf(izquierda)
    )

    val input = readInput("Day16").map { it.toCharArray().toList() }

    // Parte 1
    val energizadosParte1 = parte1(input, movimientos)
    println("Parte 1: ${energizadosParte1.size}")

    // Parte 2
    val maxEnergizadosParte2 = parte2(input, movimientos)
    println("Parte 2: $maxEnergizadosParte2")
}

fun parte1(
    input: List<List<Char>>,
    movimientos: Map<Pair<String, Pair<Int, Int>>, List<Pair<Int, Int>>>
): MutableMap<Pair<Int, Int>, MutableSet<Pair<Int, Int>>> {
    val energizados = mutableMapOf<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>()

    val movimientosHechos = mutableListOf(Pair(Pair(-1, 0), Pair(1, 0)))

    while (movimientosHechos.isNotEmpty()) {
        val (posicionHaz, dir) = movimientosHechos.removeAt(0)
        val (x, y) = Pair(posicionHaz.first + dir.first, posicionHaz.second + dir.second)

        if (x in 0 until input[0].size && y in 0 until input.size &&
            !energizados.getOrPut(Pair(x, y)) { mutableSetOf() }.contains(dir)
        ) {
            energizados[Pair(x, y)]?.add(dir)

            for (nuevaDir in movimientos[Pair(input[y][x].toString(), dir)] ?: error("Movimiento no válido")) {
                movimientosHechos.add(Pair(Pair(x, y), nuevaDir))
            }
        }
    }

    return energizados
}

// --- Parte 2 ----

// Mientras tratas de descubrir qué podría estar mal, el reno tira de tu camisa y te lleva a un panel de control
// cercano. Allí, una colección de botones te permite alinear el artefacto de manera que el rayo entre desde
// cualquier casilla en el borde y se dirija hacia afuera desde ese borde. (Puedes elegir cualquiera de las dos
// direcciones para el rayo si comienza en una esquina; por ejemplo, si el rayo comienza en la esquina inferior
// derecha, puede dirigirse hacia la izquierda o hacia arriba).

// Entonces, el rayo podría comenzar en cualquier casilla en la fila superior (dirigiéndose hacia abajo), en
// cualquier casilla en la fila inferior (dirigiéndose hacia arriba), en cualquier casilla en la columna más a
// la izquierda (dirigiéndose hacia la derecha) o en cualquier casilla en la columna más a la derecha
// (dirigiéndose hacia la izquierda). Para producir lava, necesitas encontrar la configuración que energiza
// la mayor cantidad de casillas posible.

// En el ejemplo anterior, esto se puede lograr comenzando el rayo en la cuarta casilla desde la izquierda en la
// fila superior:

// .|<2<....
// |v-v^....
// .v.v.|->>>
// .v.v.v^.|.
// .v.v.v^...
// .v.v.v^..
// .v.v/2\..
// <-2-/vv|..
// .|<<<2-|.
// .v//.|.v..

// Utilizando esta configuración, se energizan 51 casillas:

// .#####....
// .#.#.#....
// .#.#.#####
// .#.#.##...
// .#.#.##...
// .#.#.##...
// .#.#####..
// ########..
// .#######..
// .#...#.#..

// Encuentra la configuración inicial del rayo que energiza el mayor número de casillas; ¿cuántas casillas se
// energizan en esa configuración?

fun parte2(
    input: List<List<Char>>,
    movimientos: Map<Pair<String, Pair<Int, Int>>, List<Pair<Int, Int>>>
): Int {

    var maxEnergizadosActual = -1

    val inicioHaces = mutableListOf(
        Pair(Pair(-1, 0), Pair(1, 0)),
        Pair(Pair(input[0].size, 0), Pair(-1, 0)),
        Pair(Pair(0, -1), Pair(0, 1)),
        Pair(Pair(0, input.size), Pair(0, -1))
    )

    for (inicioHaz in inicioHaces) {
        val tempEnergizados = mutableMapOf<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>()
        val tempHaces = mutableListOf(inicioHaz)

        while (tempHaces.isNotEmpty()) {
            val (posicionHaz, dir) = tempHaces.removeAt(0)
            val (x, y) = Pair(posicionHaz.first + dir.first, posicionHaz.second + dir.second)

            if (x in 0 until input[0].size && y in 0 until input.size &&
                !tempEnergizados.getOrPut(Pair(x, y)) { mutableSetOf() }.contains(dir)
            ) {
                tempEnergizados[Pair(x, y)]?.add(dir)

                for (nuevaDir in movimientos[Pair(input[y][x].toString(), dir)] ?: error("Movimiento no válido")) {
                    tempHaces.add(Pair(Pair(x, y), nuevaDir))
                }
            }
        }

        maxEnergizadosActual = maxOf(maxEnergizadosActual, tempEnergizados.size)
    }

    return maxEnergizadosActual
}