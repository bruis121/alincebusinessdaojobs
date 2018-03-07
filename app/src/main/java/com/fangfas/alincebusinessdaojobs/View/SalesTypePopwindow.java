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

import com.fangfas.alincebusinessdaojobs.Baseadapter.SalesTypeAdapter;
import com.fangfas.alincebusinessdaojobs.Json.SalesTeypeContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/8 0008.
 *
 */

public class SalesTypePopwindow extends PopupWindow {
    private Activity context;
    private View view;
    SalesTypeAdapter adapter;
    private ListView  list;
    public   String  flag;

    public SalesTypePopwindow(FragmentActivity activity, TextView textS, final ArrayList<SalesTeypeContent.Content> data) {
        this.context=activity;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.homepopwindow,null);
        list= (ListView) view.findViewById(R.id.list);


        adapter=new SalesTypeAdapter(context,data);
        list.setAdapter(adapter);
        list.setSelector(R.color.blue);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int  write=context.getResources().getColor(R.color.red);
//                TextView  tx= (TextView)view.findViewById(R.id.tx);
//                tx.setTextColor(write);
                flag=data.get(i).flag;
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
        this.setWidth(textS.getWidth());
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
