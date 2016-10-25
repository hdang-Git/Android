package edu.temple.cis3505_lab6;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;


public class PaletteFragment extends Fragment {
    SenderInterface activity;
    final String[] actualColors = {"WHITE","RED", "BLUE", "GREEN", "YELLOW"};

    public PaletteFragment(){
    }


    @Override
    public void onAttach(Activity c) {
        super.onAttach(c);
        activity = (SenderInterface) c;
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_palette, container, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerFrag);


        Resources res = getResources();
        String[] colors = res.getStringArray(R.array.color_labels_array);
        CustomAdapter adapter = new CustomAdapter(v.getContext(), android.R.layout.simple_spinner_dropdown_item, colors); //or getActivity()?
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String color = parent.getSelectedItem().toString();
                //pass the data from this fragment to implemention of interface
                activity.passColor(actualColors[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }

    public interface SenderInterface{
        public void passColor(String color);
    }
}
