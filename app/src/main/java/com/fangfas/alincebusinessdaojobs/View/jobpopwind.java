package com.fangfas.alincebusinessdaojobs.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Baseadapter.JobPopwindAdapter;
import com.fangfas.alincebusinessdaojobs.Baseadapter.JobPopwind2Adapter;
import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.R;



/**
 * Created by Administrator on 2016/12/17 0017.
 * 行业选择弹窗
 */

public class jobpopwind extends PopupWindow {
    public  String data1,data2;
    private View view;
    private ListView list;
    private TextView  title1;
    private ListView  list1;
    private TextView line1;
    private TextView  line2;
    private TextView delete;

    JobPopwind2Adapter adapter2;

    public jobpopwind(final Context context, final HangyeContent bean) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.jobpop, null);
        list= (ListView) view.findViewById(R.id.list);
        title1= (TextView) view.findViewById(R.id.title1);
        list1= (ListView) view.findViewById(R.id.list1);
        line1= (TextView) view.findViewById(R.id.line1);
        line2= (TextView) view.findViewById(R.id.line2);
        delete= (TextView) view.findViewById(R.id.delete);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
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

        final JobPopwindAdapter adapter=new JobPopwindAdapter(context,bean);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView v= (TextView) view.findViewById(R.id.name);
                ImageView s= (ImageView) view.findViewById(R.id.check);
                v.setTextColor(context.getResources().getColor(R.color.red_x));
                s.setVisibility(View.VISIBLE);
                title1.setText(bean.content.get(i).catTitle);
                data1=title1.getText().toString();
                list.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
                list1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                adapter2=new JobPopwind2Adapter(context,bean.content.get(i).jobCatList);
                list1.setAdapter(adapter2);
                list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView v1= (TextView) view.findViewById(R.id.name);
                        ImageView s1= (ImageView) view.findViewById(R.id.check);
                        v1.setTextColor(context.getResources().getColor(R.color.red_x));
                        s1.setVisibility(View.VISIBLE);
                         data2=v1.getText().toString();
                        dismiss();
                    }
                });
            }
        });
    }
}
