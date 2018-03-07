package com.fangfas.alincebusinessdaojobs.View;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public abstract class PFragment extends Fragment {
    protected boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible =true;
            onVisible();
        }else {
            isVisible =false;
            onInvisible();
        }
    }
    protected void onVisible(){
        bruisLoad();
    }
    protected abstract void bruisLoad();
    protected void onInvisible(){}
}
