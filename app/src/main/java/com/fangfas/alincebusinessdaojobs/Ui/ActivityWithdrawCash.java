package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;



/**
 * Created by Administrator on 2016/11/28 0028.
 * 钱包
 */

public class ActivityWithdrawCash  extends BaseActicity implements View.OnClickListener{

    private ImageView  returns;
    //提现
    private CardView  cv_cash;
    private TextView tx_title;
    //余额
    private TextView tx_balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawcash);
        returns= (ImageView) findViewById(R.id.returns);
        cv_cash= (CardView) findViewById(R.id.cv_cash);
        tx_title= (TextView) findViewById(R.id.tx_title);
        tx_balance= (TextView) findViewById(R.id.tx_balance);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     }

        Intent  intent=getIntent();
        String balance=intent.getStringExtra("balance");
        tx_balance.setText(balance);

        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivityWithdrawCash.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        tx_title.startAnimation(scaleAnimation);


        returns.setOnClickListener(this);
        cv_cash.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if(R.id.returns==view.getId()){
            finish();
        }else  if(R.id.cv_cash==view.getId()){
            Intent  i=new Intent(ActivityWithdrawCash.this,ActivityTakenCash.class);
            startActivity(i);
        }

    }
}
