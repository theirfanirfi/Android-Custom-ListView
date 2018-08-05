package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CUpicture extends AppCompatActivity  implements View.OnClickListener {
    Button choose,upload,downloadImagee,save;
    ImageView iv3,div;
    Bitmap bmp;
    String imageName = "test.jpg";
    String encoded_string = "";
    Bitmap DownloadImageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupicture);
        choose = (Button) findViewById(R.id.chooseImage);
        choose.setOnClickListener(this);

        upload = (Button) findViewById(R.id.uploadImage);
        save = (Button) findViewById(R.id.Save);
        save.setOnClickListener(this);
        upload.setOnClickListener(this);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        div = (ImageView) findViewById(R.id.downloadImageView);

        downloadImagee = (Button) findViewById(R.id.downloadImage);
        downloadImagee.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int idd = view.getId();
        switch(idd)
        {
            case R.id.chooseImage:
               // Toast.makeText(CUpicture.this,"working",Toast.LENGTH_LONG).show();

               //Intent i  = new Intent(Intent.ACTION_CHOOSER, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent i = new Intent(Intent.ACTION_GET_CONTENT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i,1);


                break;
            case R.id.uploadImage:
                bmp = ((BitmapDrawable) iv3.getDrawable()).getBitmap();
                    new UploadImage().execute();
                break;
            case R.id.downloadImage:
                //Toast.makeText(CUpicture.this,"working",Toast.LENGTH_LONG).show();

                new DownloadImage().execute();
                break;
            case R.id.Save:

                div.setDrawingCacheEnabled(true);
                Bitmap bitmap = div.getDrawingCache();
                //File file = new File("/DCIM/Camera/image.jpg");
                File root = Environment.getExternalStorageDirectory();
                File cachePath = new File(root.getAbsolutePath() + "/Download/image.jpg");
                try
                {
                    cachePath.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(cachePath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                    Toast.makeText(CUpicture.this,"Image Saved",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(CUpicture.this,e.toString(),Toast.LENGTH_LONG).show();
                }
                break;
        }


    }

    class UploadImage extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array,0);
            return null;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(CUpicture.this,"Image Uploading.......",Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
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
                        Toast.makeText(CUpicture.this,msg,Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        Toast.makeText(CUpicture.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CUpicture.this,error.toString(),Toast.LENGTH_LONG).show();
                }

            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("encoded",encoded_string);
                    params.put("image_name",imageName);
                    return params;
                }
            };
            RequestQueue rq = Volley.newRequestQueue(CUpicture.this);
            rq.add(request);
        }
    }



    private class DownloadImage extends AsyncTask<Void,Void,Bitmap> {
           private Bitmap dimage;
        @Override
        protected Bitmap doInBackground(Void... voids) {

            this.makeRequest();
            //return BitmapFactory.decodeFile(DownloadImageUrl);
            return DownloadImageUrl;
        }

        @Override
        protected void onPreExecute() {

            Toast.makeText(CUpicture.this, "Image Downloading....", Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //div.setImageBitmap(dimage);

        }


        private void makeRequest() {
            final ImageRequest request = new ImageRequest("http://192.168.199.2/AndroidFiles/images/test.jpg", new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    try {
                        //JSONObject object = new JSONObject(response);
                        //byte[] b = response.getBytes();
                        div.setImageBitmap(bitmap);
                        dimage = bitmap;
                        DownloadImageUrl = bitmap;
                        //String msg = object.getString("message");
                        //Toast.makeText(CUpicture.this, DownloadImageUrl.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        Toast.makeText(CUpicture.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            },200, 100, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CUpicture.this, error.toString(), Toast.LENGTH_LONG).show();
                }

            });

            RequestQueue rq = Volley.newRequestQueue(CUpicture.this);

            rq.add(request);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            Uri sImage = data.getData();
            iv3.setImageURI(sImage);
        }
    }


}
