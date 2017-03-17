package com.example.cuileikun.androidandjava.activity.Constant;

import com.qk.applibrary.util.CommonUtil;

/**
 * 保存配置
 */
public class QkConstant {
    public static final String ID_USERNAME = "username";
    public static final String ID_PASSWORD = "password";
    public static final String USERID = "uid";
    public static final int PAGE_SIZE = 10;//每页加载10条
    public static final String QK_LOG_DIRECTORY = "/qk/log";//日志日志目录
    public static final String PHOTO_LOG_DIRECTORY = "/qk/photo";//图片日志目录
    public static final String DB_Name="HouseKeeper.db";
    public static final String PUSH_TOKEN="PUSH_TOKEN";
    public static final String CODE = "utf-8";
    public static final String URLPOSITION = "position";
    public static final String TIMES = "times";


    /**
     * 回访信息录入传递参数
     */
    public static final class VisitInformationInputDef {
        // public static final String ROOM_INF = "room_inf";//房间信息
        public static final String RETURN_VISIT_CATEGORY = "return_visit_category";//回访分类
        public static final String RETURN_VISIT_TYPE = "return_visit_type";//回访类型
        public static final String RETURN_VISIT_ID = "return_visit_id";//回访id
        public static final int NORMAL_RETURN_VISIT = 1001;//普通回访
        public static final int WITH_LOOK_RETURN_VISIT = 1002;//带看回访
        public static final String ROOM_INFO = "room_info";//房间信息
        public static final int HISTORY_RETURN_VISIT = 1003;//历史回访
        public static final String CLIENT_INFO = "client_info";//客户信息
        //        public static final int MONTHLY_RETURN_VISIT= 1;//每月回访
//        public static final int MATURITY_RETURN_VISIT= 2;//到期回访
        public static final String CLIENT_ID = "client_id";//客户id
    }

    /**
     * 配置文件
     */
    public static final class SharedPreferencesKeyDef {
        public static final String USER_ACCOUNT = "login_account";//用户账号
        public static final String TOKEN = "token";
        public static final String USER_NAME = "user_name";  //员工姓名
        public static final String USER_ID = "user_id"; //用户id
        public static final String USER_PASSWORD = "user_password";//用户密码
        public static final String MOBILE = "mobile";  //手机号
        public static final String FILE_NAME = "housekeeper"; //配置文件名
        public static final String DOWN_LOAD_APP_URL = "download_load_app_rul";// 连接到下载页面的二维码
        public static final String FGY_ID_URL = "fgy_id_url";//包含登录房管员id的接口url（用来绑定房管员和中介人员的关系）
        public static final String USER_AREA_IDS = "user_area_ids";// oa菜单中分配给这个房管员的服务中心权限
        public static final String SERVERS_CENTER_ID = "serves_center_id";//登录房管员所属的服务中心id
        public static final String ERWEIMA = "ermawei_url";//二维码下载链接
        public static final String USER_POSITION = "user_position";//0:房管员 1:服务中心经理 2.分公司经理
        public static final String VACANTDAYCON="vacantDayCon"; // 0.待租房间 1.空置【30-60】天 2.空置【60-90】天 3.空置90天及以上
        public static String IS_REMEMBER_PASSWORD = "isRememberPassword";
        public static final String CHANGLIANGZHI = "changliangzhi";//测试常量值

    }




    /**
     * 我的带看我的勘察传递参数
     */
    public static final class MyBeltReconnitreDef {
        /**
         * 表示我的带看
         */
        public static final int TYPE_MYBELT = 0;
        /**
         * 表示我的勘察
         */
        public static final int TYPE_RECONNITRE = 1;
        public static final String WAITSURVERYROOM = "room";
        public static final String TYPE = "mybeltwaitdetailaddress";
        public static final String LISTNAME = "listName";
        public static final String SURVERY = "survery";
        /**
         * 登记带看取用户信息时间间隔
         */
        public static final long GETUSERINFOTIME = 10000;

    }

    /**
     * 房间列表
     */
    public static final class RoomListDef {
        public static final String kEYET = "seek";
        public static final String yuding = "预定";
    }

    /**
     * 修改客户信息列表传递参数
     */
    public static final class ModifyuserDef {
        public static final String MODIFYSHOWBEAN = "Modify";
    }

    /**
     * 房间地址
     */
    public static final class RoomAddressDef {
        public static final String ROOMADDRESS = "RoomAddress";
        public static final String ROOMCANCEL = "RomCancel";
        public static final String ROOMADDRESSID = "RoomAddressid";
        public static final String ROOMLock = "RoomLock";
        public static final String SDID = "sdId";
    }

    /**
     * 版本更新会员app下载二维码图片链接
     */
    public static final class MemberAppUrl {
        public static final String MEMBERAPPURL = "MemberAppUrl";
    }

    /**
     * 试算器webview
     */
    public static final class WebDef{
        public static final String WEB_URL = "web_url";
        public static final String WEB_NAME = "web_name";
        //0:从首页进入的试算器  1:销售成本  2:降价和空置  3:合同成交
        public static final int MODULECODE0 = 0;
        public static final int MODULECODE1 = 1;
        public static final int MODULECODE2 = 2;
        public static final int MODULECODE3 = 3;
        //title
        public static final String TITLE0 = "8.1试算器";
        public static final String TITLE1 = "6.1.1销售成本";
        public static final String TITLE2 = "6.1.2降价和空置";
        public static final String TITLE3 = "6.1.3合同成交";

    }

    /**
     * 排行榜传递参数常量
     */
    public static final class RankingListIntentDef{
        public static final String RANKING_INFO = "ranking_info";//排行榜信息
        public static final String CURRENT_DATE = "current_date";//当前日期
    }


    //下载歌曲的路径
    public static final String DOWN_SONGS_SAVE_PATH= CommonUtil.getSDCardPath() + "/qk/SONGS";

    //下载歌曲的路径
    public static final String DOWN_SONGS_UPZIP_PATH= CommonUtil.getSDCardPath() + "/qk/upzip";

    //下载歌曲的歌曲名字存放
    public static final String DOWN_SONGS_SAVE_NAME= "songs.zip";

    public static final class History{
        public static final String SEARCHHISTORY = "search_history_servicecenter";
        public static final String ACCOUNT = "account";
        public static final String ACCOUNTDETAIL = "accountDetail";

        //9.3 房管员不结算退房历史记录key
        public static final String FGYNOSETTLESEARCHHISTORY = "search_history_fyg_no_settle";
        //9.4 房管员结算单查询历史记录key
        public static final String FGYStateMentSEARCHHISTORY = "search_history_fyg_statement";

    }
    //分公司异常
    public static final class ExceptionCompany{
        public static final String WAITEXAMINATIONDETAIL = "wait_examination_detail";  //9.1.1审批
        public static final String HISTORYDETAIL = "history_detail";  //9.1.2详情查看

    }

    //运营中心异常
    public static final class ExceptionOperater{
        public static final String WAITEXAMINATIONDETAIL = "wait_examination_detail";  //9.1.1审批
        public static final String HISTORYDETAIL = "history_detail";                   //9.1.2详情查看
        public static final int UPDATEADJUSTPRICE=1111;
    }
    //服务中心审批
    public static final class checkoutServiceCenter{
        public static final String CHECKOUTENTITY = "checkoutentity";
        public static final String SALESLISTENTITY = "salesListEntity";
    }

    //房管员
    public static final class checkoutFgy{
        public static final String FGYCHECKOUTENTITY = "checkoutentity";
        public static final String YUANCHENGYANSHOUPACKAGENAME = "com.qk365.remoteacceptance";
    }

}
