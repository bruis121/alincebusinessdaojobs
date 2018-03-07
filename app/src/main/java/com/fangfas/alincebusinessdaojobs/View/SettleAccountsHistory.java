package com.fangfas.alincebusinessdaojobs.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.Baseadapter.SettleAccountAdapter;
import com.fangfas.alincebusinessdaojobs.Json.JSHistoryContent;
import com.fangfas.alincebusinessdaojobs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/25 0025.
 * 结算历史查看
 */

public class SettleAccountsHistory extends  Dialog{

    public SettleAccountsHistory(Context context) {
        super(context);

    }

    public SettleAccountsHistory(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SettleAccountsHistory(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder{
        private Context context;
        private String title;
//        public EditText s;
        private SettleAccountAdapter adapter;
        private ListView  list;
        public String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;

        }

        public SettleAccountsHistory.Builder getMessage(Activity mContext, ArrayList<JSHistoryContent.Content> d) {
            if(list!=null) {
                adapter = new SettleAccountAdapter(mContext, d);
                list.setAdapter(adapter);
            }
            return this;
        }



        /**
         * Set the Dialog message from resource
         *
         * @return
         */
//        public Builder setMessage(int message) {
//            this.message = (String) context.getText(message);
//            return this;
//        }


        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public SettleAccountsHistory.Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }



        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public SettleAccountsHistory.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public SettleAccountsHistory.Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public SettleAccountsHistory.Builder setPositiveButton(int positiveButtonText,
                                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public SettleAccountsHistory.Builder setPositiveButton(String positiveButtonText,
                                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }






        public SettleAccountsHistory create(){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final SettleAccountsHistory dialog = new SettleAccountsHistory(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.settleaccountshistory, null);

            list= (ListView) layout.findViewById(R.id.list);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) layout.findViewById(R.id.title)).setText(title);

            if(positiveButtonText != null){
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
            }
            if (positiveButtonClickListener != null){
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }else{
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }

            if(message != null){

            }else if(contentView != null){
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content))
                        .addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}


//public class SettleAccountsHistory extends PopupWindow {
//    private Activity context;
//    private View view;
//    @Bind(R.id.btn_close)
//    Button  btn_close;
//
//
//    public SettleAccountsHistory(Activity activity){
//        this.context=activity;
//        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view=inflater.inflate(R.layout.settleaccountshistory,null);
//        ButterKnife.bind(this,view);
//
//
//
//        /**
//         * 设置弹出窗口的内容
//         */
//        this.setContentView(view);
//        /**
//         * 设置弹窗的宽度
//         */
//        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        /**
//         * 设置弹窗的高度
//         */
//        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//
//        this.setFocusable(true);
//        /**
//         * 设置主Activity背景颜色
//         */
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        this.setBackgroundDrawable(dw);
//
//
//        btn_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//
//            }
//        });
//    }

//}
