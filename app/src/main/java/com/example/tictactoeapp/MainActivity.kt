package com.example.tictactoeapp

import android.os.Bundle
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private var gridLayout: GridLayout? = null
    private var playersTurn: TextView? = null
    private var winner: TextView? = null
    private var current_player: Char = 'X'
    private var play_again_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        gridLayout = findViewById<GridLayout>(R.id.grid_layout)
        playersTurn = findViewById<TextView>(R.id.players_turn_text)
        winner = findViewById<TextView>(R.id.winner_text)
        play_again_button = findViewById<Button>(R.id.play_again_button)


        play_again_button?.isEnabled = false
        playersTurn?.text = "It's ${current_player}'s turn"
        setBoard()
    }

    fun setBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                val id = resources.getIdentifier(
                    "button_${i}_${j}",
                    "id",
                    packageName
                )
                val currentButton: Button = findViewById<Button>(id)
                currentButton.setOnClickListener {
                    playATurn(currentButton)
                }
            }
        }
    }

    fun playATurn(currentButton: Button) {
        currentButton.text = "${current_player}"

        if (checkForAWin(currentButton)) {
          winner?.text = "${current_player} wins!"
          play_again_button?.isEnabled = true
          play_again_button?.setOnClickListener {
            startANewGame()
          }
        } else {
            if (checkForADraw()) {
                winner?.text = "It's a draw!"
                play_again_button?.isEnabled = true
                play_again_button?.setOnClickListener {
                    startANewGame()
                }
            } else {
                if (current_player == 'X') {
                    current_player = 'O'
                } else {
                    current_player = 'X'
                }
                playersTurn?.text = "It's ${current_player}'s turn"
            }
        }
    }

    fun checkForAWin(currentButton: Button): Boolean {
        // go over it's row
        //go over it's col
        //go over it's / \
        return false
    }

    fun checkForADraw(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                val id = resources.getIdentifier(
                    "button_${i}_${j}",
                    "id",
                    packageName
                )
                val currentButton: Button = findViewById<Button>(id)
                if (currentButton.text.isNullOrEmpty()) {
                    return false
                }
            }
        }

        return true
    }

    fun cleanBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                val id = resources.getIdentifier(
                    "button_${i}_${j}",
                    "id",
                    packageName
                )
                val currentButton: Button = findViewById<Button>(id)
                currentButton.text = ""
            }
        }
    }

    fun startANewGame() {
        cleanBoard()
        play_again_button?.isEnabled = false
        current_player = 'X'
        winner?.text = ""
        playersTurn?.text = "It's ${current_player}'s turn"
        setBoard()
    }
}
