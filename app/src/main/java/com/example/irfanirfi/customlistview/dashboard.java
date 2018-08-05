package com.example.irfanirfi.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class dashboard extends AppCompatActivity {
    final String FETCH_USERS_URL = "http://192.168.199.2/AndroidFiles/fetchusers.php";
    ListView lv;
    HashMap<String,String> hm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        lv = (ListView) findViewById(R.id.lv);
        StringRequest request = new StringRequest(Request.Method.POST, FETCH_USERS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray ids = object.getJSONArray("ids");
                    JSONArray emails = object.getJSONArray("emails");
                    JSONArray passwords = object.getJSONArray("passwords");
                        hm = new HashMap<>();

                    String[] em = new String[emails.length()];
                    for(int i =0 ;i<emails.length(); i++)
                    {
                        em[i] = emails.getString(i);
                        hm.put(""+i+"",passwords.getString(i));
                    }


                    ArrayAdapter adapter = new ArrayAdapter(dashboard.this,android.R.layout.simple_list_item_1,em);
                    lv.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(dashboard.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(dashboard.this,error.toString(),Toast.LENGTH_LONG).show();
            }

    }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                    parameters.put("id","1");
                return parameters;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(request);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pass = hm.get(""+i+"");
                Toast.makeText(dashboard.this,pass,Toast.LENGTH_LONG).show();
            }
        });
    }

}
