package com.fangfas.alincebusinessdaojobs.View;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.DensityUtil;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityPhoto;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class CommodityImage extends RelativeLayout {
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private ViewPager vpAd;
    private LinearLayout llIndexContainer;
    private Context context;




    private List<ImageView> ivList;

    private String[]  data;




    public CommodityImage(Context context) {
        super(context);

    }
    public CommodityImage(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);


    }
    public CommodityImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

    }


    private void dealWithTheView(String[] list) {
        ivList.clear();
        int size = list.length;
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list[0]));
        }

        myHeaderAdAdapter photoAdapter = new myHeaderAdAdapter(ivList);
        vpAd.setAdapter(photoAdapter);


        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
    }
    // 添加指示图标
    private void addIndicatorImageViews(int size) {
        llIndexContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            if (i != 0) {
                lp.leftMargin = DensityUtil.dip2px(context, 7);
            }
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(R.drawable.xml_round_blue_grey_sel);
            iv.setEnabled(false);
            if (i == 0) {
                iv.setEnabled(true);
            }
            llIndexContainer.addView(iv);
        }
    }



    // 创建要显示的ImageView
    private ImageView createImageView(String url) {
        ImageView imageView = new ImageView(context);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .into(imageView);
        return imageView;
    }

    // 为ViewPager设置监听器
    private void setViewPagerChangeListener(final int size) {
        vpAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                if (ivList != null && ivList.size() > 0) {
                    int newPosition = position % size;
                    for (int i = 0; i < size; i++) {
                        llIndexContainer.getChildAt(i).setEnabled(false);
                        if (i == newPosition) {
                            llIndexContainer.getChildAt(i).setEnabled(true);
                        }
                    }
                }

            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }


        });
    }




    public void setdata(Activity activity, String[] datav) {
        this.data = datav;
        this.context=activity;
        LayoutInflater.from(context).inflate(R.layout.commodity_image, this);

        vpAd= (ViewPager) findViewById(R.id.vp_ad);
        llIndexContainer= (LinearLayout) findViewById(R.id.ll_index_container);

        ivList = new ArrayList<>();
        if(data.length!=0) {
            dealWithTheView(data);

        }


    }




    class   myHeaderAdAdapter extends PagerAdapter {
        List<ImageView> ivList;

        public myHeaderAdAdapter(List<ImageView> ivList) {
            this.ivList=ivList;
        }

        @Override
        public int getCount() {
            return ivList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(ivList.get(position));
            return ivList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }


    }
}


