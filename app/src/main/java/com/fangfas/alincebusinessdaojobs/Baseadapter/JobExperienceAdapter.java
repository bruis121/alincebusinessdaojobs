package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityJobExperience;


import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/17 0017.
 * 工作经历
 */

public class JobExperienceAdapter extends BaseAdapter {
    //数量
    public  ArrayList<String> count;
    Context  context;
    private HangyeContent bean;
    private  LinearLayout ll;



    public JobExperienceAdapter(ActivityJobExperience activityJobExperience, ArrayList<String> count, HangyeContent bean, LinearLayout ll) {
     this.count=count;
     this.context=activityJobExperience;
        this.bean=bean;
        this.ll=ll;


    }

    @Override
    public int getCount() {
        return count == null?0:count.size();
    }

    @Override
    public Object getItem(int i) {
        return count.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
       final Viewhold  hold;
        if(view==null){
            view=LayoutInflater.from(context).inflate(R.layout.experience,null);
            hold=new Viewhold(view);
           view.setTag(hold);
            hold.et_firm.setTag(hold);
            hold.hangye.setTag(hold);
            hold.time_start.setTag(hold);
            hold.time_end.setTag(hold);
            hold.bewrite.setTag(hold);
        }else{
            hold= (Viewhold) view.getTag();
            hold.et_firm.setTag(hold);
            hold.hangye.setTag(hold);
            hold.time_start.setTag(hold);
            hold.time_end.setTag(hold);
            hold.bewrite.setTag(hold);
        }




        //开始时间
        hold.time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        hold.time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return view;
    }




    public  class  Viewhold{
        private TextView  hangye;
        //开始时间
        private TextView  time_start;
        //结束时间
        private TextView  time_end;
        //描述
        private EditText bewrite;

        //公司
        private EditText et_firm;

        public Viewhold(View view) {
            hangye= (TextView) view.findViewById(R.id.hangye);
            et_firm= (EditText) view.findViewById(R.id.et_firm);
            bewrite= (EditText) view.findViewById(R.id.bewrite);
            time_end= (TextView) view.findViewById(R.id.time_end);
            time_start= (TextView) view.findViewById(R.id.time_start);
        }
    }
}
