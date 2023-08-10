package com.kamilagcabay.paintapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

@SuppressLint("ClickableViewAccessibility")
class CanvasView(context: Context, attrs : AttributeSet) : View(context,attrs) {

    private var currentColor = Color.BLACK
    private val paths = mutableListOf<Pair<Path,Paint>>()
    private val path = Path()

    private var lastX = 0f
    private var lastY = 0f

    private val paintConfig = Paint().apply {
        isAntiAlias = true
        strokeWidth = 9f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
    }

    init {
        setOnTouchListener { _, event ->
            val x = event.x
            val y = event.y

            when(event.action){
                MotionEvent.ACTION_DOWN -> startDrawing(x,y)
                MotionEvent.ACTION_MOVE -> continueDrawing(x,y)
                MotionEvent.ACTION_UP -> stopDrawing()
            }
            invalidate()
            true
        }

    }



    private fun startDrawing(x:Float,y:Float) {
        path.reset()
        path.moveTo(x,y)
        lastX = x
        lastY = y
    }

    private fun continueDrawing(x:Float,y:Float) {
        path.quadTo(lastX,lastY,(x+lastX)/2,(y+lastY)/2)

        lastX = x
        lastY = y
    }



    private fun stopDrawing() {
        path.lineTo(lastX,lastY)
        val paint= Paint(paintConfig)
        paint.color = currentColor
        paths.add(Pair(Path(path),paint))
        path.reset()

    }

    private fun setRandomColor() : Int{
        return Color.rgb(
            (0..255).random(),(0..255).random(),(0..255).random()
        )
    }

    fun setColor() {
        currentColor = setRandomColor()
    }
    fun clearCanvas() {
        paths.clear()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for ((p,paint) in paths) {
            canvas?.drawPath(p,paint)

        }
        canvas?.drawPath(path,paintConfig)
    }

}