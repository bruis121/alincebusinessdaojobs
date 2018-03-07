package com.fangfas.alincebusinessdaojobs.Tool;

import com.fangfas.alincebusinessdaojobs.Json.ChinaContent;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/20 0020.
 * 省市区数据获取
 */

public class initDataProceBean   {
    public ArrayList<String> options1Items = new ArrayList<String>();
    public ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    public ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    public   void initDataProceBean(ActivityAuthentication activityAuthentication){
    try {
        //解析json数据
        InputStream is = activityAuthentication.getResources().getAssets().open("city.json");

        int available = is.available();

        byte [] b=new byte[available];
        int read = is.read(b);
        //注意格式，utf-8 或者gbk  否则解析出来可能会出现乱码
        String json=new String(b,"UTF-8");


        Gson gson= new Gson();
        ChinaContent china = gson.fromJson(json, ChinaContent.class);
        ArrayList<ChinaContent.Province> citylist = china.citylist;
        //======省级
        for (ChinaContent.Province province: citylist
                ) {
            String provinceName = province.p;

            // System.out.println("provinceName==="+provinceName);
            ArrayList<ChinaContent.Province.Area> c = province.c;
            //选项1
            options1Items.add(provinceName);
            ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
            //区级
            //选项2

            ArrayList<String> options2Items_01=new ArrayList<String>();
            if (c!=null) {
                for (ChinaContent.Province.Area area : c
                        ) {
                    //System.out.println("area------" + area.n + "------");

                    options2Items_01.add(area.n);
                    ArrayList<ChinaContent.Province.Area.Street> a = area.a;
                    ArrayList<String> options3Items_01_01=new ArrayList<String>();

                    //县级
                    if (a!=null) {
                        for (ChinaContent.Province.Area.Street street : a
                                ) {
                            // System.out.println("street/////" + street.s);
                            options3Items_01_01.add(street.s);
                        }
                        options3Items_01.add(options3Items_01_01);
                    }else{
                        //县级为空的时候
                        options3Items_01_01.add("");
                        options3Items_01.add(options3Items_01_01);
                    }
                }
                options2Items.add(options2Items_01);
            }else{
                //区级为空的时候  国外
                options2Items_01.add("");
                options2Items.add(options2Items_01);
            }
            options3Items.add(options3Items_01);
            ArrayList<String> options3Items_01_01=new ArrayList<String>();
            options3Items_01_01.add("");
            options3Items_01.add(options3Items_01_01);
        }


    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
