package com.example.ambareeshb.payukickstarter.ui;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ambareesh on 17/8/17.
 */

public class BindingAdapters {
    @BindingAdapter("android:text")
    public static void setText1(View view, String  text) {
        ((TextView)view).setText(text);
    }
}
