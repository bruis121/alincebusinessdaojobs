package com.fangfas.alincebusinessdaojobs.Json;

import java.util.ArrayList;


/**
 * 省市区
 */

public class ChinaContent {
    public ArrayList<Province> citylist;

   public class Province {
        public ArrayList<Area> c	;
        public String p;

       public  class Area{
           public ArrayList<Street> a;
            public String n;
           public class Street{
               public String s;
           }
        }
    }
}
