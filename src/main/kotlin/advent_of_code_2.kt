// --- Día 2: Cube Conundrum ---

// Eres lanzado a lo alto de la atmósfera. El vértice de tu trayectoria apenas alcanza la superficie de una gran isla
// que flota en el cielo. Aterrizas suavemente en un mullido montón de hojas. Hace bastante frío, pero no ves mucha
// nieve. Un elfo corre a saludarte.

// El elfo te explica que has llegado a la Isla de las Nieves y se disculpa por la falta de nieve. Estará encantado de
// explicarte la situación, pero hay que caminar un poco, así que tienes algo de tiempo. No reciben muchas visitas aquí
// arriba; ¿te gustaría jugar a algo mientras tanto?

// Mientras camináis, el elfo os enseña una pequeña bolsa y unos cubos de color rojo, verde o azul. Cada vez que juegues
// a este juego, él esconderá un número secreto de cubos de cada color en la bolsa, y tu objetivo es averiguar
// información sobre el número de cubos.

// Para obtener la información, una vez que la bolsa esté llena de cubos, el Duende meterá la mano en la bolsa, cogerá
// un puñado de cubos al azar, te los enseñará y los volverá a meter en la bolsa. Hará esto varias veces por partida.

// Juega varias partidas y anota la información de cada una de ellas (la información de tu puzzle). Cada juego se lista
// con su número de identificación (como el 11 en Juego 11: ...) seguido de una lista separada por punto y coma de
// subconjuntos de cubos que fueron revelados de la bolsa (como 3 rojo, 5 verde, 4 azul).

// Por ejemplo, el registro de unas cuantas partidas podría tener este aspecto:
//
// Juego 1: 3 azules, 4 rojos; 1 rojo, 2 verdes, 6 azules; 2 verdes
// Juego 2: 1 azul, 2 verdes; 3 verdes, 4 azules, 1 rojo; 1 verde, 1 azul
// Juego 3: 8 verde, 6 azul, 20 rojo; 5 azul, 4 rojo, 13 verde; 5 verde, 1 rojo
// Juego 4: 1 verde, 3 rojo, 6 azul; 3 verde, 6 rojo; 3 verde, 15 azul, 14 rojo
// Juego 5: 6 rojo, 1 azul, 3 verde; 2 azul, 1 rojo, 2 verde

// En el juego 1, se sacan tres grupos de cubos de la bolsa (y se vuelven a guardar). El primer grupo consta de 3 cubos
// azules y 4 rojos; el segundo grupo consta de 1 cubo rojo, 2 verdes y 6 azules; el tercer grupo consta sólo de 2 verdes.

// Al Duende le gustaría saber primero qué juegos habrían sido posibles si la bolsa contuviera sólo 12 cubos rojos,
// 13 cubos verdes y 14 cubos azules.

// En el ejemplo anterior, los juegos 1, 2 y 5 habrían sido posibles si la bolsa se hubiera cargado con esa
// configuración. Sin embargo, el juego 3 habría sido imposible porque en un momento dado el Duende te mostró 20 cubos
// rojos a la vez; del mismo modo, el juego 4 también habría sido imposible porque el Duende te mostró 15 cubos azules
// a la vez. Si sumas las ID de los juegos que habrían sido posibles, obtienes 8.

// Determina qué juegos habrían sido posibles si la bolsa se hubiera cargado sólo con 12 cubos rojos, 13 cubos verdes
// y 14 cubos azules. ¿Cuál es la suma de los ID de esos juegos?

fun main() {
    // Llama a las funciones parte1 y parte2 con la entrada del día 2 e imprime los resultados.
    calcularResultadoParte1(readInput("Day02")).println()
    calcularResultadoParte2(readInput("Day02")).println()
}

fun calcularResultadoParte1(entrada: List<String>): Int {
    // Inicializa la variable suma que almacenará el resultado.
    var suma = 0

    // Define los límites máximos de cubos para cada color.
    val maximoCuboRojo = 12
    val maximoCuboVerde = 13
    val maximoCuboAzul = 14

    // Itera sobre cada línea de la entrada enumerándola con su índice.
    for ((indice, linea) in entrada.withIndex()) {
        // Divide la línea en rondas y luego en cubos, almacenándolos en una lista.
        val rondas = linea.split(": ")[1].split("; ")
        val bolsaCubos = mutableListOf<String>()

        rondas.forEach {
            bolsaCubos += it.split(", ")
        }
        // Inicializa la variable esValido que determina si la ronda es válida.
        var esValido = true
        // Itera sobre cada cubo de la ronda verificando si supera el límite para cada color.
        bolsaCubos.forEach { cubo ->
            val arr = cubo.split(" ")
            if (arr[1] == "red" && arr[0].toInt() > maximoCuboRojo) {
                esValido = false
            } else if (arr[1] == "green" && arr[0].toInt() > maximoCuboVerde) {
                esValido = false
            } else if (arr[1] == "blue" && arr[0].toInt() > maximoCuboAzul) {
                esValido = false
            }
        }
        // Si la ronda es válida, suma el índice más 1 al resultado final.
        if (esValido) {
            suma += indice + 1
        }
    }
    // Devuelve el resultado final.
    return suma
}

fun calcularResultadoParte2(entrada: List<String>): Int {
    var suma = 0

    // Itera sobre cada línea de la entrada.
    for (linea in entrada) {
        // Divide la línea en rondas y luego en cubos, almacenándolos en una lista.
        val datosRonda = linea.split(": ")[1].split("; ")
        val datosCubos = mutableListOf<String>()

        datosRonda.forEach {
            datosCubos += it.split(", ")
        }

        // Inicializa las variables mínimas para cada color.
        var rojoMinimo = 0
        var verdeMinimo = 0
        var azulMinimo = 0

        // Itera sobre cada cubo de la ronda actual, actualizando los mínimos.
        datosCubos.forEach { cubo ->
            val arr = cubo.split(" ")
            if (arr[1] == "red") {
                if (arr[0].toInt() > rojoMinimo) {
                    rojoMinimo = arr[0].toInt()
                }
            } else if (arr[1] == "green") {
                if (arr[0].toInt() > verdeMinimo) {
                    verdeMinimo = arr[0].toInt()
                }
            } else if (arr[1] == "blue") {
                if (arr[0].toInt() > azulMinimo) {
                    azulMinimo = arr[0].toInt()
                }
            }
        }

        // Multiplica los mínimos y suma al resultado final.
        suma += rojoMinimo * verdeMinimo * azulMinimo
    }
    // Devuelve el resultado final.
    return suma
}