package com.example.connorglennon.movies.util;

import android.content.Context;
import android.widget.ImageView;

import com.example.connorglennon.movies.service.API;
import com.squareup.picasso.Picasso;

/**
 * Created by Connor Glennon on 23/11/2017.
 */

public class ImageLoader {
    public static void loadURL(String url, ImageView imageView, Context context)
    {
        Picasso.with(context).load(url).into(imageView);
    }
}
