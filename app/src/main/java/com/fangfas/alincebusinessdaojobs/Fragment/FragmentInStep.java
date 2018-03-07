package com.fangfas.alincebusinessdaojobs.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Ui.ActivtyWeb;




/**
 * Created by Administrator on 2016/12/15 0015.
 * 招商栏介绍--任务步骤
 */

public class FragmentInStep extends Fragment {
    private TextView tx_2;
    private TextView tx_price;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View  view=inflater.inflate(R.layout.fragment_instep,null);
        tx_2= (TextView) view.findViewById(R.id.tx_2);
        tx_price= (TextView) view.findViewById(R.id.tx_price);
         tx_2.setText(Html.fromHtml("<font  size=\"11sp\" color=\"#333333\">"+getResources().getString(R.string.str_caozuo2)+"</font><font size=\"11sp\" color=\"#ff6600\">4000-028-999</font>"+"<font size=\"11sp\" color=\"#333333\">"+getResources().getString(R.string.str_caozuo3)+"</font>"));
        tx_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i=new Intent(getActivity(), ActivtyWeb.class);
                i.putExtra("enter","1");
                startActivity(i);
            }
        });
         return view;
    }
}
