package com.example.ambareeshb.payukickstarter.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.databinding.ActivityWebViewBinding;

import static com.example.ambareeshb.payukickstarter.Constants.WEB_VIEW_PATH;

public class WebViewActivity extends AppCompatActivity {
    private String url;
    private ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_web_view);

        url = getIntent().getExtras().getString(WEB_VIEW_PATH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.webView.loadUrl(url);
        binding.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });
    }
}
