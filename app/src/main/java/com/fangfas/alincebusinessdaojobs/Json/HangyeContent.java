package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/17 0017.
 * 兼职人员行业列表
 */

public class HangyeContent {
     public   String errCode;
    public   String   msg;
    public ArrayList<Content> content;
    public  class  Content{
        public String  catTitle;  //行业名称
        public  String  catPid;  //行业pid
        public   String  cat_status;
        public  String  cat_sort;
        public  ArrayList<JobCatList>  jobCatList;
        public String catRemarks;
        public String catLevel;
        public String catId;
        public  class   JobCatList{
           public String  catTitle;
            public String catPid;
            public String cat_status;
            public String cat_sort;
            public ArrayList<JobCatLists> jobCatList;
            public String catRemarks;
            public String catLevel;
            public String catId;
            public  class   JobCatLists{
                public String  catTitle;
                public String catPid;
                public String cat_status;
                public String cat_sort;
                public String catRemarks;
                public String catLevel;
                public String catId;
            }
        }

    }


}
