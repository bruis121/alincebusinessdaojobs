package com.fangfas.alincebusinessdaojobs.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.fangfas.alincebusinessdaojobs.Json.SubordinateContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Baseadapter.MyTaskSubAdapter;
import com.fangfas.alincebusinessdaojobs.Tool.PhoneHorizontalLine;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.View.CircleImageView;
import com.fangfas.alincebusinessdaojobs.View.HomePopwindow;
import com.fangfas.alincebusinessdaojobs.Tool.ListViewHeight;
import com.fangfas.alincebusinessdaojobs.View.MyScrollView;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestSubordinate;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/11/9 0009.
 * 我的设置--我的任务--下级
 */

public class FragmentSubordinate extends Fragment implements View.OnClickListener{

    private MyTaskSubAdapter adapter;
    private  HomePopwindow  popwindow;
    private  View  view;
    private TextView btn_tab1;
    private TextView btn_tab2;
    private TextView  btn_tab3;
    private LinearLayout rg_snapbar_top;
    private TextView  btn_tab1_top;
    private TextView  btn_tab2_top;
    private TextView btn_tab3_top;
    private ListView  list;
    private MyScrollView  scrollView;
    private  int snapbar_y;


    private CircleImageView header_mage;
    private TextView name;
    private TextView tx_tel;
    //账户金额
     private TextView tx_account;
    //招商数量
    private TextView count;
    //招商金额
     private TextView attract_moneys;
    //提成金额
    private TextView  tx_ticheng;
    private LinearLayout ll;

    private RequestSubordinate model;

    private SubordinateContent bean;

     private StringRequest stringRequest;
    /**
     * 筛选数据
     */
    ArrayList<String>time=new ArrayList<>();
    ArrayList<String>jie=new ArrayList<>();






    ArrayList<SubordinateContent.Context.Account_list> list_data=new ArrayList<SubordinateContent.Context.Account_list>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_subordinate,null);
        btn_tab1= (TextView) view.findViewById(R.id.btn_tab1);
        btn_tab2= (TextView) view.findViewById(R.id.btn_tab2);
        btn_tab3= (TextView) view.findViewById(R.id.btn_tab3);
        rg_snapbar_top= (LinearLayout) view.findViewById(R.id.rg_snapbar_top);
        btn_tab1_top= (TextView) view.findViewById(R.id.btn_tab1_top);
        btn_tab2_top= (TextView) view.findViewById(R.id.btn_tab2_top);
        btn_tab3_top= (TextView) view.findViewById(R.id.btn_tab3_top);
        list= (ListView) view.findViewById(R.id.list);
        scrollView= (MyScrollView) view.findViewById(R.id.scrollView);
        header_mage= (CircleImageView) view.findViewById(R.id.header_mage);
        name= (TextView) view.findViewById(R.id.name);
        tx_tel= (TextView) view.findViewById(R.id.tx_tel);
        tx_account= (TextView) view.findViewById(R.id.tx_account);
        count= (TextView) view.findViewById(R.id.count);
        attract_moneys= (TextView) view.findViewById(R.id.attract_moneys);
        tx_ticheng= (TextView) view.findViewById(R.id.tx_ticheng);
        ll= (LinearLayout) view.findViewById(R.id.ll);






        top_btn();



                 model=new RequestSubordinate(stringRequest,getActivity(),hander);




        list.setFocusable(false);


        snapbar_y = dip2px(getActivity(),90);//snapbar以上部分的高度值，将其转换为px（最好设置为固定值，如果非固定，则要动态计算高度）


        scrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y >= snapbar_y) {
                    rg_snapbar_top.setVisibility(View.VISIBLE);
                } else {
                    rg_snapbar_top.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    Handler  hander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                try{
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                bean=JSONObject.parseObject((String) msg.obj, SubordinateContent.class);
                Glide.with(getActivity())
                        .load(bean.content.head_photo)
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .into(header_mage);
                name.setText(bean.content.real_name);
                tx_tel.setText(new PhoneHorizontalLine().PhoneHorizontalLine(bean.content.tel));
                tx_account.setText(bean.content.account);
                count.setText(bean.content.zhaoshangshuliang);
                attract_moneys.setText(bean.content.zhaoshangjine);
                tx_ticheng.setText(bean.content.tichengjine);
                if(bean.content.account_list!=null&&bean.content.account_list.size()!=0){
                    list_data=bean.content.account_list;

                    adapter=new MyTaskSubAdapter(getActivity(),bean.content.account_list);
                    list.setAdapter(adapter);
                    new ListViewHeight(list);


                    animatins();




                }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(msg.what==2){
                try{
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                bean=JSONObject.parseObject((String) msg.obj, SubordinateContent.class);
                if(bean.content.account_list.size()!=0){
                    list_data=bean.content.account_list;

                    adapter=new MyTaskSubAdapter(getActivity(),bean.content.account_list);
                    list.setAdapter(adapter);
                    new ListViewHeight(list);
                    animatins();
                    adapter.notifyDataSetChanged();


                }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else  if(msg.what==3){

                try {
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                    time = (ArrayList<String>) JSON.parseArray(JSONObject.parseObject((String) msg.obj).getString("content"), String.class);
                   if(time.size()==0){
                       return;
                   }
                    popwin(btn_tab3, "3", time);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };



    private void top_btn() {
        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
        btn_tab3.setOnClickListener(this);
        btn_tab1_top.setOnClickListener(this);
        btn_tab2_top.setOnClickListener(this);
        btn_tab3_top.setOnClickListener(this);
    }

    private void popwin(TextView view, final String  po, ArrayList<String> bs) {
        popwindow=new HomePopwindow(getActivity(),view,bs);
        popwindow.showAsDropDown(view);


        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
               int  i=popwindow.postion;
                //时间选择
                if(po.equals("1")){
                   if(i==0){
                       model.saixuan(hander,"create_time_desc","");




                   }else if(i==1){
                        model.saixuan(hander,"create_time_asc","");

                   }

                    //时间筛选
               }else if(po.equals("2")){
                    if(i==0){
                        model.saixuan(hander,"per_money_desc","");
                    } else if(i==1){
                    model.saixuan(hander,"per_money_asc","");
                }

                    //姓名筛选
                }else if(po.equals("3")){

                     model.saixuan(hander,"",time.get(i));
                }

            }
        });
    }




    /**
     * 将dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    public void onClick(View view) {
        if(R.id.btn_tab1==view.getId()){
            time.clear();
            time.add("从近到远");
            time.add("从远到近");
            popwin(btn_tab1,"1",time);

        }else  if( R.id.btn_tab2==view.getId()){
            time.clear();
            time.add("由高到低");
            time.add("由低到高");
            popwin(btn_tab2,"2", time);

        }else if(R.id.btn_tab3==view.getId()){

            model.getnamesaixuan(hander);
        }else if(R.id.btn_tab1_top==view.getId()){
            time.clear();
            time.add("由近到远");
            time.add("由远到近");
            popwin(btn_tab1_top,"1", time);

        }else  if(R.id.btn_tab2_top==view.getId()){
            time.clear();
            time.add("由高到低");
            time.add("由低到高");
            popwin(btn_tab2_top, "2",time);

        }else  if(R.id.btn_tab3_top==view.getId()){
            model.getnamesaixuan(hander);

        }

    }


    //动画处理
    private void animatins() {
        //实现item动画
        Animation animation = (Animation) AnimationUtils.loadAnimation(
                getActivity(), R.anim.list_anim);
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        lac.setDelay(0.2f);  //设置动画间隔时间
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL); //设置列表的显示顺序
        list.setLayoutAnimation(lac);  //为ListView 添加动画
    }


    @Override
    public void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(getActivity()).cancelToRequestQueue(stringRequest);
    }
}
