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
    val input = readInput("Day06")
    println("Resultado Parte 1: ${multDistanciaTiempo(input)}")
    println("Resultado Parte 2: ${formasPosibles(input)}")
}

fun multDistanciaTiempo(listaTiempDist: List<String>): Int {
    // Definir una expresión regular para buscar espacios en blanco
    val espacioEnBlanco = Regex("\\s+")

    // Extraer y convertir los tiempos y distancias de la entrada
    val tiempo = listaTiempDist[0].removePrefix("Time:").trim().split(espacioEnBlanco).map { it.toInt() }
    val distancia = listaTiempDist[1].removePrefix("Distance:").trim().split(espacioEnBlanco).map { it.toInt() }

    // Calcula el resultado utilizando foldIndexed
    return tiempo.foldIndexed(1) { index, resto, temp ->
        val dist = distancia[index]
        resto * (1..temp).count { n ->
            (temp - n) * n > dist
        }
    }
}

// --- Parte Dos ---

// A medida que la carrera está a punto de comenzar, te das cuenta de que el trozo de papel
// con los tiempos de la carrera y las distancias registradas que obtuviste antes tiene un
// espaciado muy malo. En realidad, solo hay una carrera; ignora los espacios entre los
// números en cada línea.

// Entonces, el ejemplo anterior ahora se interpreta de la siguiente manera:

// Tiempo: 71530
// Distancia: 940200

// Ahora, debes averiguar cuántas formas hay de ganar esta única carrera. En este ejemplo,
// la carrera dura 71530 milisegundos y la distancia récord que debes superar es 940200
// milímetros. Podrías mantener presionado el botón en cualquier momento entre 14 y 71516
// milisegundos y superar el récord, ¡un total de 71503 formas!

// ¿Cuántas formas puedes superar el récord en esta carrera mucho más larga?

fun formasPosibles(listaFormas: List<String>): Int {
    // Definir una expresión regular para buscar espacios en blanco
    val espacioEnBlanco = Regex("\\s+")

    val tiempo = listaFormas[0].removePrefix("Time:").replace(espacioEnBlanco, "").toLong()
    val distancia = listaFormas[1].removePrefix("Distance:").replace(espacioEnBlanco, "").toLong()

    // Contar las formas posibles de superar el récord en la carrera
    return (1..tiempo).count { n ->
        (tiempo - n) * n > distancia
    }
}