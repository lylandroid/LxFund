package com.framework.view.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.framework.app.CommApp;
import com.framework.util.NetworkUtils;

/**
 * Created by Administrator on 2017/3/24.
 */

public class CustomWebView extends WebView {

    private ViewGroup mContainerView;

    public CustomWebView(Context context, ViewGroup containerView) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        mContainerView = containerView;
        mContainerView.addView(this);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWebSettings();
        setWebChromeClient(newWebChromeClient());
        setWebViewClient(newWebViewClient());
    }

    public void setWebSettings() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);  //开启javascript
        webSettings.setDomStorageEnabled(true);  //开启DOM
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码
        // // web页面处理
        webSettings.setAllowFileAccess(true);// 支持文件流
        // webSettings.setSupportZoom(true);// 支持缩放
        // webSettings.setBuiltInZoomControls(true);// 支持缩放
        webSettings.setUseWideViewPort(true);// 调整到适合webview大小
        webSettings.setLoadWithOverviewMode(true);//调整到适合webview大小
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);//屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        webSettings.setBlockNetworkImage(true);
        //开启缓存机制
        webSettings.setAppCacheEnabled(true);
        //根据当前网页连接状态
        if (NetworkUtils.getWifiEnabled(CommApp.getApp())) {
            //设置无缓存
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //设置缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if(Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    private WebViewClient newWebViewClient() {
        return new CustomWebViewClient();
    }


    public WebChromeClient newWebChromeClient() {
        return new CustomWebChromeClient();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && onCustomBack()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * WebView返回处理
     *
     * @return true:自己处理
     */
    public boolean onCustomBack() {
        if (CustomWebView.this.canGoBack()) {
            CustomWebView.this.goBack();
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        if (mContainerView != null) {
            mContainerView.removeView(this);
            mContainerView = null;
        }
        super.destroy();
        freeMemory();
    }
}
