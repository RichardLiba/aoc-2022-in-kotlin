fun main() {
    fun getPriority(char: Char): Int {
        return if (char.isUpperCase()) char - 'A' + 27 else char - 'a' + 1
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            it.chunked(it.length / 2).let {
                it[0].toSet().intersect(it[1].toSet()).sumOf { getPriority(it) }
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).sumOf {
            it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet())
                .sumOf { getPriority(it) }
        }
    }

    val input = readInput("Day03")
    println(part1(input))

    println(part2(input))
}
