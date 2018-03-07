package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/20 0020.
 * 认证信息界面获取的相关选项信息
 */

public class AuthenticationContent {
    public  String  errCode;
    public   String  msg;
    public  Content  content;
    public  class   Content{
        public  ArrayList<Cardtype>cardtype; //证件类型（界面无需此）
        public ArrayList<Degree> degree;//学历
        public ArrayList<Workyear>workyear;//工作年限
        public  ArrayList<Worktype>worktype;
        public ArrayList<Salary>salary;//薪水范围
        public  class   Cardtype{
            public  String  name;
        }
        public  class   Degree{
            public  String  name;
        }
        public  class   Workyear{
            public  String  name;
        }
        public  class   Worktype{
            public  String  name;
        }
        public  class   Salary{
            public  String  name;
        }



    }

}
