package com.fangfas.alincebusinessdaojobs.Tool;

import com.fangfas.alincebusinessdaojobs.Json.SubordinateContent;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/12/23 0023.
 *我的任务---时间排序   降序
 */

public class SuborTiemComparable implements Comparator<SubordinateContent.Context.Account_list> {

    @Override
    public int compare(SubordinateContent.Context.Account_list account_list, SubordinateContent.Context.Account_list t1) {
        return Integer.parseInt(t1.create_time) - Integer.parseInt(account_list.create_time);
    }
}
