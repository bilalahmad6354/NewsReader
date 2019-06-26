package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.newsreader.common.HelperVariables;
import com.wang.avi.AVLoadingIndicatorView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    public String urlToLoad = "";
    @BindView(R.id.webview) WebView webView;
    @BindView(R.id.progressBar) AVLoadingIndicatorView progressBar;
    @BindView(R.id.viewLoadindBar) View viewLoadindBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ButterKnife.bind(this);
        showLoader();
        setOnClickListenerForWebView();
        setUrl();
    }

    private void setOnClickListenerForWebView() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                hideLoader();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)  {
                hideLoader();
            }
        });

    }

    private void showLoader() {
        viewLoadindBar.setVisibility(View.VISIBLE);
        progressBar.show();
    }

    private void hideLoader() {
        viewLoadindBar.setVisibility(View.INVISIBLE);
        progressBar.hide();
    }

    private void setUrl() {
        urlToLoad = getIntent().getStringExtra(HelperVariables.KEY_URL_ARGUMENT);
        webView.loadUrl(urlToLoad);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
