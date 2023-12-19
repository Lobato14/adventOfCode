
// --- Día 1: ¿¡Trabuco?! ---

// -- Parte 1 ---

// Algo está mal con la producción global de nieve, y te han seleccionado para echar un vistazo. Los Elfos
// incluso te han proporcionado un mapa; en él, han utilizado estrellas para marcar los cincuenta lugares
// principales que probablemente tengan problemas.

// Has estado haciendo esto el tiempo suficiente para saber que para restaurar las operaciones de nieve,
// necesitas verificar todas las cincuenta estrellas antes del 25 de diciembre.

// Recoge estrellas resolviendo rompecabezas. Se harán disponibles dos rompecabezas cada día en el calendario
// de Adviento; el segundo rompecabezas se desbloquea cuando completes el primero. Cada rompecabezas otorga
// una estrella. ¡Buena suerte!

// Intentas preguntar por qué no pueden simplemente usar una máquina del tiempo ("no lo suficientemente potente")
// y a dónde te están enviando incluso ("al cielo") y por qué tu mapa parece en su mayoría en blanco ("seguro que
// haces muchas preguntas") y espera, ¿acabas de decir el cielo? ("por supuesto, ¿de dónde crees que viene la
// nieve?") cuando te das cuenta de que los Elfos ya te están cargando en un trabuco ("por favor, quédate quieto,
// necesitamos atarte").

// Mientras hacen los ajustes finales, descubren que su documento de calibración mejorado (tu entrada de
// rompecabezas) ha sido modificado por un Elfo muy joven que aparentemente estaba emocionado por mostrar
// sus habilidades artísticas. En consecuencia, los Elfos tienen problemas para leer los valores en el documento.

// El documento de calibración recién mejorado consta de líneas de texto; cada línea originalmente contenía un
// valor de calibración específico que los Elfos ahora necesitan recuperar. En cada línea, el valor de calibración
// se puede encontrar combinando el primer dígito y el último dígito (en ese orden) para formar un único número de
// dos dígitos.

// Por ejemplo:

// 1abc2
// pqr3stu8vwx
// a1b2c3d4e5f
// treb7uchet
// En este ejemplo, los valores de calibración de estas cuatro líneas son 12, 38, 15 y 77. Sumar estos valores
// produce 142.

// Considera todo tu documento de calibración. ¿Cuál es la suma de todos los valores de calibración?

fun main() {

    val puzles = readInput("Day01")
    val sumaDeValoresParte1 = calcularCalibracionDeValores(puzles)

    val sumaDeValoresParte2 = ""

    println("La suma de todos los valores es: $sumaDeValoresParte1")

}

fun calcularCalibracionDeValores(listaDeCadenas: List<String>): Int {
    val listaDigitos = mutableListOf<Int>()

    for (linea in listaDeCadenas) {
        var numerosLinea = ""
        for (caracter in linea) {
            if (caracter.isDigit()) {
                numerosLinea += caracter
            }
        }
        val primerDigito = numerosLinea.first()
        val segundoDigito = numerosLinea.last()
        val digitoCreado = "$primerDigito$segundoDigito".toInt()
        listaDigitos.add(digitoCreado)

    }
    return listaDigitos.sum()
}

// ---- Parte 2 ------

// Tu cálculo no es del todo correcto. Parece que algunos de los dígitos en realidad están escritos con
//  letras: uno, dos, tres, cuatro, cinco, seis, siete, ocho y nueve también cuentan como "dígitos"
//  válidos.

// Equipado con esta nueva información, ahora necesita encontrar el primer y último dígito real en
// cada línea. Por ejemplo:

// two1nine
// eightwothree
// abcone2threexyz
// xtwone3four
// 4nineeightseven2
// zoneight234
// 7pqrstsixteen

// En este ejemplo, los valores de calibración son 29, 83, 13, 24, 42, 14 y 76. Sumarlos produce 281.

// ¿Cuál es la suma de todos los valores de calibración?

fun calcularCalibracionDeValores2(listaDeCadenas: List<String>): Int {

    val listaPalabrasNumeros = mutableListOf<Int>()
    val dicNumeros = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
        "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

    for (linea in listaDeCadenas) {
        var numerosLinea = ""
        var cadenaNumero = ""
        for (caracter in linea) {
            if (caracter.isDigit()) {
                numerosLinea += caracter
            }
            if (cadenaNumero in dicNumeros){

            }
        }


        val primerDigito = numerosLinea.first()
        val segundoDigito = numerosLinea.last()
        val digitoCreado = "$primerDigito$segundoDigito".toInt()
        listaPalabrasNumeros.add(digitoCreado)

    }
    return listaPalabrasNumeros.sum()
}
