

fun main() {

    val puzles = readInput("Day01")
    val sumaDeValores = calcularCalibracionDeValores(puzles)
    println("La suma de todos los valores es: $sumaDeValores")

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