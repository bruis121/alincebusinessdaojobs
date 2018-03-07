package com.fangfas.alincebusinessdaojobs.Ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.View.jobpopwind;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestHangye;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestJobEXperience;
import com.jaeger.library.StatusBarUtil;



/**
 * Created by Administrator on 2016/12/6 0006.
 * 工作经历
 */

public class ActivityJobExperience extends BaseActicity implements View.OnClickListener{



    private ImageView  returns;
    private TextView  title;
    private LinearLayout ll;

    private TextView  hangye;
    //开始时间
    private TextView  time_start;
    //结束时间
    private TextView  time_end;
    //描述
    private EditText bewrite;

    //公司
    private EditText et_firm;


    private CardView  cv_commit;
    private int  red;
    private HangyeContent bean;
   //修改经历的id
   private  String  id;
    private RequestJobEXperience models;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_jobexperience);
        returns= (ImageView) findViewById(R.id.returns);
        title= (TextView) findViewById(R.id.title);
        ll= (LinearLayout) findViewById(R.id.ll);
        hangye= (TextView) findViewById(R.id.hangye);
        time_start= (TextView) findViewById(R.id.time_start);
        time_end= (TextView) findViewById(R.id.time_end);
        bewrite= (EditText) findViewById(R.id.bewrite);
        et_firm= (EditText) findViewById(R.id.et_firm);
        cv_commit= (CardView) findViewById(R.id.cv_commit);

        int blue= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            blue = ContextCompat.getColor(ActivityJobExperience.this,R.color.blue);
        }else {
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);


        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivityJobExperience.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        title.startAnimation(scaleAnimation);



                RequestHangye mdoel= new RequestHangye(stringRequest,ActivityJobExperience.this,hander);
                models = new RequestJobEXperience(stringRequest,ActivityJobExperience.this,hander);



        Intent  i=this.getIntent();
        if(i!=null){
            id=i.getStringExtra("id");
            et_firm.setText(i.getStringExtra("company"));
            time_start.setText(i.getStringExtra("yearbegin"));
            time_end.setText(i.getStringExtra("yearend"));
            bewrite.setText(i.getStringExtra("summary"));
            hangye.setText(i.getStringExtra("hangye"));
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            red=ContextCompat.getColor(ActivityJobExperience.this,R.color.red);
        }else {
            red=getResources().getColor(R.color.red);
        }
        returns.setOnClickListener(this);
        time_start.setOnClickListener(this);
        time_end.setOnClickListener(this);
        hangye.setOnClickListener(this);
        cv_commit.setOnClickListener(this);
    }




    private Handler  hander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==8){
                try {
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new  Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }

             bean=JSONObject.parseObject((String) msg.obj,HangyeContent.class);
                } catch (com.alibaba.fastjson.JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(msg.what==2){
                Intent  intent=new Intent();
                intent.putExtra("jobdata",(String) msg.obj);
                setResult(3, intent);
                finish();
            }
        }
    };
    @Override
    public void onClick(View view) {
        if(R.id.returns==view.getId()){

            finish();
        }else  if (R.id.time_start==view.getId()){
            //开始时间
            new DatePickerDialog(ActivityJobExperience.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    time_start.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));

                }
            },2000,1,2).show();
        }else  if(R.id.time_end==view.getId()){
            //结束时间：
            new DatePickerDialog(ActivityJobExperience.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    time_end.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));

                }
            },2000,1,2).show();
        }else  if(R.id.hangye==view.getId()){
            //行业
            hangye.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final jobpopwind pop=new  jobpopwind(ActivityJobExperience.this,bean);
                    pop.showAtLocation(ll,Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                    pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            hangye.setText(pop.data1+"-"+pop.data2);

                        }
                    });
                }
            });
        }else  if(R.id.cv_commit==view.getId()){
            //提交
            if(TextUtils.isEmpty(et_firm.getText().toString())){
                et_firm.setHintTextColor(red);
                new Snackbars(ll,getString(R.string.hint17));
            }else if(hangye.getText().toString().equals("")){
                new Snackbars(ll,getString(R.string.tx_hangye));

            }else if(time_start.getText().toString().equals("")){
                new Snackbars(ll,getString(R.string.tx_start_time));
            }else if(time_end.getText().toString().equals("")){
                new Snackbars(ll,getString(R.string.tx_end_time));
            }else if(TextUtils.isEmpty( bewrite.getText().toString())){
                bewrite.setHintTextColor(red);
                new Snackbars(ll,getString(R.string.tx_title));

            }else {
                if(TextUtils.isEmpty(id)){


                    models.submit( et_firm.getText().toString(), hangye.getText().toString(), time_start.getText().toString(), time_end.getText().toString(), bewrite.getText().toString());

                }
                //修改
                else {

                    models.revisions( id,et_firm.getText().toString(), hangye.getText().toString(), time_start.getText().toString(), time_end.getText().toString(), bewrite.getText().toString());
                }

            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(ActivityJobExperience.this).cancelToRequestQueue(stringRequest);
    }
}
