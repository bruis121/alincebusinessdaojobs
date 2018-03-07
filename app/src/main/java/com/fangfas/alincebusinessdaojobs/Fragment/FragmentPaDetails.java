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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.fangfas.alincebusinessdaojobs.Baseadapter.ParameterDetailsAdapter;
import com.fangfas.alincebusinessdaojobs.Json.DetailsProductContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.View.MyViewPager;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskDetails;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;


import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/10 0010.
 * 销售详情--任务详情-参数详情
 */

public class FragmentPaDetails extends Fragment{
    private  View view;
    private MyViewPager viewPager;
    private int type;
    private LinearLayout ll_pade;
    private ListView  list;

    private ParameterDetailsAdapter adapter;







    public FragmentPaDetails(){

    }
    @SuppressLint("ValidFragment")
    public FragmentPaDetails(MyViewPager vp, int i) {
        this.viewPager=vp;
        this.type=i;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_padetails,null);
        ll_pade= (LinearLayout) view.findViewById(R.id.ll_pade);
        list= (ListView) view.findViewById(R.id.list);



        FragmentTaskDetail activty= (FragmentTaskDetail) getParentFragment();
        if(activty.trid!=null&&!"".equals(activty.trid)) {
            new RequestTaskDetails(activty.stringRequest, getActivity(), handler).canshu(activty.trid);
        }



        return view;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==5){
                try{
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll_pade,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                    DetailsProductContent bean  = JSONObject.parseObject((String) msg.obj,DetailsProductContent.class);
                    adapter=new ParameterDetailsAdapter(getActivity(),bean.content.good_content2);
                    list.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(list);
                } catch (com.alibaba.fastjson.JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }}};


    // listView自定义高度
    public void setListViewHeightBasedOnChildren(ListView lanzi) {

        ListAdapter listAdapter = lanzi.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lanzi);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lanzi.getLayoutParams();

        params.height = totalHeight
                + (lanzi.getDividerHeight() * (listAdapter.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(0, 0, 0, 0); // 可删


        viewPager.calculate(type, totalHeight
                + (lanzi.getDividerHeight() * (listAdapter.getCount() - 1)));
        lanzi.setLayoutParams(params);

    }



}
