package com.example.honkaiprojectz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honkaiprojectz.R;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private int[] imageResource;
    private String[] name;
    private String[] description;
    LayoutInflater inflater;

    public ListAdapter(Context context, int[] imageResource, String[] name, String[] description) {
        this.context = context;
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
    }

    public int getCount() {
        return imageResource.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int[] getImageResource() {
        return imageResource;
    }

    public void setImageResource(int[] imageResource) {
        this.imageResource = imageResource;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.txtName);
        TextView descriptionTextView = convertView.findViewById(R.id.txtDescription);
        ImageView imageView = convertView.findViewById(R.id.ivItem);

        nameTextView.setText(name[position]);
        descriptionTextView.setText(description[position]);
        imageView.setImageResource(imageResource[position]);

        return convertView;
    }
}
