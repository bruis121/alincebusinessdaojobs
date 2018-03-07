package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestBinding;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;


/**
 * Created by Administrator on 2016/11/28 0028.
 * 提现申请
 */

public class ActivityTakenCash  extends BaseActicity implements View.OnClickListener{
    Animation scaleAnimation;
    private TextView  tx_title;
    private ImageView returns;
    //支付宝
    private RelativeLayout  rl_alipay;
    private RelativeLayout  rl_weichant;
    private RelativeLayout rl_bank;
    private LinearLayout  down_more;
    private LinearLayout rootview;
    private TextView aipay_isbinding;
    private TextView  weixin_isbinding;
    private TextView bank_isbinding;


    private ImageView  ailipay_enter;
    private CheckBox  ailipay_check;

    private ImageView  weixin_enter;
    private CheckBox weixin_check;


    private ImageView bank_enter;
    private CheckBox bank_check;

    //取现界面
    private LinearLayout  ll_withdraw_cash;
    //取现金额
    private EditText et_withdraw_cash_sum;
    private CardView btn_summit;


    private  String  way;

    //判断是否有绑定账号
    private  boolean isbinding=false;
    private   String  withdraw_type="";//1代表支付宝  2代表微信  3代表银行卡

    private StringRequest  stringRequest;



    //提示弹窗
    //提示框
    BounceTopEnter mBasIn;
    SlideBottomExit mBasOut;
    NormalDialog dialogs;
    RequestBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takencash);
        tx_title= (TextView) findViewById(R.id.tx_title);
        returns= (ImageView) findViewById(R.id.returns);
        rl_alipay= (RelativeLayout) findViewById(R.id.rl_alipay);
        rl_weichant= (RelativeLayout) findViewById(R.id.rl_weichant);
        rl_bank= (RelativeLayout) findViewById(R.id.rl_bank);
        down_more= (LinearLayout) findViewById(R.id.down_more);
        rootview= (LinearLayout) findViewById(R.id.rootview);
        aipay_isbinding= (TextView) findViewById(R.id.aipay_isbinding);
        weixin_isbinding= (TextView) findViewById(R.id.weixin_isbinding);
        bank_isbinding= (TextView) findViewById(R.id.bank_isbinding);
        ailipay_enter= (ImageView) findViewById(R.id.ailipay_enter);
        ailipay_check= (CheckBox) findViewById(R.id.ailipay_check);
        weixin_enter= (ImageView) findViewById(R.id.weixin_enter);
        weixin_check= (CheckBox) findViewById(R.id.weixin_check);
        bank_enter= (ImageView) findViewById(R.id.bank_enter);
        bank_check= (CheckBox) findViewById(R.id.bank_check);
        ll_withdraw_cash= (LinearLayout) findViewById(R.id.ll_withdraw_cash);
        et_withdraw_cash_sum= (EditText) findViewById(R.id.et_withdraw_cash_sum);
        et_withdraw_cash_sum= (EditText) findViewById(R.id.et_withdraw_cash_sum);
        btn_summit= (CardView) findViewById(R.id.btn_summit);

        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        binding= new RequestBinding();
        binding.UseMoney(stringRequest,hander,ActivityTakenCash.this);

        returns.startAnimation(animation());
        tx_title.startAnimation(animation());


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        dialogs=new NormalDialog(ActivityTakenCash.this);

        down_more.setOnClickListener(this);
        returns.setOnClickListener(this);
        btn_summit.setOnClickListener(this);
        weixin_check.setOnClickListener(symptomTextchecklistener);
        ailipay_check.setOnClickListener(symptomTextchecklistener);
        bank_check.setOnClickListener(symptomTextchecklistener);
    }

    @Override
    public void onClick(View view) {
        if(R.id.down_more==view.getId()){
            Animation   downAnimation= AnimationUtils.loadAnimation(ActivityTakenCash.this, R.anim.fade_down);
            rl_weichant.setVisibility(View.VISIBLE);
            rl_weichant.startAnimation(downAnimation);
            rl_bank.startAnimation(downAnimation);
            rl_bank.setVisibility(View.VISIBLE);
            down_more.setVisibility(View.GONE);

        }else  if(R.id.returns==view.getId()){
            finish();
        }else  if(R.id.btn_summit==view.getId()){
            if("".equals(withdraw_type)){
                new Snackbars(rootview,getString(R.string.withdraw_cash_way));
            }else if("".equals(et_withdraw_cash_sum.getText().toString())){
                new Snackbars(rootview,getString(R.string.withdraw_cash_money));
            }else {
                binding.WithdrawCash(stringRequest, withdraw_type,et_withdraw_cash_sum.getText().toString(), hander, ActivityTakenCash.this);
            }
        }

    }

    private void TakenCash(String s) {

            Intent i = new Intent(ActivityTakenCash.this, ActivityTakenCashWay.class);
            i.putExtra("way", way);
            startActivity(i);

    }


    private   Animation  animation(){
      return   scaleAnimation= AnimationUtils.loadAnimation(ActivityTakenCash.this, R.anim.fade_in_top);
    }


   private  void  requst(){
       binding.IsBinding1(stringRequest,"1",hander,ActivityTakenCash.this);
       binding.IsBinding2(stringRequest,"2",hander,ActivityTakenCash.this);
       binding.IsBinding3(stringRequest,"3",hander,ActivityTakenCash.this);

   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SingleVolleyRequestQueue.getInstance(ActivityTakenCash.this).cancelToRequestQueue(stringRequest);
    }

    private Handler hander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==2){
                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                       if("".equals(JSONObject.parseObject((String) msg.obj).getString("content"))){
                           rl_alipay.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   TakenCash(way=getResources().getString(R.string.str_bingdingalipay));
                               }
                           });
                       }else{
                           aipay_isbinding.setText(JSONObject.parseObject((String) msg.obj).getString("content"));
                           ailipay_check.setVisibility(View.VISIBLE);
                           ailipay_enter.setVisibility(View.GONE);
                           isbinding=true;

                       }


                    }else{
                      new  Snackbars(rootview,JSONObject.parseObject((String) msg.obj).getString("content"));

                    }
                }catch(JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if(msg.what==3){
                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                        if("".equals(JSONObject.parseObject((String) msg.obj).getString("content"))){
                            rl_weichant.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TakenCash(way=getResources().getString(R.string.str_bingdingweichant));
                                }
                            });
                        }else{
                            weixin_isbinding.setText(JSONObject.parseObject((String) msg.obj).getString("content"));
                            weixin_enter.setVisibility(View.GONE);
                            weixin_check.setVisibility(View.VISIBLE);
                            isbinding=true;
                        }


                    }else{

                        new  Snackbars(rootview,JSONObject.parseObject((String) msg.obj).getString("content"));

                    }
                }catch(JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else  if(msg.what==4){
                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                        if("".equals(JSONObject.parseObject((String) msg.obj).getString("content"))){
                            rl_bank.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TakenCash(way=getResources().getString(R.string.str_bingdingbank));
                                }
                            });
                        }else{
                            bank_isbinding.setText(JSONObject.parseObject((String) msg.obj).getString("content"));
                            bank_check.setVisibility(View.VISIBLE);
                            bank_enter.setVisibility(View.GONE);
                            isbinding=true;
                        }

                    }else{
                        new  Snackbars(rootview,JSONObject.parseObject((String) msg.obj).getString("content"));
                    }
                }catch(JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(msg.what==5){
                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                         Dialogs(JSONObject.parseObject((String) msg.obj).getString("content"));
                    }else{

                        Dialogs(JSONObject.parseObject((String) msg.obj).getString("content"));

                    }
                }catch(JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(msg.what==6){
                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("ok")) {
                        et_withdraw_cash_sum.setHint("当前可提现金额"+JSONObject.parseObject((String) msg.obj).getString("content")+"元");
                    }else{

                        new  Snackbars(rootview,JSONObject.parseObject((String) msg.obj).getString("content"));

                    }
                }catch(JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(isbinding){
                ll_withdraw_cash.setVisibility(View.VISIBLE);
            }else {
                ll_withdraw_cash.setVisibility(View.GONE);
            }
        }
    };

    private void Dialogs(String content) {
        dialogs.content(content)
                .btnNum(1)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText(getResources().getString(R.string.know))
                .dismissAnim(mBasOut)
                .show();
        dialogs.setOnBtnClickL(

                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                    }
                });

        et_withdraw_cash_sum.setText("");
        binding.UseMoney(stringRequest,hander,ActivityTakenCash.this);



    }


    private View.OnClickListener symptomTextchecklistener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(R.id.ailipay_check==view.getId()){
                if(ailipay_check.isChecked()){
                    withdraw_type="1";
                    bank_check.setChecked(false);
                    weixin_check.setChecked(false);
                }else {
                    withdraw_type="";
                }
            }else  if(R.id.weixin_check==view.getId()){
                if(weixin_check.isChecked()){
                    withdraw_type="2";
                    bank_check.setChecked(false);
                    ailipay_check.setChecked(false);
                }else{
                    withdraw_type="";
                }
            }else  if(R.id.bank_check==view.getId()){

                if( bank_check.isChecked()){
                    withdraw_type="3";
                    weixin_check.setChecked(false);
                    ailipay_check.setChecked(false);
                }else{
                    withdraw_type="";
                }

            }

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        requst();
    }


}
