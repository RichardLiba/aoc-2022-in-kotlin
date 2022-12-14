import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        var caloriesForElf = 0
        val caloriesByElves: MutableList<Int> = mutableListOf()
        var max = 0
        input.forEach {
            if (it.isEmpty() || it.toIntOrNull() == null) {
                // next elf
                max = max(max, caloriesForElf)
                caloriesByElves.add(caloriesForElf)
                caloriesForElf = 0
            } else {
                // add up calories
                caloriesForElf += it.toInt()
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        var caloriesForElf = 0
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
        return caloriesByElves.sortedDescending().take(3).sum()
    }

    val input = readInput("Day01")
    println(part1(input))

    println(part2(input))
}
