fun main() {
    fun readGrid(input: List<String>): List<List<Int>> {
        return input.map {
            it.map { s -> s.digitToInt() }
        }
    }

    fun visibleTrees(grid: List<List<Int>>): Int {
        val n = grid.size
        val m = grid[0].size
        val visible = Array(n) { BooleanArray(m) }
        for (i in grid.indices) {
            var heighest = -1
            var heighestOtherDir = -1
            for (j in 0 until m) {
                if (grid[i][j] > heighest) {
                    heighest = grid[i][j]
                    visible[i][j] = true
                }
                if (grid[i][m - 1 - j] > heighestOtherDir) {
                    heighestOtherDir = grid[i][m - 1 - j]
                    visible[i][m - 1 - j] = true
                }
            }
        }
        for (j in grid[0].indices) {
            var heighest = -1
            var heighestOtherDir = -1
            for (i in 0 until n) {
                if (grid[i][j] > heighest) {
                    heighest = grid[i][j]
                    visible[i][j] = true
                }
                if (grid[n - 1 - i][j] > heighestOtherDir) {
                    heighestOtherDir = grid[n - 1 - i][j]
                    visible[n - 1 - i][j] = true
                }
            }
        }
        return visible.sumOf { it.count { it } }
    }

    fun part1(input: List<String>): Int {
        val grid = readGrid(input)
        val visibleTrees = visibleTrees(grid)
        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val input = readInput("Day08")
    println(part1(input))
    val inputTEst = readInput("Day08_test")
    println(part1(inputTEst))

    println(part2(input))
}