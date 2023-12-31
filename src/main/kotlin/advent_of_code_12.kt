
// --- Día 12: Aguas Termales ---

// --- Parte 1 ---

// Finalmente llegas a las aguas termales. Puedes ver vapor elevándose desde áreas
// apartadas del edificio principal y ornamentado.

// Al darte la vuelta para entrar, el investigador te detiene. "Espera, pensé que estabas
// buscando las aguas termales, ¿verdad?" Indicas que esto definitivamente parece aguas
// termales para ti.

// "Oh, lo siento, ¡error común! ¡Esto es en realidad un onsen! Las aguas termales están
// al lado."

// Miras en la dirección a la que apunta el investigador y de repente notas las enormes
// hélices de metal que se elevan sobre ti. "¡Por aquí!"

// Solo necesitas dar unos pocos pasos más para llegar a la puerta principal del enorme
// área cercada que contiene las fuentes termales. Pasas por la puerta y entras en un
// pequeño edificio administrativo.

// "¡Hola! ¿Qué te trae hoy a las aguas termales? Lo siento, no están muy calientes en
// este momento; estamos teniendo escasez de lava en estos momentos." Preguntas sobre las
// piezas de máquinas faltantes para Desert Island.

// "Oh, ¡toda Gear Island está fuera de línea en este momento! No se está fabricando nada
// en este momento, no hasta que obtengamos más lava para calentar nuestras forjas. Y
// nuestras fuentes termales. ¡Las fuentes no son muy vigorizantes a menos que estén
// calientes!"

// "Dime, ¿podrías subir y ver por qué se detuvo el flujo de lava? Las fuentes están
// demasiado frías para un funcionamiento normal, ¡pero deberíamos poder encontrar una
// lo suficientemente vigorizante para lanzarte hacia allá arriba!"

// Solo hay un problema: muchas de las fuentes están en mal estado, ¡así que ni siquiera
// están seguros de cuáles serían seguras de usar! Peor aún, los registros de las
// condiciones que indican qué fuentes están dañadas (tu entrada de rompecabezas) ¡también
// están dañados! Necesitarás ayudarles a reparar los registros dañados.

// En el gigantesco campo justo afuera, las fuentes están dispuestas en filas. Para cada
// fila, los registros de condiciones muestran cada fuente y si está operativa (.) o
// dañada (#). Esta es la parte de los registros de condiciones que está dañada en sí
// misma; para algunas fuentes, simplemente es desconocido (?) si la fuente está operativa
// o dañada.

// Sin embargo, el ingeniero que produjo los registros de condiciones también duplicó
// parte de esta información en un formato diferente. Después de la lista de fuentes para
// una fila dada, se muestra el tamaño de cada grupo contiguo de fuentes dañadas en el
// orden en que aparecen en la fila. Esta lista siempre tiene en cuenta cada fuente
// dañada, y cada número es el tamaño completo de su grupo contiguo (es decir, los grupos
// siempre están separados por al menos una fuente operativa: #### siempre serían 4, nunca
// 2,2).

// Así, los registros de condiciones sin condiciones de fuente desconocidas podrían verse
// así:

// #.#.### 1,1,3
// .#...#....###. 1,1,3
// .#.###.#.###### 1,3,1,6
// ####.#...#... 4,1,1
// #....######..#####. 1,6,5
// .###.##....# 3,2,1

// Sin embargo, los registros de condiciones están parcialmente dañados; algunas de las
// condiciones de las fuentes son en realidad desconocidas (?). Por ejemplo:

// ???.### 1,1,3
// .??..??...?##. 1,1,3
// ?#?#?#?#?#?#?#? 1,3,1,6
// ????.#...#... 4,1,1
// ????.######..#####. 1,6,5
// ?###???????? 3,2,1

// Equipado con esta información, tu tarea es averiguar cuántas disposiciones diferentes
// de fuentes operativas y dañadas cumplen con los criterios dados en cada fila.

// En la primera línea (???.### 1,1,3), hay exactamente una forma de separar grupos de
// una, una y tres fuentes dañadas (en ese orden) en esa fila: las primeras tres fuentes
// desconocidas deben estar dañadas, luego operativa, luego dañada (#.#), haciendo toda
// la fila #.#.###.

// La segunda línea es más interesante: .??..??...?##. 1,1,3 podría ser un total de cuatro
// disposiciones diferentes. El último ? siempre debe estar dañado (para satisfacer el
// último grupo contiguo de tres fuentes dañadas), y cada ?? debe ocultar exactamente una
// de las dos fuentes dañadas. (Ningún ?? podría ser ambas fuentes dañadas o formarían un
// solo grupo contiguo de dos; si eso fuera cierto, los números después serían 2,3 en su
// lugar). Dado que cada ?? puede ser #. o .#, hay cuatro disposiciones posibles de
// fuentes.

// ¡La última línea es en realidad consistente con diez disposiciones diferentes! Debido
// a que el primer número es 3, los dos primeros ? deben ser . (si alguno fuera #, el
// primer número tendría que ser 4 o más alto). Sin embargo, el resto de las condiciones
// de fuente desconocidas tienen muchas formas diferentes de contener grupos de dos y una
// fuente dañadas:

// ?###???????? 3,2,1
// .###.##.#...
// .###.##..#..
// .###.##...#.
// .###.##....#
// .###..##.#..
// .###..##..#.
// .###..##...#
// .###...##.#.
// .###...##..#
// .###....##.#

// En este ejemplo, la cantidad de disposiciones posibles para cada fila es:

// ???.### 1,1,3 - 1 disposición
// .??..??...?##. 1,1,3 - 4 disposiciones
// ?#?#?#?#?#?#?#? 1,3,1,6 - 1 disposición
// ????.#...#... 4,1,1 - 1 disposición
// ????.######..#####. 1,6,5 - 4 disposiciones
// ?###???????? 3,2,1 - 10 disposiciones

// Sumando todas las cuentas de disposiciones posibles, se obtiene un total de 21
// disposiciones.

// Para cada fila, cuenta todas las disposiciones diferentes de fuentes operativas y
// dañadas que cumplen con los criterios dados. ¿Cuál es la suma de esas cuentas?

fun main() {
    val entrada = readInput("Day12")

    // Parte 1
    var total = 0

    for (linea in entrada) {
        val (registro, grupos) = analizarDatos(linea)

        val registroConPuntos = "." + registro + "."
        total += busquedaEnProfundidad(registroConPuntos, grupos)
    }

    println("La suma total de disposiciones es $total")

}

// Función para analizar los datos de la línea y devolver un par de valores
fun analizarDatos(linea: String): Pair<String, List<Int>> {
    val (registro, gruposStr) = linea.split(" ")
    val grupos = gruposStr.split(",").map { it.toInt() }
    return Pair(registro, grupos)
}

// Función de búsqueda en profundidad (dfs)
fun busquedaEnProfundidad(registro: String, grupos: List<Int>): Int {
    if (grupos.isEmpty()) {
        return if ('#' in registro) 0 else 1
    }

    val tamanio = grupos[0]
    val restanteGrupos = grupos.drop(1)

    var contador = 0

    for (inicio in 0..registro.length - tamanio) {
        val fin = inicio + tamanio - 1
        if (encaja(registro, inicio, fin)) {
            contador += busquedaEnProfundidad(registro.substring(fin + 1), restanteGrupos)
        }
    }
    return contador
}

// Función para verificar si la configuración encaja
fun encaja(s: String, inicio: Int, fin: Int): Boolean {
    if (inicio < 1 || fin >= s.length - 1) {
        return false
    }
    if (s[inicio - 1] == '#' || s[fin + 1] == '#') {
        return false
    }

    if ('#' in s.substring(0, inicio)) {
        return false
    }
    for (i in inicio..fin) {
        if (s[i] == '.') {
            return false
        }
    }
    return true
}

// --- Parte 2 -----

// Al mirar el campo de manantiales, siente que hay muchos más manantiales que los que figuran en la lista
// de registros de condición. Cuando examinas los registros, descubres que en realidad estuvieron doblados
// todo este tiempo.

// Para desplegar los registros, en cada fila, reemplace la lista de condiciones del resorte con cinco
// copias de sí mismo (separadas por ?) y reemplace la lista de grupos contiguos de resortes dañados
// con cinco copias de sí mismo (separadas por ,).

// Entonces, esta fila:

// .# 1
// Se convertiría:

// .#?.#?.#?.#?.# 1,1,1,1,1
// La primera línea del ejemplo anterior sería:

// ???.###????.###????.###????.###????.### 1,1,3,1,1,3 ,1,1,3,1,1,3,1,1,3

// En el ejemplo anterior, después del despliegue, el número de disposiciones posibles para algunas
// filas ahora es mucho mayor:

// ???.### 1,1,3 - 1 arreglo
// .??..??...?##. 1,1,3 - 16384 arreglos
// ?#?#?#?#?#?#?#? 1,3,1,6 - 1 disposición
// ????.#...#... 4,1,1 - 16 arreglos
// ????.######..#####. 1,6,5 - 2500 arreglos
// ?###????????? 3,2,1 - 506250 arreglos

// Después de desplegar, sumar todos los recuentos de arreglos posibles produce 525152.

// Despliegue sus registros de condición; ¿Cuál es la nueva suma de posibles recuentos de arreglos?