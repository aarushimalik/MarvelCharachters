package com.aarushi.marvel;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by LENOVO on 5/24/2017.
 */

public class MarvelBean implements Serializable
{
    String discription;
    String imageviewurl;
    String name;

    public MarvelBean(String discription, String imageviewurl, String name) {
        this.discription = discription;
        this.imageviewurl = imageviewurl;
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImageviewurl() {
        return imageviewurl;
    }

    public void setImageviewurl(String imageviewurl) {
        this.imageviewurl = imageviewurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
