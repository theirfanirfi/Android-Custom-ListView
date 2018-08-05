package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btn,btnlogin;
    EditText email,password;
    String em = "";
    String pass = "";
    final String LOGIN_URL = "http://192.168.199.2/AndroidFiles/login.php";
    final String LOGINN_URL = "http://192.168.199.2/AndroidFiles/loginn.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn = (Button) findViewById(R.id.reg);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = email.getText().toString();
                pass = password.getText().toString();
                StringRequest rqq = new StringRequest(Request.Method.POST, LOGINN_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String msg = object.getString("message");
                            Boolean err = object.getBoolean("error");
                            if(err)
                            {
                                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
                                Intent dashboard = new Intent(MainActivity.this, dashboard.class);
                                startActivity(dashboard);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

            }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("email",em);
                        params.put("pass",pass);

                        return params;
                    }
                };
                RequestQueue rqu = Volley.newRequestQueue(MainActivity.this);
                rqu.add(rqq);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = email.getText().toString();
                pass = password.getText().toString();

                StringRequest request = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Boolean err = object.getBoolean("error");
                            String msg = object.getString("message");
                            if(err)
                            {


                                Toast.makeText(MainActivity.this,"Error Has occurred.  "+msg,Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

            }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("email",em);
                        params.put("pass",pass);
                        return params;
                    }
                };
                RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
                rq.add(request);

            }
        });
//
//        lv = (ListView) findViewById(R.id.lv);
//
//
//        String[] names = {"Web App developer","Android Apps developer", "Desktop Apps Developer"};
//        String[] descriptions = {"This is description. This is Description\n" +
//                "                this is description. This is description.","This is description. This is Description\n" +
//                "                this is description. This is description.","This is description. This is Description\n" +
//                "                this is description. This is description."};
//
//        int[] iv = {R.drawable.penguin,R.drawable.jelly,R.drawable.koala};
//        ListAdapter adapter = new MyAdapter(MainActivity.this,names,descriptions,iv);
//        lv.setAdapter(adapter);



    }
}
