package edu.temple.intentapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String laucnhIntentExtraKey = "data";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent launchIntent = new Intent(this, SecondaryActivity.class);
        //RULE don't put string literals, instead use constant values
        launchIntent.putExtra("data", "PLease show this message on your layout");
        //startActivity(launchIntent);        //fires and just waits
        startActivityForResult(launchIntent, 111); //when activity starts exits, actiivty will be notified
    }
    /*
    * //OnactivityResult is a called back called when activity called returns ; gets three bits of info
    * Request code = 111,
    *   A -> B once activity B has dones its job
    *   A <- B activity B sends info about whether or not it did its job
    *
    *   Intent is being used to pass information
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 111){     //111 should be in a constant
            if(resultCode == RESULT_OK){ //DId activity do the job I asked?
                //Means intent isn't null
                //data.getStringExtra(laucnhIntentExtraKey);
                Toast.makeText(this, data.getStringExtra(laucnhIntentExtraKey), Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }
}



