package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailSend extends AppCompatActivity {
    EditText sub,em,message;
    Button snd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);
         sub = (EditText) findViewById(R.id.subject);
         em = (EditText) findViewById(R.id.to);
         message = (EditText) findViewById(R.id.msg);
        snd = (Button) findViewById(R.id.send);
        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.putExtra(Intent.EXTRA_SUBJECT, sub.getText().toString());
                    i.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                    i.setData(Uri.parse("mailto:"));
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_EMAIL, em.getText().toString());
                    try {
                        //startActivity(Intent.createChooser(i, "Send  mail.... "));
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(EmailSend.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
             catch (Exception e) {
                Toast.makeText(EmailSend.this, e.toString(), Toast.LENGTH_LONG).show();
            }
            }
        });

    }
}
