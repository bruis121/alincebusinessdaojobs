package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.fangfas.alincebusinessdaojobs.Baseadapter.SalesCollectionAdapter;
import com.fangfas.alincebusinessdaojobs.Baseadapter.SalesTaskAdapter;
import com.fangfas.alincebusinessdaojobs.Json.HomeSalesContent;
import com.fangfas.alincebusinessdaojobs.Json.SalesColelctionContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestSalesCollection;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/9 0009.
 * 销售收藏
 */

public class FragmentSalesCollection extends Fragment implements View.OnClickListener{
    StringRequest stringRequest;

    SalesCollectionAdapter adapter;
    private  View view;
    private XRecyclerView mRecyclerView;

    private TextView general;
    private TextView commission;
    private TextView  time;
    public SalesColelctionContent bean;
    private RequestSalesCollection model;
    private   String  paixu="trid";
    public   int  page=0;

    private int refreshTime = 0;


    private SVProgressHUD mSVProgressHUD;
   public   ArrayList<SalesColelctionContent.Content.Contents> dataList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_salescollection,null);
        mRecyclerView= (XRecyclerView) view.findViewById(R.id.list);
        general= (TextView) view.findViewById(R.id.general);
        commission= (TextView) view.findViewById(R.id.commission);
        time= (TextView) view.findViewById(R.id.time);

        mSVProgressHUD = new SVProgressHUD(getActivity());
        mSVProgressHUD.showWithStatus(getString(R.string.loading));







        recycler();
        general.setOnClickListener(this);
        commission.setOnClickListener(this);
        time.setOnClickListener(this);
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
                                    initStartedService();
                                }
                                mRecyclerView.refreshComplete();

                            }

                        }, 1000);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        model.Paixu(String.valueOf(++page),paixu);
                        mRecyclerView.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        mRecyclerView.refresh();



    }

    private void initStartedService(){
        //sort =  trid（综合）    pric（价格）    ctime（创建时间）
        paixu="trid";
        page=0;
        model=new RequestSalesCollection(stringRequest,getActivity(),handler,paixu,page);
    }



    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                mSVProgressHUD.dismiss();
                try {

                refreshTime=0;
                String a =(String) msg.obj;
                    if(JSONObject.parseObject(a).getString("msg").equalsIgnoreCase("error")){
                       Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                        return;
                    }
                if (JSONObject.parseObject(JSONObject.parseObject(a).getString("content")).getString("totalElements").equals("0")) {
                    return;
                }


                bean = JSONObject.parseObject(a, SalesColelctionContent.class);
                dataList=bean.content.content;
                adapter = new SalesCollectionAdapter(getActivity(),dataList);
                mRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }




            }else if(msg.what==2){

                try{

                if(TextUtils.isEmpty(JSONObject.parseObject(JSONObject.parseObject((String) msg.obj).getString("content")).getString("content"))){
                    return;
                }else if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                        return;
                    }
                else {
                    bean = JSONObject.parseObject((String) msg.obj, SalesColelctionContent.class);
                    for (SalesColelctionContent.Content.Contents v : bean.content.content) {
                        dataList.add(v);

                    }


                    adapter.dataList=dataList;
                    adapter.notifyDataSetChanged();


                }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else if(msg.what==3){
                try {
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equals("ERROR")){
                        Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                        return;
                    }
                    bean=JSONObject.parseObject((String) msg.obj,SalesColelctionContent.class);
                    dataList=bean.content.content;

                    adapter = new SalesCollectionAdapter(getActivity(),dataList);
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
            }
        }
 };

    @Override
    public void onClick(View view) {
        //综合
        if(R.id.general==view.getId()){
            paixu="trid";
            model.getshort(paixu,0);

            //佣金
        }else  if(R.id.commission==view.getId()){
            paixu="pric";
            model.getshort(paixu,0);
        }else if(R.id.time==view.getId()){
            paixu="ctime";
            model.getshort(paixu,0);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

                initStartedService();

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

    }
}
