package edu.temple.serviceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OverEngineer this
        /*
        for(int i = 0; i < 20; i++){
            Log.d("Count", String.valueOf(i));
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        */
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        serviceIntent.putExtra("counterValue", 50);
        startService(serviceIntent);
    }
}
