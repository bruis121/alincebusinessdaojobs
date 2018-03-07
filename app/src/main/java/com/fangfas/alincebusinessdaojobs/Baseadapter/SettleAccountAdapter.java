package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.JSHistoryContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.Comma;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class SettleAccountAdapter extends BaseAdapter {
    private   Activity  context;
    private  ArrayList<JSHistoryContent.Content> d;
    public SettleAccountAdapter(Activity mContext, ArrayList<JSHistoryContent.Content> d) {
        this.context=mContext;
        this.d=d;
    }

    @Override
    public int getCount() {
        return d == null?0:d.size();
    }

    @Override
    public Object getItem(int i) {
        return d.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder hoder;
        if(view==null) {
            view = View.inflate(context, R.layout.settleaccounts_item, null);
            hoder=new Viewholder(view);
            view.setTag(hoder);
        }else{
            hoder= (Viewholder) view.getTag();

        }
        hoder.name.setText(d.get(i).name);
        hoder.tx_percentage.setText(new Comma().Comma(d.get(i).price));
        return view;
    }
    class  Viewholder{
        private TextView name;
        //提成
        private TextView  tx_percentage;
        public Viewholder(View view) {
            name= (TextView) view.findViewById(R.id.tx_name);
            tx_percentage= (TextView) view.findViewById(R.id.tx_percentage);
        }


    }
}
