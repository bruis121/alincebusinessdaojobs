package com.fangfas.alincebusinessdaojobs.View;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangfas.alincebusinessdaojobs.R;



/**
 * 自定义弹窗
 */
public class Alertremittance extends Dialog {
    public Alertremittance(Context context) {
        super(context);

    }

    public Alertremittance(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected Alertremittance(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder{
        private Context context;
        private String title;
        public  EditText  s;
        public String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder getMessage() {
            message=s.getText().toString();
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
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }



        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }


        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }




        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Alertremittance create(){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final Alertremittance dialog = new Alertremittance(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.adialog_remittance, null);

            s=((EditText) layout.findViewById(R.id.message));
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
