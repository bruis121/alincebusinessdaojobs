package com.fangfas.alincebusinessdaojobs.Ui;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.fangfas.alincebusinessdaojobs.Baseadapter.AuthenJobAdapter2;
import com.fangfas.alincebusinessdaojobs.Json.GetAuthentContent;
import com.fangfas.alincebusinessdaojobs.Json.HangyeContent;
import com.fangfas.alincebusinessdaojobs.Json.Jobbean;
import com.fangfas.alincebusinessdaojobs.Method.BaseActicity;
import com.fangfas.alincebusinessdaojobs.MyApplication;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.ConvertUrl;
import com.fangfas.alincebusinessdaojobs.Tool.ListViewHeight;
import com.fangfas.alincebusinessdaojobs.Tool.PictureUtil;
import com.fangfas.alincebusinessdaojobs.Tool.SingleVolleyRequestQueue;
import com.fangfas.alincebusinessdaojobs.Tool.initDataProceBean;
import com.fangfas.alincebusinessdaojobs.Tool.phone;
import com.fangfas.alincebusinessdaojobs.View.AlertdialogPhoto;
import com.fangfas.alincebusinessdaojobs.View.DropDow_Ind_pop;
import com.fangfas.alincebusinessdaojobs.View.GenBitmap;
import com.fangfas.alincebusinessdaojobs.Baseadapter.AuthenJobAdapter;
import com.fangfas.alincebusinessdaojobs.View.NormalDialogs;
import com.fangfas.alincebusinessdaojobs.View.SlideList.SlideView;
import com.fangfas.alincebusinessdaojobs.View.Snackbars;
import com.fangfas.alincebusinessdaojobs.Json.AuthenticationContent;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestCarded;
import com.fangfas.alincebusinessdaojobs.Httprequest.GetAuth;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestHangye;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestSMS;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.widget.NormalDialog;
import com.jaeger.library.StatusBarUtil;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by Administrator on 2016/11/28 0028.
 * 认证资料
 *
 */

public class ActivityAuthentication extends BaseActicity implements View.OnClickListener {


    StringRequest stringRequest;
    boolean isclick = false;

    //获取选项信息
    AuthenticationContent choosedata;

    //正面相机
    private static final int CAMERA_Face_REQUEST_CODE = 222;
    //正面相册
    private static final int PICTURE_Face_REQUEST_CODE = 333;


    //反面相机
    private static final int CAMERA_Inverse_REQUEST_CODE = 444;
    //反面相册
    private static final int PICTURE_Inverse_REQUEST_CODE = 555;

    //手持相机
    private static final int CAMERA_Armed_With_Knives_REQUEST_CODE = 666;
    //手持相册
    private static final int PICTURE_Armed_With_Knives_REQUEST_CODE = 777;

    private static final int msgOk = 3;


    private LinearLayout ll_t;
    //提交
    private CardView commit;
    private ImageView returns;
    //出生日期
    private RelativeLayout RlBorth;
    private TextView TxBorth;

    //工作经历
    private LinearLayout ll_experience;

    //性别
    private RelativeLayout RlSex;
    private TextView TxSex;
    //工作年限
    private TextView year;


    //所属公司性质
    private TextView nature;


    private EditText et_phone;


    private TextView tx_title;
    //获取验证码按钮
    private CardView card_code;
    private TextView tx_verification_code;

    //职务
    private TextView et_post;
    //正面照
    private ImageView mage_face;
    //反面照
    private ImageView mage_inverse;
    //手持照
    private ImageView armed_with_knives_mage;

    private EditText et_name;

    private EditText et_firm;

    //详细地址
    private EditText address;
    //身份证
    private EditText carded;
    //邮箱
    private EditText et_emial;
    //验证
    private EditText et_ver_code;
    //工作状态
    private RelativeLayout rl_zhuangtai;
    private TextView zhuangtai;

    private TextView diqu;

    private ListView list_job;
    //相机和相册弹窗
    AlertdialogPhoto.Builder dialog;

    //正面返回数据,反面
    String ResponseFace, ResponseBack, ResponseHand;

    //上传的图片
    private Bitmap bitmap = null;


    //返回的工作经历数据
    private String jobdata;

    boolean isMsgSend = true;
    private TimeCount time;
    /**
     * 相册获取的头部信息
     */
//    private String header;
    private RequestCarded model;

    //职业数据
    private HangyeContent hangyebean;
    //职务id
    private String zhiwuid;

    //返回经历的信息
    private Jobbean jobbean;

    //左滑删除
    private SlideView mFocusedItemView;
    //权限使用
    private int codes;

    int x1, x2, y1, y2;
    private SVProgressHUD mSVProgressHUD;


    // 记录文件保存位置
    private String mFilePath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_authentication);
        ll_t= (LinearLayout) findViewById(R.id. ll_t);
        commit= (CardView) findViewById(R.id.commit);
        returns= (ImageView) findViewById(R.id.returns);
        RlBorth= (RelativeLayout) findViewById(R.id.rl_borth);
        TxBorth= (TextView) findViewById(R.id.tx_borth);
        ll_experience= (LinearLayout) findViewById(R.id.ll_experience);
        RlSex= (RelativeLayout) findViewById(R.id.rl_sex);
        TxSex= (TextView) findViewById(R.id.tx_sex);
        year= (TextView) findViewById(R.id.year);
        nature= (TextView) findViewById(R.id.nature);
        list_job= (ListView) findViewById(R.id.list_job);
        diqu= (TextView) findViewById(R.id.diqu);
        zhuangtai= (TextView) findViewById(R.id.zhuangtai);
        rl_zhuangtai= (RelativeLayout) findViewById(R.id.rl_zhuangtai);
        et_ver_code= (EditText) findViewById(R.id.et_ver_code);
        et_emial= (EditText) findViewById(R.id.et_emial);
        carded= (EditText) findViewById(R.id.carded);
        address= (EditText) findViewById(R.id.address);
        et_firm= (EditText) findViewById(R.id.et_firm);
        et_name= (EditText) findViewById(R.id.et_name);
        armed_with_knives_mage= (ImageView) findViewById(R.id.armed_with_knives_mage);
        mage_inverse= (ImageView) findViewById(R.id.mage_inverse);
        mage_face= (ImageView) findViewById(R.id.mage_face);
        et_post= (TextView) findViewById(R.id.et_post);
        tx_verification_code= (TextView) findViewById(R.id.tx_verification_code);
        card_code= (CardView) findViewById(R.id.card_verification_code);
        tx_title= (TextView) findViewById(R.id.tx_title);
        et_phone= (EditText) findViewById(R.id.et_phone);






        mSVProgressHUD = new SVProgressHUD(ActivityAuthentication.this);
        int blue = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            blue = ContextCompat.getColor(ActivityAuthentication.this, R.color.blue);

        } else {
            blue = getResources().getColor(R.color.blue);
        }
        StatusBarUtil.setColorNoTranslucent(this, blue);


        Animation scaleAnimation = AnimationUtils.loadAnimation(ActivityAuthentication.this, R.anim.fade_in_top);
        returns.startAnimation(scaleAnimation);
        tx_title.startAnimation(scaleAnimation);



       if(MyApplication.getInstance().GetAuth!=null&&MyApplication.getInstance().GetAuth.equalsIgnoreCase("wait")||MyApplication.getInstance().GetAuth.equalsIgnoreCase("pass")){
           norivise();
           AlreadyAuthen();
           /**
            * 认证审核中
            */
           if(MyApplication.getInstance().GetAuth!=null&&MyApplication.getInstance().GetAuth.equalsIgnoreCase("wait")){
              new NormalDialogs().Dialogs(ActivityAuthentication.this,getResources().getString(R.string.hint40));
           }else{
               /**
                * 审核通过
                */
               new NormalDialogs().Dialogs(ActivityAuthentication.this,getResources().getString(R.string.hint41));
           }
       }else if(MyApplication.getInstance().GetAuth==null||MyApplication.getInstance().GetAuth.equalsIgnoreCase("null")||MyApplication.getInstance().GetAuth.equalsIgnoreCase("error")){
        if(MyApplication.getInstance().GetAuth!=null&&MyApplication.getInstance().GetAuth.equalsIgnoreCase("error")){
            /**
             * 审核失败
             */
            new NormalDialogs().Dialogs(ActivityAuthentication.this,getResources().getString(R.string.hint30));
        }
        dialog = new AlertdialogPhoto.Builder(ActivityAuthentication.this);
        model = new RequestCarded(stringRequest, ActivityAuthentication.this, hander);


        new RequestHangye(stringRequest, ActivityAuthentication.this, hander);


        mFilePath = Environment.getExternalStorageDirectory().getPath();
        // 文件名
        mFilePath = mFilePath + "/" + "photo.png";

        /**
         * 获取上传身份证的
         */


        model.getHeader(stringRequest, ActivityAuthentication.this);
        model.Auchoose();


        RlSex.setOnClickListener(this);
        RlBorth.setOnClickListener(this);
        card_code.setOnClickListener(this);
        commit.setOnClickListener(this);
        ll_experience.setOnClickListener(this);
        mage_face.setOnClickListener(this);
        mage_inverse.setOnClickListener(this);
        armed_with_knives_mage.setOnClickListener(this);
        nature.setOnClickListener(this);
        year.setOnClickListener(this);
        rl_zhuangtai.setOnClickListener(this);
        diqu.setOnClickListener(this);
        et_post.setOnClickListener(this);
       }

        returns.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(R.id.et_post==view.getId()){
            //职务选择
            final DropDow_Ind_pop pop = new DropDow_Ind_pop(ActivityAuthentication.this, hangyebean);
            pop.showAsDropDown(et_post, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    et_post.setText(pop.datas);
                    zhiwuid = pop.zhiyeid;
                }
            });


        }else  if(R.id.diqu==view.getId()){
            //地区
            final initDataProceBean data = new initDataProceBean();
            data.initDataProceBean(ActivityAuthentication.this);

            //选项选择器
            OptionsPickerView pvOptionsp = new OptionsPickerView(this);
            //三级联动效果
            pvOptionsp.setPicker(data.options1Items, data.options2Items, data.options3Items, true);
            pvOptionsp.setTitle("选择城市");
            pvOptionsp.setCyclic(false, true, true);
            //设置默认选中的三级项目
            //监听确定选择按钮
            pvOptionsp.setSelectOptions(1, 1, 1);
            pvOptionsp.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    //返回的分别是三个级别的选中位置
                    String tx = data.options1Items.get(options1)
                            + data.options2Items.get(options1).get(option2)
                            + data.options3Items.get(options1).get(option2).get(options3);
                    diqu.setText(tx);

                }
            });
            pvOptionsp.show();
        }else if(R.id.rl_zhuangtai==view.getId()){
            //工作状态
            final ArrayList<String> zhuangtais = new ArrayList<>();
            zhuangtais.add(0, "在职");
            zhuangtais.add(1, "离职");
            //选项选择器
            OptionsPickerView pvOptionsi = new OptionsPickerView(this);
            //三级联动效果
            pvOptionsi.setPicker(zhuangtais);
            pvOptionsi.setTitle("请选择工作状态");
            pvOptionsi.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    //返回的分别是三个级别的选中位置
                    String tx = zhuangtais.get(options1);
                    zhuangtai.setText(tx);

                }
            });
            //设置是否循环滚动
            pvOptionsi.setCyclic(false);
            pvOptionsi.show();

        }else  if(R.id.year==view.getId()){

            //工作年限
            final ArrayList<String> dataitem = new ArrayList<>();
            for (int i = 0; i < choosedata.content.workyear.size(); i++) {
                dataitem.add(choosedata.content.workyear.get(i).name);
            }

            //选项选择器
            OptionsPickerView pvOptions = new OptionsPickerView(this);
            //三级联动效果
            pvOptions.setPicker(dataitem);
            pvOptions.setTitle("请选择工作年限");

            pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    //返回的分别是三个级别的选中位置
                    String tx = dataitem.get(options1);
                    year.setText(tx);

                }
            });
            //设置是否循环滚动
            pvOptions.setCyclic(false);
            pvOptions.show();
        }else  if(R.id.mage_face==view.getId()){
            //正面图像
            dialog.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    onfaceCameraClick();




                }
            });
            dialog.setNegativeButton("相册", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    onfacePictureClick();


                }
            });
            dialog.create().show();

        }else  if(R.id.mage_inverse==view.getId()){
            //反面
            dialog.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    oninverseCameraClick();
                }
            });
            dialog.setNegativeButton("相册", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    oninversePictureClick();
                }
            });
            dialog.create().show();
        }else  if(R.id.armed_with_knives_mage==view.getId()){
            //手持证件照
            dialog.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    onarmed_with_knivesCameraClick();
                }
            });
            dialog.setNegativeButton("相册", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    onarmed_with_knivesPictureClick();
                }
            });
            dialog.create().show();

        }else  if(R.id.card_verification_code==view.getId()){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

            if (!phone.isPhoneLegal(et_phone.getText().toString())) {
                Snackbars bar = new Snackbars(ll_t, getResources().getString(R.string.hint26));
            } else {
                if (!isMsgSend)
                    return;
                isMsgSend = false;

                new RequestSMS(stringRequest, ActivityAuthentication.this, hander, et_phone.getText().toString());


                time = new TimeCount(30000, 1000);// 构造CountDownTimer对象
                time.start();


            }

        }else if(R.id.returns==view.getId()){
            finish();

        }else  if(R.id.rl_borth==view.getId()){
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    TxBorth.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                }
            }, 2000, 1, 2).show();


        }else  if(R.id.rl_sex==view.getId()){
            //选项选择器
            OptionsPickerView pvOptions3 = new OptionsPickerView(this);
            final ArrayList<String> options1Items = new ArrayList<>();
            options1Items.add("男");
            options1Items.add("女");
            options1Items.add("保密");
            //三级联动效果
            pvOptions3.setPicker(options1Items);
            pvOptions3.setTitle("请选择性别");

            pvOptions3.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    //返回的分别是三个级别的选中位置
                    String tx = options1Items.get(options1);
                    TxSex.setText(tx);

                }
            });
            //设置是否循环滚动
            pvOptions3.setCyclic(false);
            pvOptions3.show();


        }else  if(R.id.commit==view.getId()){
            //验证码
            if (et_ver_code.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_code_test));
            }
            //姓名
            else if (et_name.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_name));
            }
            //性别
            else if (TxSex.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_sex_choose));
            }
            //出生日期
            else if (TxBorth.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_bort_choose));
            }
            //公司名称
            else if (et_firm.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_company_choose));
            }
            //所属公司性质
            else if (nature.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_nature_choose));
            }
            //职务
            else if (et_post.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_post_choose));
            }

            //工作年限
            else if (year.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_year_choose));
            }
            //地区
            else if (diqu.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_address));
            }
            //详细地址
            else if (address.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_address_choose));
            }
            //身份证
            else if (carded.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_carded_choose));
            }
            //邮箱
            else if (et_emial.getText().toString().isEmpty()) {
                new Snackbars(ll_t, getResources().getString(R.string.str_email_choose));
            }

            //身份证背面
            else if (ResponseBack == null) {

                new Snackbars(ll_t, getResources().getString(R.string.str_back));

            }
            //身份证正面
            else if (ResponseFace == null) {
                new Snackbars(ll_t, getResources().getString(R.string.str_face));
                //身份证手持
            } else if (ResponseHand == null) {
                new Snackbars(ll_t, getResources().getString(R.string.str_hand));
            } else {

                model.getCommit(et_phone.getText().toString(), et_ver_code.getText().toString(), et_name.getText().toString(), TxBorth.getText().toString(), TxSex.getText().toString(), et_firm.getText().toString(), nature.getText().toString(), zhiwuid
                        , year.getText().toString(), zhuangtai.getText().toString(), address.getText().toString(), diqu.getText().toString(), carded.getText().toString(), et_emial.getText().toString(), jobdata, ResponseFace, ResponseBack, ResponseHand);
            }

        }else  if(R.id.ll_experience==view.getId()){
            Intent i = new Intent(ActivityAuthentication.this, ActivityJobExperience.class);
            startActivityForResult(i, msgOk);


        }else  if(R.id.nature==view.getId()){
            //选项选择器
            OptionsPickerView pvOptions1 = new OptionsPickerView(ActivityAuthentication.this);

            final ArrayList<String> options1Items1 = new ArrayList<String>();
            options1Items1.add(0, "酒企");
            options1Items1.add(1, "经销商");
            options1Items1.add(2, "其他");
            //三级联动效果
            pvOptions1.setPicker(options1Items1);
            pvOptions1.setTitle("所属公司性质");

            pvOptions1.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    //返回的分别是三个级别的选中位置
                    String tx = options1Items1.get(options1);
                    nature.setText(tx);

                }
            });
            //设置是否循环滚动
            pvOptions1.setCyclic(false);
            pvOptions1.show();

        }

    }


    /**
     * 手持相册
     */
    private void onarmed_with_knivesPictureClick() {
        Picture(PICTURE_Armed_With_Knives_REQUEST_CODE);
    }

    /**
     * 手持拍照
     */
    private void onarmed_with_knivesCameraClick() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            codes = CAMERA_Armed_With_Knives_REQUEST_CODE;
            getPersimmionsCAMERA();
        } else {
            Camera(CAMERA_Armed_With_Knives_REQUEST_CODE);
        }

    }


    /**
     * 反面相册
     */
    private void oninversePictureClick() {
        Picture(PICTURE_Inverse_REQUEST_CODE);
    }


    /**
     * 反面拍照
     */
    private void oninverseCameraClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            codes = CAMERA_Inverse_REQUEST_CODE;
            getPersimmionsCAMERA();
        } else {
            Camera(CAMERA_Inverse_REQUEST_CODE);
        }
    }


    /**
     * 正面相册
     */
    private void onfacePictureClick() {
        Picture(PICTURE_Face_REQUEST_CODE);

    }

    /**
     * 正面相机
     */
    private void onfaceCameraClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            codes = CAMERA_Face_REQUEST_CODE;
            getPersimmionsCAMERA();
        } else {
            Camera(CAMERA_Face_REQUEST_CODE);
        }

    }

    /**
     * 相机公共类
     *
     * @param camera_code
     */
    private void Camera(int camera_code) {
        // 指定拍照
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 加载路径
        Uri uri = Uri.fromFile(new File(mFilePath));
        // 指定存储路径，这样就可以保存原图了
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // 拍照返回图片
        startActivityForResult(intent,camera_code);

    }

    /**
     * 相册公共类
     *
     * @param picturecode
     */
    private void Picture(int picturecode) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");// 选择图片类型
        // 在onActivityResult中处理选择结果
        startActivityForResult(intent, picturecode);

    }


    // 获取验证码倒计时
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            isMsgSend = true;
            tx_verification_code.setText("重新验证");
            int main_color = getResources().getColor(R.color.blue);
            tx_verification_code.setBackgroundColor(main_color);

        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示

            int hui = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hui = ContextCompat.getColor(ActivityAuthentication.this, R.color.hui);
            } else {
                hui = getResources().getColor(R.color.hui);
            }
            tx_verification_code.setBackgroundColor(hui);
            tx_verification_code.setText(+millisUntilFinished / 1000
                    + "后重发");
        }

    }


    Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //发送短信
            }
//            else if (msg.what == 2) {
//                try {
//
//
//                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")) {
//                        new Snackbars(ll_t, JSONObject.parseObject((String) msg.obj).getString("content"));
//                        return;
//                    }
//                    String headers = JSONObject.parseObject((String) msg.obj).getString("content");
//                    header = JSONObject.parseObject(headers).getString("value");
//
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
            else if (msg.what == 3) {
                mSVProgressHUD.dismiss();
                //正面
                try {
                    ResponseFace = JSONObject.parseObject((String) msg.obj).getString("content");
                    hander.postDelayed(new Runnable() {
                        public void run() {
                            new Snackbars(ll_t, getResources().getString(R.string.zhengmiansucess));
                        }

                    }, 1000);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mage_face.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //大图查看
                        photo(ResponseFace);
                    }
                });
            } else if (msg.what == 4) {
                mSVProgressHUD.dismiss();

                //手持
                try {


                    ResponseHand = JSONObject.parseObject((String) msg.obj).getString("content");
                    hander.postDelayed(new Runnable() {
                        public void run() {
                            new Snackbars(ll_t, getResources().getString(R.string.shouchisu));
                        }

                    }, 1000);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                armed_with_knives_mage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        photo(ResponseHand);
                    }
                });


            } else if (msg.what == 5) {
                mSVProgressHUD.dismiss();

                //背面
                try {
                    ResponseBack = JSONObject.parseObject((String) msg.obj).getString("content");
                    hander.postDelayed(new Runnable() {
                        public void run() {
                            new Snackbars(ll_t, getResources().getString(R.string.fanmian_suc));
                        }

                    }, 1000);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mage_inverse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        photo(ResponseBack);
                    }
                });
            } else if (msg.what == 6) {


                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")) {
                        new Snackbars(ll_t, JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }

                    new Snackbars(ll_t, JSONObject.parseObject((String) msg.obj).getString("msg"));
                    try {
                        //获取认证信息
                        new GetAuth(hander, ActivityAuthentication.this, stringRequest);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            } else if (msg.what == 7) {
                //获取选项信息
                try {

                    choosedata = JSONObject.parseObject((String) msg.obj, AuthenticationContent.class);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (msg.what == 8) {
                try {
                    hangyebean = JSONObject.parseObject((String) msg.obj, HangyeContent.class);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (msg.what == 9) {
                list_job.setVisibility(View.VISIBLE);
                AuthenJobAdapter mAdapter = new AuthenJobAdapter(stringRequest, ActivityAuthentication.this, list_job, hander);
                mAdapter.setmMessageItems(jobbean.res);
                list_job.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                new ListViewHeight(list_job);
                jobdata = jobbean.xml;
                list_job.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent is = new Intent(ActivityAuthentication.this, ActivityJobExperience.class);
                        is.putExtra("id", jobbean.res.get(i).wid);
                        is.putExtra("company", jobbean.res.get(i).Company);
                        is.putExtra("yearbegin", jobbean.res.get(i).yearbegin);
                        is.putExtra("yearend", jobbean.res.get(i).yearend);
                        is.putExtra("summary", jobbean.res.get(i).summary);
                        is.putExtra("hangye", jobbean.res.get(i).cats);
                        startActivityForResult(is, msgOk);
                    }
                });


                list_job.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                x1 = (int) motionEvent.getX();
                                y1 = (int) motionEvent.getY();
                                isclick = false;
                                break;
                            }
                            case MotionEvent.ACTION_MOVE: {
                                x2 = (int) motionEvent.getX();
                                y2 = (int) motionEvent.getY();
                                if (Math.abs(x1 - x2) < 5) {
                                    isclick = false;
                                } else {
                                    int position = list_job.pointToPosition(x1, y1);
                                    if (position != list_job.INVALID_POSITION) {
                                        Jobbean.Res data = (Jobbean.Res) list_job.getItemAtPosition(position);
                                        mFocusedItemView = data.slideView;
                                    }
                                    if (mFocusedItemView != null) {
                                        mFocusedItemView.onRequireTouchEvent(motionEvent);
                                    }
                                    isclick = true;
                                    return onTouchEvent(motionEvent);
                                }
                                break;
                            }


                        }

                        return isclick;
                    }
                });
            } else if (msg.what == 10) {
                //删除工作经历
                try {
                    jobdata = JSONObject.parseObject((String) msg.obj).getString("xml");
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (msg.what == 12) {

                try {
                    if (JSONObject.parseObject((String) msg.obj).getString("msg").equalsIgnoreCase("error")) {
                        new Snackbars(ll_t, JSONObject.parseObject((String) msg.obj).getString("content"));
                        return;
                    }
                    String data = JSONObject.parseObject((String) msg.obj).getString("content");
                    GetAuthentContent bean = JSONObject.parseObject(data, GetAuthentContent.class);
                    if (bean.resume_natrue == 1) {
                        MyApplication.getInstance().GetAuth = "wait";
                    } else if (bean.resume_natrue == 2) {
                        MyApplication.getInstance().GetAuth = "pass";
                    } else {
                        //未通过
                        MyApplication.getInstance().GetAuth = "erro";
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    finish();
                }


            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_Face_REQUEST_CODE) {

            mSVProgressHUD.showWithStatus(getString(R.string.uplodeloding));
            bitmap = PictureUtil.getSmallBitmap(mFilePath);
            mage_face.setImageBitmap(new GenBitmap().GenBitmap(bitmap));
            //正面处理
            new Thread(new Runnable() {
                @Override
                public void run() {
                    uploadFace(bitmap);
                }
            }).start();


        }else if (requestCode == CAMERA_Inverse_REQUEST_CODE)// 反面拍照
        {
            mSVProgressHUD.showWithStatus(getString(R.string.uplodeloding));
            bitmap = PictureUtil.getSmallBitmap(mFilePath);
            mage_inverse.setImageBitmap(new GenBitmap().GenBitmap(bitmap));
            //正面处理
            new Thread(new Runnable() {
                @Override
                public void run() {
                    uploadInverse(bitmap);
                }
            }).start();



        }else if (requestCode == CAMERA_Armed_With_Knives_REQUEST_CODE)// 手持拍照
        {



            mSVProgressHUD.showWithStatus(getString(R.string.uplodeloding));
            bitmap = PictureUtil.getSmallBitmap(mFilePath);
            armed_with_knives_mage.setImageBitmap(new GenBitmap().GenBitmap(bitmap));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    uploadArmed_With_Knives(bitmap);
                }
            }).start();


        } else if (resultCode == RESULT_OK && data != null) {
            mSVProgressHUD.showWithStatus(getString(R.string.uplodeloding));

            Uri uri = data.getData();
            if (null != uri) {
                String imgPath = null;
                ContentResolver resolver = this.getContentResolver();
                String[] columns = {MediaStore.Images.Media.DATA};
                Cursor cursor = null;
                cursor = resolver.query(uri, columns, null, null, null);
                if (Build.VERSION.SDK_INT > 18)// 4.4以后文件选择发生变化，判断处理
                {

                    if (requestCode == PICTURE_Face_REQUEST_CODE)// 正面相册
                    {


                        imgPath = uri.getPath();
                        if (!TextUtils.isEmpty(imgPath)
                                && imgPath.contains(":")) {
                            String imgIndex = imgPath.split(":")[1];
                            cursor = resolver.query(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    columns, "_id=?", new String[]{imgIndex},
                                    null);
                        }

                        if (null != cursor && cursor.moveToFirst()) {
                            int columnIndex = cursor.getColumnIndex(columns[0]);
                            imgPath = cursor.getString(columnIndex);
                            cursor.close();
                        }
                        if (!TextUtils.isEmpty(imgPath)) {

                            bitmap = PictureUtil.getSmallBitmap(imgPath);

                        }

                        //正面
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                uploadFace(bitmap);
                            }
                        }).start();

                        mage_face.setImageBitmap(new GenBitmap().GenBitmap(bitmap));
                        bitmap.recycle();

                    } else if (requestCode == PICTURE_Inverse_REQUEST_CODE) {//反面相册

                        imgPath = uri.getPath();
                        if (!TextUtils.isEmpty(imgPath)
                                && imgPath.contains(":")) {
                            String imgIndex = imgPath.split(":")[1];
                            cursor = resolver.query(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    columns, "_id=?", new String[]{imgIndex},
                                    null);
                        }

                        if (null != cursor && cursor.moveToFirst()) {
                            int columnIndex = cursor.getColumnIndex(columns[0]);
                            imgPath = cursor.getString(columnIndex);
                            cursor.close();
                        }
                        if (!TextUtils.isEmpty(imgPath)) {
                            bitmap = PictureUtil.getSmallBitmap(imgPath);
                        }
                        //反面处理
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                uploadInverse(bitmap);
                            }
                        }).start();

                        mage_inverse.setImageBitmap(new GenBitmap().GenBitmap(bitmap));

                        bitmap.recycle();
                    } else if (requestCode == PICTURE_Armed_With_Knives_REQUEST_CODE) {//手持相册
                        imgPath = uri.getPath();
                        if (!TextUtils.isEmpty(imgPath)
                                && imgPath.contains(":")) {
                            String imgIndex = imgPath.split(":")[1];
                            cursor = resolver.query(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    columns, "_id=?", new String[]{imgIndex},
                                    null);
                        }

                        if (null != cursor && cursor.moveToFirst()) {
                            int columnIndex = cursor.getColumnIndex(columns[0]);
                            imgPath = cursor.getString(columnIndex);
                            cursor.close();
                        }
                        if (!TextUtils.isEmpty(imgPath)) {
                            bitmap = PictureUtil.getSmallBitmap(imgPath);
                        }
                        //手持
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                uploadArmed_With_Knives(bitmap);
                            }
                        }).start();

                        armed_with_knives_mage.setImageBitmap(new GenBitmap().GenBitmap(bitmap));

                        bitmap.recycle();
                    }

                }

            }


        } else if (resultCode == 3) {
            if (jobbean != null && jobbean.res.size() != 0) {
                jobbean.res.clear();
            }
            //工作经历返回的数据
            String jobdatas = data.getStringExtra("jobdata");

            jobbean = JSONObject.parseObject(jobdatas, Jobbean.class);
            if (jobbean.res.size() != 0) {

                Message msg = new Message();
                msg.what = 9;
                hander.sendMessage(msg);
            }

        }

    }


    /**
     * 反面
     *
     * @param bitmap
     */

    private void uploadInverse(Bitmap bitmap) {
        upload(bitmap, "inverse.png");
        model.getInverse( MyApplication.Token);

    }


    /**
     * 手持
     *
     * @param bitmap
     */
    private void uploadArmed_With_Knives(Bitmap bitmap) {
        upload(bitmap, "Knives.png");
        model.getArmed_With_Knives(MyApplication.Token);

    }

    /**
     * 正面处理
     *
     * @param bitmap
     */
    private void uploadFace(Bitmap bitmap) {
        upload(bitmap, "face.png");
        model.getFace(MyApplication.Token);

    }


    private void upload(Bitmap bitmap, String s) {
        //版本大于6.0的情况
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }

        }
        try {
            //此处没有判断是否有sd卡
            File dirFile = new File("sdcard/android/cache");
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File file = new File(dirFile, s);
            //图片压缩 100为不对图片进行压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void photo(String ResponseFaces) {
        Intent i = new Intent(ActivityAuthentication.this, ActivityPhoto.class);
        i.putExtra("photo", ResponseFaces);
        startActivity(i);
    }


    //已经认证
    private void AlreadyAuthen() {

        Glide.with(ActivityAuthentication.this)
                .load(ActivtyMainTab.getInstance().bean.resumeidcardface)
                .into(mage_face);

        Glide.with(ActivityAuthentication.this)
                .load(ActivtyMainTab.getInstance().bean.resumeidcardback)
                .into(mage_inverse);

        Glide.with(ActivityAuthentication.this)
                .load(ActivtyMainTab.getInstance().bean.resumeidcardhand)
                .into(armed_with_knives_mage);


        et_phone.setText(ActivtyMainTab.getInstance().bean.resumemobile);
        et_name.setText(ActivtyMainTab.getInstance().bean.resumerealname);
        TxSex.setText(ActivtyMainTab.getInstance().bean.resumegender);
        TxBorth.setText(ActivtyMainTab.getInstance().bean.resumebirthday);
        zhuangtai.setText(ActivtyMainTab.getInstance().bean.resumeworkstatus);
        et_firm.setText(ActivtyMainTab.getInstance().bean.resumecompanyname);
        nature.setText(ActivtyMainTab.getInstance().bean.resumecompanynature);
        et_post.setText(ActivtyMainTab.getInstance().bean.resumefuntypename);
        year.setText(ActivtyMainTab.getInstance().bean.resumeworkyear);
        diqu.setText(ActivtyMainTab.getInstance().bean.resumelocation);
        address.setText(ActivtyMainTab.getInstance().bean.resumelocationdetail);
        carded.setText(ActivtyMainTab.getInstance().bean.resumeidnumber);
        et_emial.setText(ActivtyMainTab.getInstance().bean.resumeemail);

        list_job.setVisibility(View.VISIBLE);
        AuthenJobAdapter2 mAdapter = new AuthenJobAdapter2(ActivityAuthentication.this, ActivtyMainTab.getInstance().bean.resumework);
        list_job.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        new ListViewHeight(list_job);


    }


    public void norivise() {
        commit.setVisibility(View.GONE);
        card_code.setEnabled(false);
        et_phone.setEnabled(false);
        et_ver_code.setEnabled(false);
        et_name.setEnabled(false);
        et_firm.setEnabled(false);
        address.setEnabled(false);
        carded.setEnabled(false);
        et_emial.setEnabled(false);
        mage_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photo(ActivtyMainTab.getInstance().bean.resumeidcardface);
            }
        });
        mage_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photo(ActivtyMainTab.getInstance().bean.resumeidcardback);
            }
        });
        armed_with_knives_mage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photo(ActivtyMainTab.getInstance().bean.resumeidcardhand);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
        SingleVolleyRequestQueue.getInstance(ActivityAuthentication.this).cancelToRequestQueue(stringRequest);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 3) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Camera(codes);
//            } else {
//                Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_LONG).show();
//            }
//

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (grantResults.length > 0 && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                        Camera(codes);
                    } else {
                        Toast.makeText(this, "请在应用管理中打开“存储”访问权限！", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "请在应用管理中打开“存储”访问权限！", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_LONG).show();
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getPersimmionsCAMERA() {
//        // 获取相机权限
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
//                    3);
//        }



        List<String> permissionsNeeded = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED) {
            permissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            permissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            permissionsNeeded.add(Manifest.permission.CAMERA);

        }
        if(permissionsNeeded.size()>0){
            ActivityCompat.requestPermissions(this, permissionsNeeded.toArray(new String[permissionsNeeded.size()]), 3);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSVProgressHUD.dismissImmediately();
    }






}
