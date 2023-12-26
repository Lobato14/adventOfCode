// --- Día 18: Laguna de Conductos Lava ---

// --- Parte 1 ---

// Gracias a tus esfuerzos, la fábrica de piezas para la máquina es una de las primeras fábricas en funcionamiento
// desde que la cascada de lava regresó. Sin embargo, para ponerse al día con la gran cantidad de solicitudes de
// piezas, la fábrica también necesitará un suministro abundante de lava durante un tiempo; los elfos ya han
// comenzado a crear una gran laguna cercana con este propósito.

// Sin embargo, no están seguros de que la laguna sea lo suficientemente grande; te han pedido que revises el
// plan de excavación (tu entrada de rompecabezas). Por ejemplo:

// R 6 (#70c710)
// D 5 (#0dc571)
// L 2 (#5713f0)
// D 2 (#d2c081)
// R 2 (#59c680)
// D 2 (#411b91)
// L 5 (#8ceee2)
// U 2 (#caa173)
// L 1 (#1b58a2)
// U 2 (#caa171)
// R 2 (#7807d2)
// U 3 (#a77fa3)
// L 2 (#015232)
// U 2 (#7a21e3)

// El excavador comienza en un agujero de 1 metro cúbico en el suelo. Luego excava la cantidad especificada de
// metros hacia arriba (U), hacia abajo (D), hacia la izquierda (L) o hacia la derecha (R), despejando cubos
// completos de 1 metro a medida que avanza. Las direcciones se dan como se ven desde arriba, por lo que si
// "arriba" fuera el norte, entonces "derecha" sería el este, y así sucesivamente. Cada zanja también se lista
// con el color que el borde de la zanja debería ser pintado como un código hexadecimal de color RGB.

// Cuando se ve desde arriba, el plan de excavación de ejemplo anterior resultaría en el siguiente bucle de zanjas
// (#) que se han excavado desde el terreno de nivel del suelo (.):

// #######
// #.....#
// ###...#
// ..#...#
// ..#...#
// ###.###
// #...#..
// ##..###
// .#....#
// .######

// En este punto, la zanja podría contener 38 metros cúbicos de lava. Sin embargo, esto es solo el borde de la laguna;
// el siguiente paso es excavar el interior para que también tenga un metro de profundidad:

// #######
// #######
// #######
// ..#####
// ..#####
// #######
// #####..
// #######
// .######
// .######

// Ahora, la laguna puede contener una cantidad mucho más respetable de 62 metros cúbicos de lava. Mientras se
// excava el interior, los bordes también se pintan según los códigos de color en el plan de excavación.

// Los elfos están preocupados de que la laguna no sea lo suficientemente grande; si siguen su plan de excavación,
// ¿cuántos metros cúbicos de lava podría contener?

import kotlin.math.abs

fun calcularArea(puntos: List<Pair<Int, Int>>): Int {
    var resultado = 0
    for (i in 0..<puntos.size - 1) {
        val (x1, y1) = puntos[i]
        val (x2, y2) = puntos[i + 1]
        resultado += x1 * y2 - x2 * y1
    }
    return abs(resultado) / 2
}

fun main() {
    // Parte 1
    val direccion1 = mapOf("U" to Pair(0, -1), "D" to Pair(0, 1), "L" to Pair(-1, 0), "R" to Pair(1, 0))
    val lineas = readInput("Day18")
    val planDeExcavacion = lineas.map { it.split(" ") }

    var perimetro = 0

    val puntos = mutableListOf(Pair(0, 0))
    for (inst in planDeExcavacion) {
        val distancia = inst[1].toInt()
        val direccion = direccion1[inst[0]]!!

        perimetro += distancia

        puntos.add(Pair(puntos.last().first + distancia * direccion.first, puntos.last().second + distancia * direccion.second))
    }

    val resultadoParte1 = calcularArea(puntos) + perimetro / 2 + 1
    println("Los metros cúbicos de lava son de $resultadoParte1")

}


// --- Parte Dos ---

// Los Elfos tenían razón al estar preocupados; la laguna planeada sería demasiado pequeña.

// Después de unos minutos, alguien se da cuenta de lo que sucedió; alguien intercambió los parámetros de color
// e instrucción al producir el plan de excavación. No tienen tiempo para corregir el error; alguien te
// pregunta si puedes extraer las instrucciones correctas de los códigos hexadecimales.

// Cada código hexadecimal tiene seis dígitos hexadecimales. Los primeros cinco dígitos hexadecimales codifican
// la distancia en metros como un número hexadecimal de cinco dígitos. El último dígito hexadecimal codifica la
// dirección de excavación: 0 significa R, 1 significa D, 2 significa L y 3 significa U.

// Entonces, en el ejemplo anterior, los códigos hexadecimales se pueden convertir en las instrucciones reales:

// #70c710 = R 461937
// #0dc571 = D 56407
// #5713f0 = R 356671
// #d2c081 = D 863240
// #59c680 = R 367720
// #411b91 = D 266681
// #8ceee2 = L 577262
// #caa173 = U 829975
// #1b58a2 = L 112010
// #caa171 = D 829975
// #7807d2 = L 491645
// #a77fa3 = U 686074
// #015232 = L 5411
// #7a21e3 = U 500254

// Excavar este bucle y su interior produce una laguna que puede contener impresionantes 952408144115 metros
// cúbicos de lava.

// Convierte los códigos de color hexadecimales en las instrucciones correctas; si los Elfos siguen este nuevo
// plan de excavación, ¿cuántos metros cúbicos de lava podría contener la laguna?