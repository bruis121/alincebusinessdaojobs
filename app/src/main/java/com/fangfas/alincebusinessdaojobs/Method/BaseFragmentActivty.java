package com.fangfas.alincebusinessdaojobs.Method;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.fangfas.alincebusinessdaojobs.MyApplication;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class BaseFragmentActivty extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 添加activity实例
                */
        MyApplication.getInstance().addActivity(this);
    }
}
