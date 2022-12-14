fun main() {

    val values = mapOf(
        //rock
        "A" to 1,
        //paper
        "B" to 2,
        //scissors
        "C" to 3,
        //rock
        "X" to 1,
        //paper
        "Y" to 2,
        //scissors
        "Z" to 3
    )

    fun evalWin(round: Pair<String, String>): Int {
        return when (round.first) {
            "A" -> when (round.second) {
                "X" -> 3
                "Y" -> 6
                "Z" -> 0
                else -> 0
            }
            "B" -> when (round.second) {
                "X" -> 0
                "Y" -> 3
                "Z" -> 6
                else -> 0
            }
            "C" -> when (round.second) {
                "X" -> 6
                "Y" -> 0
                "Z" -> 3
                else -> 0
            }
            else -> 0
        }
    }

    //X- loose, Y-draw, Z-win
    fun evalWin2(round: Pair<String, String>): Int {
        return when (round.second) {
            "X" -> when (round.first) {
                "A" -> 3
                "B" -> 1
                "C" -> 2
                else -> 0
            } + 0
            "Y" -> when (round.first) {
                "A" -> 1
                "B" -> 2
                "C" -> 3
                else -> 0
            } + 3
            "Z" -> when (round.first) {
                "A" -> 2
                "B" -> 3
                "C" -> 1
                else -> 0
            } + 6
            else -> 0
        }
    }

    fun readRounds(input: List<String>): List<Pair<String, String>> {
        return input.map { it.split(" ").let { it[0] to it[1] } }
    }

    fun countPoints(round: Pair<String, String>): Int {
        return values[round.second]!! + evalWin(round)
    }


    fun countPart2(round: Pair<String, String>): Int {
        return evalWin2(round)
    }

    fun part1(input: List<String>): Int {
        return readRounds(input).sumOf { countPoints(it) }
    }

    fun part2(input: List<String>): Int {
        return readRounds(input).sumOf { countPart2(it) }
    }


    val input = readInput("Day02")
    println(part1(input))

    println(part2(input))
}