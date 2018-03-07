package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;
import com.jaeger.library.StatusBarUtil;


import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/12/5 0005.
 * 图片查看
 */

public class ActivityPhoto extends BaseActicity{
    private PhotoView mage;
    private TextView  title;
    private ImageView  returns;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_photo);
        mage= (PhotoView) findViewById(R.id.my_image);
        title= (TextView) findViewById(R.id.title);
        returns= (ImageView) findViewById(R.id.returns);

        int blue= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            blue=ContextCompat.getColor(this,R.color.blue);
        }else{
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);

        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivityPhoto.this, R.anim.fade_in_top);
        title.startAnimation(scaleAnimation);
        returns.startAnimation(scaleAnimation);

        Intent  intent=getIntent();
        String  photo=intent.getStringExtra("photo");

        Glide.with(ActivityPhoto.this)
                .load(photo)
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .skipMemoryCache( true )
                .into(mage);

        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    finishAfterTransition();
                }
                else{

                    finish();
                }
            }
        });
    }



}
