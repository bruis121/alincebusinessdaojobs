package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.AccountContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/10 0010.
 * 我的任务
 */

public class MyTaskAdapter  extends BaseAdapter {
    private Context context;
    private  ArrayList<AccountContent.Content.Account_list> l;

    public MyTaskAdapter(FragmentActivity activity, ArrayList<AccountContent.Content.Account_list> data) {
        this.context=activity;
        this.l=data;
    }


    @Override
    public int getCount() {
        return l.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {

          private   TextView name;
            private TextView time;
          //金额
        TextView  tx_per_money;

        public ViewHolder(View convertView) {
            name= (TextView) convertView.findViewById(R.id.name);
            time= (TextView) convertView.findViewById(R.id.time);
            tx_per_money= (TextView) convertView.findViewById(R.id.tx_per_money);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
           return 1;
        }else{
          return  2;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(getItemViewType(position)==1){
           convertView=View.inflate(context, R.layout.account_header,null);
           return convertView;
       }else{


        final ViewHolder mviewHolder ;
        if (convertView == null) {
            convertView=View.inflate(context, R.layout.mytask_item,null);
            mviewHolder = new ViewHolder(convertView);
            convertView.setTag(mviewHolder);
        }else{
            mviewHolder = (ViewHolder) convertView.getTag();
        }


           mviewHolder.name.setText(l.get(position-1).task_name);
           mviewHolder.time.setText(l.get(position-1).create_time);
           mviewHolder.tx_per_money.setText(l.get(position-1).per_money);
        return convertView;
    }
    }
}
