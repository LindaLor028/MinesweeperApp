package com.macalester.minesweeper.model

object FlagModel {
    const val SET: Short = 1
    private const val EMPTY: Short = 0
    const val FLAG_REVEAL: Short = 2
    var flagModeOn = false

    private var flagMatrix = arrayOf(
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY)
    )

    fun resetFlags() {
        disableFlagMode()
        for (i in 0 until MinesweeperModel.boardSize){
            for (j in 0 until MinesweeperModel.boardSize){
                flagMatrix[i][j]= EMPTY
            }
        }
    }

    fun getFlagValue (x:Int, y:Int) = flagMatrix[x][y]

    fun setFlagDown(x: Int, y: Int){
        flagMatrix[x][y] = SET
    }

    fun enableFlagMode(){
        flagModeOn = true
    }

    fun disableFlagMode(){
        flagModeOn = false
    }
}