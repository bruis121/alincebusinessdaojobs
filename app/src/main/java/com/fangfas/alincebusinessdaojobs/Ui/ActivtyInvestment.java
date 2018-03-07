package com.fangfas.alincebusinessdaojobs.Ui;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.fangfas.alincebusinessdaojobs.Fragment.FragmentInRule;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentInStep;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentInDetail;
import com.fangfas.alincebusinessdaojobs.Method.BaseFragmentActivty;
import com.fangfas.alincebusinessdaojobs.R;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/12/15 0015.
 * 招商页面
 */

public class ActivtyInvestment extends BaseFragmentActivty {

    private ViewPager viewpager;
    private TextView btn_tab1;
    private TextView btn_tab2;
    private TextView btn_tab3;
    private ImageView cursor;

    private ImageView returns;
    private TextView title;

    // 动画图片偏移量
    private int offset = 0;
    // 当前页卡编号
    private int currIndex = 0;
    // 动画图片宽度
    private int bmpW;
    private int selectedColor, unSelectedColor;
    // tab页面列表
    private List<Fragment> fragments;
    /**
     * 页卡总数
     */
    private static final int pagesize = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_investment);
        viewpager= (ViewPager) findViewById(R.id.viewpager);
        btn_tab1= (TextView) findViewById(R.id.btn_tab1);
        btn_tab2= (TextView) findViewById(R.id.btn_tab2);
        btn_tab3= (TextView) findViewById(R.id.btn_tab3);
        cursor= (ImageView) findViewById(R.id.cursor);
        returns= (ImageView) findViewById(R.id.returns);
        title= (TextView) findViewById(R.id.title);


        int blue= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            blue = getResources().getColor(R.color.blue,null);
        }else{
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);


        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivtyInvestment.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        title.startAnimation(scaleAnimation);


        initView();

        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        selectedColor = getResources()
                .getColor(R.color.blue);
        unSelectedColor = getResources().getColor(
                R.color.hui2);
        InitImageView();
        InitTextView();
        InitViewPager();


    }


    /**
     * 初始化头标
     */
    private void InitTextView() {
        btn_tab1.setTextColor(selectedColor);
        btn_tab2.setTextColor(unSelectedColor);
        btn_tab3.setTextColor(unSelectedColor);


        btn_tab1.setText("任务详情");
        btn_tab2.setText("任务步骤");
        btn_tab3.setText("提成规则");

        btn_tab1.setOnClickListener(new MyOnClickListener(0));
        btn_tab2.setOnClickListener(new MyOnClickListener(1));
        btn_tab3.setOnClickListener(new MyOnClickListener(2));

    }

    /**
     * 初始化viewpager页
     */
    private void InitViewPager() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentInDetail());
        fragments.add(new FragmentInStep());
        fragments.add(new FragmentInRule());
        viewpager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(4);
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
    }



    /**
     * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果
     */
    private void InitImageView() {



        // 获得滑动时下面横线也跟着滑动的横线宽度
        BitmapFactory
                .decodeResource(getResources(), R.mipmap.tab_selected_bg)
                .getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 获取分辨率宽度
        int screenw = dm.widthPixels;
        /**
         * 计算偏移量 （屏幕宽度/页卡总数-图片实际宽度）/2=偏移量
         */
        offset = (screenw / pagesize - bmpW) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        /**
         * 设置动画的初始位置
         */
        cursor.setImageMatrix(matrix);

    }


    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
     * 为选项卡绑定监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * 页卡1->页卡2偏移量----------------------------------------------------------
         * -----------------------------------------
         */
        int one = offset * 2 + bmpW;
        /**
         * 页卡1->页卡3的偏移了量
         */
        int two = one * 2;
        int trhee=one * 3;

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
             * 选项卡滑动动画
             */
            Animation animation = new TranslateAnimation(one * currIndex, one
                    * index, 0, 0);
            currIndex = index;
            // 图片停在动画结束位置
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor.startAnimation(animation);
            /**
             * 某个选项卡选中后，title颜色改变
             */
            switch (index) {
                case 0:
                    btn_tab1.setTextColor(selectedColor);
                    btn_tab2.setTextColor(unSelectedColor);
                    btn_tab3.setTextColor(unSelectedColor);

                    break;

                case 1:
                    btn_tab1.setTextColor(unSelectedColor);
                    btn_tab2.setTextColor(selectedColor);
                    btn_tab3.setTextColor(unSelectedColor);

                    break;
                case 2:
                    btn_tab1.setTextColor(unSelectedColor);
                    btn_tab2.setTextColor(unSelectedColor);
                    btn_tab3.setTextColor(selectedColor);

                    break;

            }
        }

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
                    btn_tab1.setTextColor(selectedColor);
                    btn_tab2.setTextColor(unSelectedColor);
                    btn_tab3.setTextColor(unSelectedColor);
                    break;

                case 1:
                    btn_tab1.setTextColor(unSelectedColor);
                    btn_tab2.setTextColor(selectedColor);
                    btn_tab3.setTextColor(unSelectedColor);


                    break;
                case 2:
                    btn_tab1.setTextColor(unSelectedColor);
                    btn_tab2.setTextColor(unSelectedColor);
                    btn_tab3.setTextColor(selectedColor);

                    break;
                case  3:
                    btn_tab1.setTextColor(unSelectedColor);
                    btn_tab2.setTextColor(unSelectedColor);
                    btn_tab3.setTextColor(unSelectedColor);

            }
            viewpager.setCurrentItem(index);
        }

    }
}
