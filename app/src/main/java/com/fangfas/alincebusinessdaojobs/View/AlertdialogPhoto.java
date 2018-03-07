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
 * Created by Administrator on 2016/11/23 0023.
 * 调用相册和相机的弹窗
 */

public class AlertdialogPhoto  extends Dialog {
    public AlertdialogPhoto(Context context) {
        super(context);

    }

    public AlertdialogPhoto(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AlertdialogPhoto(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder{
        private Context context;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;

        }








        public AlertdialogPhoto.Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public AlertdialogPhoto.Builder setPositiveButton(int positiveButtonText,
                                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public AlertdialogPhoto.Builder setPositiveButton(String positiveButtonText,
                                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }


        public AlertdialogPhoto.Builder setNegativeButton(int negativeButtonText,
                                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }




        public AlertdialogPhoto.Builder setNegativeButton(String negativeButtonText,
                                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Alertremittance create(){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final Alertremittance dialog = new Alertremittance(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_photo, null);

            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


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

            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }

//            if(contentView != null){
//                ((LinearLayout) layout.findViewById(R.id.content))
//                        .removeAllViews();
//                ((LinearLayout) layout.findViewById(R.id.content))
//                        .addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
