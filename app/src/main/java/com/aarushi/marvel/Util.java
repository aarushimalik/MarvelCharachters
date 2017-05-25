package com.aarushi.marvel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Util extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);
    }
    public static boolean isNetworkAvailable(Context cxt){
        ConnectivityManager manager = (ConnectivityManager)cxt.getSystemService(cxt.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if(networkInfo!=null&&networkInfo.isConnected())

            return true;
        else return  false;
    }
}
