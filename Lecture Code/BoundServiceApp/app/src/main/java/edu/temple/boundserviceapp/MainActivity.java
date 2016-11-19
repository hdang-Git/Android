package edu.temple.boundserviceapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    MathService.MathBinder myBinder;
    //could throw execution because callback of null pointerexception with int result
    //So make a flag
    boolean connected= false;

    MathService mathStuff;

    //Abstract
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MathService.MathBinder) service;
            mathStuff = ((MathService.MathBinder) service).getService();
            connected = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent bindIntent = new Intent(this, MathService.class);
        //Binding to a service asynchoronous want reference to service from binder
        //Need to have intent but also a callback since asynchronous and need to define when service has been connected.
        //Callback will receive
        bindService(bindIntent, connection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int op1 = Integer.valueOf(((EditText) findViewById(R.id.op1)).getText().toString());
                int op2 = Integer.valueOf(((EditText) findViewById(R.id.op2)).getText().toString());
                if(connected) {
                    int result = myBinder.addTwoNumbers(op1, op2);

                    ((TextView) findViewById(R.id.answer)).setText(String.valueOf(result));
                }
            }
        });
    }
}
