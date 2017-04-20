package com.example.cuileikun.androidandjava.api;

public class Connect {

    private static Connect connect;
    /**
     * 后台服务器地址
     */
    private String apiUrl;//基地址带mdk
    private String apiHmUrl;//基地址带hm
    private String pushUrl;//推送平台的基地址
    private String appChannelId;//推送渠道id
    private String priceApiUrl;// 定价系统
    private String settlementUrl;//结算单地址
    private String rankPrivoider;//排行榜地址
    private String scanCardUrl;
    private String apiMyRoomUrl;//基地址带myroom add by clk
    private String healthyUrl;
    private String aliParams;//获取阿里参数即地址
    private String customerGrabsingle;//客户线索抢单


    private String ScanClientCodeUrl;


    private static int environmentType;//环境类型 1是正式 2是仿真 3是测试 4审计环境


    public String getApiMyRoomUrl() {
        return apiMyRoomUrl;
    }

    public void setApiMyRoomUrl(String apiMyRoomUrl) {
        this.apiMyRoomUrl = apiMyRoomUrl;
    }

    public static Connect getInstance() {
        if (connect == null) {
            connect = new Connect();
        }
        environmentType = 3;
        changeEnvironment();
        return connect;
    }

    /**
     * 切换环境
     */
    private static void changeEnvironment() {
        switch (environmentType) {
            case 1:
                /**
                 * 切换正式环境
                 */
                switchOfficial();
                break;
            case 2:
                /**
                 * 切换仿真环境
                 */
                switchSimulation();
                break;
            case 3:
                /**
                 * 切换测试环境
                 */
                switchTest();
                break;
            case 4:
                /**
                 * 切换测试环境
                 */
                switchAudit();
                break;
        }
    }

    /**
     * 切换正式环境
     */
    public static void switchOfficial() {
        connect.setPushUrl("http://58.215.175.243:8585/qkMessagePushProvider");//推送平台正式地址
        connect.setApiUrl("http://58.215.175.244:8080/mdk");//正式
        connect.setApiHmUrl("http://58.215.175.244:8080/hmconsumer");//退房
        connect.setApiMyRoomUrl("http://58.215.175.244:8080/hdkprovider");//我的房间
        connect.setAppChannelId("043ef3db-82f7-11e6-a590-a0481c7d4a7c"); //极光推送渠道id测试和正式
        connect.setPriceApiUrl("http://mdj.qk365.com/User/Login");//定价系统正式地址
        connect.setSettlementUrl("http://api.qk365.com/mobile");//结算单正式地址
        connect.setRankPrivoider("http://58.215.175.244:8080/rankPrivoider");// 排行榜正式
        connect.setScanCardUrl("http://kpff.qk365.com:83/api");//扫码正式
        connect.setHealthyUrl("http://58.215.175.244:8080/hdkprovider");// 我的健康度
        connect.setAliParams("http://58.215.175.244:8080");//获取阿里参数地址正式
        connect.setCustomerGrabsingle("http://58.215.175.244:83");//客户线索抢单
    }

    /**
     * 切换测试环境
     */
    public static void switchTest() {
        connect.setPushUrl("http://192.168.1.236:58706/qkMessagePushProvider");//推送平台测试基地址
        connect.setApiUrl("http://192.168.1.72:89/mdk");//72测试mdk

//        connect.setApiUrl("http://192.168.1.74:41111/mdk");//74开发地址mdk
//        connect.setApiHmUrl("http://192.168.1.74:41111/hmconsumer");//74开发地址 hmconsumer

        connect.setApiHmUrl("http://192.168.1.72:89/hmconsumer");//测试hmconsumer
        connect.setApiMyRoomUrl("http://192.168.1.72:20172/myroom");//测试我的房间
        connect.setAppChannelId("043ef3db-82f7-11e6-a590-a0481c7d4a7c"); //极光推送渠道id测试和正式
        connect.setPriceApiUrl("http://192.168.1.86:8080/User/Login");//定价系统测试环境
        connect.setSettlementUrl("http://wx01.test.qk365.com/mobile");//结算单测试环境
        connect.setRankPrivoider("http://192.168.1.72:41003/rankPrivoider");//排行榜测试环境
//        connect.setScanCardUrl("http://qktest.chinacloudapp.cn:8082/api");//扫码测试地址
        connect.setScanCardUrl("http://192.168.1.118:9000/api");//扫码测试地址
        connect.setHealthyUrl("http://192.168.1.72:20172/myroom");//我的健康度测试
        connect.setAliParams("http://139.219.192.169");//获取阿里参数地址
        connect.setCustomerGrabsingle("http://192.168.1.73:2000/index.html");//客户线索抢单

    }

    /**
     * 切换审计环境
     */
    public static void switchAudit() {

    }

    /**
     * 切换仿真环境
     */
    public static void switchSimulation() {
        connect.setPushUrl("http://192.168.1.13:45106/qkMessagePushProvider");//推送平台仿真地址
        connect.setApiUrl("http://139.219.236.183:8082/mdk");//仿真mdk
        connect.setApiHmUrl("http://139.219.236.183:8082/hmconsumer");//退房仿真
        connect.setApiMyRoomUrl("http://139.219.236.183:35306/myroom");//仿真我的房间
        connect.setAppChannelId("5737711b-86e7-11e6-9ea1-525400d85155"); //仿真极光推送渠道id
        connect.setPriceApiUrl("http://192.168.1.58:81/User/Login");//定价系统仿真地址
        connect.setSettlementUrl("http://wx03.test.qk365.com/mobile");//结算单仿真地址
        connect.setRankPrivoider("http://139.219.236.183:45306/rankPrivoider");//排行榜仿真
        connect.setScanCardUrl("http://qksim.chinacloudapp.cn:8089/api");//扫码仿真外网映射
        connect.setHealthyUrl("http://139.219.236.183:35306/myroom");//我的健康度仿真
        connect.setAliParams("http://139.219.236.183:8082");//获取阿里参数地址仿真
        connect.setCustomerGrabsingle("http://139.219.236.183:8088");//客户线索抢单

    }


    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getAppChannelId() {
        return appChannelId;
    }

    public void setAppChannelId(String appChannelId) {
        this.appChannelId = appChannelId;
    }

    public String getPriceApiUrl() {
        return priceApiUrl;
    }

    public void setPriceApiUrl(String priceApiUrl) {
        this.priceApiUrl = priceApiUrl;
    }

    public String getApiHmUrl() {
        return apiHmUrl;
    }

    public void setApiHmUrl(String apiHmUrl) {
        this.apiHmUrl = apiHmUrl;
    }


    public String getSettlementUrl() {
        return settlementUrl;
    }

    public void setSettlementUrl(String settlementUrl) {
        this.settlementUrl = settlementUrl;
    }

    public String getRankPrivoider() {
        return rankPrivoider;
    }

    public void setRankPrivoider(String rankPrivoider) {
        this.rankPrivoider = rankPrivoider;
    }

    public static Connect getConnect() {
        return connect;
    }

    public static void setConnect(Connect connect) {
        Connect.connect = connect;
    }

    public String getScanCardUrl() {
        return scanCardUrl;
    }

    public void setScanCardUrl(String scanCardUrl) {
        this.scanCardUrl = scanCardUrl;
    }

    public String getHealthyUrl() {
        return healthyUrl;
    }

    public void setHealthyUrl(String healthyUrl) {
        this.healthyUrl = healthyUrl;
    }

    public String getAliParams() {
        return aliParams;
    }

    public void setAliParams(String aliParams) {
        this.aliParams = aliParams;
    }

    public String getCustomerGrabsingle() {
        return customerGrabsingle;
    }

    public void setCustomerGrabsingle(String customerGrabsingle) {
        this.customerGrabsingle = customerGrabsingle;
    }

    public String getScanClientCodeUrl() {
        return ScanClientCodeUrl;
    }

    public void setScanClientCodeUrl(String scanClientCodeUrl) {
        ScanClientCodeUrl = scanClientCodeUrl;
    }

}
