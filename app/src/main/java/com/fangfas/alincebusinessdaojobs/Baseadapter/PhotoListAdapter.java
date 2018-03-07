package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityPhoto;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityPhotoList;

import java.util.ArrayList;




/**
 * Created by Administrator on 2017/1/5 0005.
 * 图片列表
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.MyViewHolder>{

    private ArrayList<String>mDatas;
    private  Context  context;
    public PhotoListAdapter(ArrayList<String> mDatas, ActivityPhotoList activityPhotoList) {
        this.mDatas=mDatas;
        this.context=activityPhotoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.photo_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {

        Glide.with(context)
                .load(mDatas.get(position))
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .skipMemoryCache( true )
                .into(holder.image);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    Intent it=new Intent(context,ActivityPhoto.class);
                    it.putExtra("photo",mDatas.get(position));
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation((Activity) context,holder.image, "robot");

                    context.startActivity(it, options.toBundle());

                }else{
                    Intent  i=new Intent(context, ActivityPhoto.class);
                    i.putExtra("photo",mDatas.get(position));
                    context.startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mDatas == null?0:mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
         private ImageView image;
         private CardView cardview;


        public MyViewHolder(View view)
        {
            super(view);
            image= (ImageView) view.findViewById(R.id.image);
            cardview= (CardView) view.findViewById(R.id.cardview);
        }
    }
}




