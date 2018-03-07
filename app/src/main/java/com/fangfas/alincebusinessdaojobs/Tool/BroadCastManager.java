package com.fangfas.alincebusinessdaojobs.Tool;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Administrator on 2017/1/7 0007.
 * 广播类
 */

public class BroadCastManager {
    private static BroadCastManager broadCastManager = new BroadCastManager();

    public static BroadCastManager getInstance() {
        return broadCastManager;
    }


    public void registerReceiver(Activity activity,
                                 BroadcastReceiver receiver, IntentFilter filter) {
        activity.registerReceiver(receiver, filter);
    }


    public void unregisterReceiver(Activity activity,
                                   BroadcastReceiver receiver) {
        activity.unregisterReceiver(receiver);
    }


    public void sendBroadCast(Activity activity, Intent intent) {
        activity.sendBroadcast(intent);
    }

}
