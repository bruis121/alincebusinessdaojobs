package com.fangfas.alincebusinessdaojobs.Tool;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/11/1 0001.
 * popowind弹出，状态栏隐藏显示
 */

public class full {


    public void  funls(boolean enable, Activity context){
        if (enable) {//隐藏状态栏
            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            context.getWindow().setAttributes(lp);
        } else {//显示状态栏
            WindowManager.LayoutParams attr = context.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            context.getWindow().setAttributes(attr);
        }
    }
}
