package com.fangfas.alincebusinessdaojobs.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;
import com.fangfas.alincebusinessdaojobs.View.HintDialog;
import com.fangfas.alincebusinessdaojobs.View.PFragment;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;



/**
 * Created by Administrator on 2016/12/14 0014.
 * 广告招商介绍提成规则
 */

public class FragmentAdRule extends PFragment {
    private boolean isPrepared;



    private Context mContext;

    private  boolean  show;

    private LinearLayout  ll;

    //提示框
    BounceTopEnter   mBasIn;
    SlideBottomExit  mBasOut;
    NormalDialog dialogs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View  view=inflater.inflate(R.layout.fragment_adrule,null);
        ll= (LinearLayout) view.findViewById(R.id.ll);
        this.mContext=getActivity();



        if(!MyApplication.getInstance().GetAuth.equalsIgnoreCase("pass")){
            show=true;
        }else {
            show=false;
        }
        isPrepared =true;
        bruisLoad();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        dialogs=new NormalDialog(getActivity());
        return  view;

    }

    @Override
    protected void bruisLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }

        if(show) {
            //没有认证
            if(MyApplication.getInstance().GetAuth.equalsIgnoreCase("null")){
            Au();
                //认证失败
            }else if(MyApplication.getInstance().GetAuth.equalsIgnoreCase("erro")){
                erro();
            }else if(MyApplication.getInstance().GetAuth.equalsIgnoreCase("wait")){
                Wait();
            }
        }else {
            ll.setVisibility(View.VISIBLE);
        }

    }

    //跳转进入认证界面
    private void Au() {
        dialogs.content(getResources().getString(R.string.hint24))
                .btnNum(2)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"),Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText("取消","确定")
                .dismissAnim(mBasOut)
                .show();
        dialogs.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                        Intent i=new Intent(getActivity(),ActivityAuthentication.class);
                        startActivity(i);
                    }
                });


    }
    //失败
    private void erro() {
        dialogs.content(getResources().getString(R.string.hint30))
                .btnNum(2)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"),Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText("取消","前去修改")
                .dismissAnim(mBasOut)
                .show();
        dialogs.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                    }
                },
        new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                        Intent i=new Intent(getActivity(),ActivityAuthentication.class);
                        startActivity(i);
                    }
                });
    }
    //等待审核
    private   void  Wait(){

        dialogs.content(getResources().getString(R.string.hint40))
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
    }

}
