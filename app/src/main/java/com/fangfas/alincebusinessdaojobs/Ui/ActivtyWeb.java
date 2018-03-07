package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.AppConst;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;



/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class ActivtyWeb extends BaseActicity {
    private ImageView returns;
    private TextView title;
    private WebView  web;
    private RelativeLayout  rl;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        returns= (ImageView) findViewById(R.id.returns);
        title= (TextView) findViewById(R.id.title);
        web= (WebView) findViewById(R.id.web);
        rl= (RelativeLayout) findViewById(R.id.rl);
        views();

        rl.setVisibility(View.VISIBLE);
         Intent  i=getIntent();
        if(i!=null){
           if(i.getStringExtra("enter").equals("2")){
               url=AppConst.GUANGGAOPRICE;
               title.setText(getResources().getString(R.string.gg));
           }else if(i.getStringExtra("enter").equals("1")) {
                url = AppConst.ZHANGSHANGPRICE;
                title.setText(getResources().getString(R.string.zs));
           }
        }
        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivtyWeb.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        title.startAnimation(scaleAnimation);



        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl(url);

        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void views() {
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setUseWideViewPort(true);//关键点//扩大比例的缩放
//        //自适应屏幕
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setDisplayZoomControls(false);
        web.getSettings().setAllowFileAccess(true); // 允许访问文件
        web.getSettings().setBuiltInZoomControls(true); // 设置显示缩放按钮
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }
}
