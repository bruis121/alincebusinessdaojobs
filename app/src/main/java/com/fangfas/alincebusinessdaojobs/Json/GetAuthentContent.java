package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/30 0030.
 * 返回的的认证资料
 */

public class GetAuthentContent {
    public   String  resumetime;
    public   String   resumecompanyname;  //公司名称
    public   String  resumefuntypename;  //职务
    public   String  resumeindustrytypename;
    public  String   resumeworkstatus;   //工作状态
    public   String   userid;
    public   String   resumerealname;   //姓名
    public   String   resumelocation;   //居住地
    public   String  resumecompanynature;  //公司性质
    public   String   resumelocationdetail;  //详细地址
    public   String   resumeidcardback;    //身份证背面
    public   String   resumeworkyear;    //工作年限
    public   String   resumefuntype;    //期望职务
    public   String   resumegender;     //性别
    public   String   resumecardtype;    //证件类型，只为身份证
    public   String   resumedisplay;    //
    public   String   resumeidcardface;   //正面身份证
    public   String   resumebirthday;    //生日
    public   String   resumemobile;   //电话号码
    public   String   fav;
    public   String  resumeidnumber;   //证件号码
    public   String   resumeemail;    //邮箱
    public   String   resumeidcardhand;   //手持身份证
    public ArrayList<Resumework> resumework;
    public  String resumestatus;
    public   Integer  resume_natrue;//第一次认证content=null 1待审核  2通过  3未通过
    public  class  Resumework{
        public   String  yearEnd;  //结束时间
        public   String  cats;   //职务
        public  String  company;  //单位名称
        public   String  yearBegin; //开始时间
        public   String   summary;  //内容
    }
}

//出生地址
