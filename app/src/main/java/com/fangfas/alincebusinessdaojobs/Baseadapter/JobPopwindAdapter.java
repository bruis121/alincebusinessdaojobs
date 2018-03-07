package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.R;

/**
 * Created by Administrator on 2016/12/17 0017.
 */

public class JobPopwindAdapter extends BaseAdapter{
    private  Context context;
    private HangyeContent bean;
    public JobPopwindAdapter(Context context, HangyeContent bean) {
          this.context=context;
          this.bean=bean;
    }

    @Override
    public int getCount() {
        return bean.content == null?0:bean.content.size();

    }

    @Override
    public Object getItem(int i) {
        return bean.content.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.job_popitem, null);
        TextView  name= (TextView) view.findViewById(R.id.name);
        name.setText(bean.content.get(i).catTitle);
        return view;
    }
}
