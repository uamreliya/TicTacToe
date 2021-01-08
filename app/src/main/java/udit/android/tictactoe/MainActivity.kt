package udit.android.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val boardArray = Array(3) { arrayOfNulls<ImageView>(3) }
    private var playerOneTurn: Boolean = true;
    var logic = Logic()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawBoard()

        btn_restart.setOnClickListener {
            logic = Logic()

            tv_result.text = ""

            updateUI()
        }
    }

    private fun drawBoard() {
        for (i in boardArray.indices) {
            for (j in boardArray.indices) {
                boardArray[i][j] = ImageView(this)
                boardArray[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i);
                    columnSpec = GridLayout.spec(j);
                    width = 248
                    height = 228
                    bottomMargin = 4
                    topMargin = 4
                    leftMargin = 4
                    rightMargin = 4
                }

                boardArray[i][j]?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimary
                    )
                )



                boardArray[i][j]?.setOnClickListener(buttonClickListener(i, j))

                board_layout.addView(boardArray[i][j])
            }
        }
    }

    private fun updateUI() {
        for (i in logic.cellsArray.indices) {
            for (j in logic.cellsArray.indices) {
                when (logic.cellsArray[i][j]) {
                    Logic.PLAYER1 -> {
                        boardArray[i][j]?.setImageResource(R.drawable.ic_circle)
                        boardArray[i][j]?.isEnabled = false
                    }
                    Logic.PLAYER2 -> {
                        boardArray[i][j]?.setImageResource(R.drawable.ic_cross)
                        boardArray[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardArray[i][j]?.setImageResource(0)
                        boardArray[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    inner class buttonClickListener(val i: Int, val j: Int) : View.OnClickListener {
        override fun onClick(p0: View?) {

            if (!logic.checkGameOver) {
                val position = Cell(i, j)

                playerOneTurn = if (playerOneTurn) {
                    logic.placeMove(position, Logic.PLAYER1)
                    false;
                } else {
                    logic.placeMove(position, Logic.PLAYER2)
                    true
                }

                updateUI()
            }

            when {
                logic.isPlayerOneWon() -> tv_result.text = "Player One won"
                logic.isPlayerTwoWon() -> tv_result.text = "Player Two won"
                logic.checkGameOver -> tv_result.text = "Match Draw"
            }
        }

    }
}