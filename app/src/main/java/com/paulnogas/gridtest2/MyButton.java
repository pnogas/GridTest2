package com.paulnogas.gridtest2;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Paul Nogas on 2016-04-30
 */
public class MyButton extends Button {
    private int row;
    private int column;

    public MyButton(Context context, int row, int column) {
        super(context);
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
