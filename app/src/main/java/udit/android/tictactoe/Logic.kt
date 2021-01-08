package udit.android.tictactoe

class Logic {
    companion object {
        const val PLAYER1 = "O"
        const val PLAYER2 = "X"
    }

    val cellsArray = Array(3) { arrayOfNulls<String>(3) }

    val availableCells: List<Cell>
        get() {
            val cells = mutableListOf<Cell>()
            for (i in cellsArray.indices) {
                for (j in cellsArray.indices) {
                    if (cellsArray[i][j].isNullOrEmpty()) {
                        cells.add(Cell(i, j))
                    }
                }
            }
            return cells
        }

    val checkGameOver: Boolean
        get() = isPlayerTwoWon() || isPlayerOneWon() || availableCells.isEmpty()

    fun isPlayerTwoWon(): Boolean {
        if (cellsArray[0][0] == cellsArray[1][1] &&
            cellsArray[0][0] == cellsArray[2][2] &&
            cellsArray[0][0] == PLAYER2 ||
            cellsArray[0][2] == cellsArray[1][1] &&
            cellsArray[0][2] == cellsArray[2][0] &&
            cellsArray[0][2] == PLAYER2
        ) {
            return true
        }

        for (i in cellsArray.indices) {
            if (cellsArray[i][0] == cellsArray[i][1] &&
                cellsArray[i][0] == cellsArray[i][2] &&
                cellsArray[i][0] == PLAYER2 ||
                cellsArray[0][i] == cellsArray[1][i] &&
                cellsArray[0][i] == cellsArray[2][i] &&
                cellsArray[0][i] == PLAYER2
            ) {
                return true
            }
        }

        return false
    }

    fun isPlayerOneWon(): Boolean {

        if (cellsArray[0][0] == cellsArray[1][1] &&
            cellsArray[0][0] == cellsArray[2][2] &&
            cellsArray[0][0] == PLAYER1 ||
            cellsArray[0][2] == cellsArray[1][1] &&
            cellsArray[0][2] == cellsArray[2][0] &&
            cellsArray[0][2] == PLAYER1
        ) {
            return true
        }

        for (i in cellsArray.indices) {
            if (
                cellsArray[i][0] == cellsArray[i][1] &&
                cellsArray[i][0] == cellsArray[i][2] &&
                cellsArray[i][0] == PLAYER1 ||
                cellsArray[0][i] == cellsArray[1][i] &&
                cellsArray[0][i] == cellsArray[2][i] &&
                cellsArray[0][i] == PLAYER1
            ) {
                return true
            }
        }
        return false
    }

    fun placeMove(cell: Cell, player: String) {
        cellsArray[cell.i][cell.j] = player
    }
}