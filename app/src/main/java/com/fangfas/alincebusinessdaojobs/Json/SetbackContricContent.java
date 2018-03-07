package com.fangfas.alincebusinessdaojobs.Json;

/**
 * Created by Administrator on 2016/12/8 0008.
 * 兼职人员进度中间部分--意向客户
 */

public class SetbackContricContent {
    public  String   err_Code;
    public   String  msg;
    public   Content  content;
    public   class   Content{
        public  String   cus_company;
        public   String  cus_name;
        public   String  cus_tel;
        public   String   cus_address;
    }

}
