package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.View.HomePopwindow;
import com.fangfas.alincebusinessdaojobs.View.NoScrollViewPager;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/11/8 0008.
 * 收藏
 */

public class FragmentCollection extends Fragment  implements View.OnClickListener{
    View  view;
    private NoScrollViewPager pager;
    private TextView  sales_collection;
    private TextView service_collection;

    private LinearLayout  ll;
    private  int  blue;
    private int  write;
    private HomePopwindow popwindow;
    // tab页面列表
    private List<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_collection,null);
        pager= (NoScrollViewPager) view.findViewById(R.id.pager);
        sales_collection= (TextView) view.findViewById(R.id.sales_collection);
        service_collection= (TextView) view.findViewById(R.id.service_collection);
        ll= (LinearLayout) view.findViewById(R.id.ll);

        blue= getResources().getColor(R.color.blue);
        write=getResources().getColor(R.color.write);
        StatusBarUtil.setColorNoTranslucent(getActivity(), blue);


        Animation scaleAnimation= AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_top);
        ll.startAnimation(scaleAnimation);


        InitTextView();
        InitViewPager();



        return view;
    }


    /**
         * 初始化viewpager页
         */
        private void InitViewPager() {
            fragments = new ArrayList<Fragment>();
            fragments.add(new FragmentSalesCollection());
            fragments.add(new FragmentSerCollection());
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


    private void InitTextView() {
        sales_collection.setOnClickListener(new MyOnClickListener(0));
        //此功能暂未开放
//        service_collection.setOnClickListener(new MyOnClickListener(1) );
            service_collection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbars  bar=new Snackbars(getActivity().findViewById(R.id.activity_main),getActivity().getResources().getString(R.string.hint13));
                }
            });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }

    }

    private void popwin(TextView text) {
//        popwindow=new HomePopwindow(getActivity(),text, time);
//        popwindow.showAsDropDown(text);
    }

    /**
     * 头标点击监听
     *
     * @author Administrator
     *
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
                    sales_collection.setTextColor(blue);
                    service_collection.setTextColor(write);
                    sales_collection.setBackgroundResource(R.drawable.shape_home_check);
                    service_collection.setBackgroundResource(R.drawable.shape_home_service);
                    break;

                case 1:
                    sales_collection.setTextColor(write);
                    service_collection.setTextColor(blue);
                    sales_collection.setBackgroundResource(R.drawable.shape_home);
                    service_collection.setBackgroundResource(R.drawable.shape_home_service_check);
                    break;


            }
            pager.setCurrentItem(index);
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
                    sales_collection.setTextColor(blue);
                    service_collection.setTextColor(write);
                    sales_collection.setBackgroundResource(R.drawable.shape_home_check);
                    service_collection.setBackgroundResource(R.drawable.shape_home_service);

                    break;

                case 1:
                    sales_collection.setTextColor(write);
                    service_collection.setTextColor(blue);
                    sales_collection.setBackgroundResource(R.drawable.shape_home);
                    service_collection.setBackgroundResource(R.drawable.shape_home_service_check);
                    break;

            }
        }

    }

}
