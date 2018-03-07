package com.fangfas.alincebusinessdaojobs.Method;

import android.app.Activity;
import android.os.Bundle;

import com.fangfas.alincebusinessdaojobs.MyApplication;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class BaseActicity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 添加activity实例
                */
        MyApplication.getInstance().addActivity(this);
    }
}
