package edu.temple.cis3505_lab3;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PaletteActivity extends AppCompatActivity {

    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        //dropdown list
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //Color array
        String colors[] = {"WHITE","RED", "BLUE", "GREEN", "YELLOW"};

        CustomAdapter adapter = new CustomAdapter(PaletteActivity.this, android.R.layout.simple_spinner_dropdown_item, colors);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(flag) {
                  flag = false;
                } else {
                    String color = parent.getSelectedItem().toString();
                    Intent launchActivityIntent = new Intent(PaletteActivity.this, CanvasActivity.class);

                    //pass the data from this activity to canvas
                    launchActivityIntent.putExtra("colorStuff", color);
                    startActivity(launchActivityIntent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
