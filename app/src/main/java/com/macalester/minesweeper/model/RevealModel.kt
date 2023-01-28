package com.macalester.minesweeper.model

object RevealModel {

    const val REVEAL: Short = 1
    const val HIDDEN: Short = -1

    private val revealMatrix = arrayOf(
        shortArrayOf(HIDDEN, HIDDEN, HIDDEN, HIDDEN, HIDDEN),
        shortArrayOf(HIDDEN, HIDDEN, HIDDEN, HIDDEN, HIDDEN),
        shortArrayOf(HIDDEN, HIDDEN, HIDDEN, HIDDEN, HIDDEN),
        shortArrayOf(HIDDEN, HIDDEN, HIDDEN, HIDDEN, HIDDEN),
        shortArrayOf(HIDDEN, HIDDEN, HIDDEN, HIDDEN, HIDDEN)
    )

    fun getReveal(x: Int, y: Int) = revealMatrix[x][y]

    fun setReveal(value: Short, x: Int, y: Int) {
        revealMatrix[x][y] = value
    }
}