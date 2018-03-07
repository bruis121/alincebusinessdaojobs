package com.fangfas.alincebusinessdaojobs.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Baseadapter.HomePopwindowAdapter;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/8 0008.
 *
 */

public class HomePopwindow extends PopupWindow {
    private Activity context;
    private View view;
    HomePopwindowAdapter adapter;
    private ListView  list;
    public   int  postion;

    public HomePopwindow(FragmentActivity activity, TextView text, ArrayList<String> data) {
        this.context=activity;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.homepopwindow,null);
        list= (ListView) view.findViewById(R.id.list);


        adapter=new HomePopwindowAdapter(context,data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                postion=i;
                dismiss();
            }
        });




        /**
         * 设置弹出窗口的内容
         */
        this.setContentView(view);
        /**
         * 设置弹窗的宽度
         */
        this.setWidth(text.getWidth());
        /**
         * 设置弹窗的高度
         */
         this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        /**
         * 设置主Activity背景颜色
         */
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
    }


}
