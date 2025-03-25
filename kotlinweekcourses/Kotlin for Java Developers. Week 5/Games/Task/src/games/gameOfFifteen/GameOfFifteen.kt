package games.gameOfFifteen

import board.Direction
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    companion object {
        private const val width = 4
    }

    private val board = createGameBoard<Int?>(width)

    override fun initialize() {
        val values = initializer.initialPermutation

        board.getAllCells()
            .take(width * width - 1)
            .zip(values)
            .forEach { (cell, value) -> board[cell] = value }
    }

    override fun canMove() = true

    override fun hasWon() = (1..15).toList() == board.getAllCells()
        .filter { board[it] != null }
        ?.map { board[it] }

    override fun processMove(direction: Direction) {
        with(board) {
            val emptyCell = find { it == null }!!
            emptyCell.getNeighbour(direction.reversed())?.let { neighbour ->
                this[emptyCell] = this[neighbour]
                this[neighbour] = null
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

}

fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)


