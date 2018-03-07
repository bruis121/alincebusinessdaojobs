package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.DetailsProductContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/14 0014.
 * 任务详情
 */

public class ParameterDetailsAdapter  extends BaseAdapter{
    private Activity  context;
    LayoutInflater inflater;
    ArrayList<DetailsProductContent.Content.Good_content2> data;
    public ParameterDetailsAdapter(FragmentActivity activity, ArrayList<DetailsProductContent.Content.Good_content2> data) {
        this.context=activity;
         this.data=data;
        inflater= LayoutInflater.from(context);
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
        ViewHolder   hoild;
        if(view==null){
            view=inflater.inflate( R.layout.paramteter_details_item,null);
            hoild=new ViewHolder(view);
            view.setTag(hoild);
        }else{
            hoild= (ViewHolder) view.getTag();
        }
        hoild.name.setText(data.get(i).name);
        hoild.value.setText(data.get(i).value);
        return view;
    }

    static class ViewHolder {
        private TextView name;
        private TextView value;
        public ViewHolder(View view) {
            name= (TextView) view.findViewById(R.id.name);
            value= (TextView) view.findViewById(R.id.value);
        }
    }
}
