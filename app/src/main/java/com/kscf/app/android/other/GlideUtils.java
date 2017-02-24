package com.kscf.app.android.other;

import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * Created by luoyl on 2017/2/22.
 */

public class GlideUtils {

    public static void load(Context context, ImageView imageView, String url, int defResId) {
        /*Glide.with(context)
                .load(mode)
                .centerCrop()
                .crossFade()
                .into(imageView);*/
        /*Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(defResId)
                .crossFade()
                .into(imageView);*/
        Picasso.with(context)
                .load(url)
                .centerCrop()
                .placeholder(defResId)
                .error(defResId)
                .into(imageView);
    }

    public static void load(android.support.v4.app.Fragment fragment, ImageView imageView, String url, int defResId) {
       /* Glide.with(fragment)
                .load(url)
                *//*.centerCrop()*//*
                .fitCenter()
                .
                .placeholder(defResId)
                .crossFade()
                .into(imageView);*/
        Picasso.with(fragment.getContext())
                .load(url)
                .placeholder(defResId)
                .error(defResId)
                .into(imageView);
    }
}
