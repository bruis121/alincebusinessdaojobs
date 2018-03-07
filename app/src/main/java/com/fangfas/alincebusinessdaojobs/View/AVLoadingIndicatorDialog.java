package com.fangfas.alincebusinessdaojobs.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;


/**
 * Created by Administrator on 2016/5/24.
 */
public class AVLoadingIndicatorDialog extends Dialog {
    private TextView mMessageView;
    private Context context;

    public AVLoadingIndicatorDialog(Context context) {
        super(context, R.style.Dialog);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_avld);


    }



}
