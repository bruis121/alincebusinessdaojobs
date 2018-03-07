package com.fangfas.alincebusinessdaojobs.Httprequest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/17 0017.
 * 经历
 */

public class RequestJobEXperience {
    Handler hander;
    StringRequest stringRequest;
    Activity  activity;
    private   String tokens;
    final String TYPE_UTF8_CHARSET = "charset=UTF-8";
    public RequestJobEXperience(StringRequest stringRequest, Activity  activity, Handler hander) {
        this.hander=hander;
        this.stringRequest=stringRequest;
        this.activity=activity;
        try {
            if(MyApplication.Token!=null&&!"".equals(MyApplication.Token)) {
                tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    //提交
    public  void submit(final String company, final String cats, final String yearbegin, final String yearend, final String summary){

        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.ADDEXPERIENCE, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect =decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 2;
                msg.obj =collect;
                hander.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("cats",  cats);  //职位
                map.put("company",company);   //公司
                map.put("yearBegin",yearbegin); //开始时间
                map.put("yearEnd",yearend);//结束时间
                map.put("summary",summary); //描述
                map.put("token", MyApplication.Token);
                return map;
            }

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

    //修改资料
    public void revisions(final String  id, final String company, final String cats, final String yearbegin, final String yearend, final String summary) {

        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.EDITEXPERIENCE, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect =decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 2;
                msg.obj =collect;
                hander.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("wid",id);
                map.put("cats",  cats);  //职位
                map.put("company",company);   //公司
                map.put("yearBegin",yearbegin); //开始时间
                map.put("yearEnd",yearend);//结束时间
                map.put("summary",summary); //描述
                map.put("token", MyApplication.Token);
                return map;
            }

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

    //删除工作经历
    public void delete(final String wid) {

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.DeletePERIENCE, new com.android.volley.Response.Listener<String>() {
            @Override
            public
            void onResponse(String response) {
                String collect =decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 10;
                msg.obj =collect;
                hander.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("wid",wid);
                map.put("token", MyApplication.Token);
                return map;
            }

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
