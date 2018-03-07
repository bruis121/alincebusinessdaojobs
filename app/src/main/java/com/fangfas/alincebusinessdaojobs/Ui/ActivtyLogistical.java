package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.AppConst;
import com.fangfas.alincebusinessdaojobs.Baseadapter.LoginsticalAdapter;
import com.fangfas.alincebusinessdaojobs.Json.GetDanjuContent;
import com.fangfas.alincebusinessdaojobs.Json.LogisticalContent;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestLogistical;
import com.jaeger.library.StatusBarUtil;

import java.util.Collections;



/**
 * Created by Administrator on 2016/11/23 0023.
 * 物流查看
 */

public class ActivtyLogistical extends BaseActicity{

    private TextView  title;
    private ImageView  returns;
    private ListView list;
    private TextView    lookinvoices;

   //订单号
    TextView tx_orders;
    //电话号码
    TextView tel;
    LinearLayout  header;

    LoginsticalAdapter adapter;
    private  StringRequest  stringRequest;
    private  String gtid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_logistical);
        title= (TextView) findViewById(R.id.title);
        returns= (ImageView) findViewById(R.id.returns);
        list= (ListView) findViewById(R.id.list);
        lookinvoices= (TextView) findViewById(R.id.lookinvoices);

        int blue= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            blue =ContextCompat.getColor(ActivtyLogistical.this,R.color.blue);

        }else {
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);



        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivtyLogistical.this, R.anim.fade_in_top);
        title.startAnimation(scaleAnimation);
        returns.startAnimation(scaleAnimation);

        Intent  i=getIntent();
        gtid=i.getStringExtra("gtid");


           new RequestLogistical(stringRequest,ActivtyLogistical.this,handler,gtid);



        header = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.logistical_header,null);
        tx_orders = (TextView) header.findViewById(R.id.tx_orders);
        tel= (TextView) header.findViewById(R.id.tel);





    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){


               try {


               if(JSONObject.parseObject(JSONObject.parseObject((String) msg.obj).getString("content")).getString("status").equals("1")) {
                   LogisticalContent bean = JSONObject.parseObject((String) msg.obj, LogisticalContent.class);
                   tx_orders.setText(getResources().getString(R.string.str_orders) + bean.content.nu);
                   tel.setText(bean.content.comcontact);
                   //倒叙
                   Collections.reverse(bean.content.data);
                   adapter = new LoginsticalAdapter(ActivtyLogistical.this, bean.content.data);
                   list.setAdapter(adapter);
                   list.addHeaderView(header);
               }else {
                   new Snackbars(title, JSONObject.parseObject(JSONObject.parseObject((String) msg.obj).getString("content")).getString("message"));
               }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }


            }else if(msg.what==2){

                try {
                    final GetDanjuContent bean = JSONObject.parseObject((String) msg.obj, GetDanjuContent.class);
                    lookinvoices.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(ActivtyLogistical.this, ActivityPhoto.class);
                            i.putExtra("photo", AppConst.Main+bean.content.get(0).danju);
                            startActivity(i);
                        }
                    });
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };




    public   void  returns(View view){
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(ActivtyLogistical.this).cancelToRequestQueue(stringRequest);
    }
}
