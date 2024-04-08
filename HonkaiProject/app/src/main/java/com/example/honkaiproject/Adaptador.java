package com.example.honkaiproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Adaptador extends BaseAdapter {
    private Context context;
    private int[] list;
    public Adaptador(Context context, int[] list){
        this.context=context;
        this.list=list;
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
        ImageView iv=new ImageView(context);
        iv.setImageResource(list[position]);
        iv.setLayoutParams(new ViewGroup.LayoutParams(250,250));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setPadding(5,5,5,5);
        return iv;
    }
}
