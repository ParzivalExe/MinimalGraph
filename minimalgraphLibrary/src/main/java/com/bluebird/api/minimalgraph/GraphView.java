package com.bluebird.api.minimalgraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GraphView extends View {

    private int borderColor;
    private int graphColor;
    private static final int width = 3;
    private ArrayList<Integer> activePoints = new ArrayList<Integer>();

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray attrsArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.GraphView, 0, 0);
        try{
            borderColor = attrsArray.getColor(R.styleable.GraphView_borderColor, Color.rgb(95, 95, 95));
            graphColor = attrsArray.getColor(R.styleable.GraphView_graphColor, Color.rgb(2, 167, 2));
        } finally {
            attrsArray.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        /*DRAW LINES*/
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint.setColor();
        paint.setColor(borderColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dpToPixel(width));
        canvas.drawLine(dpToPixel(width), getHeight()-dpToPixel(width), getWidth()-dpToPixel(width), getHeight()-dpToPixel(width), paint);
        canvas.drawLine(dpToPixel(width), dpToPixel(width), dpToPixel(width), getHeight()-dpToPixel(width), paint);

        /*DRAW ACTIVE_GRAPH*/
        paint.setColor(graphColor);
        int pathPartWidth = (int) ((getWidth()-dpToPixel(width)*4)/(activePoints.size()-1));
        for(int i = 1; i < activePoints.size(); i++) {
            float activePointStart = activePoints.get(i-1);
            float activePointEnd = activePoints.get(i);

            canvas.drawLine(
                    getWidth()-pathPartWidth*(i-1)-dpToPixel(width),
                    getHeight()-activePointStart/100*getHeight()-dpToPixel(width*2),
                    getWidth()-pathPartWidth*i-dpToPixel(width),
                    getHeight()-activePointEnd/100*getHeight()-dpToPixel(width*2),
                    paint);
        }
    }


    public void setActivePoints(ArrayList<Integer> activePoints) {
        this.activePoints = activePoints;
    }

    public void setActivePoint(int activeMeterPoint, int pos) {
        activePoints.set(pos, activeMeterPoint);
    }

    public void addActivePoint(int activeMeterPoint) {
        activePoints.add(activeMeterPoint);
    }

    private float dpToPixel(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }
}
