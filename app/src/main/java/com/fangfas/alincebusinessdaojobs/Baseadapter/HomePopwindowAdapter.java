package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class HomePopwindowAdapter extends BaseAdapter {
    private Context context;
    ArrayList<String> data;

    public HomePopwindowAdapter(Activity context, ArrayList<String> data) {
         this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data == null?0:data.size();
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
        viewhold  hold;
        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.homepopwindow_item, null);
            hold=new viewhold(view);
            view.setTag(hold);
        }else{
            hold= (viewhold) view.getTag();
        }
        hold.tx.setText(data.get(i));
        return view;
    }
    public class  viewhold{
        private TextView  tx;
        public viewhold(View view) {
           tx= (TextView) view.findViewById(R.id.tx);
        }
    }
}
