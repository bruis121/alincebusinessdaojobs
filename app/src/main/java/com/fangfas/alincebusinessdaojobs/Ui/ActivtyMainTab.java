package com.fangfas.alincebusinessdaojobs.Ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentSuperTask;
import com.fangfas.alincebusinessdaojobs.Json.GetAuthentContent;
import com.fangfas.alincebusinessdaojobs.Method.BaseFragmentActivty;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentCollection;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentHome;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentMy;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentTask;
import com.fangfas.alincebusinessdaojobs.Tool.BroadCastManager;
import com.fangfas.alincebusinessdaojobs.Tool.LocationService;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestCarded;
import com.fangfas.alincebusinessdaojobs.Httprequest.GetAuth;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @author yangyu
 *	首页
 */
public class ActivtyMainTab extends BaseFragmentActivty {
    public LinearLayout ll_bg;
    StringRequest stringRequest;
    public  LocationService locationService;
    public FragmentTabHost mTabHost = null;
    public View indicator = null;
    public static GetAuthentContent bean;
    String  token;




    public static ActivtyMainTab instance;

    public synchronized static ActivtyMainTab getInstance() {
        if (null == instance) {
            instance = new ActivtyMainTab();
        }
        return instance;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_maintab);
        ll_bg= (LinearLayout) findViewById(R.id.ll_bg);


        instance = this;
        inivite();




        getPersimmions();

    }

    Handler  hander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==12) {
               if(!msg.obj.equals("")) {

                  try{
                   if (JSONObject.parseObject((String) msg.obj).getString("msg").equals("没有认证信息")) {//之前返回的是error
                       //没有认证
                       MyApplication.getInstance().GetAuth = "null";
                   } else {

                       String data = JSONObject.parseObject((String) msg.obj).getString("content");
                       bean = JSONObject.parseObject(data, GetAuthentContent.class);

                        if(bean.resume_natrue==1){
                           MyApplication.getInstance().GetAuth="wait";
                       }else if(bean.resume_natrue==2){
                           MyApplication.getInstance().GetAuth="pass";
                       }else{
                            //未通过
                           MyApplication.getInstance().GetAuth="erro";
                       }
                   }
               } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


               }
            }
        }
    };

    private void inivite() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

        indicator = getIndicatorView("首页", R.layout.home_indicator);
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(indicator),
                FragmentHome.class, null);


        indicator = getIndicatorView("收藏", R.layout.collection_indicator);
        mTabHost.addTab(mTabHost.newTabSpec("collection").setIndicator(indicator),
                FragmentCollection.class, null);


        indicator = getIndicatorView("任务", R.layout.task_indicator);
        mTabHost.addTab(mTabHost.newTabSpec("task").setIndicator(indicator),
                FragmentSuperTask.class, null);


        indicator = getIndicatorView("我的", R.layout.my_indicator);
        mTabHost.addTab(mTabHost.newTabSpec("my").setIndicator(indicator),
                FragmentMy.class, null);




    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取header
       new RequestCarded(stringRequest,ActivtyMainTab.this,hander).getHeader(stringRequest,ActivtyMainTab.this);
        //获取认证信息
        try {
            new GetAuth(hander, ActivtyMainTab.this,stringRequest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private View getIndicatorView(String name, int layoutId) {
        View v = getLayoutInflater().inflate(layoutId, null);
        TextView tv = (TextView) v.findViewById(R.id.tabText);
        tv.setText(name);
        return v;
    }


    // 按两次返回键退出登陆
    // 定义退出程序boolen类型
    private boolean isExit = false;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)// 当keyCode等于退出事件值时
        {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private void exit() {
        if (isExit) {
            MyApplication.getInstance().isfirst=0;
            this.finish();
            MyApplication.getInstance().exit();

        } else {
            isExit = true;

           new Snackbars(ll_bg,getResources().getString(R.string.exit));

            mHandler.sendEmptyMessageDelayed(0, 3000);// 3秒后发送消息
        }

    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg)// 处理消息
        {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    public void onDestroy() {
        super.onDestroy();
        mTabHost = null;

    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener);
        locationService.stop(); //停止定位服务
        SingleVolleyRequestQueue.getInstance(ActivtyMainTab.this).cancelToRequestQueue(stringRequest);
        super.onStop();
    }


    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        int checkPermission = ContextCompat.checkSelfPermission(ActivtyMainTab.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivtyMainTab.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        } else {
            start();
        }

    }else{
            start();
        }
    }
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                start();
            } else {
              //被拒绝更新
            } break;
            default:
                super.onRequestPermissionsResult(requestCode,
                        permissions, grantResults);
        }
    }



    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    public BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */


                sb.append(location.getLatitude());// 纬度
                sb.append(location.getLongitude());// 经度
                sb.append(location.getCity());// 城市


                MyApplication.getInstance().Longitude= String.valueOf(+location.getLongitude()+","+location.getLatitude());
                //发送广播
                Intent intent = new Intent();
                intent.putExtra("adress", location.getCity());
                intent.setAction("gengxin");
                BroadCastManager.getInstance().sendBroadCast(ActivtyMainTab.this, intent);

            }
        }

    };



    private void start() {

        locationService = ((MyApplication)getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
//        int type = getActivity().getIntent().getIntExtra("from", 0);
        int  type=0;
        if (type == 0) {
            locationService.setLocationOption( locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption( locationService.getOption());
        }


        locationService.start();// 定位SDK// start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request


        //正常情况下访问百度定位服务器只需要2秒 3秒关闭
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivtyMainTab.getInstance().locationService.stop();
            }
        }, 3000);


    }




}



