import java.util.*

fun main() {
    var levelNumber = 1

    println("----------------------------")
    println("|                          |")
    println("|     Sokoban v2023.12     |")
    println("|                          |")
    println("----------------------------")
    println("Press enter to start!")
    readln()

    while (levelNumber <= 5) {
        println("-> Level $levelNumber")
        val level = Level(levelNumber)
        var gameOver = false

        while (!gameOver) {
            level.writeLevel()
            println("----------------------------")
            val input = readln().uppercase(Locale.getDefault())
            if (!level.getUserInput(input)) {
                continue
            }

            level.makeMove()
            if (level.isGameOver()) {
                gameOver = true
            }
        }

        println("============================")
        println("Level $levelNumber completed! Number of moves: ${level.moveCounter}")
        println("============================")
        levelNumber++
    }

    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    println("|                                       |")
    println("|    Congrats for finishing my game!    |")
    println("| See my github \"radimir007\" for more |")
    println("|                                       |")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
}