package com.example.ambareeshb.payukickstarter.ui;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ambareesh on 17/8/17.
 */

public class BindingAdapters {
//    @BindingAdapter("android:text")
//    public static void setText(TextView view, String  text) {
//         view.setText("test");
//    }
    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImageView(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }
}
