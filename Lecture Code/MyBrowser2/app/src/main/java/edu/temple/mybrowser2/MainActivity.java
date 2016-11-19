package edu.temple.mybrowser2;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText urlEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.web);
        urlEditText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Thread t = new Thread(){
                    @Override
                    public void run(){
                        try {
                            URL url = new URL(urlEditText.getText().toString());
                            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(
                                            url.openStream()));


                            String tmpString = "";
                            String response = "";
                            while (tmpString != null) {
                                response.concat(tmpString);
                                response = response + tmpString;
                                tmpString = reader.readLine();
                            }
                            Message msg = Message.obtain();
                            msg.obj = response;

                            Log.d("downloaded data", response);
                            //webHandler.sendMessage(msg);


                        } catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                };
            }
        });
    }
}
