package com.fangfas.alincebusinessdaojobs.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.fangfas.alincebusinessdaojobs.Baseadapter.InvesAdapter;
import com.fangfas.alincebusinessdaojobs.Json.SetbacksContent;
import com.fangfas.alincebusinessdaojobs.Json.SetbackContricContent;
import com.fangfas.alincebusinessdaojobs.Json.SetbackHederContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.BroadCastManager;
import com.fangfas.alincebusinessdaojobs.Tool.ListViewHeight;
import com.fangfas.alincebusinessdaojobs.Tool.PhoneHorizontalLine;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskSetbacks;
import com.jaeger.library.StatusBarUtil;



/**
 * Created by Administrator on 2016/11/21 0021.
 * 任务进度---兼职人员
 */

public class FragmentTaskSetbacks extends Fragment {
    private LinearLayout  ll;
    private ListView  list;
    InvesAdapter adapter;
    //放弃任务
    private Button  btn_abandoned;
    //名称
    private TextView  name;
    //品名
    private TextView brand_name;
    //净含量
   private TextView  tx_jinghanliang;
    //香型
    private TextView  tx_xiangxing;
    //产地
    private TextView  tx_chandi;
    //度数
     private TextView  tx_dushu;
    //厂家
    private TextView  tx_changjia;
     private ImageView mage;
    //编号
    private TextView  tx_code;
    //意向客户公司名称
    private  TextView  tx_cus_company;
    //意向客户地址
    private TextView  tx_cus_address;
    //客户经理
    private TextView  tx_cus_name;
    //首单金额
   private TextView  tx_first_com;
    //招商人数
   private   TextView tx_zhaoshangrenshu;
    //招商区域
    private TextView  tx_area_names;
    //联系方式
    private TextView  tx_tel;
    //首单金额单位
    private TextView   employer_frist;
    //提成金额单位
    private TextView employer_ticheng;
    //提成
    private TextView  ticheng;

    public  String contract_id;
    String gtid;
    private RequestTaskSetbacks model;


   View  view;
    private StringRequest  stringRequest;
    private SVProgressHUD mSVProgressHUD;
    private int count=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_tasksetbacks,null);
        ll= (LinearLayout) view.findViewById(R.id.ll);
        list= (ListView) view.findViewById(R.id.list);
        btn_abandoned= (Button) view.findViewById(R.id.btn_abandoned);
        name= (TextView) view.findViewById(R.id.name);
        ticheng= (TextView) view.findViewById(R.id.ticheng);
        employer_ticheng= (TextView) view.findViewById(R.id.employer_ticheng);
        employer_frist= (TextView) view.findViewById(R.id.employer_frist);
        tx_tel= (TextView) view.findViewById(R.id.tx_tel);
        tx_area_names= (TextView) view.findViewById(R.id.tx_area_names);
        tx_zhaoshangrenshu= (TextView) view.findViewById(R.id.tx_zhaoshangrenshu);
        tx_first_com= (TextView) view.findViewById(R.id.tx_first_com);
        tx_cus_name= (TextView) view.findViewById(R.id.tx_cus_name);
        tx_cus_address= (TextView) view.findViewById(R.id.tx_cus_address);
        tx_cus_company= (TextView) view.findViewById(R.id.tx_cus_company);
        tx_code= (TextView) view.findViewById(R.id.tx_code);
        mage= (ImageView) view.findViewById(R.id.mage);
        tx_changjia= (TextView) view.findViewById(R.id.tx_changjia);
        tx_dushu= (TextView) view.findViewById(R.id.tx_dushu);
        tx_chandi= (TextView) view.findViewById(R.id.tx_chandi);
        tx_xiangxing= (TextView) view.findViewById(R.id.tx_xiangxing);
        tx_jinghanliang= (TextView) view.findViewById(R.id.tx_jinghanliang);
        brand_name= (TextView) view.findViewById(R.id.brand_name);





        int blue= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ContextCompat.getColor(getActivity(),R.color.blue);
        }else{
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(getActivity(), blue);


        mSVProgressHUD = new SVProgressHUD(getActivity());




          list.setFocusable(false);





           ActivtyDetails activity= (ActivtyDetails) getActivity();
           gtid=activity.getlingqu;


          if(!TextUtils.isEmpty(gtid)) {
                      Requst();
          }

        btn_abandoned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        //放弃任务
                        model.Abandoned();

            }
        });



        return view;
    }

    private void Requst() {
        model=new RequestTaskSetbacks(stringRequest,getActivity(),handler,gtid);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1) {
                mSVProgressHUD.dismiss();
                try{
                if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")) {
                    new Snackbars(ll, JSONObject.parseObject((String) msg.obj).getString("content"));
                    return;
                }
                SetbackHederContent bean1 = JSONObject.parseObject((String) msg.obj, SetbackHederContent.class);


                Glide.with(getActivity())
                        .load(bean1.content.original_img)
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .into(mage);
                contract_id = bean1.content.contract_id;

                //发送广播
                Intent intent = new Intent();
                intent.putExtra("contract_id", contract_id);
                intent.setAction("contract");
                BroadCastManager.getInstance().sendBroadCast(getActivity(), intent);

                name.setText(bean1.content.goods_name);
                brand_name.setText(bean1.content.brand_name);
                tx_jinghanliang.setText(bean1.content.jinghanliang);
                tx_xiangxing.setText(bean1.content.xiangxing);
                tx_chandi.setText(bean1.content.chandi);
                tx_dushu.setText(bean1.content.dushu);
                tx_changjia.setText(bean1.content.changjia);
                tx_code.setText(getActivity().getResources().getString(R.string.str_code) + bean1.content.child_task_sn);
                tx_first_com.setText(bean1.content.first_com);
                ticheng.setText(bean1.content.ticheng);
                employer_frist.setText(bean1.content.first_com_danwei);
                tx_zhaoshangrenshu.setText(bean1.content.zhaoshangrenshu);
                employer_ticheng.setText(bean1.content.ticheng_danwei);
                tx_area_names.setText(bean1.content.area_names);

            }   catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


            }
           else if(msg.what==2){
                try{
                if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                    new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                    return;
                }
                SetbackContricContent data=JSONObject.parseObject((String) msg.obj,SetbackContricContent.class);
                if(!data.content.cus_company.isEmpty()) {
                    tx_cus_company.setText(data.content.cus_company);
                }
                tx_cus_address.setText(data.content.cus_address);
                tx_cus_name.setText(data.content.cus_name);
                tx_tel.setText(new PhoneHorizontalLine().PhoneHorizontalLine(data.content.cus_tel));
            }   catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } }
            else if(msg.what==3){
                try{
                if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                    new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                    return;
                }

                SetbacksContent bean=JSONObject.parseObject((String) msg.obj,SetbacksContent.class);
                adapter = new InvesAdapter(getActivity(),bean.content,gtid);
                list.setAdapter(adapter);
                new ListViewHeight(list);

            }   catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            }else if(msg.what==4){
                try{
                if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                    new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                    return;
                }
                new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));

              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      getActivity().finish();
                  }
              },1000);

            }   catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }




        }};

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            ActivtyDetails activity= (ActivtyDetails) getActivity();
            gtid=activity.getlingqu;
            if(!TextUtils.isEmpty(gtid)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Requst();
                    }
                }).start();

            }
           if(count==0) {
               mSVProgressHUD.showWithStatus(getString(R.string.loading));
           }
            count++;

        }
    }




    @Override
    public void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(getActivity()).cancelToRequestQueue(stringRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSVProgressHUD.dismissImmediately();

    }
}
