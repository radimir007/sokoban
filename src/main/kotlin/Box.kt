class Box(var posX: Int, var posY: Int, var basePosX: Int = 0, var basePosY: Int = 0) {

    init {
        this.basePosX = posX
        this.basePosY = posY
    }
    fun moveUp () {
        this.posY--
    }

    fun moveDown() {
        this.posY++
    }

    fun moveLeft() {
        this.posX--
    }

    fun moveRight() {
        this.posX++
    }
}