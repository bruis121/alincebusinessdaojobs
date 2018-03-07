package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/9 0009.
 * 首页服务适配
 */

public class HomeServiceAdapter extends RecyclerView.Adapter<HomeServiceAdapter.MyViewHolder>  {
   private Activity  context;
    private ArrayList<String> data;


    public HomeServiceAdapter(FragmentActivity activity, ArrayList<String> data) {
       this.context=activity;
       this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.home_service_item,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(position>3){
            holder.green.setVisibility(View.VISIBLE);
            holder.blue.setVisibility(View.GONE);
            holder.yellow.setVisibility(View.GONE);
        }else if(position<3||position>6){
            holder.green.setVisibility(View.GONE);
            holder.blue.setVisibility(View.VISIBLE);
            holder.yellow.setVisibility(View.GONE);
        }else{
            holder.green.setVisibility(View.GONE);
            holder.blue.setVisibility(View.GONE);
            holder.yellow.setVisibility(View.VISIBLE);
        }

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ActivtyDetails.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data== null?0:data.size();
    }

    public   class  MyViewHolder extends RecyclerView.ViewHolder
    {

        private TextView  blue;
        private TextView  yellow;
        private TextView   green;
        private CardView cardview;
        public MyViewHolder(View itemView) {
            super(itemView);
            blue= (TextView) itemView.findViewById(R.id.blue);
            yellow= (TextView) itemView.findViewById(R.id.yellow);
            green= (TextView) itemView.findViewById(R.id.green);
            cardview= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
