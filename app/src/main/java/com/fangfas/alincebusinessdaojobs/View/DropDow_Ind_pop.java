package com.fangfas.alincebusinessdaojobs.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.fangfas.alincebusinessdaojobs.Baseadapter.DropDowIndpopAdapter;
import com.fangfas.alincebusinessdaojobs.Baseadapter.JobPopwind2Adapter;
import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivityAuthentication;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/12/22 0022.
 * 职业选择下拉框
 */

public class DropDow_Ind_pop extends PopupWindow{
    private  ArrayList<HangyeContent.Content.JobCatList> jobCatList;
    private View  view;
    private HangyeContent hangyebean;
    private DropDowIndpopAdapter adapter;
    private ListView list;
    private ListView  list2;

    public   String  datas;
    public  String zhiyeid;
    public DropDow_Ind_pop(final ActivityAuthentication activityAuthentication, final HangyeContent hangyebean) {
        LayoutInflater inflater= (LayoutInflater) activityAuthentication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.dropdow_ind_pop, null);
        list= (ListView) view.findViewById(R.id.list);
        list2= (ListView) view.findViewById(R.id.list2);

        adapter=new DropDowIndpopAdapter(activityAuthentication,hangyebean);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                datas=hangyebean.content.get(i).catTitle;

                list.setVisibility(View.GONE);
                list2.setVisibility(View.VISIBLE);
                JobPopwind2Adapter adapter2=new JobPopwind2Adapter(activityAuthentication,hangyebean.content.get(i).jobCatList);
                jobCatList=hangyebean.content.get(i).jobCatList;
                list2.setAdapter(adapter2);
                list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        datas+="-"+jobCatList.get(i).catTitle;
                        zhiyeid=jobCatList.get(i).catId;
                        dismiss();

                    }
                });

            }
        });

        /**
         * 设置弹出窗口的内容
         */
        this.setContentView(view);
        /**
         * 设置弹窗的宽度
         */
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        /**
         * 设置弹窗的高度
         */
        // this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);


        /**
         * 设置主Activity背景颜色
         */
        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        ColorDrawable dw = new ColorDrawable(0000000);
        this.setBackgroundDrawable(dw);

        //添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.da).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();


                    }
                }
                return true;
            }
        });

    }
}
