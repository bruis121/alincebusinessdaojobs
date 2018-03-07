package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/29 0029.
 * 首页销售
 */

public class HomeSalesContent {
    public   String  err_Code;
    public  String  msg;
    public  Content   content;
    public  class  Content{
        public  String sort;
        public  String  number;
        public  String  first;
        public  String  size;
        public   String  totalElements;
        public  String  totalPages;
        public  String  last;
        public  String  numberOfElements;
        public ArrayList<Contents>content;
        public  class  Contents{
            public  String  gtid;
            public  String  trid;
            public   String  area_ids;
            public  String  child_task_sn;
            public  String person_limit;
            public  String  task_name;
            public  String  name;
            public   String  flag;
            public  String  region_name;
            public  String  zhaoshangrenshu;
            public  String  goods_name;
            public  String  ticheng;
            public   String  danwei;
        }
    }
}
