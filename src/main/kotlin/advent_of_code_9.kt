// --- Día 9: Mantenimiento de Mirage ---

// ---- Parte 1 -----

// Atraviesas la tormenta de arena con el camello y te detienes donde los mapas del fantasma te
// indican. Posteriormente, la tormenta de arena amaina y, de alguna manera, ¡te encuentras en un
// oasis!

// El camello va a por agua y tú estiras el cuello. Al mirar hacia arriba, descubres lo que debe de ser
// otra gigantesca isla flotante, ¡esta vez de metal! De ahí deben de venir las piezas para arreglar
// las máquinas de arena.

// Aquí hay incluso un ala delta parcialmente enterrada en la arena; cuando salga el sol y caliente la
// arena, quizá puedas utilizar el ala delta y el aire caliente para llegar hasta la isla de metal.

// Mientras esperas a que salga el sol, admiras el oasis que se esconde aquí, en medio de la Isla
// Desierta. Debe de tener un ecosistema delicado; podrías hacer algunas lecturas ecológicas mientras
// esperas. Tal vez puedas informar a alguien de cualquier inestabilidad ambiental que encuentres
// para que el oasis pueda estar cerca para el próximo viajero agotado por la tormenta de arena.

// Sacas tu práctico Sensor de Inestabilidad del Oasis y la Arena y analizas tu entorno. El OASIS
// produce un informe de muchos valores y cómo están cambiando con el tiempo (la entrada de tu puzzle).
// Cada línea del informe contiene la historia de un único valor. Por ejemplo:

// 0 3 6 9 12 15
// 1 3 6 10 15 21
// 10 13 16 21 30 45

// Para proteger mejor el oasis, su informe medioambiental debe incluir una predicción del siguiente
// valor en cada historia. Para ello, empiece por hacer una nueva secuencia a partir de la diferencia
// en cada paso de su historia.

// En el conjunto de datos anterior, el primer historial es 0 3 6 9 12 15. Como los valores aumentan
// en 3 en cada paso, la primera secuencia de diferencias que genere será 3 3 3 3 3. Observe que esta
// secuencia tiene un valor menos que la secuencia de entrada porque en cada paso considera dos
// números de la entrada. Como estos valores no son todos cero, repite el proceso: los valores
// difieren en 0 en cada paso, por lo que la siguiente secuencia es 0 0 0 0. Esto significa que
// tienes suficiente información para extrapolar la historia. Visualmente, estas secuencias se pueden
// ordenar así:

// 0 3 6 9 12 15
//   3 3 3 3 3
//     0 0 0 0

// Para extrapolar, empieza añadiendo un nuevo cero al final de tu lista de ceros; como los ceros
// representan diferencias entre los dos valores que hay sobre ellos, esto también significa que
// ahora hay un marcador de posición en cada secuencia superior:

// 0 3 6 9 12 15 B
//   3 3 3 3 3 A
//     0 0 0 0 0

// A continuación, puede empezar a rellenar los marcadores de posición de abajo arriba. A tiene que
// ser el resultado de incrementar 3 (el valor a su izquierda) por 0 (el valor debajo de él); esto
// significa que A debe ser 3:

// 0 3 6 9 12 15 B
//   3 3 3 3 3 3
//     0 0 0 0 0

// Por último, puedes completar B, que debe ser el resultado de multiplicar 15 (el valor situado a su
// izquierda) por 3 (el valor situado debajo), es decir, 18:

// 0 3 6 9 12 15 18
//   3 3 3 3 3 3
//     0 0 0 0 0

// Por lo tanto, el siguiente valor de la primera historia es 18.

// Para encontrar las diferencias de todos los ceros de la segunda historia se requiere una secuencia
// adicional:

// 1 3 6 10 15 21 28
// 2 3 4 5 6 7
// 1 1 1 1 1
// 0 0 0 0

// Por lo tanto, el siguiente valor de la segunda historia es 28.

// La tercera historia requiere aún más secuencias, pero su siguiente valor se puede encontrar de la
// misma manera:

// 10 13 16 21 30 45 68
// 3 3 5 9 15 23
// 0 2 4 6 8
// 2 2 2 2
// 0 0 0

// Por lo tanto, el siguiente valor de la tercera historia es 68.

// Si encuentra el siguiente valor para cada historia en este ejemplo y los suma, obtiene 114.

// Analice su informe OASIS y extrapole el siguiente valor para cada historia. ¿Cuál es la suma de
// estos valores extrapolados?

fun main() {

    val marcadorUno = 'A'
    val marcadorDos = 'B'

    val primeraSecuencia = mutableListOf(0, 3, 6, 9, 12, 15, marcadorUno)
    val segundaSecuencia = mutableListOf(1, 3, 6, 10, 15, 21, marcadorDos)
    val terceraSecuencia = mutableListOf(10, 13, 16, 21, 30, 45, 68)

    val listaSecuenciaCompleta = mutableListOf(primeraSecuencia, segundaSecuencia, terceraSecuencia)

    for (lista in listaSecuenciaCompleta) {
        val resultado = calcularSiguienteValor(lista)
        println(resultado)
    }
}

fun calcularSiguienteValor(secuencia: MutableList<out Any>): Any {
    // Calcular la secuencia de diferencias
    val diferencias = mutableListOf<Int>()
    for (numero in 0..<secuencia.size - 1) {
        diferencias.add((secuencia[numero + 1] as Int) - (secuencia[numero] as Int))
    }

    diferencias.add(0)

    for (i in secuencia.indices) {
        secuencia[i] = (secuencia[i] as Int) + diferencias[i]
    }

    return secuencia.last()
}
