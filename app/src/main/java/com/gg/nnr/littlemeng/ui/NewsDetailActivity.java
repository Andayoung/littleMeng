package com.gg.nnr.littlemeng.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gg.nnr.littlemeng.R;


/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class NewsDetailActivity extends AppCompatActivity {
    String link;
    private Toolbar toolbar;
    WebView contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Bundle bundle = this.getIntent().getExtras();
        link = bundle.getString("link");
        toolBar();
        initView();
    }

    private void initView() {
        contentView=(WebView)findViewById(R.id.txt_news_content);
        contentView.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        contentView.loadUrl(link);
        //设置Web视图
        contentView.setWebViewClient(new newsWebViewClient ());
    }
    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("详情页面");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class newsWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && contentView.canGoBack()) {
            contentView.goBack();
            return true;
        }
        return false;
    }
}
