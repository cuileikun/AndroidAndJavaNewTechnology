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

    public static Connect getInstance() {
        if (connect == null) {
            connect = new Connect();
        }

        /**
         * 推送平台地址设置
         */
        connect.setPushUrl("http://192.168.1.236:58706/qkMessagePushProvider");//推送平台测试基地址
//        connect.setPushUrl("http://192.168.1.13:45106/qkMessagePushProvider");//推送平台仿真地址
//        connect.setPushUrl("http://58.215.175.243:8585/qkMessagePushProvider");//推送平台正式地址
        /**
         * 项目地址设置(mdk)
         */
//        connect.setApiUrl("http://192.168.1.72:40306/mdk");//测试
//        connect.setApiUrl("http://116.228.53.202:40306/mdk");//测试外网映射 2016年11月22日18:11:24
        //    connect.setApiUrl("http://58.215.175.244:18106/mdk");//正式
//        connect.setApiUrl("http://192.168.1.43:57806/mdk");//仿真
//        connect.setApiUrl("http://116.228.53.202:60003/mdk");//仿真外网映射
//        connect.setApiUrl("http://192.168.2.251:8081/mdk");//卢敏电脑
        // connect.setApiUrl("http://192.168.2.136:8181/mdkconsumer");//程建乐电脑
//        connect.setApiUrl("http://192.168.2.136:8181/mdkconsumer");//程建乐电脑
//        connect.setApiUrl("http://192.168.2.136:8181/mdkconsumer");//程建乐电脑
//        connect.setApiUrl("http://116.228.53.202:18106/mdk");//审计

        //  connect.setApiUrl("http://192.168.1.74:41111/mdk");

         connect.setApiUrl("http://192.168.1.74:41111/mdk");
        //     connect.setApiUrl("http://192.168.1.72/mdk");//测试

        /**
         * 项目地址设置hm(退房基地址)
         */
       connect.setApiHmUrl("http://192.168.1.74:41111/hmconsumer");
        //     connect.setApiHmUrl("http://192.168.1.72/hmconsumer");//测试


        /**
         * 渠道id设置
         */
        //   connect.setAppChannelId("043ef3db-82f7-11e6-a590-a0481c7d4a7c"); //测试和正式
        connect.setAppChannelId("5737711b-86e7-11e6-9ea1-525400d85155"); //仿真
        /**
         * 定价系统地址设置
         */
        connect.setPriceApiUrl("http://192.168.1.86:8080/User/Login");//定价系统测试地址
//        connect.setPriceApiUrl("http://116.228.53.202:62222/User/Login");//定价系统测试外网映射
//        connect.setPriceApiUrl("http://192.168.1.58:81/User/Login");//定价系统仿真地址
//        connect.setPriceApiUrl("http://mdj.qk365.com/User/Login");//定价系统正式地址

        /**
         * 结算单地址设置
         */
        connect.setSettlementUrl("http://192.168.4.10:8080/mobile");
        return connect;
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
}
