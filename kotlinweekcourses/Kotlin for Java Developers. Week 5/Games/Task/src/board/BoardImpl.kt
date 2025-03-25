package board

class BoardImpl<T>(override val width: Int) : GameBoard<T> {

    val cells: Array<Array<Cell>> = Array(width) { i -> Array(width) { j -> Cell(i + 1, j + 1) } }

    val data: MutableMap<Cell, T?> = cells.flatten().associateWith { null }.toMutableMap()

    override fun get(cell: Cell): T? = data[cell]

    override fun all(predicate: (T?) -> Boolean): Boolean = data.values.all(predicate)

    override fun any(predicate: (T?) -> Boolean): Boolean = data.values.any(predicate)

    override fun find(predicate: (T?) -> Boolean): Cell? =
        data.entries.find { (_, v) -> predicate(v) }?.key

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> =
        data.entries.filter { (_, v) -> predicate(v) }.map { it.key }

    override fun set(cell: Cell, value: T?) {
        data[cell] = value
    }

    ///////////////////////////////////////////////////////

    override fun getCellOrNull(i: Int, j: Int): Cell? =
        if (i !in 1..width || j !in 1..width) null
        else cells[i - 1][j - 1]

    override fun getCell(i: Int, j: Int): Cell {
        require(i in 1..width && j in 1..width) {
            "i, j is out of bounds"
        }
        return cells[i - 1][j - 1]
    }

    override fun getAllCells(): Collection<Cell> = cells.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> = (jRange intersect (1..width))
        .map { j -> cells[i - 1][j - 1] }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> = (iRange intersect (1..width))
        .map { i -> cells[i - 1][j - 1] }

    override fun Cell.getNeighbour(direction: Direction): Cell? = when (direction) {
        Direction.UP -> getCellOrNull(i - 1, j)
        Direction.DOWN -> getCellOrNull(i + 1, j)
        Direction.LEFT -> getCellOrNull(i, j - 1)
        Direction.RIGHT -> getCellOrNull(i, j + 1)
    }

}

fun createSquareBoard(width: Int): SquareBoard = BoardImpl<Any>(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = BoardImpl(width)
