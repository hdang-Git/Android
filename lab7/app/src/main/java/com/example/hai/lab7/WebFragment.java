package com.example.hai.lab7;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        // Inflate the layout for this fragment

        webView = (WebView) v.findViewById(R.id.fragment_web); //container.getContext()
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        try {
            webView.loadUrl("http://www.yahoo.com");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        webView = new WebView(getActivity()); //getActivity //container.getContext()
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setLoadWithOverviewMode(true);
        //webView.getSettings().setUseWideViewPort(true);
        //webView.getSettings().setSupportZoom(false);
        */
        return v;
    }

    public void changeURL(String url){
        log.info("ChangeURL Called in fragment\n");
        webView.loadUrl("http://www.google.com");
    }

    public interface SenderInterface{

    }

}
