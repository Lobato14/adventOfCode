// --- Día 18: Laguna de Conductos Lava ---

// --- Parte 1 ---

// Gracias a tus esfuerzos, la fábrica de piezas para la máquina es una de las primeras fábricas en funcionamiento
// desde que la cascada de lava regresó. Sin embargo, para ponerse al día con la gran cantidad de solicitudes de
// piezas, la fábrica también necesitará un suministro abundante de lava durante un tiempo; los elfos ya han
// comenzado a crear una gran laguna cercana con este propósito.

// Sin embargo, no están seguros de que la laguna sea lo suficientemente grande; te han pedido que revises el
// plan de excavación (tu entrada de rompecabezas). Por ejemplo:

// R 6 (#70c710)
// D 5 (#0dc571)
// L 2 (#5713f0)
// D 2 (#d2c081)
// R 2 (#59c680)
// D 2 (#411b91)
// L 5 (#8ceee2)
// U 2 (#caa173)
// L 1 (#1b58a2)
// U 2 (#caa171)
// R 2 (#7807d2)
// U 3 (#a77fa3)
// L 2 (#015232)
// U 2 (#7a21e3)

// El excavador comienza en un agujero de 1 metro cúbico en el suelo. Luego excava la cantidad especificada de
// metros hacia arriba (U), hacia abajo (D), hacia la izquierda (L) o hacia la derecha (R), despejando cubos
// completos de 1 metro a medida que avanza. Las direcciones se dan como se ven desde arriba, por lo que si
// "arriba" fuera el norte, entonces "derecha" sería el este, y así sucesivamente. Cada zanja también se lista
// con el color que el borde de la zanja debería ser pintado como un código hexadecimal de color RGB.

// Cuando se ve desde arriba, el plan de excavación de ejemplo anterior resultaría en el siguiente bucle de zanjas
// (#) que se han excavado desde el terreno de nivel del suelo (.):

// #######
// #.....#
// ###...#
// ..#...#
// ..#...#
// ###.###
// #...#..
// ##..###
// .#....#
// .######

// En este punto, la zanja podría contener 38 metros cúbicos de lava. Sin embargo, esto es solo el borde de la laguna;
// el siguiente paso es excavar el interior para que también tenga un metro de profundidad:

// #######
// #######
// #######
// ..#####
// ..#####
// #######
// #####..
// #######
// .######
// .######

// Ahora, la laguna puede contener una cantidad mucho más respetable de 62 metros cúbicos de lava. Mientras se
// excava el interior, los bordes también se pintan según los códigos de color en el plan de excavación.

// Los elfos están preocupados de que la laguna no sea lo suficientemente grande; si siguen su plan de excavación,
// ¿cuántos metros cúbicos de lava podría contener?
