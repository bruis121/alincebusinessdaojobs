package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fangfas.alincebusinessdaojobs.AppConst;
import com.fangfas.alincebusinessdaojobs.R;



/**
 * Created by Administrator on 2016/12/14 0014.
 * 广告招商介绍详情
 */

public class FragmentAdDetail extends Fragment {
   private WebView web;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.web,null);
        web= (WebView) view.findViewById(R.id.web);

        views();


        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl(AppConst.GUANGGAODetAils);
        return view;

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
