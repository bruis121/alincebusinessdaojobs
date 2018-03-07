package com.fangfas.alincebusinessdaojobs.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.fangfas.alincebusinessdaojobs.Json.DetailsContent;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.BroadCastManager;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyDetails;
import com.fangfas.alincebusinessdaojobs.View.CommodityImage;
import com.fangfas.alincebusinessdaojobs.View.HintDialog;
import com.fangfas.alincebusinessdaojobs.View.MyScrollView;
import com.fangfas.alincebusinessdaojobs.View.MyViewPager;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestTaskDetails;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by Administrator on 2016/7/5.
 * 商家
 */
public class FragmentTaskDetail extends Fragment  implements  View.OnClickListener{
  private MyScrollView scrollView;
  private RadioGroup rg_snapbar_top;
  private RadioGroup rg_snapbar;
   private RadioButton btn_tab1_top;
    private RadioButton btn_tab2_top;
    RadioButton btn_tab3_top;
    private RadioButton btn_tab4_top;
    private RadioButton  btn_tab1;
    private RadioButton  btn_tab2;
    private RadioButton  btn_tab3;
    private RadioButton  btn_tab4;
    private MyViewPager vp;



    //领取任务
    private CardView receivetask;
    private CardView collection;
    private CommodityImage imge;
    //招商地址
    private TextView tx_investmentarea;
    //领取人数
    private TextView  tx_number;
    //截止时间
    private TextView  tx_time;
    //名称
    private TextView  name;

    private LinearLayout ll;

    ImageView imge_collection;
    public  LinearLayout  ll_header;



    // tab页面列表
    private List<Fragment> fragments;



    int snapbar_y;




    public static FragmentTaskDetail instance;

     private  View  view;


    //任务地区id
    public    String trid,child_task_sn;
    RequestTaskDetails model;
    private   String  cid,isinvalid;

    public StringRequest stringRequest;
    //是否领取时的gtid
    private   String  getlingqu;
    private  String    isend;//true  领取完，false未领取完
    //颜色
    private   int    hui,red;
    //提示弹窗
    //提示框
    BounceTopEnter mBasIn;
    SlideBottomExit mBasOut;
    NormalDialog dialogs;

    private DetailsContent data;
    private LocalReceiver mReceiver;
    private SVProgressHUD mSVProgressHUD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_taskdetail,null);
        scrollView= (MyScrollView) view.findViewById(R.id.scrollView);
        rg_snapbar_top= (RadioGroup) view.findViewById(R.id.rg_snapbar_top);
        rg_snapbar= (RadioGroup) view.findViewById(R.id.rg_snapbar);
        btn_tab1_top= (RadioButton) view.findViewById(R.id.btn_tab1_top);
        btn_tab2_top= (RadioButton) view.findViewById(R.id.btn_tab2_top);
        btn_tab3_top= (RadioButton) view.findViewById(R.id.btn_tab3_top);
        btn_tab4_top= (RadioButton) view.findViewById(R.id.btn_tab4_top);
        btn_tab1= (RadioButton) view.findViewById(R.id.btn_tab1);
        btn_tab2= (RadioButton) view.findViewById(R.id.btn_tab2);
        btn_tab3= (RadioButton) view.findViewById(R.id.btn_tab3);
        btn_tab4= (RadioButton) view.findViewById(R.id.btn_tab4);
        vp= (MyViewPager) view.findViewById(R.id.vp);
        receivetask= (CardView) view.findViewById(R.id.receivetask);
        collection= (CardView) view.findViewById(R.id.collection);
        imge= (CommodityImage) view.findViewById(R.id.imge);
        tx_investmentarea= (TextView) view.findViewById(R.id.tx_investmentarea);
        tx_number= (TextView) view.findViewById(R.id.tx_number);
        tx_time= (TextView) view.findViewById(R.id.tx_time);
        name= (TextView) view.findViewById(R.id.name);
        ll= (LinearLayout) view.findViewById(R.id.ll);
        imge_collection= (ImageView) view.findViewById(R.id.imge_collection);
        ll_header= (LinearLayout) view.findViewById(R.id.ll_header);

        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("datas");
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(getActivity(),
                    mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }

        initView();

        //初始化model类
        model=new RequestTaskDetails(stringRequest,getActivity(),handler);
        trid=getActivity().getIntent().getStringExtra("trid");
        child_task_sn=getActivity().getIntent().getStringExtra("child_task_sn");



        //颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hui= ContextCompat.getColor(getActivity(), R.color.hui);
            red=ContextCompat.getColor(getActivity(),R.color.red_x);
        }else{
            hui=getActivity().getResources().getColor(R.color.hui);
            red=getActivity().getResources().getColor(R.color.red_x);
        }



        snapbar_y = dip2px(getActivity(), 400);//注意你的snapbar以上部分的高度值，将其转换为px（最好设置为固定值，如果非固定，则要动态计算高度）


        model.home(trid);









        mSVProgressHUD = new SVProgressHUD(getActivity());
        mSVProgressHUD.showWithStatus(getString(R.string.loading));



        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        dialogs=new NormalDialog(getActivity());

        collection.setOnClickListener(this);
        receivetask.setOnClickListener(this);
        return  view;
    }



    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){

                try{
                    String   z  = (String) msg.obj;
                    if(JSONObject.parseObject(z).getString("msg").equalsIgnoreCase("error")){
                        return;
                    }

                    data=JSONObject.parseObject(z,DetailsContent.class);

                    initView();

                    imge.setdata(getActivity(),data.content.banimg);
                    name.setText(data.content.title);


                    tx_number.setText(data.content.lingyongNum + "/" + data.content.limitNum);
                    tx_time.setText(getResources().getString(R.string.str_endtime)+data.content.endtime);
                    tx_investmentarea.setText(getResources().getString(R.string.str_sales_locality)+data.content.area_names);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mSVProgressHUD.dismiss();
            }else if(msg.what==2){
                try{
                    new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            model.GetGtid(trid);
                        }
                    }).start();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else if(msg.what==3){
                try{
                    if(JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")){
                        new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                    }


                    new Snackbars(ll,JSONObject.parseObject(JSONObject.parseObject((String) msg.obj).getString("content")).getString("ok"));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            model.GetGtid(trid);
                        }
                    }).start();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //判断是否收藏或领取任务
            }else if(msg.what==5){
                try{
                    Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(msg.what==6){
                try{
                    String  datas= JSONObject.parseObject((String) msg.obj).getString("content");
                    getlingqu=JSONObject.parseObject(datas).getString("gtid");
                    cid=JSONObject.parseObject(datas).getString("cid");
                    String  isend=JSONObject.parseObject(datas).getString("isend");
                    if(!TextUtils.isEmpty(cid)){
                        imge_collection.setImageResource(R.mipmap.collecton_yellow);
                    }
                    if(!TextUtils.isEmpty(getlingqu)||isend.equals("true")){
                        receivetask.setCardBackgroundColor(hui);
                    }else {
                        receivetask.setCardBackgroundColor(red);
                    }


                    if(!TextUtils.isEmpty(getlingqu)&&getlingqu!=null){
                        ActivtyDetails activity= (ActivtyDetails) getActivity();
                        activity.getlingqu=getlingqu;
                        TextView tx= (TextView) activity.findViewById(R.id.task_setbacks);
                        tx.setOnClickListener(activity.new MyOnClickListener(1));
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else if(msg.what==8){
                try{
                    new Snackbars(ll,JSONObject.parseObject((String) msg.obj).getString("content"));
                    cid="";
                    imge_collection.setImageResource(R.mipmap.collection_hei);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        }
    };






    public synchronized static FragmentTaskDetail getInstance() {
        if (null == instance) {
            instance = new FragmentTaskDetail();
        }
        return instance;
    }






    private void initView() {
        btn_tab1.setOnClickListener(new MyOnClickListener(0));
        btn_tab1_top.setOnClickListener(new MyOnClickListener(0));
        btn_tab2.setOnClickListener(new MyOnClickListener(1));
        btn_tab2_top.setOnClickListener(new MyOnClickListener(1));
        btn_tab3.setOnClickListener(new MyOnClickListener(2));
        btn_tab3_top.setOnClickListener(new MyOnClickListener(2));
        btn_tab4.setOnClickListener(new MyOnClickListener(3));
        btn_tab4_top.setOnClickListener(new MyOnClickListener(3));





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

        initViewPaper();


    }






    /**
     * 初始化viewpaper
     */
    private void initViewPaper() {



        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentProShow(vp,1));
        fragments.add(new FragmentPaDetails(vp,2));
        fragments.add(new FragmentPeRele(vp,3));
        fragments.add(new FragmentAtInvestment(vp,4));


        vp.setAdapter(new MyAdapter(getChildFragmentManager(), fragments));
        vp.resetHeight(0);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(5);

        vp.addOnPageChangeListener(new MyOnPageChangeListener());


    }




    public class MyAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public MyAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个界面
         */
        @Override
        public Fragment getItem(int arg0) {

            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        /**
         * 得到每个界面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return super.getPageTitle(position);
        }

        /**
         * 页面总个数
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragmentList == null ? 0 : fragmentList.size();
        }


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

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            vp.resetHeight(position);
            switch (position){
                case 0:

                    btn_tab1.setChecked(true);
                    btn_tab1_top.setChecked(true);
                    break;
                case 1:
                    btn_tab2.setChecked(true);
                    btn_tab2_top.setChecked(true);
                    break;
                case 2:
                    btn_tab3.setChecked(true);
                    btn_tab3_top.setChecked(true);
                    break;
                case 3:
                    btn_tab4.setChecked(true);
                    btn_tab4_top.setChecked(true);
                    break;


            }

        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }


    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;
        public MyOnClickListener(int i) {
            index = i;

        }

        @Override
        public void onClick(View v) {
            if(R.id.btn_tab1==index||R.id.btn_tab1_top==index){
                vp.setCurrentItem(0);

            }else  if(R.id.btn_tab2==index||R.id.btn_tab2_top==index){
                vp.setCurrentItem(1);

            }else  if(R.id.btn_tab3==index||R.id.btn_tab3_top==index){
                vp.setCurrentItem(2);
            }else  if(R.id.btn_tab4==index||R.id.btn_tab4_top==index){
                vp.setCurrentItem(3);

            }
            vp.setCurrentItem(index);
        }


    }



    @Override
    public void onClick(View view) {
        if(R.id.receivetask==view.getId()){
            //未认证
            if(!MyApplication.getInstance().GetAuth.equals("pass")){
                getDialog();
            }else {
                if(!TextUtils.isEmpty(getlingqu)&&getlingqu!=null&&isend.equals("false")){
                    new  Snackbars(ll,getResources().getString(R.string.lingqu));
                }else if(isend.equals("true")){
                    new  Snackbars(ll,getResources().getString(R.string.isend));
                }else  if(isinvalid.equals("flase")){
                    new  Snackbars(ll,getResources().getString(R.string.isinvalid
                    ));
                }else {

                    model.Receivetask(trid);

                }

            }

        }else  if(R.id.collection==view.getId()){
            //未认证
            if(!MyApplication.getInstance().GetAuth.equalsIgnoreCase("pass")){
                getDialog();
            }else {
                if(TextUtils.isEmpty(cid)) {

                    Collections();

                }else{
                    model.CancelCollection(trid);

                }
            }


        }
    }

    //收藏
    private void Collections() {
        model.collection(trid,child_task_sn);
    }


    @Override
    public void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(getActivity()).cancelToRequestQueue(stringRequest);
    }


    public void getDialog() {
        //没有认证
        if(MyApplication.GetAuth.equalsIgnoreCase("null")) {
            getAu();
        } //认证失败
        else if(MyApplication.getInstance().GetAuth.equalsIgnoreCase("erro")){
            erro();
        }else if(MyApplication.getInstance().GetAuth.equalsIgnoreCase("wait")){
            Wait();
        }
    }


    public void getAu() {
        dialogs.content(getResources().getString(R.string.hint24))
                .btnNum(2)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"),Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText("取消","确定")
                .dismissAnim(mBasOut)
                .show();
        dialogs.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                        Intent i=new Intent(getActivity(),ActivityAuthentication.class);
                        startActivity(i);
                    }
                });

    }
    //等待审核
    private   void  Wait(){
        dialogs.content(getResources().getString(R.string.hint40))
                .btnNum(1)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText(getResources().getString(R.string.know))
                .dismissAnim(mBasOut)
                .show();
        dialogs.setOnBtnClickL(

                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                    }
                });
    }


    //失败
    private void erro() {
        dialogs.content(getResources().getString(R.string.hint30))
                .btnNum(2)
                .style(NormalDialog.STYLE_TWO)
                .titleTextColor(Color.parseColor("#323232"))
                .contentTextColor(Color.parseColor("#666666"))
                .btnTextColor(Color.parseColor("#1bb7e4"),Color.parseColor("#1bb7e4"))
                .titleTextSize(20)
                .showAnim(mBasIn)
                .btnText("取消","前去修改")
                .dismissAnim(mBasOut)
                .show();
        dialogs.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialogs.dismiss();
                        Intent i=new Intent(getActivity(),ActivityAuthentication.class);
                        startActivity(i);
                    }
                });
    }



    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            cid = intent.getStringExtra("cid");
            getlingqu=intent.getStringExtra("getlingqu");
            isend=intent.getStringExtra("isend");
            isinvalid=intent.getStringExtra("isinvalid");





            if(TextUtils.isEmpty(cid)){
                imge_collection.setImageResource(R.mipmap.collection_hei);
            }else {
                imge_collection.setImageResource(R.mipmap.collecton_yellow);
            }

            //activity.isinvalid  为true是可用
        if(!TextUtils.isEmpty(getlingqu)||isend.equals("true")||isinvalid.equals("false")){
            receivetask.setCardBackgroundColor(hui);
        }else {
            receivetask.setCardBackgroundColor(red);
        }

        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(),mReceiver);//注销广播接收者
    }


    @Override
    public void onPause() {
        super.onPause();
        mSVProgressHUD.dismissImmediately();
    }
}
