package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Json.HomeSalesContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/24 0024.
 * 首页、任务适配器
 */

public class SalesTaskAdapter extends  RecyclerView.Adapter {
    private Activity context;
    private ArrayList<HomeSalesContent.Content.Contents> dataList;

    public SalesTaskAdapter(FragmentActivity activity, ArrayList<HomeSalesContent.Content.Contents> dataList) {
        this.context = activity;
        this.dataList = dataList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,null);
                holder = new ViewHolderTwo(view);


        return  holder;
    }





    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

                ViewHolderTwo viewholder = (ViewHolderTwo) holder;
                final HomeSalesContent.Content.Contents bean=dataList.get(position);
                viewholder.name.setText(bean.goods_name);
                viewholder.text_category.setText(context.getResources().getString(R.string.str_sales_locality) + bean.region_name);
                viewholder.code.setText(context.getResources().getString(R.string.str_code) + bean.child_task_sn);
                viewholder.attract_investment_number.setText(bean.zhaoshangrenshu);
                viewholder.tx_receive_numberof.setText(bean.person_limit);
                viewholder.ticheng.setText(bean.ticheng);
                viewholder.employer.setText(bean.danwei);
                if (bean.flag.equals("1")) {
                    viewholder.green.setVisibility(View.GONE);
                    viewholder.blue.setVisibility(View.VISIBLE);
                    viewholder.yellow.setVisibility(View.GONE);
                } else if (bean.flag.equals("2")) {
                    viewholder.green.setVisibility(View.GONE);
                    viewholder.blue.setVisibility(View.GONE);
                    viewholder.yellow.setVisibility(View.VISIBLE);
                }





                viewholder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(context, ActivtyDetails.class);
                        i.putExtra("gtid", bean.gtid);
                        i.putExtra("trid", bean.trid);//任务地区id
                        i.putExtra("getnumber", bean.person_limit);
                        i.putExtra("child_task_sn", bean.child_task_sn);
                        context.startActivity(i);

                    }
                });


    }


    @Override
    public int getItemCount() {
        return dataList == null?0:dataList.size();
    }




    public static class ViewHolderTwo extends RecyclerView.ViewHolder {
        private CardView cardview;
        private TextView yellow;
        private TextView green;
        private TextView blue;

        private TextView name;
        //招商地区
        private TextView text_category;
        //编码
        private TextView code;
        //招商人数
        private TextView attract_investment_number;
        //领取人数
        private TextView tx_receive_numberof;
        //提成
        private TextView ticheng;
        //单位
        private TextView employer;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            cardview= (CardView) itemView.findViewById(R.id.cardview);
            yellow= (TextView) itemView.findViewById(R.id.yellow);
            green= (TextView) itemView.findViewById(R.id.green);
            blue= (TextView) itemView.findViewById(R.id.blue);
            name= (TextView) itemView.findViewById(R.id.name);
            text_category= (TextView) itemView.findViewById(R.id.text_category);
            code= (TextView) itemView.findViewById(R.id.code);
            attract_investment_number= (TextView) itemView.findViewById(R.id.attract_investment_number);
            tx_receive_numberof= (TextView) itemView.findViewById(R.id.tx_receive_numberof);
            ticheng= (TextView) itemView.findViewById(R.id.ticheng);
            employer= (TextView) itemView.findViewById(R.id.employer);
        }
    }
}


