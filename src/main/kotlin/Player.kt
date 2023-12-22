class Player(var posX: Int = 0, var posY: Int = 0, var basePosX: Int = 0, var basePosY: Int = 0) {

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