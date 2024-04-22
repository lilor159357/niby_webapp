package com.app.niby;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity {

    private AdvancedWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        costumwebviewclient client = new costumwebviewclient(this);
        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new MyChrome());
        webView = (AdvancedWebView) findViewById(R.id.webView);
                webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                findViewById(R.id.imageLoading1).setVisibility(View.GONE);
                findViewById(R.id.imageLoading2).setVisibility(View.GONE);
                //show webview
                findViewById(R.id.webView).setVisibility(View.VISIBLE);
            }
            public boolean shouldOverrideUrlLoading(WebView webView, String url){
                if (Uri.parse(url).getHost().endsWith("niby.org.il")){
                    // This is my web site, so do not override; let the WebView load the page.
                    return false;
                }
                if (Uri.parse(url).getHost().endsWith("googleapis.com")){
                    // This is my web site, so do not override; let the WebView load the page.
                    return false;
                }

                if (Uri.parse(url).getHost().endsWith("w3.org")){
                    // This is my web site, so do not override; let the WebView load the page.
                    return false;
                }
                if (Uri.parse(url).getHost().endsWith("youtube.com")){
                    // This is my web site, so do not override; let the WebView load the page.
                    return false;
                }

                // Reject everything else.
                return true;
            }
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request){
                if(Uri.parse(request.getUrl().toString()).getHost().endsWith("niby.org.il")) {
                    //The current WebView handles the url.
                    return false;

                }
                if(Uri.parse(request.getUrl().toString()).getHost().endsWith("youtube.com")) {
                    //The current WebView handles the url.
                    return false;

                }
                if(Uri.parse(request.getUrl().toString()).getHost().endsWith("googleapis.com")) {
                    //The current WebView handles the url.
                    return false;

                }

                if(Uri.parse(request.getUrl().toString()).getHost().endsWith("w3.org")) {
                    //The current WebView handles the url.
                    return false;

                }

                // Reject everything else.
                return true;
            }


        });
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);



        Intent intent = getIntent();
        Uri data = intent.getData();
        if(data != null) {
            //Load the deep-link url:
            String url = intent.getDataString();
            webView.loadUrl(url);
        }
        else
        {
            webView.loadUrl("https://www.niby.org.il/");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);




    }
    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if(KeyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack()){
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(KeyCode, event);
    }

    private class MyChrome extends WebChromeClient {
        View fullscreen = null;

        @Override
        public void onHideCustomView()
        {
            fullscreen.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback)
        {
            webView.setVisibility(View.GONE);

            if(fullscreen != null)
            {
                ((FrameLayout)getWindow().getDecorView()).removeView(fullscreen);
            }

            fullscreen = view;
            ((FrameLayout)getWindow().getDecorView()).addView(fullscreen, new FrameLayout.LayoutParams(-1, -1));
            fullscreen.setVisibility(View.VISIBLE);
        }
    }
}

class  costumwebviewclient extends WebViewClient{
    private Activity activity;

    public costumwebviewclient(Activity activity){
        this.activity = activity;
    }

    //api level less than 24
    public boolean shouldOverrideUrlLoading(WebView webView, String url){
        if (Uri.parse(url).getHost().endsWith("niby.org.il")){
        // This is my web site, so do not override; let the WebView load the page.
        return false;
        }
        if (Uri.parse(url).getHost().endsWith("youtube.com")){
            // This is my web site, so do not override; let the WebView load the page.
            return false;
        }
        if (Uri.parse(url).getHost().endsWith("googleapis.com")){
            // This is my web site, so do not override; let the WebView load the page.
            return false;
        }

        if (Uri.parse(url).getHost().endsWith("w3.org")){
            // This is my web site, so do not override; let the WebView load the page.
            return false;
        }
        if (Uri.parse(url).getHost().endsWith("google.com")){
            // This is my web site, so do not override; let the WebView load the page.
            return false;
        }

        // Reject everything else.
        return true;
    }

    //api level >=24
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request){
        if(Uri.parse(request.getUrl().toString()).getHost().endsWith("niby.org.il")) {
            //The current WebView handles the url.
            return false;

        }
        if(Uri.parse(request.getUrl().toString()).getHost().endsWith("googleapis.com")) {
            //The current WebView handles the url.
            return false;

        }

        if(Uri.parse(request.getUrl().toString()).getHost().endsWith("w3.org")) {
            //The current WebView handles the url.
            return false;

        }
        if(Uri.parse(request.getUrl().toString()).getHost().endsWith("google.com")) {
            //The current WebView handles the url.
            return false;

        }
        if(Uri.parse(request.getUrl().toString()).getHost().endsWith("youtube.com")) {
            //The current WebView handles the url.
            return false;

        }

        // Reject everything else.
        return true;
    }

   }