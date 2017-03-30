package com.framework.view.webview;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/3/24.
 */

public class CustomWebChromeClient extends WebChromeClient {
    private ViewGroup mContainerView;
    private WebView mWebView;

    public CustomWebChromeClient() {
    }

    public CustomWebChromeClient(ViewGroup containerView, WebView webView) {
        mContainerView = containerView;
        mWebView = webView;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        /*if (mContainerView != null && mWebView != null) {
            mContainerView.addView(mWebView);
        }*/
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
        /*if (mContainerView != null && mWebView != null) {
            mContainerView.removeView(mWebView);
        }*/
    }
}
