fun main() {
    fun fullyContainsOther(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return a.first <= b.first && a.second >= b.second
    }

    fun intersecting(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return a.first <= b.second && b.first <= a.second
    }

    fun intersecting(a: Pair<String, String>, b: Pair<String, String>): Boolean {
        return a.first.toInt() <= b.second.toInt() && b.first.toInt() <= a.second.toInt()
    }

    fun String.splitBy(char: Char): Pair<Int, Int> {
        return Pair(this.substringBefore(char).toInt(), this.substringAfter(char).toInt())
    }

    fun splitLine(string: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        return Pair(string.substringBefore(',').splitBy('-'), string.substringAfter(',').splitBy('-'))
    }

    fun List<String>.intoPairs(): List<Pair<String, String>> {
        return chunked(2).map { it[0] to it[1] }
    }

    fun splitLine2(string: String): Pair<Pair<String, String>, Pair<String, String>> {
        return string.replace(",", "-").split("-").intoPairs().zipWithNext().first()
    }


    fun checkLine(string: String): Int {
        val ranges = splitLine(string)
        return if (fullyContainsOther(ranges.first, ranges.second) || fullyContainsOther(
                ranges.second,
                ranges.first
            )
        ) 1 else 0
    }


    fun checkLine2(string: String): Int {
        val ranges = splitLine(string)
        return (if (intersecting(ranges.first, ranges.second) || intersecting(
                ranges.second,
                ranges.first
            )
        ) 1 else 0)
    }

    fun checkLine2_2(string: String): Int {
        val ranges = splitLine2(string)
        return (if (intersecting(ranges.first, ranges.second) || intersecting(
                ranges.second,
                ranges.first
            )
        ) 1 else 0)
    }


    fun part1(input: List<String>): Int {
        return input.sumOf { checkLine(it) }
    }


    fun part2(input: List<String>): Int {
        return input.sumOf { checkLine2(it) }
    }

    fun part2_2(input: List<String>): Int {
        return input.sumOf { checkLine2_2(it) }
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2_2(input))

    println(part2(input))
}
