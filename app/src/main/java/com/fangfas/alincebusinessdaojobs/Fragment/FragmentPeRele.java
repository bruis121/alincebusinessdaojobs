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
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.fangfas.alincebusinessdaojobs.Json.DetailsProductContent;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.View.MyViewPager;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskDetails;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;




/**
 * Created by Administrator on 2016/11/10 0010.
 * 销售详情--任务详情--提成规则
 */

public class FragmentPeRele extends Fragment {
    private  View  view;
    private MyViewPager viewPager;
    private int type;

    //出厂价
    private TextView  tx_shop_price;
    //零售价
    private TextView tx_suggest_price;
    private LinearLayout  ll;
    //提成比例
    private TextView tx_ticheng_ratio;
    //提成奖金
    private TextView tx_mix_ticheng_price;
    //打款金额
    private TextView tx_mix_dakuan_price;
    private LinearLayout  lv;

    public FragmentPeRele() {

    }

    @SuppressLint("ValidFragment")
    public FragmentPeRele(MyViewPager vp, int i) {
        this.viewPager=vp;
        this.type=i;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_perele,null);
        tx_shop_price= (TextView) view.findViewById(R.id.tx_shop_price);
        tx_suggest_price= (TextView) view.findViewById(R.id.tx_suggest_price);
        ll= (LinearLayout) view.findViewById(R.id.ll);
        tx_ticheng_ratio= (TextView) view.findViewById(R.id.tx_ticheng_ratio);
        tx_mix_ticheng_price= (TextView) view.findViewById(R.id.tx_mix_ticheng_price);
        tx_mix_dakuan_price= (TextView) view.findViewById(R.id.tx_mix_dakuan_price);
        lv= (LinearLayout) view.findViewById(R.id.lv);

        ViewGroup.LayoutParams   l=ll.getLayoutParams();
        int  h=l.height;

        viewPager.calculate(type,h);




            FragmentTaskDetail activty= (FragmentTaskDetail) getParentFragment();
            new RequestTaskDetails(activty.stringRequest,getActivity(),handler).canshu(activty.trid);






        return view;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==5) {
                try{
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                    String content = JSONObject.parseObject((String) msg.obj).getString("content");
                    if ("".equals(JSONObject.parseObject(content).getString("good_content3"))) {
                        ll.setBackgroundResource(R.mipmap.empty);
                        lv.setVisibility(View.INVISIBLE);
                    } else {
                        lv.setVisibility(View.VISIBLE);
                        DetailsProductContent bean = JSONObject.parseObject((String) msg.obj, DetailsProductContent.class);
                        tx_shop_price.setText("￥" + bean.content.good_content3.shop_price + "元/瓶");
                        tx_suggest_price.setText("￥" + bean.content.good_content3.suggest_price + "元/瓶");
                        tx_ticheng_ratio.setText(bean.content.good_content3.ticheng_ratio + "%");
                        tx_mix_ticheng_price.setText("￥" + bean.content.good_content3.mix_ticheng_price);
                        tx_mix_dakuan_price.setText("￥" + bean.content.good_content3.mix_dakuan_price);
                    }
                } catch (com.alibaba.fastjson.JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }}};
}
