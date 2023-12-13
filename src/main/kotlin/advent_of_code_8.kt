// --- Día 8: Desierto Encantado ---

// ---- Parte 1 ------

// Todavía estás montando un camello en la Isla del Desierto cuando ves una tormenta de arena que se
// acerca rápidamente. ¡Cuando te giras para advertirle al Elfo, ella desaparece ante tus ojos! Para
// ser justos, acababa de terminar de advertirte sobre fantasmas hace unos minutos.

// Una de las bolsas del camello está etiquetada como "mapas" y, efectivamente, está llena de documentos
// (tu entrada de rompecabezas) sobre cómo navegar por el desierto. Al menos, estás bastante seguro de
// que eso es lo que son; uno de los documentos contiene una lista de instrucciones de izquierda/derecha
// , y el resto de los documentos parecen describir algún tipo de red de nodos etiquetados.

// Parece que debes usar las instrucciones de izquierda/derecha para navegar por la red. Quizás, si
// haces que el camello siga las mismas instrucciones, ¡puedas escapar del desierto encantado!

// Después de examinar los mapas por un rato, dos nodos destacan: AAA y ZZZ. Sientes que AAA es donde
// estás ahora, y debes seguir las instrucciones de izquierda/derecha hasta llegar a ZZZ.

// Este formato define cada nodo de la red individualmente. Por ejemplo:

// RL

// AAA = (BBB, CCC)
// BBB = (DDD, EEE)
// CCC = (ZZZ, GGG)
// DDD = (DDD, DDD)
// EEE = (EEE, EEE)
// GGG = (GGG, GGG)
// ZZZ = (ZZZ, ZZZ)

// Comenzando con AAA, debes buscar el próximo elemento según la siguiente instrucción de
// izquierda/derecha en tu entrada. En este ejemplo, comienza con AAA y ve a la derecha (R) eligiendo
// el elemento derecho de AAA, CCC. Luego, L significa elegir el elemento izquierdo de CCC, ZZZ.
// Siguiendo las instrucciones de izquierda/derecha, llegarás a ZZZ en 2 pasos.

// Por supuesto, es posible que no encuentres ZZZ de inmediato. Si te quedas sin instrucciones de
// izquierda/derecha, repite toda la secuencia de instrucciones según sea necesario: RL realmente
// significa RLRLRLRLRLRLRLRL... y así sucesivamente. Por ejemplo, aquí hay una situación que lleva
// 6 pasos para llegar a ZZZ:

// LLR

// AAA = (BBB, BBB)
// BBB = (AAA, ZZZ)
// ZZZ = (ZZZ, ZZZ)

// Comenzando en AAA, sigue las instrucciones de izquierda/derecha. ¿Cuántos pasos se requieren para
// llegar a ZZZ?

fun main() {

    val input = readInput("Day08")
    val resultadoParte1 = pasosRqueridos(input)
    println("Parte 1: $resultadoParte1")

    val resultadoParte2 = pasosRequeridos2(input)
    println("Parte 2: $resultadoParte2")
}

// Función para calcular el máximo común divisor (MCD)
fun mcd(a: Long, b: Long): Long = if (b == 0L) a else mcd(b, a % b)

// Función para calcular el mínimo común múltiplo (MCM)
fun mcm(a: Long, b: Long): Long = (a * b) / mcd(a, b)

// Función de extensión para calcular el MCM(maximo comun divisor) de una lista de Long
fun List<Long>.mcm() = this.reduce { acc, n -> mcm(acc, n) }

// Función para analizar la entrada y devolver un par de instrucciones y un mapa de red
fun analizarEntrada(entrada: List<String>): Pair<String, Map<String, Pair<String, String>>> {
    val inst = entrada.first()
    val mapaRed = entrada.drop(2).associate { linea ->
        val (nodo, lr) = linea.split(" = ")
        val (izquierda, derecha) = lr.drop(1).dropLast(1).split(", ")
        nodo to (izquierda to derecha)
    }
    return inst to mapaRed
}

// Función para encontrar el número mínimo de pasos para llegar a un nodo final
fun encontrarMinimoPasos(instancia: String, mapaRed: Map<String, Pair<String, String>>,
                         inicio: String, esFin: (String) -> Boolean): Long {
    var pasos = 0
    var lugarActual = inicio
    while (!esFin(lugarActual)) {
        lugarActual = when (instancia[pasos % instancia.length]) {
            'L' -> mapaRed[lugarActual]?.first ?: ""
            else -> mapaRed[lugarActual]?.second ?: ""
        }
        pasos++
    }
    return pasos.toLong()
}

fun pasosRqueridos(entrada: List<String>): Long {
    val (inst, mapaRed) = analizarEntrada(entrada)
    return encontrarMinimoPasos(inst, mapaRed, "AAA") { it == "ZZZ" }
}

// --- Parte Dos ---

// La tormenta de arena está sobre ti y no estás más cerca de escapar del páramo. Hiciste que el
// camello siguiera las instrucciones, pero apenas has salido de tu posición de inicio. ¡Va a
// tomar significativamente más pasos para escapar!

// ¿Y si el mapa no es para personas, sino para fantasmas? ¿Los fantasmas están incluso sujetos a
// las leyes del espacio-tiempo? Solo hay una forma de averiguarlo.

// Después de examinar los mapas un poco más, tu atención se centra en un hecho curioso: ¡el número
// de nodos con nombres que terminan en A es igual al número que termina en Z! Si fueras un fantasma,
// probablemente comenzarías en cada nodo que termine con A y seguirías todos los caminos al mismo
// tiempo hasta que todos terminen simultáneamente en nodos que terminen con Z.

// Por ejemplo:

// LR

// 11A = (11B, XXX)
// 11B = (XXX, 11Z)
// 11Z = (11B, XXX)
// 22A = (22B, XXX)
// 22B = (22C, 22C)
// 22C = (22Z, 22Z)
// 22Z = (22B, 22B)
// XXX = (XXX, XXX)

// Aquí, hay dos nodos de inicio, 11A y 22A (porque ambos terminan con A). Al seguir cada
// instrucción de izquierda/derecha, usa esa instrucción para navegar simultáneamente lejos
// de ambos nodos en los que te encuentras actualmente. Repite este proceso hasta que todos
// los nodos en los que te encuentras actualmente terminen con Z. (Si solo algunos de los
// nodos en los que te encuentras terminan con Z, actúan como cualquier otro nodo y continúas
// normalmente). En este ejemplo, procederías de la siguiente manera:

// Paso 0: Estás en 11A y 22A.
// Paso 1: Eliges todos los caminos a la izquierda, llevándote a 11B y 22B.
// Paso 2: Eliges todos los caminos a la derecha, llevándote a 11Z y 22C.
// Paso 3: Eliges todos los caminos a la izquierda, llevándote a 11B y 22Z.
// Paso 4: Eliges todos los caminos a la derecha, llevándote a 11Z y 22B.
// Paso 5: Eliges todos los caminos a la izquierda, llevándote a 11B y 22C.
// Paso 6: Eliges todos los caminos a la derecha, llevándote a 11Z y 22Z.
// Así que, en este ejemplo, terminas completamente en nodos que terminan en Z después de 6 pasos.

// Comienza simultáneamente en cada nodo que termine con A. ¿Cuántos pasos se necesitan antes de
// que solo estés en nodos que terminen con Z?

fun pasosRequeridos2(entrada: List<String>): Long {
    val (inst, mapaRed) = analizarEntrada(entrada)
    return mapaRed.keys.filter { it.last() == 'A' }.map { inicio ->
        encontrarMinimoPasos(inst, mapaRed, inicio) { it.last() == 'Z' }
    }.mcm()
}