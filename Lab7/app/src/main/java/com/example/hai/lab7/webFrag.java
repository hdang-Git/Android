package com.example.hai.lab7;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class webFrag extends Fragment {
    WebView webView;

    public webFrag() {
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
        webView = new WebView(container.getContext()); //getActivity
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return v;
    }

    public void changeURL(String url){
        webView.loadUrl(url);
    }

    public interface SenderInterface{

    }

}
