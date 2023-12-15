// --- Día 10: Laberinto de tuberías ---

// Usas el ala delta para viajar en el aire caliente desde la Isla Desierta hasta la isla flotante de
// metal. Esta isla es sorprendentemente fría y definitivamente no hay corrientes térmicas para planear
// ,así que deja atrás tu ala delta.

// Deambulas un rato, pero no encuentras personas ni animales. Sin embargo, de vez en cuando se
// encuentran señales con la etiqueta "Hot Springs" que apuntan en una dirección aparentemente
// consistente; tal vez puedas encontrar a alguien en las aguas termales y preguntarle dónde se
// fabrican las piezas de las máquinas del desierto.

// El paisaje aquí es extraño; Incluso las flores y los árboles están hechos de metal. Cuando te
// detienes a admirar un poco de pasto metálico, notas que algo metálico se escabulle en tu visión
// periférica y salta a una tubería grande. No se parecía a ningún animal que hayas visto jamás;
// Si quieres verlo mejor, tendrás que adelantarte.

// Al escanear el área, descubres que todo el campo en el que te encuentras está densamente poblado
// de tuberías; Al principio fue difícil saberlo porque son del mismo color plateado metálico que el
// "suelo". Haces un boceto rápido de todas las tuberías de superficie que puedes ver (tu entrada del
// rompecabezas).

// Las tuberías están dispuestas en una cuadrícula bidimensional de mosaicos:

// | Es un tubo vertical que conecta el norte y el sur.
// - es un tubo horizontal que conecta el este y el oeste.
// L es una curva de 90 grados que conecta el norte y el este.
// J es una curva de 90 grados que conecta el norte y el oeste.
// 7 es una curva de 90 grados que conecta el sur y el oeste.
// F es una curva de 90 grados que conecta el sur y el este.
// . es tierra; No hay ninguna tubería en este mosaico.
// S es la posición inicial del animal; Hay una tubería en este mosaico, pero su boceto no muestra
// qué forma tiene.

// Según la acústica del movimiento del animal, estás seguro de que el tubo que contiene al animal
// es un bucle grande y continuo.

// Por ejemplo, aquí hay un bucle cuadrado de tubería:

//.....
//.F-7.
//.|.|.
//.L-J.
//.....

// Si el animal hubiera entrado en este bucle en la esquina noroeste, el boceto se vería así:

//.....
//.S-7.
//.|.|.
//.L-J.
//.....

// En el diagrama anterior, la losa S sigue siendo una curva de 90 grados F: se puede saber por cómo
// se conectan las tuberías adyacentes a ella.

// Desafortunadamente, ¡también hay muchas tuberías que no están conectadas al circuito! Este boceto
// muestra el mismo bucle que el anterior:

//-L|F7
//7S-7|
//L|7||
//-L-J|
//L|-JF

// En el diagrama anterior, todavía puedes determinar qué tuberías forman el bucle principal: son las
// que están conectadas a S, las tuberías a las que se conectan esas tuberías, las tuberías a las que
// se conectan esas tuberías, y así sucesivamente. Cada tubería en el circuito principal se conecta a
// sus dos vecinos (incluido S, que tendrá exactamente dos tuberías conectadas y que se supone que se
// conectará nuevamente a esas dos tuberías).

// Aquí hay un boceto que contiene un bucle principal un poco más complejo:

//..F7.
//.FJ|.
//SJ.L7
//|F--J
//LJ...

// Aquí se muestra el mismo boceto de ejemplo con los mosaicos de tuberías adicionales que no son del
// circuito principal:

//7-F7-
//.FJ|7
//SJLL7
//|F--J
//LJ.LJ

// Si quieres adelantarte al animal, debes encontrar en el bucle la ficha que esté más alejada de la
// posición inicial. Debido a que el animal está en la tubería, no tiene sentido medirlo por distancia
// directa. En su lugar, necesitas encontrar la ficha que tomaría el mayor número de pasos a lo largo
// del circuito para llegar desde el punto de partida, independientemente de en qué dirección recorrió
// el circuito el animal.

// En el primer ejemplo con el bucle cuadrado:

//.....
//.S-7.
//.|.|.
//.L-J.
//.....

// Puedes contar la distancia que está cada mosaico en el bucle desde el punto inicial de esta manera:

//.....
//.012.
//.1.3.
//.234.
//.....

// En este ejemplo, el punto más alejado del inicio está a 4 pasos.

// Aquí está nuevamente el bucle más complejo:

//..F7.
//.FJ|.
//SJ.L7
//|F--J
//LJ...

// Aquí están las distancias para cada mosaico en ese bucle:

//..45.
//.236.
//01.78
//14567
//23...

// Encuentra el único bucle gigante que comienza en S. ¿Cuántos pasos a lo largo del bucle se necesitan
// para llegar desde la posición inicial hasta el punto más alejado de la posición inicial?

fun main(){

}