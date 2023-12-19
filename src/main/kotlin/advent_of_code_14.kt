// --- Día 14: Plato Reflector Parabólico ---

// --- Parte 1 -----

// Llegas al lugar donde todos los espejos estaban apuntando: un masivo plato reflector parabólico
// adherido al costado de otra gran montaña.

// El plato está compuesto por muchos espejos pequeños, pero mientras que los espejos en sí mismos
// tienen aproximadamente la forma de un plato reflector parabólico, cada espejo individual parece
// apuntar ligeramente en la dirección equivocada. Si el plato está destinado a concentrar la luz,
// en este momento solo la está enviando en una dirección vaga.

// ¡Este sistema debe ser el que proporciona la energía para la lava! Si enfocas el plato reflector,
// tal vez puedas ir a donde está apuntando y usar la luz para corregir la producción de lava.

// Al examinar más de cerca, parece que cada espejo individual está conectado mediante un elaborado
// sistema de cuerdas y poleas a una gran plataforma metálica debajo del plato. La plataforma está
// cubierta de grandes rocas de varias formas. Dependiendo de su posición, el peso de las rocas deforma
// la plataforma, y la forma de la plataforma controla qué cuerdas se mueven y, en última instancia,
// el enfoque del plato.

// En resumen: si mueves las rocas, puedes enfocar el plato. ¡La plataforma incluso tiene un panel de
// control en el lateral que te permite inclinarla en una de las cuatro direcciones! Las rocas redondas
// (O) rodarán cuando se incline la plataforma, mientras que las rocas en forma de cubo (#) permanecerán
// en su lugar. Toma nota de las posiciones de todos los espacios vacíos (.) y las rocas (tu entrada de
// rompecabezas). Por ejemplo:

// O....#....
// O.OO#....#
// .....##...
// OO.#O....O
// .O.....O#.
// O.#..O.#.#
// ..O..#O..O
// .......O..
// #....###..
// #OO..#....

// Comienza inclinando la palanca para que todas las rocas se deslicen hacia el norte tanto como sea
// posible:

// OOOO.#.O..
// OO..#....#
// OO..O##..O
// O..#.OO...
// ........#.
// ..#....#.#
// ..O..#.O.O
// ..O.......
// #....###..
// #....#....

// Notas que las vigas de soporte a lo largo del lado norte de la plataforma están dañadas; para
// asegurarte de que la plataforma no se colapse, debes calcular la carga total en las vigas de
// soporte del norte.

// La cantidad de carga causada por una sola roca redonda (O) es igual al número de filas desde la roca
// hasta el borde sur de la plataforma, incluida la fila en la que se encuentra la roca. (Las rocas en
// forma de cubo (#) no contribuyen a la carga). Entonces, la cantidad de carga causada por cada roca
// en cada fila es la siguiente:

// OOOO.#.O.. 10
// OO..#....# 9
// OO..O##..O 8
// O..#.OO... 7
// ........#. 6
// ..#....#.# 5
// ..O..#.O.O 4
// .O....... 3
// #....###.. 2
// #....#.... 1

// La carga total es la suma de la carga causada por todas las rocas redondas. En este ejemplo, la
// carga total es 136.

// Inclina la plataforma para que todas las rocas redondas rueden hacia el norte. Después, ¿cuál es la
// carga total en las vigas de soporte del norte?

fun main() {

    // Entrada
    val input = readInput("Day14")
    // Proceso
    val plataforma = input.map { it.toCharArray().toTypedArray() }.toTypedArray()
    val peso = calcularPeso(plataforma)
    // Salida
    println("Parte 1 total: $peso")
}

fun calcularPeso(plataforma: Array<Array<Char>>): Int {
    var peso = 0

    for (y in plataforma.indices) {
        for (x in plataforma[y].indices) {
            if (plataforma[y][x] == 'O') {
                plataforma[y][x] = '.'
                var i = y - 1
                while (i >= 0 && plataforma[i][x] == '.') {
                    i--
                }
                plataforma[i + 1][x] = 'O'
                peso += plataforma.size - i - 1
            }
        }
    }

    return peso
}

// ---- Parte 2 -----

// El plato reflector parabólico se deforma, pero no de una manera que enfoque el haz. Para lograr
// eso, necesitarás mover las rocas hacia los bordes de la plataforma. Afortunadamente, un botón en
// el lateral del panel de control etiquetado como "ciclo de giro" intenta hacer precisamente eso.

// Cada ciclo inclina la plataforma cuatro veces para que las rocas redondas rueden hacia el norte,
// luego hacia el oeste, luego hacia el sur y finalmente hacia el este. Después de cada inclinación,
// las rocas redondas ruedan tanto como pueden antes de que la plataforma se incline en la siguiente
// dirección. Después de un ciclo, la plataforma habrá terminado de rodar las rocas redondas en esas
// cuatro direcciones en ese orden.

// Esto es lo que sucede en el ejemplo anterior después de los primeros ciclos:

// Después de 1 ciclo:

// .....#....
// ....#...O#
// ...OO##...
// .OO#......
// .....OOO#.
// .O#...O#.#
// ....O#....
// ......OOOO
// #...O###..
// #..OO#....

// Después de 2 ciclos:

// .....#....
// ....#...O#
// .....##...
// ..O#......
// .....OOO#.
// .O#...O#.#
// ....O#...O
// .......OOO
// #..OO###..
// #.OOO#...O

// Después de 3 ciclos:

// .....#....
// ....#...O#
// .....##...
// ..O#......
// .....OOO#.
// .O#...O#.#
// ....O#...O
// .......OOO
// #...O###.O
// #.OOO#...O

// Este proceso debería funcionar si lo dejas funcionando el tiempo suficiente, pero aún te preocupa
// las vigas de soporte norte. Para asegurarte de que sobrevivan por un tiempo, necesitas calcular
// la carga total en las vigas de soporte norte después de 1000000000 ciclos.

// En el ejemplo anterior, después de 1000000000 ciclos, la carga total en las vigas de soporte norte
// es 64.

// Ejecuta el ciclo de giro durante 1000000000 ciclos. Después, ¿cuál es la carga total en las vigas
// de soporte norte?

