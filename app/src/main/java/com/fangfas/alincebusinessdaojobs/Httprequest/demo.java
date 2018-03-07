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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/3 0003.
 */

public class demo {

    public void d(final Handler handler, FragmentActivity activity) {
        final String TYPE_UTF8_CHARSET = "charset=UTF-8";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "http://appmall.foway.com/ecmobile/?url=goods/desc", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 2;
                msg.obj =collect;
                handler.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("goods_id", "10");
                return map;
            }

            @Override
            protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse
                                                                                       response) {

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
