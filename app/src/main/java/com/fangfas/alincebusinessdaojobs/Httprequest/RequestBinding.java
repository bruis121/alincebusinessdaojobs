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
import com.fangfas.alincebusinessdaojobs.Ui.ActivityTakenCash;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityTakenCashWay;

import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.fangfas.alincebusinessdaojobs.R.id.cats;

/**
 * Created by Administrator on 2017/2/15 0015.
 * 支付方式绑定
 */

public class RequestBinding {
    final String TYPE_UTF8_CHARSET = "charset=UTF-8";

    public  void  BINDING(StringRequest stringRequest, final ActivityTakenCashWay activity, final Handler hander, final String real_name, final String account, final String withdraw_type, final String bank_name){

        stringRequest = new StringRequest(com.android.volley.Request.Method.POST,AppConst.Main+AppConst.BINDING, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect = decodeUnicode.decodeUnicode(response);
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
                map.put("real_name",real_name);//真实姓名
                map.put("withdraw_type",withdraw_type);//1代表支付宝  2代表微信  3代表银行卡
                map.put("account",  account);  //账号
                map.put("bank_name",bank_name);//开户行
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


    public  void  IsBinding1(StringRequest stringRequest, final String withdraw_type, final Handler hander, final ActivityTakenCash activity){
        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.ISBINGING, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect = decodeUnicode.decodeUnicode(response);
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
                map.put("withdraw_type",withdraw_type);//1代表支付宝  2代表微信  3代表银行卡
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

    public  void  IsBinding2(StringRequest stringRequest, final String withdraw_type, final Handler hander, final ActivityTakenCash activity){
        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.ISBINGING, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 3;
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
                map.put("withdraw_type",withdraw_type);//1代表支付宝  2代表微信  3代表银行卡
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

    public  void  IsBinding3(StringRequest stringRequest, final String withdraw_type, final Handler hander, final ActivityTakenCash activity){
        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.ISBINGING, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 4;
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
                map.put("withdraw_type",withdraw_type);//1代表支付宝  2代表微信  3代表银行卡
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

    //提现
    public void WithdrawCash(StringRequest stringRequest, final String withdraw_type, final String money, final Handler hander, final ActivityTakenCash activity) {
        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main+AppConst.WITHDRWCASH, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String collect = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 5;
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
                map.put("withdraw_type",withdraw_type);//1代表支付宝  2代表微信  3代表银行卡
                 map.put("money",money);
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

    //余额
    public void UseMoney(StringRequest stringRequest, final Handler hander, final Activity activity){
      String tokens = null;
       try {
            if(MyApplication.Token!=null&&!"".equals(MyApplication.Token)) {
                tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.USEMONEY+"?token="+ tokens, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String  data=decodeUnicode.decodeUnicode(response);
                Message  msg=new Message();
                msg.what=6;
                msg.obj=data;
                hander.sendMessage(msg);

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

    public void BankList(StringRequest stringRequest, final Handler hander, final ActivityTakenCashWay activity) {
        String tokens = null;
        try {
            if(MyApplication.Token!=null&&!"".equals(MyApplication.Token)) {
                tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.BANKLIST+"?token="+ tokens, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String  data=decodeUnicode.decodeUnicode(response);
                Message  msg=new Message();
                msg.what=7;
                msg.obj=data;
                hander.sendMessage(msg);

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
