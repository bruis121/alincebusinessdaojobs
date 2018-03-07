package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26 0026.
 * 首页销售任务筛选类型
 */

public class SalesTeypeContent {
    public   String err_Code;
    public  String  msg;
    public ArrayList<Content>  content;
    public  class   Content{
        public  String  name;
        public  String  flag;
    }
}
