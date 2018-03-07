package com.fangfas.alincebusinessdaojobs.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;


import com.alibaba.fastjson.JSONObject;
import com.fangfas.alincebusinessdaojobs.Httprequest.demo;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.View.CustWebView;
import com.fangfas.alincebusinessdaojobs.View.MyViewPager;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskDetails;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;



/**
 * Created by Administrator on 2016/11/10 0010.
 * 销售详情--任务详情-产品展示
 */

public class FragmentProShow extends Fragment {

    private WebView webView;

    private MyViewPager viewPager;
    private int type;


    private CustWebView sc;
    private LinearLayout ll_show;
    public FragmentProShow(){

    }
    @SuppressLint("ValidFragment")
    public FragmentProShow(MyViewPager vp, int i) {
        this.viewPager=vp;
        this.type=i;


    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.fragment_proshow,null);
        webView= (WebView) view.findViewById(R.id.webView);
        sc= (CustWebView) view.findViewById(R.id.sc);
        ll_show= (LinearLayout) view.findViewById(R.id.ll_show);
        final FragmentTaskDetail fragment= (FragmentTaskDetail) getParentFragment();


        fragment.ll_header.setFocusable(true);
        fragment.ll_header.setFocusableInTouchMode(true);
        fragment.ll_header.requestFocus();

        new demo().d(handler,getActivity());
//        new RequestTaskDetails( fragment.stringRequest,getActivity(),handler).zhanshi(fragment.trid);




        ViewGroup.LayoutParams   l=sc.getLayoutParams();
        final int heit=l.height;




        viewPager.calculate(type,heit);
        webView.setWebViewClient(new WebViewClient() { // 通过webView打开链接，不调用系统浏览器

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                int  we=webView.getContentHeight();

            }
        });


        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setSupportZoom(true);
//        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setAllowFileAccess(true); // 允许访问文件
        webView.getSettings().setBuiltInZoomControls(true); // 设置显示缩放按钮
//        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);



        return view;
    }

    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                try{
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll_show,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                    String   be=((String)msg.obj).substring(36,((String)msg.obj).length()-2);

                    webView.loadDataWithBaseURL(null,be,"text/html","text/html",null);
                } catch (com.alibaba.fastjson.JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                //测试
            }else  if(msg.what==2){
                String   be=((String)msg.obj).substring(9,((String)msg.obj).length()-25);
                webView.loadDataWithBaseURL(null,(String) msg.obj,"text/html","text/html",null);
            }
        }
    };
}
