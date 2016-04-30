package com.paulnogas.gridtest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Paul Nogas on 2016-04-30
 */
public abstract class MazeMaskDrawView extends View {

    protected int leftPadding, rightPadding, topPadding, bottomPadding;
    protected Paint painter = new Paint();

    public MazeMaskDrawView(Context context){
        super(context);
    }

    public MazeMaskDrawView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public MazeMaskDrawView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    protected void customInit(){
        MazeBorders mazeBorders = new MazeBorders(10,10,10,10);
        painter.setStyle(Paint.Style.FILL_AND_STROKE);
        leftPadding = mazeBorders.leftPadding;
        rightPadding = mazeBorders.rightPadding;
        topPadding = mazeBorders.topPadding;
        bottomPadding = mazeBorders.bottomPadding;
    }

    protected abstract void drawEmptyMaze(Canvas canvas);

    protected abstract void drawRedCells(Canvas canvas);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (isTouchInMaze(touchX, touchY)) {
            changeColorAndState(touchX, touchY);
        }
        return super.onTouchEvent(event);
    }

    protected abstract boolean isTouchInMaze(float touchX, float touchY);

    protected abstract void changeColorAndState(float touchX, float touchY);


}
