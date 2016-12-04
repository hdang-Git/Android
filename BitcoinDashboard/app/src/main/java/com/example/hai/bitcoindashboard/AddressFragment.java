package com.example.hai.bitcoindashboard;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    EditText input;
    ImageButton imgButton;
    Logger log = Logger.getAnonymousLogger();

    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_address, container, false);

        input = (EditText) v.findViewById(R.id.input_addr);
        imgButton = (ImageButton) v.findViewById(R.id.imageButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("", "Image button is clicked");
                try{
                    String address = input.getText().toString();
                    retrieveAddressData(address);
                }catch(NullPointerException e){
                    Log.d("", "Null Pointer Error in AddressFragment - onCreateView() " + e);
                }
            }
        });

        return v;
    }

    public String appendToUrl(String address){
        //return "http://btc.blockr.io/api/v1/address/balance/" + address;
        return "https://blockchain.info/address/" + address + "?format=json";
    }

    public void retrieveAddressData(String url){
        log.info("retrieveAddressData() called");
        final String urlString = appendToUrl(url);
        Thread t = new Thread(){
            @Override
            public void run(){
                log.info("run() is called");
                try {
                    URL url = new URL(urlString);
                    //URL url = new URL("https://blockchain.info/address/198aMn6ZYAczwrE5NvNTUMyJ5qkfy4g3Hi?format=json");
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
        t.start();
    }

    Handler responseHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg){
            try {
                JSONObject addressObject = new JSONObject((String) msg.obj);
                ((TextView) getView().findViewById(R.id.addrBalance))
                        .setText(String.valueOf(addressObject.getString("final_balance")));
            } catch (JSONException e) {
                log.info("JSON Parsing error in AddressFragment - responseHandler()");
                e.printStackTrace();
            } catch (NullPointerException e){
                log.info("Nullpointer Exception in AddressFragment - responseHandler()");
                e.printStackTrace();
            }
            return true;
        }
    });

}
