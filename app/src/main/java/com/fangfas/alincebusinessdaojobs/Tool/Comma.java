package com.fangfas.alincebusinessdaojobs.Tool;

/**
 * Created by Administrator on 2016/12/7 0007.
 * 字符串每三位添加逗号
 */

public class Comma {
    public  String  Comma(String  data){
        data = new StringBuilder(data).reverse().toString();     //先将字符串颠倒顺序
        String str2 = "";
        for(int i=0;i<data.length();i++){
            if(i*3+3>data.length()){
                str2 += data.substring(i*3, data.length());
                break;
            }
            str2 += data.substring(i*3, i*3+3)+",";
        }
        if(str2.endsWith(",")){
            str2 = str2.substring(0, str2.length()-1);
        }
        //最后再将顺序反转过来
       return (new StringBuilder(str2).reverse().toString());
    }
}
