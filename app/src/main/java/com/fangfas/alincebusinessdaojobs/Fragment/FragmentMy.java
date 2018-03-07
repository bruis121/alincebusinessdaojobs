package com.fangfas.alincebusinessdaojobs.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangfas.alincebusinessdaojobs.Json.MySettingContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.CornersTransform;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityWithdrawCash;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyMainTab;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyMyTask;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestMySetting;
import com.jaeger.library.StatusBarUtil;



/**
 * Created by Administrator on 2016/11/8 0008.
 * 我的
 */

public class FragmentMy extends Fragment implements View.OnClickListener{

    private StringRequest stringRequest;
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
    private  int  blue;
    private   View  view;
    //认证资料
    private RelativeLayout  authentication;
    //我的兼职
   private RelativeLayout  timejobs;
    //我的任务
    private RelativeLayout mytask;
    //返回酒店
    private CardView  retrun_business;
    //客服电话
    private CardView  card_severce;

    private TextView text1;

    private TextView  title;
    //提现
   private RelativeLayout  tx_withdraw_cash;

    //头像
    private ImageView image_header;
    private TextView name;
    //等级
    private TextView tx_grades;
    //认证情况
    private CardView  cv_qualification;
    private TextView tx_qualification;
    //余额
    private TextView tx_purse_balance;
    public SVProgressHUD mSVProgressHUD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_my,null);
        authentication= (RelativeLayout) view.findViewById(R.id.authentication);
        timejobs= (RelativeLayout) view.findViewById(R.id.timejobs);
        mytask= (RelativeLayout) view.findViewById(R.id.mytask);
        retrun_business= (CardView) view.findViewById(R.id.retrun_business);
        card_severce= (CardView) view.findViewById(R.id.card_severce);
        text1= (TextView) view.findViewById(R.id.text1);
        title= (TextView) view.findViewById(R.id.title);
        tx_withdraw_cash= (RelativeLayout) view.findViewById(R.id.tx_withdraw_cash);
        image_header= (ImageView) view.findViewById(R.id.image_header);
        name= (TextView) view.findViewById(R.id.name);
        tx_grades= (TextView) view.findViewById(R.id.tx_grades);
        cv_qualification= (CardView) view.findViewById(R.id.cv_qualification);
        tx_qualification= (TextView) view.findViewById(R.id.tx_qualification);
        tx_purse_balance= (TextView) view.findViewById(R.id.tx_purse_balance);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            blue = ContextCompat.getColor(getActivity(), R.color.blue);
        }else {
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(getActivity(), blue);

        Animation scaleAnimation= AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_top);
        title.startAnimation(scaleAnimation);

        mSVProgressHUD = new SVProgressHUD(getActivity());
        mSVProgressHUD.showWithStatus(getString(R.string.loading));


        new RequestMySetting(getActivity(),stringRequest,handler);

        tx_withdraw_cash.setOnClickListener(this);
        authentication.setOnClickListener(this);
        timejobs.setOnClickListener(this);
        mytask.setOnClickListener(this);
        retrun_business.setOnClickListener(this);
        card_severce.setOnClickListener(this);
        authentication.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View view) {
        if(R.id.mytask==view.getId()){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                Intent it=new Intent(getActivity(),ActivtyMyTask.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(getActivity(),text1, "robot");

                getActivity().startActivity(it, options.toBundle());

            }else{
                Intent it=new Intent(getActivity(),ActivtyMyTask.class);
                getActivity().startActivity(it);

            }


            /**
             * 返回酒商
             */
        }else  if(R.id.retrun_business==view.getId()){
            //                 ActivtyMainTab.getInstance().mTabHost.setCurrentTab(0);



            /**
             *  拨打客户
             */
        }else  if(R.id.card_severce==view.getId()){
            String phone="4000028999";
            if (Build.VERSION.SDK_INT >= 23) {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
                if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
                    return;
                } else {
                    //上面已经写好的拨号方法
                    callDirectly(phone);
                }
            } else {
                //上面已经写好的拨号方法
                callDirectly(phone);
            }
        }else  if(R.id.authentication==view.getId()){
            Intent  i=new Intent(getActivity(), ActivityAuthentication.class);
            startActivity(i);

        }else  if(R.id.tx_withdraw_cash==view.getId()){
            if(!"".equals(tx_purse_balance.getText().toString())) {
                Intent cash = new Intent(getActivity(), ActivityWithdrawCash.class);
                cash.putExtra("balance", tx_purse_balance.getText().toString());
                startActivity(cash);
            }

        }else if(R.id.timejobs==view.getId()){

        }

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                mSVProgressHUD.dismiss();
                try{
              if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                  cv_qualification.setVisibility(View.INVISIBLE);
                  return;
              }
                cv_qualification.setVisibility(View.VISIBLE);
                MySettingContent bean=JSONObject.parseObject((String) msg.obj, MySettingContent.class);

                Glide.with(getActivity()).load(bean.content.headimg).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new CornersTransform(getActivity()))
                        .crossFade()
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .into(image_header);
                name.setText(bean.content.username);
                tx_grades.setText(bean.content.grouptitle);

                if(bean.content.status.equals("2")){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cv_qualification.setCardBackgroundColor(ContextCompat.getColor(getActivity(),R.color.yellow1));
                    }else{
                        cv_qualification.setCardBackgroundColor(getResources().getColor(R.color.yellow1));
                    }
                }else if(bean.content.status.equals("1")){
                    getColor();
                    tx_qualification.setText(getResources().getString(R.string.wait));
                }else if(bean.content.status.equals("2")){
                    getColor();
                    tx_qualification.setText(getResources().getString(R.string.no_qualification));
                }

                tx_purse_balance.setText(bean.content.funds);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }}};



    private void callDirectly(String mobile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + mobile));
        this.startActivity(intent);
    }

    public void getColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cv_qualification.setCardBackgroundColor(ContextCompat.getColor(getActivity(),R.color.hui));
        }else{
            cv_qualification.setCardBackgroundColor(getResources().getColor(R.color.hui));
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
