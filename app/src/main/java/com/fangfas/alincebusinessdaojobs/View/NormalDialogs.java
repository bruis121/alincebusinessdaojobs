package com.fangfas.alincebusinessdaojobs.View;

import android.app.Activity;
import android.graphics.Color;

import com.fangfas.alincebusinessdaojobs.R;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

/**
 * Created by Administrator on 2017/2/17 0017.
 *   //提示框
 */

public class NormalDialogs {
    BounceTopEnter mBasIn;
    SlideBottomExit mBasOut;
    NormalDialog dialogs;

    public  void   Dialogs(Activity  activity,String  title){
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        dialogs=new NormalDialog(activity);
        dialogs.content(title)
                .btnNum(1)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText(activity.getResources().getString(R.string.know))
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
