package com.example.hai.lab7;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.logging.Logger;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {
    WebView webView;
    Logger log = Logger.getAnonymousLogger();

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        // Inflate the layout for this fragment

        webView = (WebView) v.findViewById(R.id.fragment_web);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        return v;
    }

    public void changeURL(String url){
        log.info("ChangeURL Called in fragment\n");
        //validate non-empty url
        if(url != null)
            if(!url.startsWith("http://")){ //if it doesn't have valid start create a valid url
                String temp = "https://" + url;
                url = temp;
            }
            webView.loadUrl(url);
    }

}
