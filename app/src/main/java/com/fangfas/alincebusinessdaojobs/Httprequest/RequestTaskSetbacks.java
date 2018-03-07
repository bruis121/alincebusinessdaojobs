package com.fangfas.alincebusinessdaojobs.Httprequest;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
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
 * Created by Administrator on 2016/12/8 0008.
 * 兼职人员进度
 */

public class RequestTaskSetbacks {
   private  StringRequest  stringRequest;
    private  FragmentActivity activity;
    private  String gtid;
    private Handler handler;
    final String TYPE_UTF8_CHARSET = "charset=UTF-8";
    private String tokens;

 //顶部
    public RequestTaskSetbacks(StringRequest  stringRequest, final FragmentActivity activity, final Handler handler, String gtid) {
        this.stringRequest=stringRequest;
        this.activity=activity;
        this.gtid=gtid;
        this.handler=handler;

        try {
            if(MyApplication.Token!=null&&!"".equals(MyApplication.Token)) {
                tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.TASKSETBACKS+"?gtid="+ gtid+"&token="+tokens, new com.android.volley.Response.Listener<String>() {
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



        //中间部分
        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.TASKSETABCKSCONTENT+"?gtid="+ gtid+"&token="+tokens, new com.android.volley.Response.Listener<String>() {
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





        //内容状态部分

        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.TASKSETABCKS3+"?gtid="+ gtid+"&token="+tokens, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 3;
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

    //放弃任务
    public void Abandoned() {



        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,AppConst.Main+AppConst.ABANDONED, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 4;
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("gtid",gtid);
                map.put("token",MyApplication.Token);
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
