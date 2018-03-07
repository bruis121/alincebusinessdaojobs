package com.fangfas.alincebusinessdaojobs.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Baseadapter.PhotoListAdapter;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2017/1/6 0006.
 */

public class ActivityPhotoList  extends BaseActicity {
    private RecyclerView mRecyclerView;
    private ImageView returns;
    private TextView title;
    private PhotoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_photolist);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        returns= (ImageView) findViewById(R.id.returns);
        title= (TextView) findViewById(R.id.title);


        Animation scaleAnimation= AnimationUtils.loadAnimation(ActivityPhotoList.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        title.startAnimation(scaleAnimation);


        Intent i=getIntent();
        ArrayList<String> mDatas=i.getStringArrayListExtra("photo");
        if(mDatas.size()!=0) {

            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            mRecyclerView.setAdapter(adapter = new PhotoListAdapter(mDatas,ActivityPhotoList.this));
        }


        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
