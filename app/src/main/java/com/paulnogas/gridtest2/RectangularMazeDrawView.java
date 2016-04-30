package com.paulnogas.gridtest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Paul Nogas on 2016-04-30
 */

public class RectangularMazeDrawView extends MazeMaskDrawView {

    private int columns, rows, mazeWidth, mazeHeight;
    private DrawableRectangularCell[][] cells;
    private boolean isOffState[][];
    private float cellWidth, cellHeight;
    private Set<DrawableRectangularCell> redCells;

    public RectangularMazeDrawView(Context context){
        super(context);
        customInit();
    }

    public RectangularMazeDrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        customInit();
    }

    public RectangularMazeDrawView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        customInit();
    }

    @Override
    protected void customInit(){
        super.customInit();
        this.columns = MainActivity.columns;
        this.rows = MainActivity.rows;
        cells = new DrawableRectangularCell[columns][rows];
        redCells = new HashSet<>();
        isOffState = new boolean[columns][rows];
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        mazeWidth = xNew - leftPadding - rightPadding;
        mazeHeight = yNew - topPadding - bottomPadding;

        int leftEdge = leftPadding;
        int topEdge = topPadding;
        cellWidth = mazeWidth / (float) this.columns;
        cellHeight = mazeHeight / (float) this.rows;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[column][row] = new DrawableRectangularCell(column, row, leftEdge, topEdge);
                leftEdge += cellWidth;
            }
            leftEdge = leftPadding;
            topEdge += cellHeight;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawEmptyMaze(canvas);
        drawRedCells(canvas);
    }

    @Override
    protected void drawEmptyMaze(Canvas canvas) {
        painter.setColor(Color.GREEN);
        painter.setStyle(Paint.Style.FILL);
        canvas.drawRect(leftPadding, topPadding, canvas.getWidth() - rightPadding, canvas.getHeight() - bottomPadding, painter);
    }

    @Override
    protected void drawRedCells(Canvas canvas) {
        painter.setColor(Color.RED);
        for (DrawableRectangularCell cell : redCells) {
            float x0 = cell.leftEdge;
            float x1 = x0 + cellWidth;
            float y0 = cell.topEdge;
            float y1 = y0 + cellHeight;
            canvas.drawRect(x0, y0, x1, y1, painter);
        }
    }


    @Override
    protected boolean isTouchInMaze(float touchX, float touchY) {
        float shiftedX = touchX-leftPadding;
        float shiftedY = touchY-topPadding;
        return touchX >= 0 && touchX <= mazeWidth && touchY >= 0 && touchY <= mazeHeight;
    }

    @Override
    protected void changeColorAndState(float touchX, float touchY) {
        int touchedColumn = calculateTouchedColumn(touchX);
        int touchedRow = calculateTouchedRow(touchY);
        DrawableRectangularCell touchedCell = cells[touchedColumn][touchedRow];
        if (redCells.contains(touchedCell)) {
            redCells.remove(touchedCell);
            isOffState[touchedColumn][touchedRow] = false;
        } else {
            redCells.add(touchedCell);
            isOffState[touchedColumn][touchedRow] = true;
        }
        invalidate();
    }

    private int calculateTouchedRow(float touchY) {
        float shiftedY = touchY-topPadding;
        return (int) Math.floor(shiftedY / cellHeight);
    }

    private int calculateTouchedColumn(float touchX) {
        float shiftedX = touchX-leftPadding;
        return (int) Math.floor(shiftedX / cellWidth);
    }

}
