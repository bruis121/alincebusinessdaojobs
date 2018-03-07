package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/15 0015.
 * 我的设置--我的任务--下级顶部
 */

public class SubordinateContent {
    public   String  err_Code;
    public   String  msg;
    public Context content;



    public   class   Context{
        public  String  head_photo;
        public  String  real_name;
        public  String  tel;
        public   String  account;
        public   String  zhaoshangshuliang;
        public   String  zhaoshangjine;
        public  String   tichengjine;
        public ArrayList<Account_list>  account_list;
        public  class   Account_list implements  Comparable<Account_list>{
            public  String  create_time;
            public String  task_name;
            public String  real_name;
            public  String  per_money;
            public  String  create_time1;

            @Override
            public int compareTo(Account_list account_list) {
                return Integer.parseInt(this.create_time) - Integer.parseInt(account_list.create_time);
            }
        }
    }
}
