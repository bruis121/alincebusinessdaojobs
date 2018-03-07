package com.fangfas.alincebusinessdaojobs.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangfas.alincebusinessdaojobs.R;


/**
 * Created by Administrator on 2016/11/7 0007.
 * 服务任务
 */

public class FragmentServiceTask extends Fragment {
//    ArrayList<String> data=new ArrayList<String>();
//    private HomeServiceAdapter adapter;
    private  View view;
//    @Bind(R.id.list)
//    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_salescollection,null);



//        data();
//
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity() );
//        recyclerView.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(OrientationHelper. VERTICAL);
//        adapter=new HomeServiceAdapter(getActivity(),data);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

//    private void data() {
//        for(int i=0;i<10;i++){
//            data.add(i+"");
//        }
//    }
}
