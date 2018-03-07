package com.fangfas.alincebusinessdaojobs.Httprequest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.AppConst;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Tool.decodeUnicode;

import org.apache.http.protocol.HTTP;

/**
 * Created by Administrator on 2016/12/12 0012.
 * 物流查询
 */

public class RequestLogistical {
    final String TYPE_UTF8_CHARSET = "charset=UTF-8";
    public RequestLogistical(StringRequest stringRequest, final Activity activity, final Handler handler, String gtid) {

            stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                    AppConst.Main+AppConst.LOGISTICAL+"?gtid="+gtid, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String json = decodeUnicode.decodeUnicode(response);
                    Message msg = new Message();
                    msg.what=1;
                    msg.obj=json;
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






        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.LOOKINVOICES+"?gtid="+gtid, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what=2;
                msg.obj=json;
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

