package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/5 0005.
 * 合同详情
 */

public class ContractContent {
    public  String  err_Code;
    public   String  msg;
    public   Content  content;
    public   class   Content{
        public  String  id;
        public   String  gtid; //任务领取id
        public  String  bank_account;//银行账户
        public  String   account_holder; //开户人姓名
        public   String  bank_name; //开户行
        public   String  rem_money;   //打款金额

        public ArrayList<String> rem_pz; //打款凭证
        public   String  remittance_time; //打款时间
        public  ArrayList<String>   con_photo; //合同照片
        public  String   con_money; //合同金额
        public  String    sign_time; //签署时间
        public   String  cus_company;//客户公司
        public   String   cus_name;  //客户姓名
        public   String   cus_tel;  //客户联系电话
        public   String  cus_address; //客户地址
        public   String  consignee;  //收货人
        public   String  consignee_tel; //收货联系方式
        public  String   con_address; //收货地址
        public  String   trid;  //地区任务id
        public ArrayList<String> remPz_url;  //凭证图片
        public   ArrayList<String> conPhoto_url; //合同图片

    }
}
