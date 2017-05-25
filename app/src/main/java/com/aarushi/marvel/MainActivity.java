package com.aarushi.marvel;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    RequestQueue requestQueue;
    ArrayList<MarvelBean> MarvelList;
    MarvelAdapter marvelAdapter;
    RecyclerView recList;

    void initViews(){
        pd=new ProgressDialog(this);
        requestQueue= Volley.newRequestQueue(this);
        MarvelList=new ArrayList<>();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"Insert data");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Util.isNetworkAvailable(MainActivity.this)) {

            cashier_retrieve_Request();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        recList = (RecyclerView) findViewById(R.id.recycler_view);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        recList.setLayoutManager(staggeredGridLayoutManager);

        if (Util.isNetworkAvailable(MainActivity.this)) {
            pd.show();
        cashier_retrieve_Request();}
    }

    public void cashier_retrieve_Request(){
        StringRequest request = new StringRequest(Request.Method.GET, "https://gateway.marvel.com:443/v1/public/characters?ts=1495686172&apikey=ccb8de236b746817b74ff03d4b9b6754&hash=2a572847993480e2e3eebdb9a670aeab", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                 pd.dismiss();
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("objectsss", String.valueOf(jsonObject));

if (jsonObject.has("data")) {
    JSONObject jsonobject_data=jsonObject.getJSONObject("data");
    JSONArray jsonArray_results = jsonobject_data.getJSONArray("results");
    Log.i("testing", String.valueOf(jsonArray_results));
    for (int i = 0; i < jsonArray_results.length(); i++) {
        MarvelList.add(new MarvelBean(jsonArray_results.getJSONObject(i).getString("description"), jsonArray_results.getJSONObject(i).getJSONObject("thumbnail").getString("path")+"/portrait_xlarge.jpg", jsonArray_results.getJSONObject(i).getString("name")));
    }
    marvelAdapter = new MarvelAdapter(MainActivity.this, MarvelList);
    recList.setAdapter(marvelAdapter);


}
else{
    Toast.makeText(MainActivity.this, "No data Available", Toast.LENGTH_SHORT).show();
}



                }catch(Exception e){
                    e.printStackTrace();
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "Some Connectivity Error due to JSON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("error", String.valueOf(volleyError));
                pd.dismiss();
                Toast.makeText(MainActivity.this, "Some Connectivity Error"+volleyError, Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                return params;
            }};
        requestQueue.add(request);

    }


}
