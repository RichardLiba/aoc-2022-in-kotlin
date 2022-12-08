data class TempFile(
    val name: String,
    val isDir: Boolean,
    val parentName: String,
    val children: MutableList<TempFile>?,
    val size: Int? = null,
) {
    override fun toString(): String {
        return """- $name (${if (isDir) "dir" else "file, size=$size, parent = $parentName"})
  ${if (!isDir) "" else "- ${children?.map { it.toString() }}"}
        """.trimIndent()
    }
}


fun main() {
    var root = TempFile(name = "root", isDir = true, children = mutableListOf(), parentName = "")

    fun fillFileSystem(inputs: List<String>): TempFile {
        var parents = mutableListOf<TempFile>()
        parents.add(root)
        var currentFile: TempFile = root
        inputs.forEach {
            if (it.startsWith("$")) {
                //commands
                if (it == "\$ cd /") {
                    //go to root dir
                    currentFile = root
                } else if (it.startsWith("\$ cd")) {
                    currentFile = if (it.endsWith("..")) {
                        //move level up
                        parents = parents.dropLast(1).toMutableList()
                        parents.last()
                    } else {
                        val nextDir = it.substringAfter("cd ")
                        val nextFile = currentFile.children?.first { file -> nextDir == file.name }!!
                        parents.add(nextFile)
                        nextFile
                    }
                }
            } else if (it.first().isDigit()) {
                val fileSize = it.substringBefore(" ").toIntOrNull()
                val fileName = it.substringAfter(" ")
                currentFile.children?.add(
                    TempFile(
                        isDir = false,
                        name = fileName,
                        size = fileSize,
                        children = null,
                        parentName = currentFile.parentName + "/" + currentFile.name
                    )
                )
            } else if (it.startsWith("dir")) {
                val dirName = it.substringAfter("dir ")
                currentFile.children?.add(
                    TempFile(
                        isDir = true,
                        name = dirName,
                        children = mutableListOf(),
                        parentName = currentFile.parentName + "/" + currentFile.name
                    )
                )
            }
        }
        return parents.first()
    }

    val folderSizes = mutableListOf<Int>()

    fun getSubfolderSizes(file: TempFile): Int {
        val (folders, files) = file.children.orEmpty().partition { it.isDir }
        val foldersSize = folders.sumOf {
            getSubfolderSizes(it)
        }
        val size = files.sumOf { it.size!! } + foldersSize
        folderSizes.add(size)
        return size
    }


    fun part1(input: List<String>): Int {
        folderSizes.clear()
        val fileSystem = fillFileSystem(input)
        fileSystem.children!!.forEach {
            getSubfolderSizes(it)
        }
        return folderSizes.also { println(it) }.sumOf { if (it <= 100_000) it else 0 }
    }

    fun part2(input: List<String>): Int {
        val maxSystem = 70_000_000
        val atLeast = 30_000_000
        folderSizes.clear()
        val fileSystem = fillFileSystem(input)
        getSubfolderSizes(fileSystem)
        println(folderSizes)
        folderSizes.sortDescending()
        val totalSize = folderSizes.first()
        return folderSizes.last { it >= totalSize - (maxSystem - atLeast) }
    }

    val input = readInput("Day07")
    println(part1(input))
    root = TempFile(name = "root", isDir = true, children = mutableListOf(), parentName = "")
    val inputTEst = readInput("Day07_test")
    println(part1(inputTEst))
    root = TempFile(name = "root", isDir = true, children = mutableListOf(), parentName = "")
    println(part2(input))
}