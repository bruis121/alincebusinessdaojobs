package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.SubordinateContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/10 0010.
 * 我的设置---我的任务--下级适配器
 *
 */

public class MyTaskSubAdapter extends BaseAdapter {
    private Activity context;
    ArrayList<SubordinateContent.Context.Account_list> data;
    public MyTaskSubAdapter(FragmentActivity activity, ArrayList<SubordinateContent.Context.Account_list> data) {
          this.context=activity;
           this.data=data;
    }

    @Override
    public int getCount() {
        return data== null?0:data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SubordinateContent.Context.Account_list  bean=data.get(i);
        ViewHolder  holder;
        if(view==null){
            view=View.inflate(context, R.layout.mytask_subitem,null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        holder.name.setText(bean.real_name);
        holder.tx_task_name.setText(bean.task_name);
        holder.time.setText(bean.create_time1);
        holder.per_money.setText(bean.per_money
        );

        return view;
    }

    static class ViewHolder {
       private TextView  name;
        //商家
        private TextView tx_task_name;
        private TextView time;
        //提成
        private TextView per_money;
        public ViewHolder(View view) {
            name= (TextView) view.findViewById(R.id.name);
            tx_task_name= (TextView) view.findViewById(R.id.tx_task_name);
            time= (TextView) view.findViewById(R.id.time);
            per_money= (TextView) view.findViewById(R.id.per_money);
        }
    }
}
