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
    val input = """
        .|...\....
        |.-.\.....
        .....|-...
        ........|.
        ..........
        .........\
        ..../.\\..
        .-.-/..|..
        .|....-|.\
        ..//.|....
    """.trimIndent()

    val energizedTiles = countEnergizedTiles(input)
    println("Número de baldosas energizadas: $energizedTiles")
}

fun countEnergizedTiles(input: String): Int {
    val lines = input.lines()

    val grid = lines.map { it.toCharArray().toTypedArray() }.toTypedArray()

    val height = grid.size
    val width = grid[0].size

    val visited = Array(height) { BooleanArray(width) }

    fun dfs(x: Int, y: Int) {
        if (x < 0 || x >= width || y < 0 || y >= height || visited[y][x] || grid[y][x] == '.') {
            return
        }

        visited[y][x] = true

        when (grid[y][x]) {
            '-' -> {
                dfs(x + 1, y)
                dfs(x - 1, y)
            }
            '|' -> {
                dfs(x, y + 1)
                dfs(x, y - 1)
            }
            '\\' -> dfs(x - 1, y + 1)
            '/' -> dfs(x + 1, y + 1)
        }
    }

    var energizedCount = 0

    for (y in 0 until height) {
        for (x in 0 until width) {
            if (!visited[y][x] && grid[y][x] != '.') {
                energizedCount++
                dfs(x, y)
            }
        }
    }

    return energizedCount
}