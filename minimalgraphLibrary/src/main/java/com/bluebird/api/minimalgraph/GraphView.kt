package com.bluebird.api.minimalgraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*
import kotlin.collections.ArrayList as ArrayList

@Suppress("UNCHECKED_CAST")
class GraphView : View {
    private var borderColor = 0
    private var graphColor = 0
    private var activePoints = ArrayList<Int>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val attrsArray = context.theme.obtainStyledAttributes(attrs, R.styleable.GraphView, 0, 0)
        try {
            borderColor = attrsArray.getColor(R.styleable.GraphView_borderColor, Color.rgb(95, 95, 95))
            graphColor = attrsArray.getColor(R.styleable.GraphView_graphColor, Color.rgb(2, 167, 2))
        } finally {
            attrsArray.recycle()
        }
    }


    override fun onDraw(canvas: Canvas) {
        /*DRAW LINES*/
        //paint.setColor();
        paint.color = borderColor
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = dpToPixel(Companion.width.toFloat())
        canvas.drawLine(dpToPixel(Companion.width.toFloat()), height - dpToPixel(Companion.width.toFloat()), width - dpToPixel(Companion.width.toFloat()), height - dpToPixel(Companion.width.toFloat()), paint)
        canvas.drawLine(dpToPixel(Companion.width.toFloat()), dpToPixel(Companion.width.toFloat()), dpToPixel(Companion.width.toFloat()), height - dpToPixel(Companion.width.toFloat()), paint)

        val points = activePoints.clone() as ArrayList<Int>
        points.reverse()

        /*DRAW ACTIVE_GRAPH*/paint.color = graphColor
        val pathPartWidth = ((width - dpToPixel(Companion.width.toFloat()) * 4) / (points.size - 1)).toInt()
        for (i in 1 until points.size) {
            val activePointStart = points[i - 1].toFloat()
            val activePointEnd = points[i].toFloat()
            canvas.drawLine(
                    width -pathPartWidth*(i-1)-dpToPixel(Companion.width.toFloat()),
                    height -activePointStart/100* height -dpToPixel(Companion.width*2.toFloat()),
                    width -pathPartWidth*i-dpToPixel(Companion.width.toFloat()),
                    height -activePointEnd/100* height -dpToPixel(Companion.width*2.toFloat()),
                    paint)
        }
    }

    fun setActivePoints(activePoints: ArrayList<Int>) {
        this.activePoints = activePoints
    }

    fun setActivePoint(activeMeterPoint: Int, pos: Int) {
        activePoints[pos] = activeMeterPoint
    }

    fun addActivePoint(activeMeterPoint: Int) {
        activePoints.add(activeMeterPoint)
    }

    private fun dpToPixel(dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    companion object {
        private const val width = 3
    }
}