

// --- Día 3: Relaciones de Engranajes ---

// -- PARTE 1 ---

// Tú y el Elfo finalmente llegan a una estación de teleférico; él dice que el teleférico los llevará hasta la
// fuente de agua, pero este es el límite hasta donde puede acompañarte. Entráis.

// No tardáis en encontrar los teleféricos, pero parece haber un problema: no se están moviendo.

// "¡Aah!"

// Te volteas para ver a un Elfo ligeramente engrasado con una llave inglesa y una expresión de sorpresa. "Lo siento,
// no esperaba a nadie. El teleférico no está funcionando en este momento; tomará un tiempo antes de que pueda
// arreglarlo". Ofreces tu ayuda.

// El ingeniero explica que falta una parte del motor, pero nadie puede averiguar cuál es. Si puedes sumar todos
// los números de las piezas en el esquema del motor, debería ser fácil determinar qué parte falta.

// El esquema del motor (tu entrada de rompecabezas) consiste en una representación visual del motor. Hay muchos
// números y símbolos que no comprendes realmente, pero aparentemente cualquier número adyacente a un símbolo,
// incluso en diagonal, es un "número de parte" y debe incluirse en tu suma. (Los puntos (.) no cuentan como un
// símbolo).

// Aquí tienes un ejemplo del esquema del motor:

// 467..114..
// ...*......
// ..35..633.
// ......#...
// 617*......
// .....+.58.
// ..592.....
// ......755.
// ...$.*....
// .664.598..

// En este esquema, dos números no son números de parte porque no están adyacentes a un símbolo: 114 (arriba a
// la derecha) y 58 (en el medio a la derecha). Todos los demás números son adyacentes a un símbolo y, por lo
// tanto, son números de parte; su suma es 4361.

// Por supuesto, el esquema real del motor es mucho más grande. ¿Cuál es la suma de todos los números de parte
// en el esquema del motor?


fun main() {
    // Entrada
    val entrada = readInput("Day03")
    // Proceso
    val resultado = resolverParte1(entrada)
    // Salida
    println("La sumade todos los números en el esquema del motor es de $resultado")
}

fun lineasAAnalizar(lineasAdyacentes: MutableList<String>, input: List<String>, index: Int) {
    val dimensiones = input[index].length
    lineasAdyacentes.clear()
    if (index == 0) {
        lineasAdyacentes.add(".".repeat(dimensiones))
    } else {
        lineasAdyacentes.add(input[index - 1])
    }
    lineasAdyacentes.add(input[index])
    if (index == dimensiones - 1) {
        lineasAdyacentes.add(".".repeat(dimensiones))
    } else {
        lineasAdyacentes.add(input[index + 1])
    }
}

fun esSimbolo(c: Char): Boolean {
    return !c.isDigit() && c != '.'
}

fun tieneSimboloAdyacente(lineasAdyacentes: MutableList<String>, index: Int): Boolean {
    val dimension = lineasAdyacentes[0].length

    for (fila in 0..2) {
        for (desplazamientoCaracter in -1..1) {
            val indice = index + desplazamientoCaracter
            if (indice in 0 until dimension) {
                if (esSimbolo(lineasAdyacentes[fila][indice])) {
                    return true
                }
            }
        }
    }
    return false
}

fun sumaPartNumbersDeLinea(lineasDeAnalisis: MutableList<String>): Int {
    val lineaActual = 1
    var numero = ""
    var tieneSimboloAdyacente = false
    var sumaPartNumbers = 0

    lineasDeAnalisis[lineaActual].forEachIndexed { indice, caracter ->
        if (caracter.isDigit()) {
            if (!tieneSimboloAdyacente) {
                tieneSimboloAdyacente = tieneSimboloAdyacente(lineasDeAnalisis, indice)
            }
            numero += caracter
        }
        if ((!caracter.isDigit() || indice == lineasDeAnalisis[lineaActual].length - 1) && numero.isNotBlank()) {
            if (tieneSimboloAdyacente) {
                sumaPartNumbers += numero.toInt()
                tieneSimboloAdyacente = false
            }
            numero = ""
        }
    }
    return sumaPartNumbers
}

fun resolverParte1(input: List<String>): Int {
    var parteNumerica = 0
    val lineasEnEstudio: MutableList<String> = mutableListOf()

    input.forEachIndexed { index, _ ->
        lineasAAnalizar(lineasEnEstudio, input, index)
        parteNumerica += sumaPartNumbersDeLinea(lineasEnEstudio)
    }

    return parteNumerica
}

