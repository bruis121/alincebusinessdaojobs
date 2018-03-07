package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;

/**
 * Created by Administrator on 2016/12/22 0022.
 * 认证资料职业下拉框
 */

public class DropDowIndpopAdapter extends BaseAdapter {
    private HangyeContent hangyebean;
    private Context context;
    public DropDowIndpopAdapter(ActivityAuthentication activityAuthentication, HangyeContent hangyebean) {
        this.hangyebean=hangyebean;
        this.context=activityAuthentication;
    }

    @Override
    public int getCount() {
        return hangyebean.content == null?0:hangyebean.content.size();
    }

    @Override
    public Object getItem(int i) {
        return hangyebean.content.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.job_popitem, null);
        TextView name= (TextView) view.findViewById(R.id.name);
        name.setText(hangyebean.content.get(i).catTitle);
        return view;
    }
}
