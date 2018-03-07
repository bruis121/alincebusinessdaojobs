package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/8 0008.
 * 首页销售适配
 */

public class HomeAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity  context;
    private  ArrayList<String> data;
    private  boolean home;

    public HomeAdapter(FragmentActivity activity, boolean home, ArrayList<String> data) {
         this.context=activity;
         this.data=data;
         this.home=home;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.home_item,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holders= (MyViewHolder) holder;
        if(home==true){
            holders.sticky.setVisibility(View.VISIBLE);
            if(position>1){
                holders.sticky.setVisibility(View.GONE);
            }else{
                holders.sticky.setVisibility(View.VISIBLE);
            }}else{
            holders.sticky.setVisibility(View.GONE);
        }

        if(position>3){
            holders.green.setVisibility(View.VISIBLE);
            holders.blue.setVisibility(View.GONE);
            holders.yellow.setVisibility(View.GONE);
        }else if(position<3||position>6){
            holders.green.setVisibility(View.GONE);
            holders.blue.setVisibility(View.VISIBLE);
            holders.yellow.setVisibility(View.GONE);
        }else{
            holders.green.setVisibility(View.GONE);
            holders.blue.setVisibility(View.GONE);
            holders.yellow.setVisibility(View.VISIBLE);
        }



        holders.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ActivtyDetails.class);
                i.putExtra("enter","home");
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

        private CardView cardview;
        private TextView  yellow;
        private TextView green;
        private TextView blue;
        private ImageView sticky;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardview= (CardView) itemView.findViewById(R.id.cardview);
            yellow= (TextView) itemView.findViewById(R.id.yellow);
            green= (TextView) itemView.findViewById(R.id.green);
            blue= (TextView) itemView.findViewById(R.id.blue);
            sticky= (ImageView) itemView.findViewById(R.id.sticky);
        }
    }


}
