package com.aarushi.marvel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LENOVO on 5/24/2017.
 */

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder> {

    ArrayList<MarvelBean> marvelList;
    Context context;

    public MarvelAdapter(Context context,ArrayList<MarvelBean> marvelList) {
        this.marvelList = marvelList;
        this.context=context;
    }

    @Override
    public void onBindViewHolder(MarvelViewHolder holder, final int position) {
        MarvelBean mb=marvelList.get(position);
        Picasso.with(context).load(mb.getImageviewurl()).into(holder.imageView);
       // holder.imageView.setImageBitmap(mb.getImageview());
        holder.etxtText.setText(mb.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,DiscriptionActivity.class);
                i.putExtra("marvellist",marvelList);
                i.putExtra("position",position);
                context.startActivity(i);

            }

        });
    }

    @Override
    public MarvelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);

        return new MarvelViewHolder(itemView);

    }



    @Override
    public int getItemCount() {
        return marvelList.size();
    }

    public static class MarvelViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView etxtText;
        public MarvelViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageview);
            etxtText=(TextView)itemView.findViewById(R.id.etxttext);
        }
    }

}
