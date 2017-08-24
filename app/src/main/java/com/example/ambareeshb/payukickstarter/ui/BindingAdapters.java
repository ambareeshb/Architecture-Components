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
    @BindingAdapter({"imageUrl", "error"})
    public static void loadImageView(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }

//    @BindingAdapter("visibleGone")
//    public static void showHide(View view, boolean show) {
//        view.setVisibility(show ? View.VISIBLE : View.GONE);
//    }
//
//    @BindingAdapter("loadingColor")
//    public static void backGroundColor(View view, boolean showBgColor){
//        view.setBackgroundResource(showBgColor ? android.R.color.holo_red_dark:
//                android.R.color.holo_blue_dark );
//    }
}
