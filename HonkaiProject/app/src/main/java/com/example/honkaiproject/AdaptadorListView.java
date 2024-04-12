package com.example.honkaiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorListView extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ItemListView> itens;
    public AdaptadorListView(Context context, List<ItemListView> itens){
        this.itens=itens;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return itens.size();
    }
    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ItemSuporte{
        ImageView imageView;
        TextView textView;
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte itemSuporte;
        if(convertView==null){            //Inflar o layout
            convertView=mInflater.inflate(R.layout.item_list,null);
            itemSuporte=new ItemSuporte();
            itemSuporte.textView=((TextView) convertView.findViewById(R.id.text1));
            itemSuporte.imageView=((ImageView) convertView.findViewById(R.id.image1));
            convertView.setTag(itemSuporte);
        }else{
            itemSuporte=(ItemSuporte) convertView.getTag();
        }
        //buscar os dados da lista e definir os valores nos itens
        ItemListView item=itens.get(position);
        itemSuporte.textView.setText(item.getTexto());
        itemSuporte.imageView.setImageResource(item.getIcone());
        return convertView;
    }

}
