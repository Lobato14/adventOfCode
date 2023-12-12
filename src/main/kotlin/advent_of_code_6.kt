// --- Día 6: Espera Por Esto ---

// ----- Parte 1 ------

// El ferry te lleva rápidamente a la isla Isla. Después de preguntar, descubres que
// normalmente hay un gran montón de arena por aquí, pero no ves nada más que mucha
// agua y la pequeña isla donde ha atracado el ferry.

// Mientras intentas descifrar qué hacer a continuación, ves un póster en una pared
// cerca del muelle del ferry. "¡Carreras de barcos! ¡Abierto al público! ¡El gran
// premio es un viaje con todos los gastos pagados a la isla del desierto!" ¡Eso
// debe ser de donde viene la arena! Lo mejor de todo es que las carreras de barcos
// comienzan en solo unos minutos.

// Logras inscribirte como competidor en las carreras de barcos justo a tiempo. El
// organizador explica que no es realmente una carrera tradicional, en lugar de eso,
// tendrás un tiempo fijo durante el cual tu barco debe viajar lo más lejos posible, y
// ganas si tu barco va más lejos.

// Como parte de la inscripción, te dan una hoja de papel (tu entrada de rompecabezas) que
// lista el tiempo permitido para cada carrera y también la mejor distancia registrada en
// esa carrera. Para garantizar que ganes el gran premio, debes asegurarte de ir más lejos
// en cada carrera que el titular actual del récord.

// El organizador te lleva a la zona donde se llevan a cabo las carreras de barcos. Los barcos
// son mucho más pequeños de lo que esperabas, son barcos de juguete, cada uno con un gran
// botón en la parte superior. Mantener presionado el botón carga el barco y soltar el botón
// permite que el barco se mueva. Los barcos se mueven más rápido si su botón se mantuvo
// presionado más tiempo, pero el tiempo que se pasa sosteniendo el botón cuenta en contra del
// tiempo total de la carrera. Solo puedes mantener presionado el botón al comienzo de la
// carrera, y los barcos no se mueven hasta que se suelta el botón.

// Por ejemplo:
// Tiempo: 7 15 30
// Distancia: 9 40 200
// Este documento describe tres carreras:

// La primera carrera dura 7 milisegundos. La distancia récord en esta carrera es de 9
// milímetros.

// La segunda carrera dura 15 milisegundos. La distancia récord en esta carrera es de
// 40 milímetros.

// La tercera carrera dura 30 milisegundos. La distancia récord en esta carrera es de 200
// milímetros.

// Tu barco de juguete tiene una velocidad inicial de cero milímetros por milisegundo. Por
// cada milisegundo completo que pases al principio de la carrera sosteniendo el botón, la
// velocidad de tu barco aumenta en un milímetro por milisegundo.

// Entonces, debido a que la primera carrera dura 7 milisegundos, solo tienes algunas opciones:

// No sostener el botón en absoluto (es decir, sostenerlo durante 0 milisegundos) al inicio de
// la carrera. El barco no se moverá; habrá recorrido 0 milímetros al final de la carrera.

// Mantener presionado el botón durante 1 milisegundo al comienzo de la carrera. Luego, el
// barco viajará a una velocidad de 1 milímetro por milisegundo durante 6 milisegundos,
// alcanzando una distancia total recorrida de 6 milímetros.

// Mantener presionado el botón durante 2 milisegundos, dando al barco una velocidad de 2
// milímetros por milisegundo. Luego obtendrá 5 milisegundos para moverse, alcanzando una
// distancia total de 10 milímetros.


// Mantener presionado el botón durante 3 milisegundos. Después de sus restantes 4 milisegundos
// de tiempo de viaje, el barco habrá recorrido 12 milímetros.

// Mantener presionado el botón durante 4 milisegundos. Después de sus restantes 3 milisegundos
// de tiempo de viaje, el barco habrá recorrido 12 milímetros.

// Mantener presionado el botón durante 5 milisegundos, haciendo que el barco recorra un total
// de 10 milímetros.

// Mantener presionado el botón durante 6 milisegundos, haciendo que el barco recorra un total
// de 6 milímetros.

// Mantener presionado el botón durante 7 milisegundos. Esa es toda la duración de la carrera.
// Nunca sueltas el botón. El barco no puede moverse hasta que sueltes el botón. Por favor,
// asegúrate de soltar el botón para que el barco pueda moverse. 0 milímetros.

// Dado que el récord actual para esta carrera es de 9 milímetros, en realidad hay 4 formas
// diferentes en las que podrías ganar: podrías sostener el botón durante 2, 3, 4 o 5
// milisegundos al inicio de la carrera.

// En la segunda carrera, podrías mantener presionado el botón durante al menos 4
// milisegundos y como máximo 11 milisegundos y superar el récord, un total de 8
// formas diferentes de ganar.

// En la tercera carrera, podrías mantener presionado el botón durante al menos 11 milisegundos
// y no más de 19 milisegundos y aún así superar el récord, un total de 9 formas en las que
// podrías ganar.

// Para ver cuánto margen de error tienes, determina la cantidad de formas en las que puedes
// superar el récord en cada carrera; en este ejemplo, si multiplicas estos valores entre
// sí, obtienes 288 (4 * 8 * 9).

// Determina la cantidad de formas en las que podrías superar el récord en cada carrera.
// ¿Qué obtienes si multiplicas estos números entre sí?

fun main() {
    val tiempos = listOf(7, 15, 30)
    val distancias = listOf(9, 40, 200)

    val formasGanar = calcularFormasGanar(tiempos, distancias)
    val resultado = formasGanar.reduce { acc, i -> acc * i }

    println("El resultado es: $resultado")
}

fun calcularFormasGanar(tiempos: List<Int>, distancias: List<Int>): List<Int> {
    val formasGanar = mutableListOf<Int>()

    for (i in tiempos.indices) {
        val tiempoMaximo = tiempos[i]
        val distanciaRecord = distancias[i]

        var formas = 0
        for (tiempoBoton in 0 until tiempoMaximo) {
            val distanciaRecorrida = calcularDistanciaRecorrida(tiempoBoton)
            if (distanciaRecorrida > distanciaRecord) {
                formas++
            }
        }

        formasGanar.add(formas)
    }

    return formasGanar
}

fun calcularDistanciaRecorrida(tiempoBoton: Int): Int {
    val velocidadInicial = 0
    return velocidadInicial + tiempoBoton * (tiempoBoton + 1) / 2
}