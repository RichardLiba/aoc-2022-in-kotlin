fun main() {

    fun findFirstUniqueIndex(length: Int, input: String): Int {
        var foundMarker = ""
        input.forEachIndexed { index, c ->
            val temp = foundMarker + c
            if (temp.toSet().size == length && temp.length == length) {
                return index + 1
            } else {
                if (foundMarker.length >= length - 1) {
                    foundMarker = foundMarker.drop(1).plus(c)
                } else {
                    foundMarker += c
                }
            }
        }
        return -1
    }

    fun part1(input: String): Int {
        return findFirstUniqueIndex(4, input)
    }

    fun part2(input: String): Int {
        return findFirstUniqueIndex(14, input)
    }

    val input = readInput("Day06").first()
    println(part1(input))

    println(part2(input))
}
