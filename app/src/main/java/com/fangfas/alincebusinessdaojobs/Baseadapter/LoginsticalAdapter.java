package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.fangfas.alincebusinessdaojobs.Json.LogisticalContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/25 0025.
 * 物流状态适配器
 */

public class LoginsticalAdapter extends BaseAdapter{
    private   Activity  context;
    private  ArrayList<LogisticalContent.Content.Datas>datas;
    public LoginsticalAdapter(Activity logistical, ArrayList<LogisticalContent.Content.Datas> datas) {
            this.context=logistical;
            this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas == null?0:datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder  holde;
//        if(view==null){
           view=View.inflate(context, R.layout.logistical_item,null);
            holde=new ViewHolder(view);
            holde.image.setTag(i);

//       }else{
//            holde= (ViewHolder) view.getTag();
//            holde.image.getTag(i);
//        }

         holde.show_title.setText(datas.get(i).context);
         holde.time.setText(datas.get(i).time);
         if(i==0){
           holde.image.setImageResource(R.mipmap.progress_pre);
             holde.show_title.setTextColor(context.getResources().getColor(R.color.blue));
         }

        return view;
    }

    public static   class ViewHolder{
        private TextView  show_title;
        private TextView  time;
        private ImageView  image;
        public ViewHolder(View view) {
            show_title= (TextView) view.findViewById(R.id.show_title);
            time= (TextView) view.findViewById(R.id.time);
            image= (ImageView) view.findViewById(R.id.image);
        }
    }
}
