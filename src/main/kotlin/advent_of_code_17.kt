// --- Día 17: Crisol Torpe ---

// --- Parte 1 ----

// La lava comienza a fluir rápidamente una vez que la Planta de Producción de Lava está operativa. Al salir,
// el reno te ofrece un paracaídas, permitiéndote llegar rápidamente a la Isla de Engranajes.

// Mientras desciendes, tu vista panorámica de la Isla de Engranajes revela por qué tuviste problemas para
// encontrar a alguien en tu camino hacia arriba: ¡la mitad de la Isla de Engranajes está vacía, pero la mitad
// debajo de ti es una gigantesca ciudad de fábricas!

// Aterrizas cerca de la piscina de lava que se está llenando gradualmente en la base de tu nueva cascada de lava.
// Los conductos de lava llevarán eventualmente la lava por toda la ciudad, pero para aprovecharla de inmediato,
// los elfos la están cargando en grandes crisoles sobre ruedas.

// Los crisoles son toscos y se empujan a mano. Desafortunadamente, los crisoles se vuelven muy difíciles de dirigir
// a altas velocidades, por lo que puede ser difícil mantenerse en línea recta durante mucho tiempo.

// Para llevar a la Isla del Desierto las piezas de la máquina que necesita lo antes posible, deberás encontrar la
// mejor manera de llevar el crisol desde la piscina de lava hasta la fábrica de piezas de la máquina. Para hacer
// esto, necesitas minimizar la pérdida de calor eligiendo una ruta que no requiera que el crisol vaya en línea
// recta durante demasiado tiempo.

// Afortunadamente, los elfos aquí tienen un mapa (tu entrada de rompecabezas) que utiliza patrones de tráfico,
// temperatura ambiente y cientos de otros parámetros para calcular exactamente cuánta pérdida de calor se puede
// esperar para un crisol que ingresa a cualquier bloque de la ciudad.

// Por ejemplo:

/* 2413432311323
   3215453535623
   3255245654254
   3446585845452
   4546657867536
   1438598798454
   4457876987766
   3637877979653
   4654967986887
   4564679986453
   1224686865563
   2546548887735
   4322674655533
 */

// Cada bloque de la ciudad está marcado por un solo dígito que representa la cantidad de pérdida de calor si el
// crisol ingresa a ese bloque. El punto de partida, la piscina de lava, es el bloque de la ciudad en la esquina
// superior izquierda; el destino, la fábrica de piezas de la máquina, es el bloque de la ciudad en la esquina
// inferior derecha. (Como ya comienzas en el bloque de la esquina superior izquierda, no incurre en la pérdida
// de calor de ese bloque a menos que lo abandones y luego regreses).

// Debido a que es difícil mantener el crisol tosco y con exceso de peso en línea recta durante mucho tiempo, puede
// moverse como máximo tres bloques en una sola dirección antes de tener que girar 90 grados a la izquierda o a la
// derecha. El crisol tampoco puede revertir su dirección; después de ingresar a cada bloque de la ciudad, solo
// puede girar a la izquierda, continuar en línea recta o girar a la derecha.

// Una forma de minimizar la pérdida de calor es esta ruta:

/*
2>>34^>>>1323
32v>>>35v5623
32552456v>>54
3446585845v52
4546657867v>6
14385987984v4
44578769877v6
36378779796v>
465496798688v
456467998645v
12246868655<v
25465488877v5
43226746555v>
 */

// Esta ruta nunca se mueve más de tres bloques consecutivos en la misma dirección y incurre en una pérdida de
// de solo 102.

// Dirigiendo el crisol desde la piscina de lava hasta la fábrica de piezas de la máquina, pero sin moverse más de
// tres bloques consecutivos en la misma dirección, ¿cuál es la pérdida de calor mínima que puede sufrir?

fun calculateHeatLoss(map: List<String>, path: String): Int {
    val moves = path.toCharArray()
    var heatLoss = 0
    var x = 0
    var y = 0

    val maxX = map.size - 1
    val maxY = map[0].length - 1

    for (move in moves) {
        when (move) {
            'v' -> {
                heatLoss += map[x][y].toString().toInt()
                x = minOf(maxX, x + 1)
            }
            '^' -> {
                heatLoss += map[x][y].toString().toInt()
                x = maxOf(0, x - 1)
            }
            '>' -> {
                heatLoss += map[x][y].toString().toInt()
                y = minOf(maxY, y + 1)
            }
            '<' -> {
                heatLoss += map[x][y].toString().toInt()
                y = maxOf(0, y - 1)
            }
        }
    }

    return heatLoss
}

fun main() {
    val map = readInput("Day17")

    val path = "2>>34^>>>1323\n" +
            "32v>>>35v5623\n" +
            "32552456v>>54\n" +
            "3446585845v52\n" +
            "4546657867v>6\n" +
            "14385987984v4\n" +
            "44578769877v6\n" +
            "36378779796v>\n" +
            "465496798688v\n" +
            "456467998645v\n" +
            "12246868655v<\n" +
            "25465488877v5\n" +
            "43226746555v>"

    val heatLoss = calculateHeatLoss(map, path)
    println("La pérdida de calor es: $heatLoss")
}