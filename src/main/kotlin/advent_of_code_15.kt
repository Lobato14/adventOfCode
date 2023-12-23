// --- Día 15: Biblioteca de Lentes ---

// El recién enfocado plato reflector parabólico está enviando toda la luz recopilada a un punto en
// el costado de otra montaña, la montaña más grande de la Isla Lava. Al acercarte a la montaña,
// descubres que la luz está siendo recogida por la pared de una gran instalación incrustada en el
// costado de la montaña.

// Encuentras una puerta debajo de un letrero grande que dice "Instalación de Producción de Lava" y
// junto a un letrero más pequeño que dice "Peligro - Se requiere equipo de protección personal más
// allá de este punto".

// Al entrar, te saluda inmediatamente un reno algo nervioso que lleva gafas y un casco suelto. El
// reno te lleva a un estante con gafas y cascos (encuentras rápidamente algunos que te quedan bien)
// y luego más adentro en la instalación. En un momento, pasas por un botón con una marca de hocico
// tenue y la etiqueta "PRESIONAR PARA AYUDA". No es de extrañar que te hayan cargado en esa catapulta
// tan rápidamente.

// Atraviesas un último conjunto de puertas rodeadas de aún más señales de advertencia y llegas a lo
// que debe ser la sala que recopila toda la luz del exterior. Mientras admiras la gran variedad de
// lentes disponibles para enfocar aún más la luz, el reno te trae un libro titulado "Manual de
// Inicialización".

// "¡Hola!", comienza el libro alegremente, aparentemente ajeno al reno preocupado que lee por encima
// de tu hombro. "Este procedimiento te permitirá poner en línea la Instalación de Producción de Lava,
// ¡todo sin quemar o derretir nada no deseado!"

// "Antes de comenzar, por favor, prepárate para usar el algoritmo Holiday ASCII String Helper (apéndice
// 1A)." Pasas a la sección 1A. El reno se acerca con interés.

// El algoritmo HASH es una forma de convertir cualquier cadena de caracteres en un solo número en el
// rango de 0 a 255. Para ejecutar el algoritmo HASH en una cadena, comienza con un valor actual de 0.
// Luego, para cada carácter en la cadena comenzando desde el principio:

// Determina el código ASCII para el carácter actual de la cadena.
// Aumenta el valor actual por el código ASCII que acabas de determinar.
// Establece el valor actual multiplicándolo por 17.
// Establece el valor actual como el residuo de dividirlo por 256.

// Después de seguir estos pasos para cada carácter en la cadena en orden, el valor actual es la salida
// del algoritmo HASH.

// Entonces, para encontrar el resultado de ejecutar el algoritmo HASH en la cadena HASH:

// El valor actual comienza en 0.
// El primer carácter es H; su código ASCII es 72.
// El valor actual aumenta a 72.
// El valor actual se multiplica por 17 para convertirse en 1224.
// El valor actual se convierte en 200 (el residuo de dividir 1224 por 256).
// El siguiente carácter es A; su código ASCII es 65.
// El valor actual aumenta a 265.
// El valor actual se multiplica por 17 para convertirse en 4505.
// El valor actual se convierte en 153 (el residuo de dividir 4505 por 256).
// El siguiente carácter es S; su código ASCII es 83.
// El valor actual aumenta a 236.
// El valor actual se multiplica por 17 para convertirse en 4012.
// El valor actual se convierte en 172 (el residuo de dividir 4012 por 256).
// El siguiente carácter es H; su código ASCII es 72.
// El valor actual aumenta a 244.
// El valor actual se multiplica por 17 para convertirse en 4148.
// El valor actual se convierte en 52 (el residuo de dividir 4148 por 256).
// Así, el resultado de ejecutar el algoritmo HASH en la cadena HASH es 52.

// La secuencia de inicialización (tu entrada de rompecabezas) es una lista separada por comas de pasos
// para poner en marcha la Instalación de Producción de Lava. Ignora los caracteres de nueva línea al
// analizar la secuencia de inicialización. Para verificar que tu algoritmo HASH está funcionando,
// el libro ofrece la suma del resultado de ejecutar el algoritmo HASH en cada paso de la secuencia
// de inicialización.

// Por ejemplo:

// rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7

// Esta secuencia de inicialización especifica 11 pasos individuales; el resultado de ejecutar el
// algoritmo HASH en cada uno de los pasos es el siguiente:

// rn=1 se convierte en 30.
// cm- se convierte en 253.
// qp=3 se convierte en 97.
// cm=2 se convierte en 47.
// qp- se convierte en 14.
// pc=4 se convierte en 180.
// ot=9 se convierte en 9.
// ab=5 se convierte en 197.
// pc- se convierte en 48.
// pc=6 se convierte en 214.
// ot=7 se convierte en 231.

// En este ejemplo, la suma de estos resultados es 1320. Desafortunadamente, el reno ha robado la
// página que contiene el número de verificación esperado y actualmente está corriendo emocionado
// alrededor de la instalación con ella.

// Ejecuta el algoritmo HASH en cada paso de la secuencia de inicialización. ¿Cuál es la suma de los
// resultados? (La secuencia de inicialización es una sola línea larga; ten cuidado al copiarla).

fun main() {
    fun miHash(input: String): Int {
        var currentValue = 0
        for (caracter in input) {
            currentValue += caracter.code
            currentValue = (currentValue * 17) % 256
        }
        return currentValue
    }

    fun part1(input: List<String>): Int {
        return input[0].split(",").sumOf {
            miHash(it.trim())
        }
    }

    fun part2(instructions: List<String>): Int {
        val boxes = List<MutableMap<String, Int>>(256) { mutableMapOf() }

        instructions.flatMap { it.split(",") }.forEach { instruction ->
            if (instruction.endsWith("-")) {
                val label = instruction.substringBefore('-')
                boxes[miHash(label)].remove(label)
            } else {
                val label = instruction.substringBefore('=')
                boxes[miHash(label)][label] = instruction.substringAfter("=").toInt()
            }
        }

        return boxes.withIndex().sumOf { (boxNumber, lenses) ->
            lenses.values.withIndex().sumOf { (lensNumber, lens) ->
                (boxNumber + 1) * (lensNumber + 1) * lens
            }
        }
    }

    // Parte 1 --- Realizada ----
    val testInput = readInput("Day15_test")
    check(part1(testInput) == 1320)

    println("Test passed!")

    println("Input for part 1:")
    testInput.forEach { println(it) }

    val input = readInput("Day15")
    println("Part 1 result: ${part1(input)}")

    // Parte 2 --- Realizada ----
    val testInput2 = readInput("Day15_test")
    check(part2(testInput2) == 145)

    println("Test passed!")

    println("Input for part 2:")
    testInput2.forEach { println(it) }

    val input2 = readInput("Day15")
    println("Part 2 result: ${part2(input2)}")
}


// Te convinces de que los renos te traigan la página; la página confirma que tu algoritmo HASH está funcionando.

// El libro continúa describiendo una serie de 256 cajas numeradas del 0 al 255. Las cajas están dispuestas en
// línea desde el punto donde la luz entra en la instalación. Las cajas tienen agujeros que permiten que la luz
// pase de una caja a la siguiente a lo largo de la línea.

//       +-----+  +-----+         +-----+
// Luz   | Caja|  | Caja|   ...   | Caja|
// ----------------------------------------->
//       |  0  |  |  1  |   ...   | 255 |
//       +-----+  +-----+         +-----+

// Dentro de cada caja, hay varios espacios para lentes que mantendrán una lente correctamente posicionada para
// enfocar la luz que pasa a través de la caja. El lateral de cada caja tiene un panel que se abre para permitirte
// insertar o quitar lentes según sea necesario.

// A lo largo de la pared que corre paralela a las cajas hay una gran biblioteca que contiene lentes organizadas
// por longitud focal que va desde 1 hasta 9. El reno también te trae una pequeña impresora de etiquetas portátil.

// El libro continúa explicando cómo realizar cada paso en la secuencia de inicialización, un proceso que llama
// Procedimiento de Organización Manual de Cadenas ASCII de Vacaciones, o HASHMAP en resumen.

// Cada paso comienza con una secuencia de letras que indica la etiqueta de la lente en la que opera el paso.
// El resultado de ejecutar el algoritmo HASH en la etiqueta indica la caja correcta para ese paso.

// La etiqueta estará seguida inmediatamente por un carácter que indica la operación a realizar: o un signo igual
// (=) o un guión (-).

// Si el carácter de operación es un guión (-), ve a la caja relevante y retira la lente con la etiqueta dada si
// está presente en la caja. Luego, mueve cualquier lente restante lo más hacia adelante en la caja sin cambiar
// su orden, llenando cualquier espacio creado al quitar la lente indicada. (Si ninguna lente en esa caja tiene
// la etiqueta dada, no sucede nada).

// Si el carácter de operación es un signo igual (=), estará seguido por un número que indica la longitud focal de
// la lente que debe ir en la caja relevante; asegúrate de usar la impresora de etiquetas para marcar la lente con
// la etiqueta dada al principio del paso para que puedas encontrarla más tarde. Hay dos situaciones posibles:

// Si ya hay una lente en la caja con la misma etiqueta, reemplaza la lente antigua con la nueva lente: retira
// la lente antigua y coloca la nueva lente en su lugar, sin mover ninguna otra lente en la caja.
// Si no hay una lente en la caja con la misma etiqueta, agrega la lente a la caja inmediatamente detrás de
// cualquier lente que ya esté en la caja. No muevas ninguna de las otras lentes cuando lo hagas. Si no hay
// ninguna lente en la caja, la nueva lente va hasta el frente de la caja.
// Aquí está el contenido de cada caja después de cada paso en el ejemplo de la secuencia de inicialización:

// Después de "rn=1":
// Caja 0: [rn 1]

// Después de "cm-":
// Caja 0: [rn 1]

// Después de "qp=3":
// Caja 0: [rn 1]
// Caja 1: [qp 3]

// Después de "cm=2":
// Caja 0: [rn 1] [cm 2]
// Caja 1: [qp 3]

// Después de "qp-":
// Caja 0: [rn 1] [cm 2]

// Después de "pc=4":
// Caja 0: [rn 1] [cm 2]
// Caja 3: [pc 4]

// Después de "ot=9":
// Caja 0: [rn 1] [cm 2]
// Caja 3: [pc 4] [ot 9]

// Después de "ab=5":
// Caja 0: [rn 1] [cm 2]
// Caja 3: [pc 4] [ot 9] [ab 5]

// Después de "pc-":
// Caja 0: [rn 1] [cm 2]
// Caja 3: [ot 9] [ab 5]

// Después de "pc=6":
// Caja 0: [rn 1] [cm 2]
// Caja 3: [ot 9] [ab 5] [pc 6]

// Después de "ot=7":
// Caja 0: [rn 1] [cm 2]
// Caja 3: [ot 7] [ab 5] [pc 6]

// Siempre hay 256 cajas presentes; aquí solo se muestran las cajas que contienen lentes. Dentro de cada caja,
// las lentes se enumeran de adelante hacia atrás; cada lente se muestra como su etiqueta y longitud focal entre
// corchetes cuadrados.

// Para confirmar que todas las lentes están instaladas correctamente, suma la potencia de enfoque de todas las
// lentes. La potencia de enfoque de una sola lente es el resultado de multiplicar:

// Uno más el número de la caja de la lente en cuestión.
// El número de ranura de la lente dentro de la caja: 1 para la primera lente, 2 para la segunda lente, y
// así sucesivamente.
// La longitud focal de la lente.
// Al final del ejemplo anterior, la potencia de enfoque de cada lente es la siguiente:

// rn: 1 (caja 0) * 1 (primera ranura) * 1 (longitud focal) = 1
// cm: 1 (caja 0) * 2 (segunda ranura) * 2 (longitud focal) = 4
// ot: 4 (caja 3) * 1 (primera ranura) * 7 (longitud focal) = 28
// ab: 4 (caja 3) * 2 (segunda ranura) * 5 (longitud focal) = 40
// pc: 4 (caja 3) * 3 (tercera ranura) * 6 (longitud focal) = 72
// Entonces, el ejemplo anterior termina con una potencia de enfoque total de 145.

// Con la ayuda de un reno demasiado entusiasta con un casco duro, sigue la secuencia de inicialización.
// ¿Cuál es la potencia de enfoque de la configuración resultante de las lentes?