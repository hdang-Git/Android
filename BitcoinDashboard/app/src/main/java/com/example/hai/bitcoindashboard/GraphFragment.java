package com.example.hai.bitcoindashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {


    ImageView graph;
    Spinner spinner;
    CustomSpinnerAdapter adapter;
    String times[] = {"1 day", "5 days", "1 month", "6 months", "1 year", "2 years"};   //"1d", "5d", "1M", "6M", "1Y", "2Y";
    String days[] = {"1d", "5d", "30d", "180d", "365d", "730d"};
    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graph, container, false);

        graph = (ImageView) v.findViewById(R.id.graph);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        adapter = new CustomSpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, times);
        spinner.setAdapter(adapter);

        //Picasso.with(graph.getContext()).load("http://i.imgur.com/DvpvklR.png").into(graph);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Picasso.with(graph.getContext()).load("https://chart.yahoo.com/z?s=BTCUSD=X&t="+days[position]).into(graph);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

}
