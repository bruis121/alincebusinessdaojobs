package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/17 0017.
 */

public class JobPopwind2Adapter extends BaseAdapter {
    private  ArrayList<HangyeContent.Content.JobCatList> jobCatList;
    private  Context context;
    public JobPopwind2Adapter(Context context, ArrayList<HangyeContent.Content.JobCatList> jobCatList) {
        this.jobCatList=jobCatList;
        this.context=context;


    }

    @Override
    public int getCount() {
        return jobCatList == null?0:jobCatList.size();
    }

    @Override
    public Object getItem(int i) {
        return jobCatList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.job_popitem, null);
        TextView name= (TextView) view.findViewById(R.id.name);
        name.setText(jobCatList.get(i).catTitle);
        return view;
    }
}
