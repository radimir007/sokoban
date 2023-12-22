import java.io.File

class Level (private val levelNumber: Int) {

    private val level: MutableList<String> = readLevel()
    private val validInputs = listOf("W", "A", "S", "D", "R")
    private val player = Player()
    private val boxes = mutableListOf<Box>()
    private val spots = mutableListOf<Spot>()
    
    private var userInput: String = ""

    var moveCounter = 0

    init {
        getBasePos()
    }

    private fun readLevel(): MutableList<String> {
        return File("src\\main\\kotlin\\levels\\level_${levelNumber}.txt").useLines { it.toMutableList() }
    }

    fun writeLevel() {
        for (i in level.indices) {
            println(level[i])
        }
    }

    private fun getBasePos() {
        for (i in level.indices) {
            for (j in 0 ..< level[i].length) {
                if (level[i][j] == 'P') {
                    player.posX = j
                    player.basePosX = player.posX
                    player.posY = i
                    player.basePosY = player.posY
                } else if (level[i][j] == 'B') {
                    boxes.add(Box(j, i))
                } else if (level[i][j] == 'o') {
                    spots.add(Spot(j, i))
                }
            }
        }
    }

    private fun validateInput (input: String) : Boolean {
        return input in validInputs
    }

    fun getUserInput(input: String): Boolean {
        if (!validateInput(input)) {
            println("Wrong input!")
            return false
        }
        userInput = input
        return true
    }

    fun makeMove() {
        moveCounter++
        when (userInput) {
            "W" -> {
                if (level[player.posY-1][player.posX] == '#') {
                    return
                } else if (level[player.posY-1][player.posX] == 'B' || level[player.posY-1][player.posX] == 'X') {
                    for (box in boxes) {
                        if (box.posY == player.posY-1 && box.posX == player.posX) {
                            if (level[box.posY-1][box.posX] != '#') {
                                box.moveUp()
                                var isOnSpot = false
                                for (spot in spots) {
                                    if (spot.posX == box.posX && spot.posY == box.posY) {
                                        isOnSpot = true
                                        val temp = StringBuilder(level[box.posY+1]).also { it.setCharAt(box.posX, 'P') }
                                        level[box.posY+1] = temp.toString()
                                        break
                                    }
                                }
                                if (!isOnSpot) {
                                    val temp = StringBuilder(level[box.posY+1]).also { it.setCharAt(box.posX, ' ') }
                                    level[box.posY+1] = temp.toString()
                                }
                                val tempBefore = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, ' ') }
                                level[box.posY] = tempBefore.toString()
                                if (isOnSpot) {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'X') }
                                    level[box.posY] = tempAfter.toString()
                                } else {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'B') }
                                    level[box.posY] = tempAfter.toString()
                                }
                                break
                            }
                        }
                    }
                }
                if (level[player.posY-1][player.posX] != 'B' && level[player.posY-1][player.posX] != 'X') {
                    var isOnSpot = false
                    for (spot in spots) {
                        if (spot.posX == player.posX && spot.posY == player.posY) {
                            isOnSpot = true
                            level[player.posY] = level[player.posY].replace('P', 'o')
                            break
                        }
                    }
                    if (!isOnSpot) {
                        level[player.posY] = level[player.posY].replace('P', ' ')
                    }

                    player.moveUp()
                    val temp = StringBuilder(level[player.posY]).also { it.setCharAt(player.posX, 'P') }
                    level[player.posY] = temp.toString()
                }
            }
            "S" -> {
                if (level[player.posY+1][player.posX] == '#') {
                    return
                } else if (level[player.posY+1][player.posX] == 'B' || level[player.posY+1][player.posX] == 'X') {
                    for (box in boxes) {
                        if (box.posY == player.posY+1 && box.posX == player.posX) {
                            if (level[box.posY+1][box.posX] != '#') {
                                box.moveDown()
                                var isOnSpot = false
                                for (spot in spots) {
                                    if (spot.posX == box.posX && spot.posY == box.posY) {
                                        isOnSpot = true
                                        val temp = StringBuilder(level[box.posY-1]).also { it.setCharAt(box.posX, 'P') }
                                        level[box.posY-1] = temp.toString()
                                        break
                                    }
                                }
                                if (!isOnSpot) {
                                    val temp = StringBuilder(level[box.posY-1]).also { it.setCharAt(box.posX, ' ') }
                                    level[box.posY-1] = temp.toString()
                                }
                                val tempBefore = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, ' ') }
                                level[box.posY] = tempBefore.toString()
                                if (isOnSpot) {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'X') }
                                    level[box.posY] = tempAfter.toString()
                                } else {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'B') }
                                    level[box.posY] = tempAfter.toString()
                                }
                                break
                            }
                        }
                    }
                }
                if (level[player.posY+1][player.posX] != 'B' && level[player.posY+1][player.posX] != 'X') {
                    var isOnSpot = false
                    for (spot in spots) {
                        if (spot.posX == player.posX && spot.posY == player.posY) {
                            isOnSpot = true
                            level[player.posY] = level[player.posY].replace('P', 'o')
                            break
                        }
                    }
                    if (!isOnSpot) {
                        level[player.posY] = level[player.posY].replace('P', ' ')
                    }

                    player.moveDown()
                    val temp = StringBuilder(level[player.posY]).also { it.setCharAt(player.posX, 'P') }
                    level[player.posY] = temp.toString()
                }
            }
            "A" -> {
                if (level[player.posY][player.posX-1] == '#') {
                    return
                } else if (level[player.posY][player.posX-1] == 'B' || level[player.posY][player.posX-1] == 'X') {
                    for (box in boxes) {
                        if (box.posX == player.posX-1 && box.posY == player.posY) {
                            if (level[box.posY][box.posX-1] != '#') {
                                box.moveLeft()
                                var isOnSpot = false
                                for (spot in spots) {
                                    if (spot.posX == box.posX && spot.posY == box.posY) {
                                        isOnSpot = true
                                        val temp = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX+1, 'P') }
                                        level[box.posY] = temp.toString()
                                        break
                                    }
                                }
                                if (!isOnSpot) {
                                    val temp = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX+1, ' ') }
                                    level[box.posY] = temp.toString()
                                }
                                val tempBefore = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, ' ') }
                                level[box.posY] = tempBefore.toString()
                                if (isOnSpot) {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'X') }
                                    level[box.posY] = tempAfter.toString()
                                } else {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'B') }
                                    level[box.posY] = tempAfter.toString()
                                }
                                break
                            }
                        }
                    }
                }
                if (level[player.posY][player.posX-1] != 'B' && level[player.posY][player.posX-1] != 'X') {
                    var isOnSpot = false
                    for (spot in spots) {
                        if (spot.posX == player.posX && spot.posY == player.posY) {
                            isOnSpot = true
                            level[player.posY] = level[player.posY].replace('P', 'o')
                            break
                        }
                    }
                    if (!isOnSpot) {
                        level[player.posY] = level[player.posY].replace('P', ' ')
                    }

                    player.moveLeft()
                    val temp = StringBuilder(level[player.posY]).also { it.setCharAt(player.posX, 'P') }
                    level[player.posY] = temp.toString()
                }
            }
            "D" -> {
                if (level[player.posY][player.posX+1] == '#') {
                    return
                } else if (level[player.posY][player.posX+1] == 'B' || level[player.posY][player.posX+1] == 'X') {
                    for (box in boxes) {
                        if (box.posX == player.posX+1 && box.posY == player.posY) {
                            if (level[box.posY][box.posX+1] != '#') {
                                box.moveRight()
                                var isOnSpot = false
                                for (spot in spots) {
                                    if (spot.posX == box.posX && spot.posY == box.posY) {
                                        isOnSpot = true
                                        val temp = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX-1, 'P') }
                                        level[box.posY] = temp.toString()
                                        break
                                    }
                                }
                                if (!isOnSpot) {
                                    val temp = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX-1, ' ') }
                                    level[box.posY] = temp.toString()
                                }
                                val tempBefore = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, ' ') }
                                level[box.posY] = tempBefore.toString()
                                if (isOnSpot) {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'X') }
                                    level[box.posY] = tempAfter.toString()
                                } else {
                                    val tempAfter = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'B') }
                                    level[box.posY] = tempAfter.toString()
                                }
                                break
                            }
                        }
                    }
                }
                if (level[player.posY][player.posX+1] != 'B' && level[player.posY][player.posX+1] != 'X') {
                    var isOnSpot = false
                    for (spot in spots) {
                        if (spot.posX == player.posX && spot.posY == player.posY) {
                            isOnSpot = true
                            level[player.posY] = level[player.posY].replace('P', 'o')
                            break
                        }
                    }
                    if (!isOnSpot) {
                        level[player.posY] = level[player.posY].replace('P', ' ')
                    }

                    player.moveRight()
                    val temp = StringBuilder(level[player.posY]).also { it.setCharAt(player.posX, 'P') }
                    level[player.posY] = temp.toString()
                }
            }

            "R" -> {
                moveCounter = 0
                val tempBefore = StringBuilder(level[player.posY]).also { it.setCharAt(player.posX, ' ') }
                level[player.posY] = tempBefore.toString()
                player.posX = player.basePosX
                player.posY = player.basePosY
                val tempAfter = StringBuilder(level[player.posY]).also { it.setCharAt(player.posX, 'P') }
                level[player.posY] = tempAfter.toString()

                for (box in boxes) {
                    val before = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, ' ') }
                    level[box.posY] = before.toString()
                    box.posX = box.basePosX
                    box.posY = box.basePosY
                    val after = StringBuilder(level[box.posY]).also { it.setCharAt(box.posX, 'B') }
                    level[box.posY] = after.toString()
                }
            }
        }
    }

    fun isGameOver(): Boolean {
        for (box in boxes) {
            var gameOver = false
            for (spot in spots) {
                if (box.posX == spot.posX && box.posY == spot.posY) {
                    gameOver = true
                }
            }
            if (!gameOver) {
                return false
            }
        }
        writeLevel()
        return true
    }

}