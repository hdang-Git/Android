package edu.temple.cis3505_lab6;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Hai Dang on 9/22/2016.
 */

public class CustomAdapter extends ArrayAdapter {

    String colors[] = null;
    static final String[] actualColors = {"WHITE","RED", "BLUE", "GREEN", "YELLOW"};

    public CustomAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        this.colors = (String[]) objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setBackgroundColor(Color.parseColor(actualColors[position]));
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setBackgroundColor(Color.parseColor(actualColors[position]));
        return v;
    }
}
