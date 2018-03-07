package com.fangfas.alincebusinessdaojobs.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.BroadCastManager;
import com.fangfas.alincebusinessdaojobs.View.NoScrollViewPager;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/11/7 0007.
 */

public  class FragmentHome extends Fragment  {

    //销售任务
    private TextView sales;
    //服务任务
    private TextView service;

    private NoScrollViewPager pager;
    //地址选择
    private TextView address;

    private LinearLayout ll;

    // tab页面列表
    private List<Fragment> fragments;
    private int blue, write;
    /**
     * 页卡总数
     */
    private View view;


    private LocalReceiver mReceiver;
    private  String adrress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        sales= (TextView) view.findViewById(R.id.sales);
        service= (TextView) view.findViewById(R.id.service);
        pager= (NoScrollViewPager) view.findViewById(R.id.pager);
        address= (TextView) view.findViewById(R.id.address);
        ll= (LinearLayout) view.findViewById(R.id.ll);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            blue = ContextCompat.getColor(getActivity(), R.color.blue);
            write = ContextCompat.getColor(getActivity(), R.color.write);
        }else{
            blue = getResources().getColor(R.color.blue);
            write = getResources().getColor(R.color.write);
        }
        StatusBarUtil.setColorNoTranslucent(getActivity(), blue);




        Animation scaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_top);
        address.startAnimation(scaleAnimation);
        ll.startAnimation(scaleAnimation);


        address.setMovementMethod(ScrollingMovementMethod.getInstance());





        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("gengxin");
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(getActivity(), mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }


        InitTextView();
        InitViewPager();


        return view;
    }


    private void InitTextView() {
        sales.setOnClickListener(new MyOnClickListener(0));
        //服务暂时不开放
//        service.setOnClickListener(new MyOnClickListener(1) );
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbars bar = new Snackbars(getActivity().findViewById(R.id.activity_main), getActivity().getResources().getString(R.string.hint13));
            }
        });
    }




    /**
     * 头标点击监听
     *
     * @author Administrator
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            switch (index) {
                case 0:
                    sales.setTextColor(blue);
                    service.setTextColor(write);
                    sales.setBackgroundResource(R.drawable.shape_home_check);
                    service.setBackgroundResource(R.drawable.shape_home_service);
                    break;

                case 1:
                    sales.setTextColor(write);
                    service.setTextColor(blue);
                    sales.setBackgroundResource(R.drawable.shape_home);
                    service.setBackgroundResource(R.drawable.shape_home_service_check);
                    break;


            }
            pager.setCurrentItem(index);
        }

    }

    /**
     * 初始化viewpager页
     */
    private void InitViewPager() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentSalesTask());
        fragments.add(new FragmentServiceTask());
        pager.setAdapter(new myPagerAdapter(getChildFragmentManager(),
                fragments));
        pager.setCurrentItem(0);
        pager.addOnPageChangeListener(new MyOnPageChangeListener());
    }


    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;


        public myPagerAdapter(FragmentManager childFragmentManager, List<Fragment> fragmentList) {
            super(childFragmentManager);
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
     * 为选项卡绑定监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrollStateChanged(int index) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int index) {

            /**
             * 某个选项卡选中后，title颜色改变
             */
            switch (index) {
                case 0:
                    sales.setTextColor(blue);
                    service.setTextColor(write);
                    sales.setBackgroundResource(R.drawable.shape_home_check);
                    service.setBackgroundResource(R.drawable.shape_home_service);

                    break;

                case 1:
                    sales.setTextColor(write);
                    service.setTextColor(blue);
                    sales.setBackgroundResource(R.drawable.shape_home);
                    service.setBackgroundResource(R.drawable.shape_home_service_check);

                    break;

            }
        }

    }


    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理

                adrress = intent.getStringExtra("adress");
                address.setText(adrress);


        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(adrress!=null) {
            address.setText(adrress);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(),mReceiver);
    }
}




