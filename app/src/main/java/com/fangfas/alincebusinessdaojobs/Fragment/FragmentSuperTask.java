package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.fangfas.alincebusinessdaojobs.R;



/**
 * Created by Administrator on 2017/2/16 0016.
 * 一级界面任务
 */

public class FragmentSuperTask extends Fragment {
    private  View  view;
    private TextView title;
    public SVProgressHUD mSVProgressHUD;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_supertask,null);
        title= (TextView) view.findViewById(R.id.title);
        Animation scaleAnimation= AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_top);
        title.startAnimation(scaleAnimation);

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        FragmentTask task= new FragmentTask();
        transaction.add(R.id.framelayout,task).commit();

        mSVProgressHUD = new SVProgressHUD(getActivity());



        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        mSVProgressHUD.dismissImmediately();
    }
}
