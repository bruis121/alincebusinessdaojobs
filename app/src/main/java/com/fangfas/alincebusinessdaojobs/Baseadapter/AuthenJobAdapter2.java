package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.GetAuthentContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/12/30 0030.
 * 已认证
 */

public class AuthenJobAdapter2  extends BaseAdapter{
    private Context  context;
    private  ArrayList<GetAuthentContent.Resumework>   resumework;
    public AuthenJobAdapter2(ActivityAuthentication activityAuthentication, ArrayList<GetAuthentContent.Resumework> resumework) {
           this.context=activityAuthentication;
           this.resumework=resumework;
    }

    @Override
    public int getCount() {
        return resumework== null?0:resumework.size();
    }

    @Override
    public Object getItem(int i) {
        return resumework.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder  holder;
        if(view==null) {
           view = View.inflate(context, R.layout.athenjob_item, null);
            holder=new viewHolder(view);
            view.setTag(holder);
       }else{
            holder= (viewHolder) view.getTag();
       }
        GetAuthentContent.Resumework  bean=resumework.get(i);
        holder.time.setText(bean.yearBegin+"~"+bean.yearEnd);
        holder.cats.setText(bean.cats);
        holder.summary.setText(bean.summary);
        return view;
    }
    public  class  viewHolder{
        private TextView time;
        private TextView cats;
        private TextView summary;
        public viewHolder(View view) {
            time= (TextView) view.findViewById(R.id.time);
            cats= (TextView) view.findViewById(R.id.cats);
            summary= (TextView) view.findViewById(R.id.summary);
        }
    }
}
