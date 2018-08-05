package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataSending extends AppCompatActivity {
    TextView t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sending);
        Button btn = (Button) findViewById(R.id.snd);
        t4 = (TextView) findViewById(R.id.textView4);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DataSending.this,MyPicture.class);
//                i.putExtra("data","how are you?");
//                startActivityForResult(i,2);
//            }
//        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DataSending.this,MyPicture.class);
                i.putExtra("msg","This is message form the data sending activity.");
                i.putExtra("b",true);
                i.putExtra("in",123);
               // startActivity(i);
                startActivityForResult(i,1);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            String n = bundle.getString("returnmsg");
            t4.setText(n);
        }














//        if(requestCode == 1 && resultCode == RESULT_OK)
//        {
//            Bundle b = data.getExtras();
//            String n = b.getString("back");
//            t4.setText(n);
//        }

    }
}
