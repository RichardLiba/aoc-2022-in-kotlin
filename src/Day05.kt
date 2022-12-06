fun main() {
    data class Move(val count: Int, val from: Int, val to: Int)

    fun readWorld(input: List<String>): Pair<MutableMap<Int, List<Char>>, MutableList<Move>> {
        val world: MutableMap<Int, List<Char>> = mutableMapOf()
        var worldWidth: Int = -1
        val moves: MutableList<Move> = mutableListOf()
        input.forEachIndexed { line, it ->
            if (it.startsWith("move")) {
                // parse moves
                val move = it.split(" ").mapNotNull { it.toIntOrNull() }
                moves.add(Move(move[0], move[1], move[2]))
            } else if (it.isEmpty() || it.startsWith(" ")) {

            } else {
                if (worldWidth == -1) {
                    worldWidth = (it.length + 1) / 4
                    repeat(worldWidth) {
                        world[it] = listOf()
                    }
                }
                // parse world map
                it.forEachIndexed { index, c ->
                    if (c == '[' || c == ']' || c.isWhitespace()) {
                        // do nothing
                    } else {
                        val mappedIndex = if (index == 1) 0 else index / 4
                        when (index) {
                            1, 5, 9, 13, 17, 21, 25, 29, 33 -> world[mappedIndex] = world[mappedIndex]!!.plus(c)
                            else -> {
                                println("Char $c at index $index at line $line")
                                throw IllegalStateException()
                            }
                        }
                    }
                }
            }
        }
        println(world)
        return Pair(world, moves)
    }

    fun part1(input: List<String>): String {
        val parsedInput = readWorld(input)
        val world = parsedInput.first
        val moves = parsedInput.second
        moves.forEach { (count, from, to) ->
//            println("move $count from $from to $to")
            repeat(count) {
                world[to - 1] = listOf(world[from - 1]!![0]).plus(world[to - 1]!!)
                world[from - 1] = world[from - 1]!!.drop(1)
            }
        }
        println(world)
        return world.values.joinToString { "${it[0]}" }.replace(", ", "")
    }


    fun part2(input: List<String>): String {
        val parsedInput = readWorld(input)
        val world = parsedInput.first
        val moves = parsedInput.second
        moves.forEach { (count, from, to) ->
//            println("move $count from $from to $to")
            world[to - 1] = world[from - 1]!!.take(count).plus(world[to - 1]!!)
            world[from - 1] = world[from - 1]!!.drop(count)
        }
        println(world)
        return world.values.joinToString { "${it[0]}" }.replace(", ", "")
    }

    val input = readInput("Day05")
    println(part1(input))

    println(part2(input))
}
