package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingCameraPicture extends AppCompatActivity {
    ImageView iv;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_camera_picture);
        Button btn = (Button) findViewById(R.id.takepicture);
        iv = (ImageView) findViewById(R.id.imagecamera);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,10);
            }
        });


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(i,0);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10 && resultCode == RESULT_OK)
        {
            Bundle b = data.getExtras();
           // Toast.makeText(SettingCameraPicture.this,b.toString(),Toast.LENGTH_LONG).show();
            try {
                bmp = (Bitmap) b.get("data");
                iv.setImageBitmap(bmp);
            }catch(Exception e)
            {
                Toast.makeText(SettingCameraPicture.this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
