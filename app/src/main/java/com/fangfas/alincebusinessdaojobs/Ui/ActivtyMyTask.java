package com.fangfas.alincebusinessdaojobs.Ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Method.BaseFragmentActivty;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentAccount;
import com.fangfas.alincebusinessdaojobs.Fragment.FragmentSubordinate;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/11/9 0009.
 * 我的任务
 */

public class ActivtyMyTask extends BaseFragmentActivty implements View.OnClickListener{
    private ImageView returns;
    //账户
    private TextView account_tx;
    //下级
    private TextView subordinate_tx;
    private ViewPager  pager;
    private LinearLayout  ll;
    private  int blue,write;
    // tab页面列表
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_mytask);
        returns= (ImageView) findViewById(R.id.returns);
        account_tx= (TextView) findViewById(R.id.account_tx);
        subordinate_tx= (TextView) findViewById(R.id.subordinate_tx);
        pager= (ViewPager) findViewById(R.id.page);
        ll= (LinearLayout) findViewById(R.id.ll);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            blue= getResources().getColor(R.color.blue,null);
        }else{
            blue= getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);

        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivtyMyTask.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        ll.startAnimation(scaleAnimation);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            write= ContextCompat.getColor(ActivtyMyTask.this,R.color.write);
        }else{
            write=getResources().getColor(R.color.write);
        }


        InitTextView();
        InitViewPager();

        returns.setOnClickListener(this);

    }


    private void InitTextView() {
        account_tx.setOnClickListener(new MyOnClickListener(0));
        subordinate_tx.setOnClickListener(new MyOnClickListener(1) );
    }

    /**
     * 初始化viewpager页
     */
    private void InitViewPager() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentAccount());
        fragments.add(new FragmentSubordinate());
        pager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        pager.setCurrentItem(0);
        pager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;





        public myPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
            super(supportFragmentManager);
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
                    account_tx.setTextColor(blue);
                    subordinate_tx.setTextColor(write);
                    account_tx.setBackgroundResource(R.drawable.shape_home_check);
                    subordinate_tx.setBackgroundResource(R.drawable.shape_home_service);
                    break;

                case 1:
                    account_tx.setTextColor(write);
                    subordinate_tx.setTextColor(blue);
                    account_tx.setBackgroundResource(R.drawable.shape_home);
                    subordinate_tx.setBackgroundResource(R.drawable.shape_home_service_check);
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
                    account_tx.setTextColor(blue);
                    subordinate_tx.setTextColor(write);
                    account_tx.setBackgroundResource(R.drawable.shape_home_check);
                    subordinate_tx.setBackgroundResource(R.drawable.shape_home_service);

                    break;

                case 1:
                    account_tx.setTextColor(write);
                    subordinate_tx.setTextColor(blue);
                    account_tx.setBackgroundResource(R.drawable.shape_home);
                    subordinate_tx.setBackgroundResource(R.drawable.shape_home_service_check);
                    break;

            }
        }

    }

    @Override
    public void onClick(View view) {
        if(R.id.returns==view.getId()){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                finishAfterTransition();
            }
            else{

                finish();
            }
        }
    }

}
