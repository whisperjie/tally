package com.whisper.tally.activity.bill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.whisper.tally.R;
import com.whisper.tally.data.entity.Category;

import java.util.List;

public class MySpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
public List<Category> categories;
public Context context;
public MySpinnerAdapter(Context context){
    this.context=context;
}

    @Override
    public int getCount() {
        return categories==null?0:categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        LayoutInflater _LayoutInflater=LayoutInflater.from(context);
        convertView=_LayoutInflater.inflate(R.layout.list_item, null);
        if(convertView!=null){
          //  textView=(TextView)convertView.findViewById(R.id.tev_spin_category);
           // textView.setText(categories.get(position).getName());
        }
        return convertView;
    }
}
