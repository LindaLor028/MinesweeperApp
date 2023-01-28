package com.macalester.minesweeper.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.macalester.minesweeper.R
import com.macalester.minesweeper.model.MinesweeperModel

class GraphicsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var textPaint = Paint()

    private var flagBitmap = BitmapFactory.decodeResource(resources, R.drawable.flag)
    private var bombBitmap = BitmapFactory.decodeResource(resources, R.drawable.bomb)
    private var winBitmap = BitmapFactory.decodeResource(resources, R.drawable.win)
    private var loseBitmap = BitmapFactory.decodeResource(resources, R.drawable.lose)

    init{
        textPaint.color = Color.BLACK
        textPaint.textSize = 100f
    }

    fun paintFlag(x: Int, y: Int, boardSize: Int, viewWidth: Int, viewHeight: Int,  canvas: Canvas?){
        canvas?.drawBitmap(flagBitmap, (x*viewWidth/boardSize).toFloat()+60f, (y*viewHeight/boardSize).toFloat()+45f, null)
    }

    fun paintWin(viewWidth: Int, viewHeight: Int,canvas: Canvas?){
        canvas?.drawBitmap(winBitmap, viewWidth/4f, viewHeight/4.5f, null)
    }

    fun paintLose(viewWidth: Int, viewHeight: Int,canvas: Canvas?){
        canvas?.drawBitmap(loseBitmap, viewWidth/4f, viewHeight/4.5f, null)
    }

    fun paintBomb(x: Int, y: Int, boardSize: Int, viewWidth: Int, viewHeight: Int,  canvas: Canvas?){
        canvas?.drawBitmap(bombBitmap, (x*viewWidth/boardSize).toFloat()+55f, (y*viewHeight/boardSize).toFloat()+55f, null)
    }

    fun paintTile(x: Int, y: Int, boardSize: Int, viewWidth: Int, viewHeight: Int,  canvas: Canvas?){
        canvas?.drawText(MinesweeperModel.getValue(x,y).toString(), (x*viewHeight/boardSize).toFloat()+60f,(y*viewWidth/boardSize).toFloat()+125f, textPaint)
    }
}