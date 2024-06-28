package com.example.honkaiprojectz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honkaiprojectz.R;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private int[] list;
    private String[] name;
    LayoutInflater inflater;

    public GridAdapter(Context context, int[] list, String[] name){
        this.context=context;
        this.list=list;
        this.name = name;
    }

    @Override
    public int getCount() {
        return list.length;
    }
    @Override
    public Object getItem(int position) {
        return list[position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = inflater.inflate(R.layout.custom_gridview, null);

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);

        imageView.setImageResource(list[position]);
        textView.setText(name[position]);

        return convertView;
    }
}
