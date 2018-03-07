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
 * Created by Administrator on 2016/12/15 0015.
 * 获取短信
 */

public class RequestSMS {

    private  String  tokens;
    public RequestSMS(StringRequest stringRequest, final Activity activity, final Handler hander, final String phone) {
    final String TYPE_UTF8_CHARSET = "charset=UTF-8";
    try {
        if(MyApplication.Token!=null&&!"".equals(MyApplication.Token)) {
            tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
        }
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.HUOQUSMS, new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            String collect =new decodeUnicode().decodeUnicode(response);
            Message msg = new Message();
            msg.what = 1;
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
            map.put("phone", phone);  //电话号码
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
