package edu.temple.bitcoinstuff;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends Activity {

    //choosing name and current value of bitcoin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(){
                    @Override
                    public void run(){
                        try {
                            URL url = new URL("http://btc.blockr.io/api/v1/coin/info");
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
                            responseHandler.sendMessage(msg);


                        } catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                };
            }
        });
    }
    Handler responseHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg){
            try {
                JSONObject coinObject = new JSONObject((String) msg. obj);
                //if don't know what type of data to get, use get()
                String coinName;
                double coinPrice;
                JSONObject data = coinObject.getJSONObject("data");
                JSONObject coin = data.getJSONObject("coin");
                coinName = coin.getString("name");
                ((TextView) findViewById(R.id.coinName)).setText(coinName);

                coinPrice = data.getJSONObject("markets").getJSONObject("coinbase").getDouble("value");
                ((TextView) findViewById(R.id.coinName)).setText(String.valueOf(coinPrice));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
    });

}
