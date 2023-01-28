package com.macalester.minesweeper.model

import android.graphics.Point
import com.macalester.minesweeper.view.MinesweeperView
import kotlin.random.Random

object MinesweeperModel {

    private const val EMPTY: Short = 0
    const val BOMB: Short = 9

    private val maxBombs = 3
    private var bombPoints = arrayListOf<Point>()

    var boardSize = 5

    private var gameSetUp = arrayOf(
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY),
        shortArrayOf(EMPTY,EMPTY,EMPTY,EMPTY,EMPTY)
    )

    fun populateSetUp() {
        for (eachBomb in 1..maxBombs) {
            placeBomb()
        }
        checkAllTouches()
    }

    private fun placeBomb(){
        var notPlaced = true

        while (notPlaced){
            val randomRow = Random.nextInt(0, boardSize-1)
            val randomCol = Random.nextInt(0, boardSize-1)

            if (gameSetUp[randomRow][randomCol]!=BOMB){
                gameSetUp[randomRow][randomCol] = BOMB
                bombPoints.add(Point(randomRow,randomCol))
                notPlaced = false
            }
        }
    }

    private fun checkAllTouches(){
        for (point in bombPoints){
            checkBottomTouch(point)
            checkTopTouch(point)
            checkSideTouch(point)
        }
    }

    private fun checkTopTouch(point: Point){
        if (point.y +1 <= boardSize) {
            if (point.x -1 >= 0){
                addPoint(point.x-1,point.y+1)
            }
            if (point.x <= boardSize){
                addPoint(point.x,point.y+1)
            }
            if (point.x +1 <= boardSize ){
                addPoint(point.x+1,point.y+1)
            }
        }
    }

    private fun checkBottomTouch(point: Point){
        if (point.y -1 >= 0) {
            if (point.x-1 >= 0){
                addPoint(point.x-1,point.y-1)
            }
            if(point.x <= boardSize) {
                addPoint(point.x,point.y-1)
            }
            if (point.x+1 <= boardSize ) {
                addPoint(point.x+1,point.y-1)
            }
        }
    }

    private fun checkSideTouch(point: Point){
        if (point.x-1 >= 0 && point.y <= boardSize) {
            addPoint(point.x-1,point.y)
        }
        if (point.x+1 <= boardSize  && point.y <= boardSize) {
            addPoint(point.x+1, point.y)
        }
    }

    private fun addPoint(x: Int, y:Int){
        if (!isBomb(x, y)) {
            gameSetUp[x][y] = (gameSetUp[x][y] + 1).toShort()
        }
    }

    fun checkWin() : Boolean{
        for (i in 0 until boardSize){
            for (j in 0 until boardSize){
                if (RevealModel.getReveal(i,j) < EMPTY ){
                    return false
                }
            }
        }
        return true
    }

    fun resetBoard(){
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                gameSetUp[j][i] = EMPTY
                RevealModel.setReveal(RevealModel.HIDDEN, j, i)
            }
        }
        FlagModel.resetFlags()
        bombPoints.clear()
    }

    fun setValue(value: Short, x: Int, y: Int) {
        gameSetUp[x][y] = value
    }

    private fun isBomb(x: Int, y: Int) = gameSetUp[x][y] == BOMB

    fun getValue(x: Int, y: Int) = gameSetUp[x][y]
}



