package com.example.irfanirfi.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class expandable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        ExpandableListView lv = (ExpandableListView) findViewById(R.id.explv);
       final String[] a = {"irfan","irfi","khan"};
        String[] b = {"what","how","when"};
        MyExapandableAdapter adapter = new MyExapandableAdapter(this,a,b);
        lv.setAdapter(adapter);

    }
}
