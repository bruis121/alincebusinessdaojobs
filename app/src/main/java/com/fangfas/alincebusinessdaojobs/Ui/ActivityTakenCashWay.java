package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.pickerview.OptionsPickerView;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestBinding;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/28 0028.
 * 支付方式--支付宝、微信、银行卡
 */

public class ActivityTakenCashWay  extends BaseActicity implements View.OnClickListener{
    private LinearLayout ll;
    private TextView  tx_title;
    private ImageView  returns;
    private LinearLayout  ll_1;
    private LinearLayout  ll3;
    private TextView tx2_title;
    private EditText tx1_content;

    private TextView tx3_title;

    private EditText  et3_content;

    //选择
    private LinearLayout  ll_choose;
    private TextView  tx_choose;


    private TextView  line1;
    private TextView  line2;
    //提交
    private Button summit;
    //银行列表
    ArrayList<String> bankdata=new ArrayList<>();

    private StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takencashway);
        ll= (LinearLayout) findViewById(R.id.ll);
        tx_title= (TextView) findViewById(R.id.tx_title);
        returns= (ImageView) findViewById(R.id.returns);
        ll_1= (LinearLayout) findViewById(R.id.ll_1);
        ll3= (LinearLayout) findViewById(R.id.ll3);
        tx2_title= (TextView) findViewById(R.id.tx2_title);
        tx1_content= (EditText) findViewById(R.id.tx1_content);
        summit= (Button) findViewById(R.id.summit);
        line2= (TextView) findViewById(R.id.line2);
        line1= (TextView) findViewById(R.id.line1);
        tx_choose= (TextView) findViewById(R.id.tx_choose);
        ll_choose= (LinearLayout) findViewById(R.id.ll_choose);
        et3_content= (EditText) findViewById(R.id.et3_content);
        tx3_title= (TextView) findViewById(R.id.tx3_title);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivityTakenCashWay.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        tx_title.startAnimation(scaleAnimation);

        new RequestBinding().BankList(stringRequest,hander,ActivityTakenCashWay.this);


        Intent intent=getIntent();
        if(intent!=null){
            String  way=intent.getStringExtra("way");
            tx_title.setText(way);
           if(way.equals("绑定支付宝")){
               et3_content.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
               line1.setVisibility(View.GONE);
               ll_choose.setVisibility(View.GONE);
               summit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                       if("".equals(tx1_content.getText().toString())){
                           ShowSnackbar(getString(R.string.alipay_relname));

                       }else if("".equals(et3_content.getText().toString())){
                           ShowSnackbar(getString(R.string.alipay));
                       }else {
                           new RequestBinding().BINDING(stringRequest, ActivityTakenCashWay.this, hander, tx1_content.getText().toString(), et3_content.getText().toString(), "1","");
                       }
                       }
               });
           }else{
               ll_choose.setVisibility(View.VISIBLE);
               if(way.equals("绑定微信钱包")){
                   tx3_title.setText("微信账号:");
                   et3_content.setHint("请正确输入微信账号");
                   et3_content.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                   tx1_content.setHint("请输入真实姓名");
                   ll_choose.setVisibility(View.GONE);
                   line1.setVisibility(View.VISIBLE);
                   line2.setVisibility(View.GONE);
                   ll_1.setVisibility(View.VISIBLE);
                   ll3.setVisibility(View.VISIBLE);
                   summit.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                           imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                           if("".equals(et3_content.getText().toString())){
                               ShowSnackbar(getString(R.string.weixin));

                           }else if("".equals(tx1_content.getText().toString())){
                               ShowSnackbar(getString(R.string.weixinname));
                           }else {
                               new RequestBinding().BINDING(stringRequest, ActivityTakenCashWay.this, hander,tx1_content.getText().toString(), et3_content.getText().toString(), "2","");
                           }
                       }
                   });

               }else{
                   ll_1.setVisibility(View.VISIBLE);
                   ll3.setVisibility(View.VISIBLE);
                   tx1_content.setHint("务必与绑定银行卡姓名一致");
                   tx2_title.setText("开户银行");
                   tx_choose.setHint("选择开户行");
                   tx3_title.setText("银行卡号");
                   et3_content.setHint("必填");
                   et3_content.setInputType(InputType.TYPE_CLASS_NUMBER);
                   ll_choose.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                           imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

                           //选项选择器
                           OptionsPickerView pvOptions = new OptionsPickerView(ActivityTakenCashWay.this);


                           //三级联动效果
                           pvOptions.setPicker(bankdata);
                           pvOptions.setTitle("选择开户行");

                           pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                               @Override
                               public void onOptionsSelect(int options1, int option2, int options3) {
                                   //返回的分别是三个级别的选中位置
                                   String tx = bankdata.get(options1);
                                   tx_choose.setText(tx);

                               }
                           });
                           //设置是否循环滚动
                           pvOptions.setCyclic(false);
                           pvOptions.show();

                       }
                   });
                   summit.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                           imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                           if("".equals(tx1_content.getText().toString())){
                               ShowSnackbar(getString(R.string.pay_relname));

                           }else if("".equals(tx_choose.getText().toString())){
                               ShowSnackbar(getString(R.string.bank_name));
                           }else if("".equals(et3_content.getText().toString())){
                               ShowSnackbar(getString(R.string.bank_count));
                           }else {
                               new RequestBinding().BINDING(stringRequest, ActivityTakenCashWay.this, hander,tx1_content.getText().toString(), et3_content.getText().toString(), "3",tx_choose.getText().toString());
                           }
                       }
                   });

               }
           }
        }



        returns.setOnClickListener(this);
    }


    private void ShowSnackbar(String string) {
        new Snackbars(ll,string);
    }


    @Override
    public void onClick(View view) {
        if(R.id.returns==view.getId()){
            finish();
        }

    }

        private Handler hander=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    try {
                        if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                            ShowSnackbar(JSONObject.parseObject((String) msg.obj).getString("content"));
                            hander.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            },1000);

                        }else{
                            ShowSnackbar(JSONObject.parseObject((String) msg.obj).getString("content"));
                        }
                    }catch(JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else  if(msg.what==7){
                   try {
                       if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                           bankdata= (ArrayList<String>) JSONObject.parseArray(JSONObject.parseObject((String) msg.obj).getString("content"),String.class);
                       }else {
                           ShowSnackbar(JSONObject.parseObject((String) msg.obj).getString("content"));
                       }

                       }catch (JSONException e){
                       e.printStackTrace();
                   }
                }
            }
        };

    @Override
    protected void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(ActivityTakenCashWay.this).cancelToRequestQueue(stringRequest);
    }
}
