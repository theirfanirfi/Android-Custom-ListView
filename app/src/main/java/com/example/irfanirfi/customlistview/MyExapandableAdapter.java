package com.example.irfanirfi.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Irfan Irfi on 8/22/2017.
 */

public class MyExapandableAdapter extends BaseExpandableListAdapter {
    Context context;
    String[] grps;
    String[] chlds;
    MyExapandableAdapter(Context ctx,String[] group,String[] childs)
    {
        this.context = ctx;
        this.grps = group;
        this.chlds = childs;
    }
    @Override
    public int getGroupCount() {
        return this.grps.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return this.chlds.length;
    }

    @Override
    public Object getGroup(int i) {
        return this.grps[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.chlds[i];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
//        TextView tx = new TextView(this.context);
//        tx.setText(this.grps[i]);
//        tx.setTextSize(30);
//
//        return tx;

        LayoutInflater myInflator = LayoutInflater.from(this.context);
        View CustomView = myInflator.inflate(R.layout.expan,viewGroup,false);

        TextView myTxt = (TextView) CustomView.findViewById(R.id.headingText);
        myTxt.setText(this.grps[i]);


//        myTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,grps[i],Toast.LENGTH_LONG).show();
//            }
//        });
        return CustomView;
    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        LayoutInflater myInflator = LayoutInflater.from(this.context);
        View CustomView = myInflator.inflate(R.layout.child,viewGroup,false);

        TextView xt = (TextView) CustomView.findViewById(R.id.childTextView);
        xt.setText(this.chlds[i]);
        xt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,chlds[i],Toast.LENGTH_LONG).show();
            }
        });
        return CustomView;

//
//        TextView txx = new TextView(this.context);
//        txx.setText(this.chlds[i]);
//        txx.setTextSize(30);
//        return txx;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {

        return true;
    }
}
