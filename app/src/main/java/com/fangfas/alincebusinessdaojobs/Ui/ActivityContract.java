package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Json.ContractContent;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestContract;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.jaeger.library.StatusBarUtil;


import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/21 0021.
 * 合同凭证
 */

public class ActivityContract extends BaseActicity implements View.OnClickListener{
    //签署时间
    private TextView tx_signature_time;
     //合同照片
    private TextView  tx_uploadcontract;
    //查看凭证
    private TextView  tx_ploadvoucher;
    private ImageView  returns;
    private TextView  title;
    //公司名称
    private EditText  et_company;
    //客户名称
    private EditText  et_name;
    //联系方式
    private EditText  et_tel;
    //客户地址
    private EditText  et_address;
    //银行账号
    private EditText  et_bank_account;
    //开户人姓名
    private EditText  et_account_holder;
    //开户行
    private EditText  et_bank_name;
    //打款时间
    private TextView  tx_remits_time;
    //合同金额
    private EditText  et_con_money;
    //收货人
    private EditText  et_consignee;
    //收货人联系方式
    private EditText  et_consignee_tel;
    //收货人地址
    private EditText  et_con_address;
    private LinearLayout ll_contract;


    ContractContent bean;
     private StringRequest  stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        tx_signature_time= (TextView) findViewById(R.id.tx_signature_time);
        tx_uploadcontract= (TextView) findViewById(R.id.tx_uploadcontract);
        tx_ploadvoucher= (TextView) findViewById(R.id.tx_ploadvoucher);
        returns= (ImageView) findViewById(R.id.returns);
        title= (TextView) findViewById(R.id.title);
        et_company= (EditText) findViewById(R.id.et_company);
        et_name= (EditText) findViewById(R.id.et_name);
        et_tel= (EditText) findViewById(R.id.et_tel);
        et_address= (EditText) findViewById(R.id.et_address);
        et_bank_account= (EditText) findViewById(R.id.et_bank_account);
        et_account_holder= (EditText) findViewById(R.id.et_account_holder);
        et_bank_name= (EditText) findViewById(R.id.et_bank_name);
        tx_remits_time= (TextView) findViewById(R.id.tx_remits_time);
        et_con_money= (EditText) findViewById(R.id.et_con_money);
        et_consignee= (EditText) findViewById(R.id.et_consignee);
        et_consignee_tel= (EditText) findViewById(R.id.et_consignee_tel);
        et_con_address= (EditText) findViewById(R.id.et_con_address);
        ll_contract= (LinearLayout) findViewById(R.id.ll_contract);


        int  blue= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            blue = ContextCompat.getColor(ActivityContract.this,R.color.blue);
        }else{
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);

        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivityContract.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        title.startAnimation(scaleAnimation);
        Intent i=getIntent();
        final String  contract_id=i.getStringExtra("contract_id");



        if(!"".equals(contract_id)) {
           new RequestContract(stringRequest,ActivityContract.this, handler, contract_id);
        }





        tx_uploadcontract.setOnClickListener(this);
        tx_ploadvoucher.setOnClickListener(this);

    }



    Handler  handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){

                try {
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll_contract,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }

                    String data = (String) msg.obj;
                    bean = JSONObject.parseObject(data, ContractContent.class);
                    et_company.setText(bean.content.cus_company);
                    et_name.setText(bean.content.cus_name);
                    et_tel.setText(bean.content.cus_tel);
                    et_address.setText(bean.content.cus_address);
                    et_bank_account.setText(bean.content.bank_account);
                    et_account_holder.setText(bean.content.account_holder);
                    et_bank_name.setText(bean.content.bank_name);
                    tx_remits_time.setText(bean.content.remittance_time);
                    et_con_money.setText(bean.content.con_money);
                    tx_signature_time.setText(bean.content.sign_time);
                    et_consignee.setText(bean.content.consignee);
                    et_consignee_tel.setText(bean.content.consignee_tel);
                    et_con_address.setText(bean.content.con_address);
                } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            }
        }
    };
    @Override
    public void onClick(View view) {
        //合同照片
        if(R.id.tx_uploadcontract==view.getId()){
            Photolist(bean.content.conPhoto_url);


            //查看凭证
        }else  if( R.id.tx_ploadvoucher==view.getId()){
            Photolist(bean.content.remPz_url);
        }



    }

    private void Photolist(ArrayList<String> conPhoto_url) {
        Intent  i=new Intent(ActivityContract.this,ActivityPhotoList.class);
        i.putStringArrayListExtra("photo", conPhoto_url);
        startActivity(i);
    }


    //退出
   public void returns(View view){
       Intent  intent=new Intent();
       setResult(RESULT_OK, intent);
       finish();

   }


    @Override
    protected void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(ActivityContract.this).cancelToRequestQueue(stringRequest);
    }
}
