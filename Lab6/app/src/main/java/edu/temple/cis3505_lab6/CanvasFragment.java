package edu.temple.cis3505_lab6;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.logging.Logger;


/**
 * A simple {@link Fragment} subclass.
 */
public class CanvasFragment extends Fragment {
    RelativeLayout view;

    public CanvasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Logger log = Logger.getAnonymousLogger();
        log.info("CanvasFragment is called and Created");
        View v =  inflater.inflate(R.layout.fragment_canvas, container, false);
        view = (RelativeLayout) v.findViewById(R.id.fragment_canvas);
        return v;
    }
/*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LayoutInflater inflater =  LayoutInflater.from(getActivity());

    }
*/
    public void changeBackgroundColor(String color){
        Logger log = Logger.getAnonymousLogger();
        log.info("Canvas Fragment: Background Color is changed.");
        view.setBackgroundColor(Color.parseColor(color));
    }
}
