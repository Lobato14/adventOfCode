// --- Día 5: Si a una semilla le das un fertilizante ---

// Parte 1

// Tomas el barco y encuentras al jardinero justo donde te dijeron que estaría: administrando un
// "jardín" gigante que te parece más bien una granja.

// "¿Una fuente de agua? ¡Island Island es la fuente de agua!" Señalas que Snow Island no recibe agua.

//"¡Oh, tuvimos que cortar el agua porque se nos acabó la arena para filtrarla! No se puede hacer
// nieve con agua sucia. No te preocupes, estoy seguro de que pronto tendremos más arena; solo
// apagamos el agua unos días... semanas... oh no." Su rostro se hunde en una expresión de horrorizada
// comprensión.

//"¡He estado tan ocupado asegurándome de que todos aquí tengan comida que me olvidé por completo de
// comprobar por qué dejamos de recibir más arena! Pronto saldrá un ferry que se dirige en esa
// dirección; es mucho más rápido que su barco. ¿Podría por favor ir? ¿Échale un vistazo?"

// Apenas tienes tiempo para aceptar esta solicitud cuando él menciona otra. "Mientras esperas el ferry
// ,tal vez puedas ayudarnos con nuestro problema de producción de alimentos. El último Almanaque de
// Island Island acaba de llegar y estamos teniendo problemas para entenderlo".

// El almanaque (su entrada del rompecabezas) enumera todas las semillas que deben plantarse.

// También enumera qué tipo de suelo usar con cada tipo de semilla, qué tipo de fertilizante usar con
// cada tipo de suelo, qué tipo de agua usar con cada tipo de fertilizante, etc. Cada tipo de semilla,
// suelo, fertilizante, etc. se identifica con un número, pero cada categoría reutiliza los números;
// es decir, el suelo 123 y el fertilizante 123 no están necesariamente relacionados entre sí.

// Por ejemplo:

// semillas: 79 14 55 13

// mapa semilla-suelo:
// 50 98 2
// 52 50 48

// mapa suelo-fertilizante:
// 0 15 37
// 37 52 2
// 39 0 15

// mapa de fertilizante a agua:
// 49 53 8
// 0 11 42
// 42 0 7
// 57 7 4

// mapa de agua a luz:
// 88 18 7
// 18 25 70

// mapa de luz a temperatura:
// 45 77 23
// 81 45 19
// 68 64 13

// mapa de temperatura-humedad:
// 0 69 1
// 1 0 69

// mapa de humedad a ubicación:
// 60 56 37
// 56 93 4
// El almanaque comienza enumerando qué semillas es necesario plantar: semillas 79, 14, 55 y 13.

// El resto del almanaque contiene una lista de mapas que describen cómo convertir números de una
// categoría de origen en números de una categoría de destino. Es decir, la sección que comienza
// con el mapa de semilla a suelo: describe cómo convertir un número de semilla (el origen) en un
// número de suelo (el destino). Esto permite al jardinero y a su equipo saber qué tierra usar con
// qué semillas, qué agua usar con qué fertilizante, etc.

// En lugar de enumerar cada número de origen y su correspondiente número de destino uno por uno,
// los mapas describen rangos completos de números que se pueden convertir. Cada línea dentro de un
// mapa contiene tres números: el inicio del rango de destino, el inicio del rango de origen y la
// longitud del rango.

// Consideremos nuevamente el ejemplo de mapa semilla-suelo:

// 50 98 2
// 52 50 48
// La primera línea tiene un inicio del rango de destino de 50, un inicio del rango de origen de 98
// y una longitud de rango de 2. Esta línea significa que el rango de origen comienza en 98 y contiene
// dos valores: 98 y 99. El rango de destino es el mismo de longitud, pero comienza en 50, por lo que
// sus dos valores son 50 y 51. Con esta información, sabes que la semilla número 98 corresponde al
// suelo número 50 y que la semilla número 99 corresponde al suelo número 51.

// La segunda línea significa que el rango de origen comienza en 50 y contiene 48 valores: 50, 51,
// ..., 96, 97. Esto corresponde a un rango de destino que comienza en 52 y también contiene 48
// valores: 52, 53,... , 98, 99. Entonces, la semilla número 53 corresponde al suelo número 55.

// Cualquier número de origen que no esté asignado corresponde al mismo número de destino.
// Entonces, la semilla número 10 corresponde al suelo número 10.

// Entonces, la lista completa de números de semillas y sus correspondientes números de suelo se ve
// así:

// suelo para semillas
// 0 0
// 1 1
//... ...
// 48 48
// 49 49
// 50 52
// 51 53
// ... ...
// 96 98
// 97 99
// 98 50
// 99 51
// Con este mapa, puede buscar el número de suelo requerido para cada número de semilla inicial:

// La semilla número 79 corresponde al suelo número 81.
// La semilla número 14 corresponde al suelo número 14.
// La semilla número 55 corresponde al suelo número 57.
// La semilla número 13 corresponde al suelo número 13.

// El jardinero y su equipo quieren empezar lo antes posible, por eso les gustaría saber el lugar
// más cercano que necesita una semilla. Usando estos mapas, encuentre el número de ubicación más
// bajo que corresponda a cualquiera de las semillas iniciales. Para hacer esto, necesitará convertir
// cada número de semilla a través de otras categorías hasta que pueda encontrar su número de
// ubicación correspondiente. En este ejemplo, los tipos correspondientes son:

// Semilla 79, tierra 81, fertilizante 81, agua 81, luz 74, temperatura 78, humedad 78, ubicación 82.
// Semilla 14, tierra 14, fertilizante 53, agua 49, luz 42, temperatura 42, humedad 43, ubicación 43.
// Semilla 55, tierra 57, fertilizante 57, agua 53, luz 46, temperatura 82, humedad 82, ubicación 86.
// Semilla 13, tierra 13, fertilizante 52, agua 41, luz 34, temperatura 34, humedad 35, ubicación 35.
// Entonces, el número de ubicación más bajo en este ejemplo es 35.

// ¿Cuál es el número de ubicación más bajo que corresponde a cualquiera de los números de semilla
// iniciales?

fun main() {
    val entradaPrueba = readInput("Day05")
    println("Resultado: ${parte1(entradaPrueba)}")
}
fun parte1(entrada: List<String>): Long {

    val semillas = extraerSemillas(entrada)
    val mapeadores = obtenerMapeadores(entrada)

    // Encontrar la semilla con la ubicación más baja después de aplicar todos los mapeadores
    return semillas.minOf { semilla ->
        mapeadores.fold(semilla) { semillaMapeada, mapeador ->
            mapeador.obtenerValorMapeado(semillaMapeada)
        }
    }
}

fun extraerSemillas(input: List<String>): List<Long> {
    // Obtener la última parte de la primera línea y convertirla en una lista de Long
    return input.first().split(':').last().split(' ').mapNotNull {
        if (it.isEmpty()) null else it.toLong()
    }
}

fun obtenerMapeadores(input: List<String>): List<List<List<Long>>> {
    // Obtener los índices donde comienzan las secciones de mapeadores en la entrada
    val i1 = input.indexOf("seed-to-soil map:")
    val i2 = input.indexOf("soil-to-fertilizer map:")
    val i3 = input.indexOf("fertilizer-to-water map:")
    val i4 = input.indexOf("water-to-light map:")
    val i5 = input.indexOf("light-to-temperature map:")
    val i6 = input.indexOf("temperature-to-humidity map:")
    val i7 = input.indexOf("humidity-to-location map:")

    // Obtener los datos de cada sección y formar una lista de mapeadores
    val mapaSemillaSuelo = input.obtenerListaDatos(i1, i2)
    val sueloFertilizanteMapa = input.obtenerListaDatos(i2, i3)
    val fertilizanteAguaMapa = input.obtenerListaDatos(i3, i4)
    val aguaLuzMapa = input.obtenerListaDatos(i4, i5)
    val luzATemperaturaMapa = input.obtenerListaDatos(i5, i6)
    val temperaturaAHumedadMapa = input.obtenerListaDatos(i6, i7)
    val humedadAUbicacionMapa = input.obtenerListaDatos(i7, input.size)

    // Devolver una lista con los mapeadores para cada categoría
    return listOf(
        mapaSemillaSuelo, sueloFertilizanteMapa, fertilizanteAguaMapa,
        aguaLuzMapa, luzATemperaturaMapa, temperaturaAHumedadMapa, humedadAUbicacionMapa
    )
}

fun List<String>.obtenerListaDatos(i1: Int, i2: Int): List<List<Long>> {
    // Calcular el índice de fin del rango
    val finRango = if (i2 == this.size) i2 else i2 - 1

    // Obtener los datos de la sección y convertirlos en una lista de listas de Long
    val datosRango = this.toTypedArray().copyOfRange((i1 + 1), finRango).toList().map { str ->
        str.split(' ').map { it.toLong() }
    }

    // Transformar cada lista de datos en una lista de Long con la estructura
    // [desplazamiento, inicioRango, finRango]
    return datosRango.map {
        val inicioRango = it[1]
        val cantidadDesplazamiento = it[0] - inicioRango
        val longitudRango = it[2]

        listOf(cantidadDesplazamiento, inicioRango, inicioRango + longitudRango)
    }
}

fun List<List<Long>>.obtenerValorMapeado(x: Long): Long {
    // Buscar el rango que incluye x y devolver x + desplazamiento
    this.map { if (x in it[1]..<it[2]) return x + it[0] }
    // Si no se encuentra en ningún rango, devolver x
    return x
}

// --- Parte Dos ---

// Todos pasarán hambre si solo siembras una cantidad tan pequeña de semillas. Releyendo el ç
// almanaque, parece que la línea de semillas (seeds:) describe realmente rangos de números
// de semillas.

// Los valores en la línea inicial de semillas (seeds:) vienen en pares. Dentro de cada par,
// el primer valor es el inicio del rango y el segundo valor es la longitud del rango.
// Entonces, en la primera línea del ejemplo anterior:

// seeds: 79 14 55 13

// Esta línea describe dos rangos de números de semillas para plantar en el jardín.
// El primer rango comienza con el número de semilla 79 y contiene 14 valores: 79, 80, ...,
// 91, 92. El segundo rango comienza con el número de semilla 55 y contiene 13 valores: 55,
// 56, ..., 66, 67.

// Ahora, en lugar de considerar cuatro números de semillas, necesitas considerar un total
// de 27 números de semillas.

//En el ejemplo anterior, el número de ubicación más bajo se puede obtener a partir del
// número de semilla 82, que corresponde a suelo 84, fertilizante 84, agua 84, luz 77,
// temperatura 45, humedad 46 y ubicación 46. Por lo tanto, el número de ubicación más
// bajo es 46.

// Considera todos los números de semillas iniciales enumerados en los rangos en la primera
// línea del almanaque. ¿Cuál es el número de ubicación más bajo que corresponde a alguno de
// los números de semillas iniciales?