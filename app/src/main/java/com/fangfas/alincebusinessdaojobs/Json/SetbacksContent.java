package com.fangfas.alincebusinessdaojobs.Json;

/**
 * Created by Administrator on 2016/12/8 0008.
 * 任务进度--进度
 */

public class SetbacksContent {
    public  String  err_Code;
    public  String  msg;
    public   Content  content;
    public   class  Content{
        public   String  typeNu;//物流单号
        public  String  typeCom;//物流公司

        public  Zhaoshangqiatan  zhaoshangqiatan;
        public   Tijiaohetong   tijiaohetong;
        public   Pingtaiqueren  pingtaiqueren;
        public   Jiuqifahuo  jiuqifahuo;
        public  Querenshouhuo  querenshouhuo;
        public  Tichengjiesuan  tichengjiesuan;
        public   class  Zhaoshangqiatan{
            public   String  status_name;
            public   String  time;
            public  String   is_cur;   //2是前面的  1是当前的  0是后面的
            public   String  chakan;  //1为查看
        }
        public   class Tijiaohetong{
               public   String  status_name;
               public  String   time;
               public   String  is_cur;
               public  String  chakan;
        }
        public  class  Pingtaiqueren{
               public  String status_name;
               public  String  time;
               public   String  is_cur;
               public  String  chakan;
        }
        public   class  Jiuqifahuo{
             public  String  status_name;
              public  String  time;
              public  String  is_cur;
              public   String  chakan;
        }
        public  class   Querenshouhuo{
            public  String  status_name;
            public  String  time;
            public  String  is_cur;
            public   String  chakan;
        }
        public  class   Tichengjiesuan{
            public  String  status_name;
            public  String  time;
            public  String  is_cur;
            public   String  chakan;
        }

    }
}
