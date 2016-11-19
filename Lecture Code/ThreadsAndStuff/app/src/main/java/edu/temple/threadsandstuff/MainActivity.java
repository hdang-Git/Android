package edu.temple.threadsandstuff;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView displayMessage;
    boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "You clicked the button", Toast.LENGTH_SHORT).show();
                pause = !pause;
                if(pause){
                    ((Button) v).setText("Unpause");
                } else {
                    ((Button) v).setText("Pause");
                }
            }
        });

        displayMessage = (TextView) findViewById(R.id.displayMessage);

        //can't access ui elements from worker thread so use a handler
        //but can access instance/final local values
        Thread t = new Thread(){
            @Override
            public void run() {
                int i = 0;
                while(i < 20){
                    while(pause);   //external thread can change this value for pause; if adding stop functionallity, add && !notstopped
                    Log.d("Timer", "" + i);
                    //displayMessage.setText(String.valueOf(i));   //moved to handler
                    Message msg = Message.obtain(); //each thread ocntains a pool of msg object, obtain resuses
                    msg.what = i;
                    //timerHandler.sendEmptyMessage(i);
                    timerHandler.sendMessage(msg);
                    try{
                        Thread.sleep(1000);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        };
        t.start();
    }

    //new Handler.callback for memory leak issues
    Handler timerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            displayMessage.setText(String.valueOf(msg.what));
            return false;
        }
    });
}
