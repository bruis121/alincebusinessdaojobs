package com.fangfas.alincebusinessdaojobs;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class AppConst{
//    http://192.168.0.8:8080/lhsd/admin/
//    http://171.221.175.80:8080/lhsdzs/
    public static final String Main="http://171.221.175.80:8080/lhsdzs/";
    //首页销售任务
    public static final String HOME_SALES="index.php/task/index?";

    //首页销售任务排序获取类型
    public   static  final  String  GETTYPE="index.php/task/investTypeList";

    //放弃任务
    public   static   final   String  ABANDONED="index.php/task/abandonTask";
    //详情页
    public   static  final  String  DETAILS="index.php/products/getProductHeader";
    //加入收藏
    public   static  final  String  Collect_Btn="index.php/collect/addCollect";
    //我的销售收藏
    public   static   final   String  SALESCOLLECTION="index.php/collect/sellCollectList?";
   //我的任务
    public   static   final   String  SALESTASKS="index.php/mytask/index?size=5";
  //领取任务
    public   static   final  String  RECEIVETASK="index.php/task/getTask";
  //合同详情
    public   static   final   String  CONTRACT="index.php/task/contrictView";
   //任务进度头部
    public   static   final   String  TASKSETBACKS="index.php/task/taskGetView";
    //任务进度中间部分
    public   static   final   String    TASKSETABCKSCONTENT="index.php/task/contrictViewByGet";
    //任务进度进度部分
    public   static   final   String   TASKSETABCKS3="index.php/task/taskStatus";

    //详情页--产品展示
    public  static   final   String  DISPLAY="index.php/products/getcontent1";

    //详情页底部--参数详情
    public  static   final   String  DETAILSPRODUCT="index.php/products/getProductContent";

    //详情页--政策
    public   static   final   String  ZHENGCE="index.php/products/getcontent4";
   //物流
    public  static   final   String  LOGISTICAL="index.php/logistics/getLogistics";

    //查看物流单据
    public   static   final   String  LOOKINVOICES="index.php/task/getDanju";
   //我的设置
    public   static   final   String  MYSETTING="index.php/myset/getmyset";

    //进度中已结算查看
    public   static   final   String  JIESUANHISTORY="index.php/royalty/index";

    //我的任务账户
    public   static   final   String  MYTASKACCOUNT="index.php/account/index/get";

    //我的任务下级
    public   static   final    String  SUORDINATE="index.php/account/childTaskList";
    public   static   final   String    SUORDINATEName="index.php/account/getNameList";

    //获取验证码
    public  static   final   String    HUOQUSMS="index.php/Authentication/sendCaptcha";
    //获取上传头像的header
    public   static   final  String   GETHDEAR="index.php/api/getHeader";
   //身份证上传
    public  static   final   String  CARD="http://api.jiushang.cn//api/job/resumeidcard";
  //行业
    public   static   final   String  HANGYE="http://api.jiushang.cn/api/job/resumeindustry";
    //添加工作经历
//    public  static   final   String  JINGLI="lhsd/admin/index.php/Authentication/jobExperiencesAdd";
    public  static   final   String  ADDEXPERIENCE="index.php/Authentication/addexperience";
   //修改工作经历
   public  static   final   String  EDITEXPERIENCE="index.php/Authentication/editexperience";
  //删除
  public  static   final   String  DeletePERIENCE="index.php/Authentication/delexperience";
    //身份证认证
    public   static   final   String  AUTHENTCATION="index.php/Authentication/jobResume";
  //获取认证信息
    public  static   final  String     RENGZHENGDATA="index.php/api/getResumedetails";
 //获取认证信息内选择信息
    public  static   final   String    GetCHOOSE="http://api.jiushang.cn/api/job/resumeparameter";
 //判断是否收藏或领取任务
    public   static  final   String  GETGTID="index.php/products/getGtid?trid=";
  //取消收藏
    public  static   final   String   CANCElCOLLECTION="index.php/collect/cancelCollect";

    //招商栏介绍--详情
    public   static   final  String   ZHANGSHANGDetAils="http://www.jiushang.cn/plugin.php?id=api_service&mod=jsw_jz&ac=sc";
   //广告栏目介绍--详情
    public  static   final   String   GUANGGAODetAils="http://www.jiushang.cn/plugin.php?id=api_service&mod=jsw_jz&ac=about";
    //招商报价及点位介绍
    public  static   final   String     ZHANGSHANGPRICE="http://www.jiushang.cn/plugin.php?id=api_service&mod=jsw_jz&ac=zs";
    //广告报价及点位介绍
    public  static   final   String     GUANGGAOPRICE="http://www.jiushang.cn/plugin.php?id=api_service&mod=jsw_jz&ac=jsw_jz";
    //是否绑定支付方式
    public  static  final  String  ISBINGING="index.php/account/isBindingAccount";
    //绑定支付方式
    public  static  final   String  BINDING="index.php/account/bindingAccount";
    //提现
    public  static  final   String  WITHDRWCASH="index.php/account/withdrawApply ";
    //余额
    public  static  final   String  USEMONEY="index.php/account/useMoney";
    //银行
    public   static  final   String  BANKLIST="index.php/account/bankList";

}
