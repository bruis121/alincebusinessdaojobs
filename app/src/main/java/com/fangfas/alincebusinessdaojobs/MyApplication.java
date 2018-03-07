package com.fangfas.alincebusinessdaojobs;

import java.util.LinkedList;
import java.util.List;


import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.fangfas.alincebusinessdaojobs.Tool.CrashHandler;
import com.fangfas.alincebusinessdaojobs.Tool.LocationService;
import com.baidu.mapapi.SDKInitializer;

//退出整个应用程序
public class MyApplication extends Application {
	//获取认证资料，判断是否为空
	public  static   String  GetAuth;
	public  static   String  Token="bearer bd233d55-d94e-4d93-9d8b-fa02ad68856f";
	//是否第一次登陆
	public  static  int isfirst;
	//百度地图
	public static  LocationService locationService;
	public Vibrator mVibrator;

	//经纬度
    public  static  String  Longitude="";


	 private static final String TAG = "MyApplication";


	//便与后面退出程序
	public List<Activity> mList = new LinkedList<Activity>();
	public static MyApplication instance;
	public MyApplication() {
	}
    
	

	
	public synchronized static MyApplication getInstance() {
		if (null == instance) {
			instance = new MyApplication();
		}
		return instance;
	}

	
	
	
	
	//activty退出
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null){
					activity.finish();
				    activity=null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
	}

	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
		CrashHandler crashHandler=CrashHandler.getInstance();
		//注册CrashHandler
		crashHandler.init(getApplicationContext());
		//发送以前没发送的报告
		crashHandler.sendPreviousReportsToServer();





		/***
		 * 初始化定位sdk，建议在Application中创建
		 */
		locationService = new LocationService(getApplicationContext());
		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
		SDKInitializer.initialize(getApplicationContext());




	}



}