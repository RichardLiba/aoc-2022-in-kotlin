fun main() {
    fun part1(input: List<String>): Int {
        var caloriesForElf: Int = 0
        val caloriesByElves: MutableList<Int> = mutableListOf()
        input.forEach {
            if (it.isEmpty() || it.toIntOrNull() == null) {
                // next elf
                caloriesByElves.add(caloriesForElf)
                caloriesForElf = 0
            } else {
                // add up calories
                caloriesForElf += it.toInt()
            }
        }
        return caloriesByElves.indexOf(caloriesByElves.max()) + 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
}
