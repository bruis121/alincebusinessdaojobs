package com.fangfas.alincebusinessdaojobs.Json;

import com.fangfas.alincebusinessdaojobs.View.SlideList.SlideView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/17 0017.
 */

public class Jobbean {
   public  String  xml;
    public ArrayList<Res> res;
    public  class  Res{
        public  String  wid;
        public  String  uid;
        public   String  yearbegin;
        public   String  yearend;
        public   String  Company;
        public  String   cats;
        public    String  summary;
        public  String  createtime;
        public SlideView slideView;


    }
}
