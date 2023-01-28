package com.macalester.minesweeper.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.macalester.minesweeper.model.FlagModel
import com.macalester.minesweeper.model.MinesweeperModel
import com.macalester.minesweeper.model.RevealModel

class MinesweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var backgroundPaint = Paint()
    private var linePaint = Paint()

    private var notCalled = true
    private var freezeOn = false

    private var gView = GraphicsView(context, attrs)

    init{
        backgroundPaint.color = Color.rgb(135,95,195)
        backgroundPaint.style = Paint.Style.FILL

        linePaint.color = Color.WHITE
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (notCalled){
            MinesweeperModel.populateSetUp()
            notCalled = false
        }

        drawBoard(canvas)
        revealTiles(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN && !freezeOn){
            val row = (event.x.toInt()) / (width/MinesweeperModel.boardSize)
            val column = (event.y.toInt()) / (height/MinesweeperModel.boardSize)

            if (!FlagModel.flagModeOn){
                RevealModel.setReveal(RevealModel.REVEAL, row, column)
            }
            else{
                FlagModel.setFlagDown(row, column)
            }
            invalidate()
        }
        return true
    }

    private fun drawBoard(canvas: Canvas?) {
        canvas?.drawRect(0f,0f, width.toFloat(), height.toFloat(), backgroundPaint) //TODO: Code this so that if we resize it fits on board accordingly!
        for (a in 1..MinesweeperModel.boardSize){
            canvas?.drawLine(0f, (height / MinesweeperModel.boardSize).toFloat()*a.toFloat(), width.toFloat(), (height / MinesweeperModel.boardSize).toFloat()*a.toFloat(),
                linePaint)

            canvas?.drawLine((width / MinesweeperModel.boardSize).toFloat()*a.toFloat(), 0f,(width / MinesweeperModel.boardSize).toFloat()*a.toFloat(), height.toFloat(),
                linePaint)
        }
    }

    private fun revealTiles(canvas: Canvas?) {
        for (column in 0 until MinesweeperModel.boardSize){
            for (row in 0 until MinesweeperModel.boardSize){
                if (RevealModel.getReveal(row, column) == RevealModel.REVEAL ){
                    revealTile(row, column, canvas)
                }
                if (FlagModel.getFlagValue(row, column) == FlagModel.SET){
                    setFlagDown(row, column, canvas)
                }
            }
        }
    }

    private fun revealTile(x:Int, y:Int, canvas: Canvas?){
        if (MinesweeperModel.getValue(x,y) == MinesweeperModel.BOMB){
            if(FlagModel.getFlagValue(x,y) != FlagModel.SET){
                gView.paintBomb(x, y, MinesweeperModel.boardSize, width, height, canvas)
                gameOver(canvas)
            }
        }
        else {
            gView.paintTile(x, y, MinesweeperModel.boardSize, width, height, canvas)
            if (MinesweeperModel.checkWin()){
                gameWin(canvas)
            }
        }
    }

    private fun setFlagDown(x: Int, y:Int, canvas: Canvas?){
        if (MinesweeperModel.getValue(x,y) != MinesweeperModel.BOMB){
            gameOver(canvas)
        }
        if (RevealModel.getReveal(x, y) != RevealModel.REVEAL || RevealModel.getReveal(x, y) != FlagModel.FLAG_REVEAL){
            RevealModel.setReveal(FlagModel.FLAG_REVEAL, x,y)
            gView.paintFlag(x, y, MinesweeperModel.boardSize, width, height, canvas)
        }
    }

    fun resetGame(){
        MinesweeperModel.resetBoard()
        notCalled = true
        freezeOn = false
        invalidate()
    }

    private fun gameOver(canvas : Canvas?){
        freezeOn = true
        gView.paintLose(width, height, canvas)
    }

    private fun gameWin(canvas: Canvas?){
        freezeOn = true
        gView.paintWin(width, height, canvas)
    }
}