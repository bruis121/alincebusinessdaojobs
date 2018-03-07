package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/2 0002.
 * 我的收藏
 */

public class SalesColelctionContent {
    public String err_Code;
    public String msg;
    public Content  content;
    public class  Content{
       public ArrayList<Contents>content;
        public   String  totalPages;
        public   String  totalElements;
        public    String  last;
        public    String  number;
        public    String   size;
        public    String   sort;
        public    String   numberOfElements;
        public   String    first;
        public class  Contents{
            public  String  trid;
            public String   user_id;
            public  String  child_task_sn;
            public  String   invest_type;
            public   String  bus_name;
            public  String   area_ids;
            public   String   person_limit;
            public   String   get_number;
            public   String   agent_price;
            public   String    area_names;
            public   String     invest_name;
            public   String  ticheng;
            public   String  danwei;
        }
    }
}