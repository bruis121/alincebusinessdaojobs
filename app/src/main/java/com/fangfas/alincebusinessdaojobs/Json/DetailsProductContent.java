package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/7 0007.
 * 详情页内容
 */

public class DetailsProductContent {
    public String err_Code;
    public String msg;
    public Content content;

    public class Content {
        public String role_id;

        public ArrayList<Good_content2> good_content2;
        public  Good_content3 good_content3;
         public   class   Good_content2{
             public  String  name;
             public   String  value;
         }
        public  class   Good_content3{
            public  String  shop_price;
            public  String  suggest_price;
            public   String  ticheng_ratio;
            public   String  mix_ticheng_price;
            public  String  mix_dakuan_price;
        }
    }
}
