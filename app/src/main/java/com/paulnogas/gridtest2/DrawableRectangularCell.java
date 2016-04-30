package com.paulnogas.gridtest2;

/**
 * Created by Paul Nogas on 2016-04-30
 */
public class DrawableRectangularCell extends RectangularCell {

    protected float leftEdge, topEdge;

    public DrawableRectangularCell(int column, int row, float leftEdge, float topEdge) {
        super(column, row);
        this.leftEdge = leftEdge;
        this.topEdge = topEdge;
    }
}
