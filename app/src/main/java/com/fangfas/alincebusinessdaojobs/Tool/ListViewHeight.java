package com.fangfas.alincebusinessdaojobs.Tool;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/11/10 0010.
 * listview高度自增长
 */

public class ListViewHeight {
    public   ListViewHeight(ListView lanzi){
        ListAdapter listAdapter = lanzi.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lanzi);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lanzi.getLayoutParams();

        params.height = totalHeight
                + (lanzi.getDividerHeight() * (listAdapter.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(0, 0, 0, 0); // 可删

        lanzi.setLayoutParams(params);
    }
}
