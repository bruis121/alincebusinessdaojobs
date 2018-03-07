package com.fangfas.alincebusinessdaojobs.Httprequest;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.AppConst;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Tool.decodeUnicode;

import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/12/2 0002.
 *一级界面
 */

public class RequestMyTask {
    private StringRequest  stringRequest;
    private  Handler handler;
    private   FragmentActivity  activity;
    private  String  tokens;
    final String TYPE_UTF8_CHARSET = "charset=UTF-8";
    //where  1进行中，2已完成，3已过期
    public RequestMyTask(StringRequest  stringRequest, final FragmentActivity  activity, final Handler handler, String where, int  page) {
       this.stringRequest=stringRequest;
        this.handler=handler;
        this.activity=activity;

        try {
            tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.SALESTASKS+"&where="+where+"&page="+String.valueOf(page)+"&token="+tokens , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 1;
                msg.obj =json;
                handler.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {

                // TODO Auto-generated method stub
                try {
                    String type = response.headers.get(HTTP.CONTENT_TYPE);
                    if (type == null) {
                        type = TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    } else if (!type.contains("UTF-8")) {
                        type += ";" + TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    }
                } catch (Exception e) {
                }
                return super.parseNetworkResponse(response);



            }
        };
        SingleVolleyRequestQueue.getInstance(activity).addToRequestQueue(stringRequest);

    }




    public void getMore(String where,String page) {

        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.SALESTASKS+"&where="+where+"&page="+page+"&token="+tokens , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 2;
                msg.obj =json;
                handler.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {

                // TODO Auto-generated method stub
                try {
                    String type = response.headers.get(HTTP.CONTENT_TYPE);
                    if (type == null) {
                        type = TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    } else if (!type.contains("UTF-8")) {
                        type += ";" + TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    }
                } catch (Exception e) {
                }
                return super.parseNetworkResponse(response);



            }
        };
        SingleVolleyRequestQueue.getInstance(activity).addToRequestQueue(stringRequest);


    }

    //筛选where  1进行中，2已完成，3已过期
    public void getshort(String where, int  page){
        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.SALESTASKS+"&where="+where+"&page="+String.valueOf(page)+"&token="+tokens , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what =3;
                msg.obj =json;
                handler.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {

                // TODO Auto-generated method stub
                try {
                    String type = response.headers.get(HTTP.CONTENT_TYPE);
                    if (type == null) {
                        type = TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    } else if (!type.contains("UTF-8")) {
                        type += ";" + TYPE_UTF8_CHARSET;
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    }
                } catch (Exception e) {
                }
                return super.parseNetworkResponse(response);



            }
        };
        SingleVolleyRequestQueue.getInstance(activity).addToRequestQueue(stringRequest);
    }
}
