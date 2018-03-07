package com.fangfas.alincebusinessdaojobs.Tool;

import android.net.Uri;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class ConvertUrl {
    File file = null;
    public  String  ConvertUrl(String uri){
        String[] dataStr = uri.split("/");
        String fileTruePath = "sdcard";
        for (int i=4;i<dataStr.length;i++){
            fileTruePath = fileTruePath + "/" + dataStr[i];
        }


            return fileTruePath;
    }


}
