fun main() {
    fun miHash2(input: String): Int {
        var currentValue = 0
        for (caracter in input) {
            currentValue += caracter.code
            currentValue = (currentValue * 17) % 256
        }
        return currentValue
    }

    fun part2(instructions: List<String>): Int {
        val boxes = List<MutableMap<String, Int>>(256) { mutableMapOf() }

        instructions.forEach { instruction ->
            if (instruction.endsWith("-")) {
                val label = instruction.substringBefore('-')
                boxes[miHash2(label)].remove(label)
            } else {
                val label = instruction.substringBefore('=')
                boxes[miHash2(label)][label] = instruction.substringAfter("=").toInt()
            }
        }

        return boxes.withIndex().sumOf { (boxNumber, lenses) ->
            lenses.values.withIndex().sumOf { (lensNumber, lens) ->
                (boxNumber + 1) * (lensNumber + 1) * lens
            }
        }
    }

    val sequence = readInput("Day15_test").firstOrNull() ?: ""
    val steps = sequence.split(",")

    val focusPower = part2(steps)
    println("Potencia de enfoque: $focusPower")
}