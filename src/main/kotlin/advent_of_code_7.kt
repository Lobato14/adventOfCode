
// --- Día 7: Cartas de Camel ---

// --- PARTE 1 ----

// Tu viaje con todos los gastos pagados resulta ser un paseo de cinco minutos en un dirigible.
// (¡Al menos es un dirigible genial!) Te deja en el borde de un vasto desierto y desciende de
// regreso a la Isla Isla.

// "¿Trajiste las piezas?"

// Te volteas para ver a un Elfo completamente cubierto de ropa blanca, con gafas y montando un
// gran camello.

// "¿Trajiste las piezas?", pregunta de nuevo, esta vez más alto. No estás seguro de qué piezas
// está buscando; estás aquí para descubrir por qué se detuvo la arena.

// "¡Las piezas! ¡Para la arena, sí! Ven conmigo; te mostraré". Te hace señas para que subas al
// camello.

// Después de cabalgar un poco por las arenas de la Isla del Desierto, puedes ver lo que
// parecen ser rocas muy grandes que cubren la mitad del horizonte. La Elfa explica que las
// rocas están a lo largo de la parte de la Isla del Desierto que está directamente sobre la
// Isla Isla, lo que hace difícil llegar allí. Normalmente, usan grandes máquinas para mover
// las rocas y filtrar la arena, pero las máquinas se han averiado porque la Isla del Desierto
// dejó de recibir las piezas que necesitan para repararlas.

// Ya has asumido que será tu trabajo descubrir por qué se detuvieron las piezas cuando ella te
// pregunta si puedes ayudar. Aceptas automáticamente.

// Como el viaje tomará algunos días, te ofrece enseñarte el juego de Camel Cards. Camel Cards
// es algo similar al póker, excepto que está diseñado para ser más fácil de jugar mientras se
// monta en un camello.

// En Camel Cards, recibes una lista de manos y tu objetivo es ordenarlas según la fuerza de
// cada mano. Una mano consta de cinco cartas etiquetadas como A, K, Q, J, T, 9, 8, 7, 6, 5, 4,
// 3 o 2. La fuerza relativa de cada carta sigue este orden, donde A es la más alta y 2 es la
// más baja.

// Cada mano es exactamente un tipo. De más fuerte a más débil, son:

// - Five of a kind, donde las cinco cartas tienen la misma etiqueta: AAAAA
// - Four of a kind, donde cuatro cartas tienen la misma etiqueta y una carta tiene
// una etiqueta diferente: AA8AA
// - Full house, donde tres cartas tienen la misma etiqueta y las dos cartas restantes
// comparten una etiqueta diferente: 23332
// - Three of a kind, donde tres cartas tienen la misma etiqueta y las dos cartas restantes
// son diferentes entre sí: TTT98
// - Two pair, donde dos cartas comparten una etiqueta, otras dos cartas comparten una segunda
// etiqueta y la carta restante tiene una tercera etiqueta: 23432
// - One pair, donde dos cartas comparten una etiqueta y las otras tres cartas tienen una
// etiqueta diferente a la pareja y entre sí: A23A4
// - High card, donde todas las etiquetas de las cartas son distintas: 23456

// Las manos se ordenan principalmente según su tipo; por ejemplo, cualquier full house es más
// fuerte que cualquier three of a kind.

// Si dos manos tienen el mismo tipo, se aplica una segunda regla de ordenamiento. Comienza
// comparando la primera carta de cada mano. Si estas cartas son diferentes, la mano con la
// carta más fuerte se considera más fuerte. Si la primera carta de cada mano tiene la misma
// etiqueta, entonces pasa a considerar la segunda carta de cada mano. Si son diferentes,
// gana la mano con la segunda carta más alta; de lo contrario, continúa con la tercera carta
// de cada mano, luego la cuarta y finalmente la quinta.

// Entonces, 33332 y 2AAAA son ambas manos de four of a kind, pero 33332 es más fuerte porque
// su primera carta es más fuerte. Del mismo modo, 77888 y 77788 son ambas manos de full house,
// pero 77888 es más fuerte porque su tercera carta es más fuerte (y ambas manos tienen la
// misma primera y segunda carta).

// Para jugar Camel Cards, se te proporciona una lista de manos y sus respectivas apuestas
// (tu entrada de rompecabezas). Por ejemplo:

// 32T3K 765
// T55J5 684
// KK677 28
// KTJJT 220
// QQQJA 483

// Este ejemplo muestra cinco manos; cada mano está seguida por la cantidad apostada. Cada mano
// gana una cantidad igual a su apuesta multiplicada por su rango, donde la mano más débil
// recibe el rango 1, la segunda más débil recibe el rango 2, y así sucesivamente hasta la mano
// más fuerte.

// Entonces, el primer paso es ordenar las manos según su fuerza:

// - 32T3K es el único one pair y las otras manos son de un tipo más fuerte, por lo que recibe
// el rango 1.
// - KK677 y KTJJT son ambas two pair. Sus primeras cartas tienen la misma etiqueta, pero la
// segunda carta de KK677 es más fuerte (K vs. T), por lo que KTJJT recibe el rango 2 y KK677
// recibe el rango 3.
// - T55J5 y QQQJA son ambas three of a kind. QQQJA tiene una primera carta más fuerte, por lo
// que recibe el rango 5 y T55J5 recibe el rango 4.

// Ahora, puedes determinar las ganancias totales de este conjunto de manos sumando el
// resultado de multiplicar la apuesta de cada mano por su rango
// (765 * 1 + 220 * 2 + 28 * 3 + 684 * 4 + 483 * 5). Entonces, las ganancias totales en
// este ejemplo son 6440.

// Encuentra el rango de cada mano en tu conjunto. ¿Cuáles son las ganancias totales?

fun main() {
    val ordenDeCartas1 = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    val ordenDeCartas2 = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')

    val input = readInput("Day07")

    val manos = input
        .map { mano -> mano.split(" ")}
        .map { par ->
            Triple(par[0],
                par[0].toListChars(),
                par[1].toInt())
        }

    val compararManos1 = Comparator<Triple<String, List<Pair<Char, Int>>, Int>> { t1, t2 ->
        var contador = 0
        for (i in 0..4) {
            val i1 = ordenDeCartas1.indexOf(t1.first[i])
            val i2 = ordenDeCartas1.indexOf(t2.first[i])
            contador = i2.compareTo(i1)
            if (contador != 0) break
        }
        contador
    }

    val highCardSorted1 = manos.filter { it.second[0].second == 1 && it.second[1].second == 1 }.sortedWith(compararManos1)
    val onePairSorted1 = manos.filter { it.second[0].second == 2 && it.second[1].second == 1 }.sortedWith(compararManos1)
    val twoPairSorted1 = manos.filter { it.second[0].second == 2 && it.second[1].second == 2 }.sortedWith(compararManos1)
    val threeOfAKindSorted1 = manos.filter { it.second[0].second == 3 && it.second[1].second == 1 }.sortedWith(compararManos1)
    val fullHouseSorted1 = manos.filter { it.second[0].second == 3 && it.second[1].second == 2 }.sortedWith(compararManos1)
    val fourOfAKindSorted1 = manos.filter { it.second[0].second == 4 }.sortedWith(compararManos1)
    val fiveOfAKindSorted1 = manos.filter { it.second[0].second == 5 }.sortedWith(compararManos1)

    val ordenarManos1 = highCardSorted1 + onePairSorted1 + twoPairSorted1 + threeOfAKindSorted1 + fullHouseSorted1 +
            fourOfAKindSorted1 + fiveOfAKindSorted1

    var totalDeGanancias = 0L
    for ((index, t) in ordenarManos1.withIndex()) {
        totalDeGanancias += t.third.toLong() * (index + 1)
    }

    println("Parte 1: $totalDeGanancias")

// -- Parte 2 ----

// Para hacer las cosas un poco más interesantes, el Elfo introduce una regla adicional. Ahora,
// las cartas J son comodines, es decir, pueden actuar como cualquier carta que haga que la mano
// sea del tipo más fuerte posible.

// Para equilibrar esto, las cartas J ahora son las cartas individuales más débiles, incluso más
// débiles que el 2. Las demás cartas siguen en el mismo orden: A, K, Q, T, 9, 8, 7, 6, 5, 4, 3,
// 2, J.

// Las cartas J pueden pretender ser cualquier carta que sea mejor para determinar el tipo de
// mano; por ejemplo, QJJQ2 ahora se considera un full house. Sin embargo, para romper empates
// entre dos manos del mismo tipo, la carta J siempre se trata como J, no como la carta que está pretendiendo ser: JKKK2 es más débil que QQQQ2 porque J es más débil que Q.

// Ahora, el ejemplo anterior va de manera diferente:

// - 32T3K 765: Todavía es el único par; no contiene comodines, por lo que su fuerza no aumenta.
// - T55J5 684: Ahora es el único dos pares, convirtiéndose en la segunda mano más débil.
// - KK677 28: Ahora es el único full house, siendo la tercera mano más fuerte.
// - KTJJT 220: Ahora es uno de los varios four of a kind; obtiene el rango 5.
// - QQQJA 483: Ahora es uno de los varios four of a kind; obtiene el rango 4.
// - Con la nueva regla de los comodines, las ganancias totales en este ejemplo son 5905.

// Usando la nueva regla de los comodines, encuentra el rango de cada mano en tu conjunto.
// ¿Cuáles son las nuevas ganancias totales?

    val handsWithJ = input
        .map { h -> h.split(" ")}
        .map { pair ->
            val grupoDeManos = pair[0].toListChars()
            val nuevaMano = if (grupoDeManos[0].first == 'J' && grupoDeManos[0].second == 5) pair[0].replace('J', 'A')
            else if (grupoDeManos[0].first == 'J') pair[0].replace('J', grupoDeManos[1].first)
            else pair[0].replace('J', grupoDeManos[0].first)

            Triple(pair[0],
                nuevaMano.toListChars(),
                pair[1].toInt())
        }

    val compareHands2 = Comparator<Triple<String, List<Pair<Char, Int>>, Int>> { t1, t2 ->
        var c = 0
        for (i in 0..4) {
            val i1 = ordenDeCartas2.indexOf(t1.first[i])
            val i2 = ordenDeCartas2.indexOf(t2.first[i])
            c = i2.compareTo(i1)
            if (c != 0) break
        }
        c
    }

    val highCardSorted2 = handsWithJ.filter { it.second[0].second == 1 && it.second[1].second == 1 }.sortedWith(compareHands2)
    val onePairSorted2 = handsWithJ.filter { it.second[0].second == 2 && it.second[1].second == 1 }.sortedWith(compareHands2)
    val twoPairSorted2 = handsWithJ.filter { it.second[0].second == 2 && it.second[1].second == 2 }.sortedWith(compareHands2)
    val threeOfAKindSorted2 = handsWithJ.filter { it.second[0].second == 3 && it.second[1].second == 1 }.sortedWith(compareHands2)
    val fullHouseSorted2 = handsWithJ.filter { it.second[0].second == 3 && it.second[1].second == 2 }.sortedWith(compareHands2)
    val fourOfAKindSorted2 = handsWithJ.filter { it.second[0].second == 4 }.sortedWith(compareHands2)
    val fiveOfAKindSorted2 = handsWithJ.filter { it.second[0].second == 5 }.sortedWith(compareHands2)

    val ordenarMAnos2 = highCardSorted2 + onePairSorted2 + twoPairSorted2 + threeOfAKindSorted2 + fullHouseSorted2 +
            fourOfAKindSorted2 + fiveOfAKindSorted2


    totalDeGanancias = 0L
    for ((index, t) in ordenarMAnos2.withIndex()) {
        totalDeGanancias += t.third.toLong() * (index + 1)
    }

    println("Parte 2: $totalDeGanancias")
}

// Función para listar caracteres
fun String.toListChars(): List<Pair<Char, Int>> {
    return this.toList()
        .groupBy { it }
        .mapValues { it.value.count() }
        .toList()
        .sortedByDescending { it.second }
}