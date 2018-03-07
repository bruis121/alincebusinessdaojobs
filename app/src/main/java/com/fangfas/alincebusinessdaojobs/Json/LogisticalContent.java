package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class LogisticalContent {
    public  String  err_Code;
    public  String  msg;
    public   Content  content;
    public   class   Content{
        public  String  message;
        public   String  nu;  //物流单号
        public   String  ischeck;
        public   String  condition;
        public   String  com;   //物流单位
        public   String  status;
        public  String  state;
        public   String  comcontact; //物流电话
        public   String  comurl;
        public ArrayList<Datas>  data;
       public  class   Datas{
           public  String  time;
           public   String context;
           public   String  location;
       }

    }
}
