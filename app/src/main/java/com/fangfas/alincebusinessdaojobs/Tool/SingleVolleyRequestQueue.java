package com.fangfas.alincebusinessdaojobs.Tool;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.Volley;

import java.io.File;

/**
 * 作    者: lovec.
 * 创建日期: 2016/12/26.
 * 描    述:Volley 单例模式 全局调用
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */

public class SingleVolleyRequestQueue {
    //私有化属性
    private static SingleVolleyRequestQueue singleQueue;
    private RequestQueue requestQueue;
    private static Context context;


    //私有化构造
    private SingleVolleyRequestQueue(Context context){
        this.context=context;
        requestQueue=getRequestQueue();
    }
    //提供获得请求队列的方法
    private RequestQueue  getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context);
        }
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        File cacheDir = new File(context.getCacheDir(), "volley");
        DiskBasedCache cache = new DiskBasedCache(cacheDir);
//        requestQueue.start();

        // clear all volley caches.
        requestQueue.add(new ClearCacheRequest(cache, null));
        return requestQueue;
    }
    //提供获取类对象的方法
    public static synchronized SingleVolleyRequestQueue getInstance(Context context){   //synchronized加锁防止并发
        if(singleQueue==null){
            singleQueue=new SingleVolleyRequestQueue(context);
        }
        return  singleQueue;
    }
    public <T> void  addToRequestQueue(Request<T> req){
//        requestQueue.add(req);  //防止被回收造成空指针异常，所以一般不用
        getRequestQueue().add(req);
    }
    public <T> void cancelToRequestQueue(Request<T> req)
    {
        if (req!= null)
        {
          getRequestQueue().cancelAll(req);
        }
    }
    //使用SingleVolleyRequestQueue.getInstance(this).addToRequestQueue(sr);
}
