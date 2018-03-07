package com.fangfas.alincebusinessdaojobs.Json;

/**
 * Created by Administrator on 2016/12/12 0012.
 * 我的设置信息
 */

public class MySettingContent {
    public   String err_Code;
    public   String  msg;
    public   Content  content;
    public class   Content{
        public  String  funds; //钱包余额
        public  String  status; //是否验证  //1待审核   2已认证  0未提交  3审核失败
        public   String  credits; //积分
        public   String  uid;
        public   String  username;
        public  String  headimg;
        public   String  grouptitle;//会员等级

    }
}
