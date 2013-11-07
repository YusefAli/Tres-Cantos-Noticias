package com.trescantos.link1;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}