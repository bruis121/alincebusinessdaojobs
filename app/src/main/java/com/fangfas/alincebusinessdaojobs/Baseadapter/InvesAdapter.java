package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.fangfas.alincebusinessdaojobs.Json.JSHistoryContent;
import com.fangfas.alincebusinessdaojobs.Json.SetbacksContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.BroadCastManager;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityContract;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyLogistical;
import com.fangfas.alincebusinessdaojobs.View.SettleAccountsHistory;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestJiesuanHistory;

/**
 * Created by Administrator on 2016/11/21 0021.
 * 产品招商进度---兼职员
 */

public class InvesAdapter extends BaseAdapter {
    int  blue,black,hui,write;
    private Activity mContext;
    private String gtid;
    private LocalReceiver mReceiver;
     private  String contract_id;

//     Alertremittance.Builder  alert;
    SetbacksContent.Content datas;



    public InvesAdapter(Activity mContext, SetbacksContent.Content datas, String gtid) {
        super();
        this.mContext = mContext;
        this.datas=datas;
         this.gtid=gtid;


        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("contract");
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(mContext,
                    mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }


//        alert=new  Alertremittance.Builder(mContext);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            blue=mContext.getResources().getColor(R.color.blue,null);
            write=mContext.getResources().getColor(R.color.write,null);
            black=mContext.getResources().getColor(R.color.hui2,null);
            hui=mContext.getResources().getColor(R.color.hui5,null);
        }else{
            blue=mContext.getResources().getColor(R.color.blue);
            write=mContext.getResources().getColor(R.color.write);
            black=mContext.getResources().getColor(R.color.hui2);
            hui=mContext.getResources().getColor(R.color.hui5);
        }




    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 6;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Item item;
        if (convertView == null) {
            item = new Item();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.inves_item, null);
            item.txstate = (TextView) convertView.findViewById(R.id.show_state);
            item.title = (TextView) convertView.findViewById(R.id.show_title);
            item.lineNorma = convertView.findViewById(R.id.line_normal);
            item.image = (ImageView) convertView.findViewById(R.id.image);
            item.show_look= (TextView) convertView.findViewById(R.id.show_look);
            item.time= (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(item);
        } else {
            item = (Item) convertView.getTag();
        }

        if(position==0){
            item.title.setText(mContext.getString(R.string.str_negotiate));
            item.time.setText(datas.zhaoshangqiatan.time);
            item.txstate.setText(datas.zhaoshangqiatan.status_name);
            if(datas.zhaoshangqiatan.is_cur.equals("2")){
                befor(item.image,item.txstate,item.time,item.title);
            }else if(datas.zhaoshangqiatan.is_cur.equals("1")){
                beging(item.image,item.txstate,item.time,item.title);
            }else {
                bottom(item.image,item.txstate,item.time,item.title);
            }
        }
        if(position==1){
            item.title.setText(mContext.getString(R.string.str_commit_contract));
            item.time.setText(datas.tijiaohetong.time);
            item.txstate.setText(datas.tijiaohetong.status_name);
            if(datas.tijiaohetong.is_cur.equals("2")){
                befor(item.image,item.txstate,item.time,item.title);
            }else if(datas.tijiaohetong.is_cur.equals("1")){
                beging(item.image,item.txstate,item.time,item.title);
            }else {
                bottom(item.image,item.txstate,item.time,item.title);
            }

            if(datas.tijiaohetong.chakan.equals("1")){
                item.show_look.setVisibility(View.VISIBLE);
                item.show_look.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Contract();

                   }
               });
            }
        }
        if(position==2){
            item.title.setText(mContext.getString(R.string.str_established));
            item.time.setText(datas.pingtaiqueren.time);
            item.txstate.setText(datas.pingtaiqueren.status_name);
            if(datas.pingtaiqueren.is_cur.equals("2")){
                befor(item.image,item.txstate,item.time,item.title);
            }else if(datas.pingtaiqueren.is_cur.equals("1")){
                beging(item.image,item.txstate,item.time,item.title);
            }else {
                bottom(item.image,item.txstate,item.time,item.title);
            }
        }
        if(position==3){
            item.title.setText(mContext.getString(R.string.str_send));
            item.time.setText(datas.jiuqifahuo.time);
            item.txstate.setText(datas.jiuqifahuo.status_name);
            if(datas.jiuqifahuo.is_cur.equals("2")){
                befor(item.image,item.txstate,item.time,item.title);
            }else if(datas.jiuqifahuo.is_cur.equals("1")){
                beging(item.image,item.txstate,item.time,item.title);
            }else {
                bottom(item.image,item.txstate,item.time,item.title);
            }
            if(datas.jiuqifahuo.chakan.equals("1")){
                item.show_look.setVisibility(View.VISIBLE);
                Drawable img_off = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    img_off= ContextCompat.getDrawable(mContext,R.mipmap.logisticalcon);

                }else {
                    img_off = mContext.getResources().getDrawable(R.mipmap.logisticalcon);
                }
                img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                item.show_look.setCompoundDrawables(img_off, null, null, null); //设置左图标
                item.show_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Logistical();
                    }
                });
            }

        }
        if(position==4){
            item.title.setText(mContext.getString(R.string.str_established_send));
            item.time.setText(datas.querenshouhuo.time);
            item.txstate.setText(datas.querenshouhuo.status_name);
            if(datas.querenshouhuo.is_cur.equals("2")){
                befor(item.image,item.txstate,item.time,item.title);
            }else if(datas.querenshouhuo.is_cur.equals("1")){
                beging(item.image,item.txstate,item.time,item.title);
            }else {
                bottom(item.image,item.txstate,item.time,item.title);
            }
        }
        if(position==5){
            item.title.setText(mContext.getString(R.string.str_statements));
            item.time.setText(datas.tichengjiesuan.time);
            item.txstate.setText(datas.tichengjiesuan.status_name);
            if(datas.tichengjiesuan.is_cur.equals("2")){
                befor(item.image,item.txstate,item.time,item.title);
            }else if(datas.tichengjiesuan.is_cur.equals("1")){
                beging(item.image,item.txstate,item.time,item.title);
            }else {
                bottom(item.image,item.txstate,item.time,item.title);
            }

            if(datas.tichengjiesuan.chakan.equals("1")){
                item.show_look.setVisibility(View.VISIBLE);
                item.show_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RequestJiesuanHistory model=new RequestJiesuanHistory(mContext,gtid,handler2);

                    }
                });

            }
        }




        return convertView;
    }

    Handler handler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==4){

                JSHistoryContent beans= JSONObject.parseObject((String) msg.obj,JSHistoryContent.class);

                SettleAccountsHistory.Builder  dialog=new  SettleAccountsHistory.Builder(mContext);
                dialog.setTitle(mContext.getResources().getString(R.string.strettle_accounts));
                dialog.setPositiveButton(mContext.getResources().getString(R.string.str_close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();


                    }
                });
                dialog.create().show();

                dialog.getMessage(mContext,beans.content);



            }
        }
    };

    //未执行
    private void bottom(ImageView image,TextView txstate,TextView time,TextView title) {
        image.setImageResource(R.mipmap.progress_no);
        txstate.setTextColor(hui);
        time.setTextColor(hui);
        title.setTextColor(hui);
    }

    //当前
    private void beging(ImageView image,TextView txstate,TextView time,TextView title) {
        image.setImageResource(R.mipmap.progress_pre);
        txstate.setTextColor(blue);
        time.setTextColor(blue);
        title.setTextColor(blue);

    }

    //之前
    private void befor(ImageView image,TextView txstate,TextView time,TextView title) {
        image.setImageResource(R.mipmap.progress_no);
        txstate.setTextColor(black);
        time.setTextColor(black);
        title.setTextColor(black);
    }


    private class Item {
        TextView txstate, title;
        View lineNorma;
        ImageView image;
        TextView  show_look;
        TextView  time;
    }


    //酒企发货直接进入物流
    public   void Logistical(){
                    Intent  i=new Intent(mContext,ActivtyLogistical.class);
                    i.putExtra("gtid",gtid);
                    mContext.startActivity(i);

    }

    //合同
    private void Contract() {


        Intent  i=new Intent(mContext, ActivityContract.class);
        i.putExtra("contract_id",contract_id);
        mContext.startActivity(i);

    }



    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            contract_id = intent.getStringExtra("contract_id");
        }
    }

}
