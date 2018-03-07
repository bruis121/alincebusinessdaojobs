package com.fangfas.alincebusinessdaojobs.Json;

/**
 * Created by Administrator on 2016/11/30 0030.
 * 详情页
 *
 */

public class DetailsContent {
     public   String  err_Code;
     public  String  msg;
     public  Content  content;
    public  class  Content{
//        public   String  area_ids;
//        public   String  get_number; //领取人数
//        public   String   mobile;    //手机端政策
//        public  String  end_time;    //截止时间 时间戳
//        public  String  goods_id;    //商品id
//        public  String  area_names;   //区域名称
//        public   String  end_time_1;   //截止时间
//        public  String  trid;  //子任务id
         public  String  rold_id;  //判断角色
         public  String  gtid;
         public  String  title;
         public  String  endtime;
         public  String  limitNum;
         public   String  lingyongNum;
         public  String   area_names;
         public   String[] banimg;
    }
}
