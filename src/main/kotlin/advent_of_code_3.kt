

// --- Día 3: Relaciones de Engranajes ---

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
    val lineas = readInput("Day03")
    val totalLineas = mutableListOf<Int>()
    var sumaAcumulativa = 0

    for (linea in lineas) {
        val sumaDeCadenaDeNumeros = calcularSumaCadenaNumeros(linea)
        sumaAcumulativa += sumaDeCadenaDeNumeros
        totalLineas.add(sumaAcumulativa)
    }

    println("Suma total de la lista: $sumaAcumulativa")
}

fun calcularSumaCadenaNumeros(esquemaMotor: String): Int {
    val lineas = esquemaMotor.lines()
    var suma = 0

    for (i in 0 until lineas.size) {
        for (j in 0 until lineas[i].length) {
            val simbolo = lineas[i][j]

            if (simbolo.isDigit()) {
                if (esNumeroDeParte(lineas, i, j)) {
                    suma += Character.getNumericValue(simbolo)
                }
            }
        }
    }

    return suma
}

fun esNumeroDeParte(lineas: List<String>, i: Int, j: Int): Boolean {
    for (x in -1..1) {
        for (y in -1..1) {
            val fila = i + x
            val columna = j + y

            if (fila in 0 until lineas.size && columna in 0 until lineas[fila].length &&
                (lineas[fila][columna] == '*' || lineas[fila][columna].isDigit())
            ) {
                return true
            }
        }
    }
    return false
}