package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.fangfas.alincebusinessdaojobs.Baseadapter.SalesTaskAdapter;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestMyTask;
import com.fangfas.alincebusinessdaojobs.Json.HomeSalesContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/8 0008.
 * 一级界面任务
 */

public class FragmentTask extends Fragment implements View.OnClickListener{
    private  View  view;


    SalesTaskAdapter adapter;

    //进行中
    private TextView ongoing;
    //已完成
    private  TextView complete;
    //已过期
    private  TextView  be_overdue;
    private XRecyclerView mRecyclerView;

    private CoordinatorLayout cl_task;


    RequestMyTask model;
    private HomeSalesContent bean;

    private  String  where="";//where  1进行中，2已完成，3已过期

    private ArrayList<HomeSalesContent.Content.Contents> datav;

    public  int  page=0;
    private StringRequest stringRequest;
    private  int refreshTime = 0;
    private  FragmentSuperTask fragemnt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_task,null);
        ongoing= (TextView) view.findViewById(R.id.ongoing);
        complete= (TextView) view.findViewById(R.id.complete);
        be_overdue= (TextView) view.findViewById(R.id.be_overdue);
        mRecyclerView= (XRecyclerView) view.findViewById(R.id.list);
        cl_task= (CoordinatorLayout) view.findViewById(R.id.cl_task);


         fragemnt= (FragmentSuperTask) getParentFragment();
         fragemnt.mSVProgressHUD.showWithStatus(getString(R.string.loading));



         model();
         recycler();

        ongoing.setOnClickListener(this);
        complete.setOnClickListener(this);
        be_overdue.setOnClickListener(this);
        return view;
    }



    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                fragemnt.mSVProgressHUD.dismiss();
                String  data= (String) msg.obj;
                try{
                 refreshTime=0;
                if(JSONObject.parseObject(data).getString("msg").equals("ERROR")){
                    Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                    return;
                }else if(JSONObject.parseObject(JSONObject.parseObject(data).getString("content")).getString("totalElements").equals("0")){
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    return;

                }


                mRecyclerView.setVisibility(View.VISIBLE);
                bean= JSONObject.parseObject(data,HomeSalesContent.class);
                datav=bean.content.content;

                adapter = new SalesTaskAdapter(getActivity(),datav);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                mRecyclerView.refresh();
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());


                } catch (com.alibaba.fastjson.JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else if(msg.what==2){
               try{
                if(TextUtils.isEmpty(JSONObject.parseObject(JSONObject.parseObject((String) msg.obj).getString("content")).getString("content"))){
                    return;
                }else if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){

                        new Snackbars(cl_task, JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;

                }else {


                    bean = JSONObject.parseObject((String) msg.obj, HomeSalesContent.class);


                    for (HomeSalesContent.Content.Contents v : bean.content.content) {
                        datav.add(v);

                    }
                    adapter = new SalesTaskAdapter(getActivity(),datav);
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    //实现item动画
                    Animation animation = (Animation) AnimationUtils.loadAnimation(
                            getActivity(), R.anim.list_anim);
                    LayoutAnimationController lac = new LayoutAnimationController(animation);
                    lac.setDelay(0.2f);  //设置动画间隔时间
                    lac.setOrder(LayoutAnimationController.ORDER_NORMAL); //设置列表的显示顺序
                    mRecyclerView.setLayoutAnimation(lac);

                }
            } catch (com.alibaba.fastjson.JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }else if(msg.what==3){
                try {
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equals("ERROR")){
                        Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                        return;
                    }
                    bean= JSONObject.parseObject((String) msg.obj,HomeSalesContent.class);
                    datav=bean.content.content;

                    adapter = new SalesTaskAdapter(getActivity(),datav);
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
        if(R.id.ongoing==view.getId()){
            where="1";
            model.getshort(where,0);
        }else  if( R.id.complete==view.getId()){
            where="2";
            model.getshort(where,0);
        }else  if(R.id.be_overdue==view.getId()){
            where="3";
            model.getshort(where,0);
        }


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
                        new Runnable() {
                            public void run() {
                                if(refreshTime>1) {
                                    model();
                                }
                           mRecyclerView.refreshComplete();
                            }

                        }, 1000);


            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        model.getMore(where,String.valueOf(++page));
                        mRecyclerView.loadMoreComplete();
                    }
                }, 1000);
            }
        });




    }


    private void model() {
        model=new RequestMyTask(stringRequest,getActivity(),handler,"",0);
    }


    @Override
    public void onPause() {
        super.onPause();
        refreshTime=0;

    }
}
