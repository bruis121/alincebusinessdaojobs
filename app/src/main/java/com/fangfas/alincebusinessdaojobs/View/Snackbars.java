package com.fangfas.alincebusinessdaojobs.View;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class Snackbars {
    public Snackbars(View activity, String string) {
        Snackbar sBar = Snackbar.make(activity, string, Snackbar.LENGTH_SHORT);
        //sBar.getView().setBackgroundColor(Color.parseColor("#ff5000"));
        TextView snackbar_text = (TextView) sBar.getView().findViewById(R.id.snackbar_text);
        snackbar_text.setTextColor(Color.parseColor("#ffffff"));
        sBar.show();
    }
}
