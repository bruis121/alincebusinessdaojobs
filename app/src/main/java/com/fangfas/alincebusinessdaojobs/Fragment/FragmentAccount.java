package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.fangfas.alincebusinessdaojobs.Json.AccountContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Baseadapter.MyTaskAdapter;
import com.fangfas.alincebusinessdaojobs.Tool.Comma;
import com.fangfas.alincebusinessdaojobs.Tool.ListViewHeight;
import com.fangfas.alincebusinessdaojobs.Tool.PhoneHorizontalLine;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.CircleImageView;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestAccount;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;



/**
 * Created by Administrator on 2016/11/9 0009.
 * 我的设置--我的任务--账户
 */

public class FragmentAccount extends Fragment {
    private MyTaskAdapter  adapter;
    private  View view;
    private ListView  listviews;
    //头像
    private CircleImageView header_mage;
    private TextView name;
     private TextView  tx_tel;
    //账户余额
    private TextView  tx_account;
   //招商数量
   private TextView count;
    //招商金额
    private TextView attract_moneys;
    //提成金额
    private TextView  tx_ticheng;
    private LinearLayout ll_account;
    private StringRequest stringRequest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view=inflater.inflate(R.layout.fragment_account,null);
        listviews= (ListView) view.findViewById(R.id.listviews);
        header_mage= (CircleImageView) view.findViewById(R.id.header_mage);
        name= (TextView) view.findViewById(R.id.name);
        tx_tel= (TextView) view.findViewById(R.id.tx_tel);
        tx_account= (TextView) view.findViewById(R.id.tx_account);
        count= (TextView) view.findViewById(R.id.count);
        attract_moneys= (TextView) view.findViewById(R.id.attract_moneys);
        tx_ticheng= (TextView) view.findViewById(R.id.tx_ticheng);
        ll_account= (LinearLayout) view.findViewById(R.id.ll_account);




                 new RequestAccount(stringRequest,getActivity(),hander);


                listviews.setFocusable(false);



        return view;
    }

     Handler  hander=new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             if(msg.what==1){
                 try {

                     if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                         new Snackbars(ll_account,JSONObject.parseObject((String) msg.obj).getString("content"));
                         return;
                     }
                 AccountContent bean=JSONObject.parseObject((String)msg.obj, AccountContent.class);
                 Glide.with(getActivity())
                         .load(bean.content.head_photo)
                         .placeholder(R.mipmap.default_image)
                         .error(R.mipmap.default_image)
                         .into(header_mage);

                  name.setText(bean.content.real_name);
                 tx_tel.setText(new PhoneHorizontalLine().PhoneHorizontalLine(bean.content.tel));
                 tx_account.setText(new Comma().Comma(bean.content.account));
                 count.setText(bean.content.zhaoshangshuliang);
                 attract_moneys.setText(new Comma().Comma(bean.content.zhaoshangjine));
                 tx_ticheng.setText(new  Comma().Comma(bean.content.tichengjine));



               adapter=new MyTaskAdapter(getActivity(),bean.content.account_list);
                listviews.setAdapter(adapter);
                 new ListViewHeight(listviews);


                 } catch (JSONException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
             }
         }
     };

    @Override
    public void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(getActivity()).cancelToRequestQueue(stringRequest);
    }
}
