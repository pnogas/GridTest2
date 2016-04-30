package com.paulnogas.gridtest2;

/**
 * Created by Paul Nogas on 2016-04-30
 */
public class RectangularCell extends Cell {
    private int column, row;
    public RectangularCell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
