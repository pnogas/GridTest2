package com.paulnogas.gridtest2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Paul Nogas on 2016-04-30
 */
public class ChangeValues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_values);
        Button okButton = (Button) findViewById(R.id.okButton);
        if (okButton != null) {
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getValuesAndClose();
                }
            });
        }
    }

    private void getValuesAndClose() {
        EditText rowsEditText = (EditText) findViewById(R.id.rowsEditText);
        if (rowsEditText == null) {
            MainActivity.rows = 3;
        } else {
            MainActivity.rows = Integer.parseInt(rowsEditText.getText().toString());
        }
        EditText columnsEditText = (EditText) findViewById(R.id.columnsEditText);
        if (columnsEditText == null) {
            MainActivity.columns = 3;
        } else {
            MainActivity.columns = Integer.parseInt(columnsEditText.getText().toString());
        }
        finish();
    }
}
