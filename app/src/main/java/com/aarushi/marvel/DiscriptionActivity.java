package com.aarushi.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DiscriptionActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtName,txtDiscription;
    ArrayList<MarvelBean> marvelList;
    int position;

    void initViews(){
        imageView=(ImageView)findViewById(R.id.imagedis);
        txtName=(TextView)findViewById(R.id.disname);
        txtDiscription=(TextView)findViewById(R.id.discription);
        Intent i=getIntent();
        marvelList= (ArrayList<MarvelBean>) i.getSerializableExtra("marvellist");
        position=i.getIntExtra("position",0);
        Log.i("marvelist", String.valueOf(marvelList));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discription);
        initViews();

        Picasso.with(this).load(marvelList.get(position).getImageviewurl()).into(imageView);

        txtName.setText(marvelList.get(position).getName());
        if (marvelList.get(position).getDiscription().isEmpty()){

        txtDiscription.setText("No Discription Available");
        }
        else{
            txtDiscription.setText(marvelList.get(position).getDiscription());
        }




    }
}
