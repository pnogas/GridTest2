package com.paulnogas.gridtest2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int rows;
    public static int columns;

    private final MyButton.OnClickListener buttonOnClickListener = new MyButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyButton thisButton = (MyButton) view;
            ColorDrawable colorDrawable = (ColorDrawable) view.getBackground();
            if (colorDrawable != null) {
                int colorId = colorDrawable.getColor();
                if (Color.GREEN == colorId) {
                    view.setBackgroundColor(Color.RED);
                } else {
                    view.setBackgroundColor(Color.GREEN);
                }
                String toastText = String.format("(%s,%s) touched", Integer.toString(thisButton.getColumn()), Integer.toString(thisButton.getRow()));
                Toast.makeText(getApplication(),toastText, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rows = 3;
        columns = 3;
    }

    @Override
    protected void onResume() {
        super.onResume();
        createButtons();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createButtons() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout);
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(columns);
        gridLayout.setRowCount(rows);

        for (int rowCounter = 0; rowCounter < rows; rowCounter++) {
            for (int columnCounter = 0; columnCounter < columns; columnCounter++) {
                Button b = new MyButton(getApplication(), rowCounter, columnCounter);
                b.setOnClickListener(buttonOnClickListener);
                b.setText(" ");
                b.setBackgroundColor(Color.GREEN);
                //doesn't look like there's any easy way to set android:layout_columnWeight or android:layout_rowWeight
                //also won't be able to scale to circular / triangle / hexagon mazes well. Give up and use custom canvas

                //GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(0, 0, 0, 0);
                b.setLayoutParams(params);
                gridLayout.addView(b);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.set_dimensions:
                Intent changeValuesIntent = new Intent(getApplication(), ChangeValues.class);
                startActivity(changeValuesIntent);
                return true;
            case R.id.test_rect:
                Intent testRectangularMazeDrawViewIntent = new Intent(getApplication(), TestRectangularMazeDrawView.class);
                startActivity(testRectangularMazeDrawViewIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
