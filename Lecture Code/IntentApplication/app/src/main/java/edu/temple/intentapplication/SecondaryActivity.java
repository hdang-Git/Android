package edu.temple.intentapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);


        Intent receivedIntent = getIntent();
        String message  = receivedIntent.getStringExtra(MainActivity.laucnhIntentExtraKey);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.laucnhIntentExtraKey, "Here is the return message");
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
