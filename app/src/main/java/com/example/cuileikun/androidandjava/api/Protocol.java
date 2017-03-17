package com.example.cuileikun.androidandjava.api;

/**
 * 接口地址
 */
public class Protocol {
    //用户注册
    public static String MEMBER_FGYId = "easemob/customerFgy/customerList.json";
    public static String LOGIN_JSON = "/fgy/app/login/login.json";
    public static String TOKEN_JSON = "/fgy/app/login/token.json";
    public static String LOGIN = "login";
    /**
     * 待回访信息列表接口（包括每月回访，到期回访与搜索接口合并）
     */
    public static String NO_VISIT_ROOM = "/fgy/app/visit/undone/list.json";

    /**
     * 当月到期访问接口
     */
    public static String CURRENT_MONTH_EXPIRE = "/fgy/app/visit/undone/expireList.json";
    /**
     * 回访信息录入显示
     */
    public static String RETURN_VISIT_INPUT_SHOW = "/fgy/app/visit/create/show.json";
    /**
     * 回访信息录入保存
     */
    public static String RETURN_VISIT_INPUT_SAVE = "/fgy/app/visit/create/save.json";
    /**
     * 未带看勘察列表
     */
    public static String GET_WAITING_SURVERY_ROOMS = "/fgy/app/daikanList/getWaitingSurveyRooms.json";
    /**
     * 已带看侦察列表
     */
    public static String GET_SEE_SURVERY_ROOMS = "/fgy/app/daikanList/getSeeSurveyRooms.json";
    /**
     * 新增客户信息接口(区域，价格，房型，户型)
     */
    public static String DEMAND_LIST = "/fgy/app/customer/create/show.json";
    /**
     * 账号重复登录检查接口
     */
    public static String REPEAT_LOGIN_CHECK = "/fgy/app/login/repeatLoginCheck.json";
    /**
     * 新增客户信息保存接口
     */
    public static String PUT_DEMAND_LIST = "/fgy/app/customer/create/save.json";
    /**
     * 回访记录列表接口
     */
    public static String RETURN_VISIT_RESULT_LIST = "/fgy/app/visit/history/customerList.json";
    /**
     * 已带看详情接口
     */
    public static String GET_SEE_ROOM_SDETAIL = "/fgy/app/daikanList/getSeeRoomsDetail.json";
    /**
     * 历史回访信息列表接口（包括每月回访，到期回访，带看回访）
     */
    public static String HISTORY_RETURN_VISIT_RESULT_LIST = "/fgy/app/visit/history/addressList.json";
    /**
     * 回访详情
     */
    public static String RETURN_VISIT_DETAIL = "/fgy/app/visit/history/detail.json";
    /**
     * 已勘察详情
     */
    public static String GET_SEE_SURVERY_DETAIL = "/fgy/app/daikanList/getSeeSurveyDetail.json";
    /**
     * 撤销带看勘察
     */
    public static String CANCEL = "/fgy/app/daikan/cancel.json";
    /**
     * 登记带看显示
     */
    public static String REGRISTRATION_WITH_LOOK_SHOW = "/fgy/app/daikan/registersee.json";
    /**
     * 读取客户信息
     */
    public static String LOAD_CLIENT_INFO = "/fgy/app/daikan/autoLoadInfo.json";
    /**
     * 发起（带看/勘察）显示接口	,延时带看延时勘察接口(saleCheckId: 如果是null，那就是新发起的带看或者是勘察。如果不是null，那就是延时开看或者勘察.)
     */
    public static String INITIATE = "/fgy/app/daikan/initiate.json";
    /**
     * 发起（带看/勘察）提交接口 延时带看延时勘察接口
     */
    public static String INITIATESUBMIT = "/fgy/app/daikan/initiateSubmit.json";
    /**
     * 带看提交接口(与签退接口合并）
     */
    public static String REGISTERSEESUBMIT = "/fgy/app/daikan/registerseeSubmit.json";
    /**
     * 登记勘察显示接口(与签到接口合并)
     */
    public static String SURVEY = "/fgy/app/daikan/survey.json";
    /**
     * 勘察提交接口(与签退接口合并)
     */
    public static String SURVEYSUBMIT = "/fgy/app/daikan/surveySubmit.json";
    /**
     * 房源列表初期显示接口
     */
    public static String ROOMLIST = "/fgy/app/room/getFgyRoomList.json";
    /**
     * 报修根据客户手机号查询会员和房间信息接口
     */
    public static String CUSTOMER_ROM_dETAIL = "/fgy/app/repair/getCustomerRomDetail.json";
    /**
     * 报修提交接口
     */
    public static String CREATEREPAIR = "/fgy/app/repair/createRepair.json";
    /**
     * 修改客户信息列表接口
     */
    public static String USERLIST = "/fgy/app/customer/list.json";
    /**
     * 修改客户信息界面接口
     */
    public static String MODIFYSHOW = "/fgy/app/customer/modify/show.json";
    /**
     * 修改客户信息保存接口
     */
    public static String MODIFYSAVE = "/fgy/app/customer/modify/save.json";
    /**
     * 发布渠道录入显示接口
     */
    public static String RELEASEDINTOSHOW = "/fgy/app/approach/show.json";
    /**
     * 发起渠道录入保存接口
     */
    public static String SAVERELEASEDINTO = "/fgy/app/approach/save.json";
    /**
     * 报修显示
     */
    public static String SHOWREPAIR = "/fgy/app/repair/showRepair.json";
    /**
     * 版本检测
     */
    public static String VERSION = "/fgy/app/upgrade/version.json";
    /**
     * 每日勘查
     */
    public static String SURVERYOFDAY = "/fgy/app/room/getSurveryOfDayList.json";
    /**
     * 设备号更换次数检查接口
     */
    public static String CHANGEDEVICECNT = "/fgy/app/login/changDeviceCnt.json";
    /**
     * 检查锁定状态接口(在发起带看和登记带看显示接口之前调用)
     */
    public static String CHECKISEXITDAIKAN = "/fgy/app/daikanList/checkIsExistsDaiKan.json";
    /**
     * 勘察重复检查接口(在发起勘察按钮按下时调用)
     */
    public static String CHECKISEXISTSSURVEY = "/fgy/app/daikanList/checkIsExistsSurvey.json";
    /**
     * 当月到期回访列表接口
     */
    public static String EXPIRELIST = "/fgy/app/visit/undone/expireList.json";
    /**
     * 当月回访到期显示接口
     */
    public static String EXPIRESHOW = "/fgy/app/visit/create/expireShow.json";
    /**
     * 当月回访到期保存接口
     */
    public static String EXPIRESAVE = "/fgy/app/visit/create/expireSave.json";
    /**
     * 获得实时榜初期表示列表接口
     */
    public static String GETREALTIMELIST = "/fgy/app/rankList/getRealTimeList.json";
    /**
     * 获得实时榜推送列表接口
     */
    public static String GETREALTIMEPUSHLIST = "/fgy/app/rankList/getRealTimePushList.json";
    /**
     * 获取排行榜列表接口
     */
    public static String GETVOLUMERANKLIST = "/fgy/app/rankList/getVolumeRankList.json";

    /***
     * 待租房间列表接口
     */
    public static String GETFORRENTROOMLIST = "/fgy/app/room/getFgyDaizuRoomList.json";



    /***
     * 待维修间列表接口
     */
    public static String GETFORREPAIREDROOMLIST = "/fgy/app/room/getWaitRepairRoomList.json";
    /**
     * 根据用户权限获取区域
     */
    public static String AREAUTHLIST = "/common/area/get/areauthlist.json";

    //add by clk on
    /***
     * 7.3获取签约客户列表接口
     */
    public static String GETSIGNCONTRACTLIST = "/fgy/app/signContract/signContractList.json";
    /***
     * 7.4获取续租客户列表接口
     */
    public static String GETRENEWALCONTRACTLIST = "/fgy/app/reletContract/reletContractList.json";
    //获取续租客户对应的各item对应的数量
    public static String GET_RENEWALCONTRACT_ITEM_SCOUNT = "/fgy/app/reletContract/reletSumTotal.json";
    /***
     * 7.1客户线索列表接口和客户线索总人数接口
     */
    public static String CUMLIST = "/fgy/app/customer/get/cumlist.json";
    public static String CUSTOMER_CLUE_NUMBER = "/fgy/app/customer/get/cumlistStat.json";



    /**
     * 7.2获取带看客户列表
     */
    public static String GETBELTCONTRACTLIST = "/fgy/app/WaitViewCut/getList.json";
    //获取带看客户对应的items的数量
    public static String GET_BELTCONTRACT_ITEMS_COUNT = "/fgy/app/WaitViewCut/getListStat.json";

    /**
     * 歌曲下载
     */
    public static String GET_SONG_VERSION = "/fgy/app/upgrade/songVersion.json";

    /**
     * 查看剩余电量
     */
    public static String GET_REMAIN_POWER = "/fgy/app/daikan/getRemainPowerByRoom.json";

    /**
     * 获取当前系统时间
     */
    public static String GET_RANK_LIST_CURRENT_DATE= "/fgy/app/rankList/getCurrentDate.json";

    /**
     * 排行榜单数据接口
     * userId: 登录的房管员id
     * conditionDate: 排行榜日期范围查询条件：0.日，1.周，2.月
     * conditionArea: 排行榜区域查询条件：0.房管员，1.服务中心，2.分公司
     * year:年份
     * queryRange: conditionDate为0  queryRange取值为 日期（2016-10-21）
     * conditionDate为1  queryRange取值为第几周（22）
     * conditionDate为2  queryRange取值为第几月（10）
     */
    public static String GET_RANK_LIST_VOLUME_RANK_LIST= "/fgy/app/rankList/getVolumeRankList.json";

    //add by clk off


    public static String AREALIST = "/common/area/get/arelist.json";
    /**
     * 获取待租房数量接口
     */
    public static String GETDAIZUROOMCNT = "/fgy/app/room/getDaizuRoomCnt.json";
    /**
     * 待维修房间数量接口
     */
    public static String GETWAITREPAIRROOMCNT = "/fgy/app/room/getWaitRepairRoomCnt.json";







    //***************以下接口为IP地址后面携带hm的url(退房模块)*****************
    /**
     * 列表
     */
    public static String CHECKOUTLIST = "/fgy/app/qrexp/roomlist.json";

    /**
     * 详情查看接口
     */
    public static String EXCEPTIONDETAIL = "/fgy/app/qrexp/qrdetail.json";
    /**
     * (显示)调差项目类型接口
     */
    public static String ADJUSTMENTPROJECTITEM = "/fgy/app/qrexp/dfItems.json";

    /**
     * (保存调差项目数据)调差持久化接口
     */
    public static String SAVEADJUSTPROJECT = "/fgy/app/qrexp/adjust.json";

    /**
     *退房审批通过/驳回
     */
    public static String APPROVEORREJECT = "/fgy/app/qrexp/approve.json";

    /**
     * 9.1.5正常退房 待验房 登记验房 当前房间剩余电量接口  卢敏的
     */
    //public static String GET_CHECK_OUT_REMAIN_POWER = "/fgy/app/daikan/getRemainPowerByRoom.json";
    /**
     * 9.1.5正常退房 待验房 登记验房 当前房间剩余电量接口  李飞飞的
     */
    public static String GET_CHECK_OUT_REMAIN_POWER = "/fgy/app/qrnom/getRemainPowerByRoom.json";

    /**
     * 9.1.1正常退房  发起勘察 延期勘察 发起验房  延期验房提交接口
     */
    public static String CHECKOUTINITIATESUBMIT = "/fgy/app/qrnom/precheck.json";

    /**
     * 9.2 异常退房 凭证上报接口
     */
    public static String VOUCHERREPORT = "/fgy/app/qrexp/proof.json";

    /**
     * 房管员列表接口
     */
    public static String SALESLIST= "/fgy/app/qrnom/saleslist.json";
    /**
     * 变更验房房管员
     */
    public static String CHECKER = "/fgy/app/qrnom/checker.json";

    /**
     * 不结算退房 申请退房操作
     */
    public static String NOSETTLECHECKER = "/fgy/app/qrnst/noSettAppy.json";


}

