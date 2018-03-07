package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.fangfas.alincebusinessdaojobs.Baseadapter.SalesTasklAdapter;
import com.fangfas.alincebusinessdaojobs.Json.HomeSalesContent;
import com.fangfas.alincebusinessdaojobs.Json.SalesTeypeContent;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyMainTab;
import com.fangfas.alincebusinessdaojobs.View.SalesTypePopwindow;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestSalesTask;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/24 0024.
 * 销售任务
 */


public class FragmentSalesTask extends Fragment implements View.OnClickListener{
    StringRequest stringRequest;
    HomeSalesContent bean;
    private XRecyclerView mRecyclerView;


    //类别
    private TextView  category;
    //金额
    private TextView amount_of_money;
    //佣金
    private TextView  commission;
    //时间
    private TextView  time;
    SalesTasklAdapter adapter;

    private SVProgressHUD mSVProgressHUD;
    RequestSalesTask model;
    //筛选数据类型
    SalesTeypeContent TeypeBean;

    private  int  page=0;
    //类型
    private  String  type="1";//默认1
    private   String  shorts="";
    private  ArrayList<HomeSalesContent.Content.Contents> datalist;

    private int refreshTime = 0;

   private  View  view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_salestask,null);
        mRecyclerView= (XRecyclerView) view.findViewById(R.id.recyclerView);
        category= (TextView) view.findViewById(R.id.category);
        amount_of_money= (TextView) view.findViewById(R.id.amount_of_money);
        commission= (TextView) view.findViewById(R.id.commission);
        time= (TextView) view.findViewById(R.id.time);


        if(MyApplication.getInstance().isfirst==0){
            handler.postDelayed(runnable, 1000);
        }else {

                    Requst();

        }

        mSVProgressHUD=new SVProgressHUD(getActivity());
        mSVProgressHUD.showWithStatus(getString(R.string.loading));



        recycler();



        time.setOnClickListener(this);
        commission.setOnClickListener(this);
        amount_of_money.setOnClickListener(this);
        category.setOnClickListener(this);



        return view;
    }

    private void recycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                new Handler().postDelayed(

                        new Runnable(){
                    public void run() {
                        if(refreshTime>1) {
                            model=new RequestSalesTask(stringRequest,getActivity(),handler,"1","",0);
                        }
                        mRecyclerView.refreshComplete();

                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {

                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            model.getMOre(shorts,type,++page);
                            mRecyclerView.loadMoreComplete();
                        }
                    }, 1000);
                }
        });




    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!MyApplication.getInstance().Longitude.isEmpty()) {
                Requst();
            }else{
                handler.postDelayed(this, 1000);
            }
        }
    };




    private void Requst() {
       model=new RequestSalesTask(stringRequest,getActivity(),handler,"1","",page);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSVProgressHUD.dismiss();
            if(msg.what==1){
                refreshTime=0;
                MyApplication.getInstance().isfirst=1;
                try{
                String z=(String) msg.obj;
                if(JSONObject.parseObject(z).getString("msg").equalsIgnoreCase("ERROR")){
                    Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                    return;
                }


                bean= JSONObject.parseObject(z, HomeSalesContent.class);
                datalist=bean.content.content;
                adapter = new SalesTasklAdapter(getActivity(),datalist);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.refresh();
                //获得筛选的类型
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    model.getType();
                    }
                }).start();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else if(msg.what==2){
                try {
                if(JSONObject.parseObject((String) msg.obj).getString("msg").equals("ERROR")){
                    Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                    return;
                }



                bean= JSONObject.parseObject((String) msg.obj, HomeSalesContent.class);
                adapter = new SalesTasklAdapter(getActivity(),bean.content.content);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();





                    //实现item动画
                Animation animation = (Animation) AnimationUtils.loadAnimation(
                        getActivity(), R.anim.list_anim);
                LayoutAnimationController lac = new LayoutAnimationController(animation);
                lac.setDelay(0.2f);  //设置动画间隔时间
                lac.setOrder(LayoutAnimationController.ORDER_NORMAL); //设置列表的显示顺序
                mRecyclerView.setLayoutAnimation(lac);  //为ListView 添加动画
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else  if(msg.what==3){
                try{
                TeypeBean=JSONObject.parseObject((String) msg.obj, SalesTeypeContent.class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(msg.what==4){
                   try{
                    String  v=JSONObject.parseObject(JSONObject.parseObject((String) msg.obj).getString("content")).getString("content");
                   if((JSONArray.parseArray(v)).size()==0) {
                       return;
                   }else  {
                       bean= JSONObject.parseObject((String) msg.obj, HomeSalesContent.class);
                       for(HomeSalesContent.Content.Contents  da :  bean.content.content){
                           datalist.add(da);
                       }
                       adapter.data=datalist;
                       adapter.notifyDataSetChanged();


                   }
                   } catch (JSONException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
                   }

            }






        }
    };








    @Override
    public void onClick(View view) {
        //类别
        if(R.id.category==view.getId()){
            final SalesTypePopwindow  popwindow=new SalesTypePopwindow(getActivity(),category,TeypeBean.content);
            popwindow.showAsDropDown(view);
            popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {


                    type=popwindow.flag;
                    model.getShort(popwindow.flag,"");



                }
            });


            //时间
        }else  if(R.id.time==view.getId()){
            shorts="time";

            model.getShort("1",shorts);



            //佣金
        }else  if(R.id.commission==view.getId()){

            shorts="yongjin";
            model.getShort("1",shorts);


            //金额
        }else  if(R.id.amount_of_money==view.getId()){
            shorts="money";
            model.getShort("1",shorts);

        }



    }





    @Override
    public void onPause() {
        super.onPause();
        refreshTime=0;
        mSVProgressHUD.dismissImmediately();
    }

    @Override
    public void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(getActivity()).cancelToRequestQueue(stringRequest);

    }







}