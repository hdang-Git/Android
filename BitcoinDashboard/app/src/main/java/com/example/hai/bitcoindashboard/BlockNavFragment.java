package com.example.hai.bitcoindashboard;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlockNavFragment extends Fragment {
    RelativeLayout view;

    public BlockNavFragment() {
    }        // Required empty public constructor



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_blocknav, container, false);
        view = (RelativeLayout) v.findViewById(R.id.blocknavfrag);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlockFragment block = new BlockFragment();
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.blockNav, block)
                .commit();
    }
}
