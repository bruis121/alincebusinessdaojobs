package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentTaskSetbacks;
import com.fangfas.alincebusinessdaojobs.Method.BaseFragmentActivty;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.BroadCastManager;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.NoScrollViewPager;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentTaskDetail;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskDetails;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/11/9 0009.
 * 销售任务详情页
 */

public class ActivtyDetails extends BaseFragmentActivty implements View.OnClickListener{
    private ImageView  returns;
    //任务详情
    private TextView TaskDetails;
    //任务进度
    public TextView  TaskSetbacks;
    private NoScrollViewPager Pages;
    private LinearLayout ll;
    private int blue,write;
    // tab页面列表
    private List<Fragment> fragments;




    private LinearLayout ll_task_page;


    private  String  trid;
    //判断是否已经领取任务
    public  String  getlingqu;
    //判断是否收藏
    public   String  cid;
    //判断是否过期
    public  String  isinvalid;
    //判断任务是否领取完
    public  String  isend; //true  领取完，false未领取完
    private StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_details);
        returns= (ImageView) findViewById(R.id.returns);
        TaskDetails= (TextView) findViewById(R.id.task_details);
        TaskSetbacks= (TextView) findViewById(R.id.task_setbacks);
        Pages= (NoScrollViewPager) findViewById(R.id.pages);
        ll= (LinearLayout) findViewById(R.id.ll);
        ll_task_page= (LinearLayout) findViewById(R.id.ll_task_page);

        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivtyDetails.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        ll_task_page.startAnimation(scaleAnimation);


        trid=getIntent().getStringExtra("trid");
        getLingqu();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           blue= ContextCompat.getColor(ActivtyDetails.this,R.color.blue);
            write= ContextCompat.getColor(ActivtyDetails.this,R.color.write);
        }else{
            blue= getResources().getColor(R.color.blue);
            write=getResources().getColor(R.color.write);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);





        InitViewPager();





        returns.setOnClickListener(this);

    }

    public void getLingqu() {
        //判断是否收藏、领取任务

        RequestTaskDetails model=new RequestTaskDetails(stringRequest,ActivtyDetails.this,handler);
        model.GetGtid(trid);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==6){

                try {
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new  Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }

                    String  datas= JSONObject.parseObject((String) msg.obj).getString("content");
                    getlingqu=JSONObject.parseObject(datas).getString("gtid");
                    cid=JSONObject.parseObject(datas).getString("cid");
                    isend= JSONObject.parseObject(datas).getString("isend");
                    isinvalid= JSONObject.parseObject(datas).getString("isinvalid");
                    //发送广播
                    Intent intent = new Intent();
                    intent.putExtra("cid", cid);
                    intent.putExtra("getlingqu",getlingqu);
                    intent.putExtra("isend",isend);
                    intent.putExtra("isinvalid",isinvalid);

                    intent.setAction("datas");
                    BroadCastManager.getInstance().sendBroadCast(ActivtyDetails.this, intent);


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                InitTextView();
            }
        }};
    /**
     * 初始化viewpager页
     */
    private void InitViewPager() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentTaskDetail());
        fragments.add(new FragmentTaskSetbacks());
        Pages.setAdapter(new   myPagerAdapter(getSupportFragmentManager(),
                fragments));
        Pages.setCurrentItem(0);
        Pages.addOnPageChangeListener(new MyOnPageChangeListener());
    }
    private void InitTextView() {
        TaskDetails.setOnClickListener(new MyOnClickListener(0));
        if(!TextUtils.isEmpty(getlingqu)&&getlingqu!=null){
            TaskSetbacks.setOnClickListener(new MyOnClickListener(1));
        }else {
            TaskSetbacks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Snackbars bar=new Snackbars(findViewById(R.id.ll),getString(R.string.hint25));
                }
            });
        }


    }

    @Override
    public void onClick(View view) {
        if(R.id.returns==view.getId()){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                finishAfterTransition();
            }
            else{

                finish();
            }
        }

    }


    /**
     * 头标点击监听
     *
     * @author Administrator
     *
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            switch (index) {
                case 0:
                    TaskDetails.setTextColor(blue);
                    TaskSetbacks.setTextColor(write);
                    TaskDetails.setBackgroundResource(R.drawable.shape_home_check);
                    TaskSetbacks.setBackgroundResource(R.drawable.shape_home_service);
                    break;

                case 1:
                    TaskDetails.setTextColor(write);
                    TaskSetbacks.setTextColor(blue);
                    TaskDetails.setBackgroundResource(R.drawable.shape_home);
                    TaskSetbacks.setBackgroundResource(R.drawable.shape_home_service_check);
                    break;


            }
            Pages.setCurrentItem(index);
        }

    }
    /**
     * 定义适配器
     */
    public  class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;





        public myPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
            super(supportFragmentManager);
            this.fragmentList = fragmentList;
        }


        /**
         * 得到每个界面
         */
        @Override
        public Fragment getItem(int arg0) {

            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        /**
         * 得到每个界面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return super.getPageTitle(position);
        }

        /**
         * 页面总个数
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragmentList == null ? 0 : fragmentList.size();
        }

    }



    /**
     * 为选项卡绑定监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrollStateChanged(int index) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int index) {

            /**
             * 某个选项卡选中后，title颜色改变
             */
            switch (index) {
                case 0:
                    TaskDetails.setTextColor(blue);
                    TaskSetbacks.setTextColor(write);
                    TaskDetails.setBackgroundResource(R.drawable.shape_home_check);
                    TaskSetbacks.setBackgroundResource(R.drawable.shape_home_service);

                    break;

                case 1:
                    TaskDetails.setTextColor(write);
                    TaskSetbacks.setTextColor(blue);
                    TaskDetails.setBackgroundResource(R.drawable.shape_home);
                    TaskSetbacks.setBackgroundResource(R.drawable.shape_home_service_check);
                    break;

            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getLingqu();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(this).cancelToRequestQueue(stringRequest);
    }




}


