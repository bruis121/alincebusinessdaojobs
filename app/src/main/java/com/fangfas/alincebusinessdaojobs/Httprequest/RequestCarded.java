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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/16 0016.
 * 获取头部信息
 */

public class RequestCarded {
       OkHttpClient  client=new OkHttpClient();
       private  Handler hander;
        private  StringRequest stringRequest;
        private   Activity activity;
       private   String tokens;
      final String TYPE_UTF8_CHARSET = "charset=UTF-8";
    //获取认证资料内选择信息
    public RequestCarded(StringRequest stringRequest, Activity activity, final Handler hander) {
        this.hander=hander;
        this.stringRequest=stringRequest;
        this.activity=activity;

    }

    public void getHeader(StringRequest stringRequest, final Activity activity) {

        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.Main+AppConst.GETHDEAR, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 2;
                msg.obj =json;
                hander.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(!"".equals(error.getMessage())) {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
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




    //正面身份证
    public void getFace(String header) {

        //参数类型
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        //正面
        File file = new File("sdcard/android/cache", "face.png");


        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file))
                .addFormDataPart("type","face")
                .build();

        Request  request = new Request.Builder().url(AppConst.CARD).post(body).header("Authorization",header).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                if(!"".equals(e.getMessage())) {
                    Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String collect =response.body().string();
                Message msg = new Message();
                msg.what = 3;
                msg.obj =decodeUnicode.decodeUnicode(collect);
                hander.sendMessage(msg);
            }

        });

    }

    //手持处理
    public void getArmed_With_Knives(String header) {
        //参数类型
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        //手持
        File file = new File("sdcard/android/cache", "Knives.png");


        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file))
                .addFormDataPart("type","hand")
                .build();

        Request  request = new Request.Builder().url(AppConst.CARD).post(body).header("Authorization",header).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                if(!"".equals(e.getMessage())) {
                    Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String collect =response.body().string();
                Message msg = new Message();
                msg.what = 4;
                msg.obj =decodeUnicode.decodeUnicode(collect);
                hander.sendMessage(msg);
            }

        });

    }

    //反面处理
    public void getInverse(String header) {
        //参数类型
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        //背面
        File file = new File("sdcard/android/cache", "inverse.png");

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file))
                .addFormDataPart("type","back")
                .build();

        Request  request = new Request.Builder().url(AppConst.CARD).post(body).header("Authorization",header).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                if(!"".equals(e.getMessage())) {
                    Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String collect =response.body().string();
                Message msg = new Message();
                msg.what = 5;
                msg.obj =decodeUnicode.decodeUnicode(collect);
                hander.sendMessage(msg);
            }

        });

    }


    //上传信息                i电话，ii短信，iii姓名，iiii出生日期，i5性别，i6公司名称，i7公司性质,i8 职务,i9工作年限,i10工作状态，i11 详细地址,i12 所在地区,i13证件号码,i14邮箱,i15 工作经验,i16正面路径，i17 背面路径，i18手持
    public void getCommit(final String i, final String  ii, final String iii, final String  iiii, final String i5, final String i6, final String i7, final String  i8, final String i9, final String  i10, final String i11 , final String i12, final String i13, final String i14, final String i15, final String i16, final String  i17, final String i18) {
        final String TYPE_UTF8_CHARSET = "charset=UTF-8";
        try {
            tokens = URLEncoder.encode(MyApplication.Token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stringRequest = new StringRequest(com.android.volley.Request.Method.POST, AppConst.Main + AppConst.AUTHENTCATION, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String data =decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 6;
                msg.obj =data;
                hander.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(!"".equals(error.getMessage())) {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("resumemobile", i);  //电话号码
                map.put("dxyzm", ii);//短信验证
                map.put("resumerealname",iii);  //姓名
                map.put("resumebirthday",iiii);//出生日期
                map.put("resumegender",i5);//性别
                map.put("resumecompanyname",i6);//公司名称
                map.put("resumecompanynature",i7);//公司性质
                map.put("resumefuntype",i8);//职务
                map.put("resumeworkyear",i9);//工作年限
                map.put("resumeworkstatus",i10);//工作状态
                map.put("resumelocation",i12); //所在地区
                map.put("resumelocationdetail",i11);//详细地址
                map.put("resumeidnumber",i13);//证件号码
                map.put("resumeemail",i14);//邮箱
                map.put("resumework",i15);//工作经验
                map.put("resumeidcardface",i16);//身份证正面路径
                map.put("resumeidcardback",i17);//背面路径
                map.put("resume_idcard_hand",i18);//手持
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

    public void Auchoose() {
        stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                AppConst.GetCHOOSE, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = decodeUnicode.decodeUnicode(response);
                Message msg = new Message();
                msg.what = 7;
                msg.obj =json;
                hander.sendMessage(msg);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(!"".equals(error.getMessage())) {
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                }
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
