package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/14 0014.
 *我的任务---账户
 */

public class AccountContent {
    public  String  err_Code;
    public  String  msg;
    public  Content  content;
    public   class  Content{
        public  String  head_photo;
        public   String  real_name;
        public   String  tel;
        public   String  account;
        public  String  zhaoshangshuliang;
        public  String  zhaoshangjine;
        public  String  tichengjine;
        public ArrayList<Account_list>account_list;
        public  class  Account_list{
            public  String   create_time;
            public   String  task_name;
            public  String   per_money;
        }
    }
}
