package com.example.irfanirfi.customlistview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Irfan Irfi on 8/21/2017.
 */

public class MyAdapter extends ArrayAdapter<String> {
    private String[] des;
    private int[] ivv;
    public MyAdapter(@NonNull Context context,  String[] values,String[] descriptions,int[] iv) {
        super(context, R.layout.mylistview, values);
      this.des = descriptions;
        this.ivv = iv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View CustomView = inflater.inflate(R.layout.mylistview,parent,false);
        TextView name = (TextView) CustomView.findViewById(R.id.textView);
        TextView description = (TextView) CustomView.findViewById(R.id.textView2);
        ImageView iv = (ImageView) CustomView.findViewById(R.id.imageView);

        String n = getItem(position).toString();
        name.setText(n);

        description.setText(this.des[position]);
        iv.setImageResource(this.ivv[position]);
        return CustomView;
    }
}
