package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MyPicture extends AppCompatActivity {
    private String image_name,encoded_string;
    Bitmap bitmap;
    File file;
    Uri file_uri;
    Button camera,upload;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_picture);
        camera = (Button) findViewById(R.id.Camera);
        iv = (ImageView) findViewById(R.id.imageView);
        TextView txx = (TextView) findViewById(R.id.textView3);


//        Bundle bundle = getIntent().getExtras();
//        String msg = bundle.getString("msg");
//        txx.setText(msg);


//        Bundle bundle = getIntent().getExtras();
//        String tx = bundle.getString("data");
//        TextView txx = (TextView) findViewById(R.id.textView3);
//        txx.setText(tx);



//
//        Intent backIntent = new Intent();
//        backIntent.putExtra("returnmsg","I have recieved your message.");
//        setResult(RESULT_OK,backIntent);












//        Intent i = new Intent();
//
//        i.putExtra("back","i am fine");
//        setResult(RESULT_OK,i);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take Image
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                i.putExtra(MediaStore.EXTRA_OUTPUT,file_uri);
                startActivityForResult(i,10);
            }
        });
    }

    private void getFileUri(){
        image_name = "test.jpg";



        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+image_name);
        file_uri = Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == RESULT_OK)
        {

            new Encode_image().execute();
        }
    }

    private class Encode_image extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            bitmap = BitmapFactory.decodeFile(file_uri.getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array,0);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           makeRequest();
        }



        private void makeRequest()
        {
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.199.2/AndroidFiles/picture.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        String msg = object.getString("message");
                        Toast.makeText(MyPicture.this,msg,Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        Toast.makeText(MyPicture.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MyPicture.this,error.toString(),Toast.LENGTH_LONG).show();
                }

        }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("encoded",encoded_string);
                    params.put("image_name",image_name);
                    return params;
                }
            };
            RequestQueue rq = Volley.newRequestQueue(MyPicture.this);
            rq.add(request);
        }
    }
}
