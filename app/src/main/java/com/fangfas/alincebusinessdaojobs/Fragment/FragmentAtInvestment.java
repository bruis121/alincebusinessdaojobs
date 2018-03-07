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
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.View.MyViewPager;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskDetails;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;




/**
 * Created by Administrator on 2016/11/10 0010.
 * 销售详情--任务详情--招商政策
 */

public class FragmentAtInvestment extends Fragment {
    private  View view;
    private MyViewPager viewPager;
    private int type;
    private LinearLayout ll_at;
    private TextView tx_data;
    ViewTreeObserver vto;

    public FragmentAtInvestment(){

    }

    @SuppressLint("ValidFragment")
    public FragmentAtInvestment(MyViewPager vp, int i) {
        this.viewPager=vp;
        this.type=i;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_atinvestment,null);
        ll_at= (LinearLayout) view.findViewById(R.id.ll_at);
        tx_data= (TextView) view.findViewById(R.id.tx_data);


//        ViewGroup.LayoutParams   l=tx_data.getLayoutParams();
//        int  h=l.height;

//        viewPager.calculate(type,h);


        FragmentTaskDetail fragment= (FragmentTaskDetail) getParentFragment();

        new RequestTaskDetails(fragment.stringRequest,getActivity(),handler).zhengce(fragment.trid);


        return view;
    }

    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 4) {
                try{
                    String  data= (String) msg.obj;
                    if(JSONObject.parseObject(data).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll_at,JSONObject.parseObject(data).getString("content"));
                        return;
                    }
                    String v=JSONObject.parseObject(data).getString("content");
                    String s=JSONObject.parseObject(v).getString("content");
                    tx_data.setText(s);
                    vto = tx_data.getViewTreeObserver();
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        public boolean onPreDraw() {
                            int height = tx_data.getMeasuredHeight();
                            if(height!=0){
                                viewPager.calculate(type,height);
                            }
                            return true;
                        }
                    });

                } catch (com.alibaba.fastjson.JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }}};


}
