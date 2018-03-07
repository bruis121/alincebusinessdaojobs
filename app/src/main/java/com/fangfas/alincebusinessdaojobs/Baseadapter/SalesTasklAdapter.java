package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.content.Context;
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
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyAdviertisement;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyInvestment;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class SalesTasklAdapter extends RecyclerView.Adapter {
    private Context activity;
    public ArrayList<HomeSalesContent.Content.Contents> data;

    public SalesTasklAdapter(FragmentActivity activity, ArrayList<HomeSalesContent.Content.Contents> data) {
        this.activity = activity;
        this.data = data;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_home_zhiding,null);
                holder = new ViewHolderOne(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,null);
                holder = new ViewHolderTwo(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ViewHolderOne holde = (ViewHolderOne) holder;
                holde.zhiding2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Intent itnet2 = new Intent(activity, ActivtyAdviertisement.class);
                            activity.startActivity(itnet2);

                    }
                });
                holde.zhiding1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Intent itnet3 = new Intent(activity, ActivtyInvestment.class);
                            activity.startActivity(itnet3);

                    }
                });
                break;
            case 1:
                ViewHolderTwo item = (ViewHolderTwo) holder;
                final HomeSalesContent.Content.Contents bean = data.get(position - 1);

                item.name.setText(bean.goods_name);
                item.text_category.setText(activity.getResources().getString(R.string.str_sales_locality) + bean.region_name);
                item.code.setText(activity.getResources().getString(R.string.str_code) + bean.child_task_sn);
                item.attract_investment_number.setText(bean.zhaoshangrenshu);
                item.tx_receive_numberof.setText(bean.person_limit);
                item.ticheng.setText(bean.ticheng);
                item.employer.setText(bean.danwei);

                if (bean.flag.equals("1")) {
                    item.green.setVisibility(View.GONE);
                    item.blue.setVisibility(View.VISIBLE);
                    item.yellow.setVisibility(View.GONE);
                } else if (bean.flag.equals("2")) {
                    item.green.setVisibility(View.GONE);
                    item.blue.setVisibility(View.GONE);
                    item.yellow.setVisibility(View.VISIBLE);
                }





                item.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent is = new Intent(activity, ActivtyDetails.class);
                        is.putExtra("trid", bean.trid);//任务地区id
                        is.putExtra("getnumber", bean.person_limit);
                        is.putExtra("child_task_sn", bean.child_task_sn);
                        activity.startActivity(is);
                    }
                });


                break;
        }
    }


    @Override
    public int getItemCount() {
        return data == null?1:data.size()+1;
//        return data.size() + 1;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }


    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        private CardView zhiding1;
        private CardView zhiding2;

        public ViewHolderOne(View itemView) {
            super(itemView);
            zhiding1= (CardView) itemView.findViewById(R.id.zhiding1);
            zhiding2= (CardView) itemView.findViewById(R.id.zhiding2);
        }
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

        public ViewHolderTwo(View view) {
            super(view);
            cardview= (CardView) view.findViewById(R.id.cardview);
            yellow= (TextView) view.findViewById(R.id.yellow);
            green= (TextView) view.findViewById(R.id.green);
            blue= (TextView) view.findViewById(R.id.blue);
            name= (TextView) view.findViewById(R.id.name);
            text_category= (TextView) view.findViewById(R.id.text_category);
            code= (TextView) view.findViewById(R.id.code);
            attract_investment_number= (TextView) view.findViewById(R.id.attract_investment_number);
            tx_receive_numberof= (TextView) view.findViewById(R.id.tx_receive_numberof);
            ticheng= (TextView) view.findViewById(R.id.ticheng);
            employer= (TextView) view.findViewById(R.id.employer);
        }
    }



}

