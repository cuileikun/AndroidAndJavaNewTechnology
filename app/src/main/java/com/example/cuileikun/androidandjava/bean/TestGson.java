package com.example.cuileikun.androidandjava.bean;

import java.util.List;

/**
 * 作者：popular cui
 * 时间：2017/4/13 17:41
 * 功能:
 */
public class TestGson {


    /**
     * customerList : [{"romAddress":"沈砖公路5160弄50支弄14号103室A室1S","romRent":4568.98,"romVacantDays":217,"sacCheckinTime":"2016-11-07 13:57:44","sacCreateId":4456,"sacCreateName":"天丰2号","sacCutMobile":"18205167754","sacCutName":"宇文","sacId":1593922,"sacRevePrice":1300},{"romAddress":"志丹路501弄29号101-2室A室1SY","romRent":3130.98,"romVacantDays":60,"sacCheckinTime":"2016-11-03 20:17:03","sacCreateId":4456,"sacCreateName":"天丰2号","sacCutMobile":"18525368588","sacCutName":"吴","sacId":1593890,"sacRevePrice":11111}]
     * error : 成功
     * result : 0
     */

    private String error;
    private int result;
    private List<CustomerListBean> customerList;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<CustomerListBean> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerListBean> customerList) {
        this.customerList = customerList;
    }

    public static class CustomerListBean {
        /**
         * romAddress : 沈砖公路5160弄50支弄14号103室A室1S
         * romRent : 4568.98
         * romVacantDays : 217
         * sacCheckinTime : 2016-11-07 13:57:44
         * sacCreateId : 4456
         * sacCreateName : 天丰2号
         * sacCutMobile : 18205167754
         * sacCutName : 宇文
         * sacId : 1593922
         * sacRevePrice : 1300.0
         */

        private String romAddress;
        private double romRent;
        private int romVacantDays;
        private String sacCheckinTime;
        private int sacCreateId;
        private String sacCreateName;
        private String sacCutMobile;
        private String sacCutName;
        private int sacId;
        private double sacRevePrice;

        public String getRomAddress() {
            return romAddress;
        }

        public void setRomAddress(String romAddress) {
            this.romAddress = romAddress;
        }

        public double getRomRent() {
            return romRent;
        }

        public void setRomRent(double romRent) {
            this.romRent = romRent;
        }

        public int getRomVacantDays() {
            return romVacantDays;
        }

        public void setRomVacantDays(int romVacantDays) {
            this.romVacantDays = romVacantDays;
        }

        public String getSacCheckinTime() {
            return sacCheckinTime;
        }

        public void setSacCheckinTime(String sacCheckinTime) {
            this.sacCheckinTime = sacCheckinTime;
        }

        public int getSacCreateId() {
            return sacCreateId;
        }

        public void setSacCreateId(int sacCreateId) {
            this.sacCreateId = sacCreateId;
        }

        public String getSacCreateName() {
            return sacCreateName;
        }

        public void setSacCreateName(String sacCreateName) {
            this.sacCreateName = sacCreateName;
        }

        public String getSacCutMobile() {
            return sacCutMobile;
        }

        public void setSacCutMobile(String sacCutMobile) {
            this.sacCutMobile = sacCutMobile;
        }

        public String getSacCutName() {
            return sacCutName;
        }

        public void setSacCutName(String sacCutName) {
            this.sacCutName = sacCutName;
        }

        public int getSacId() {
            return sacId;
        }

        public void setSacId(int sacId) {
            this.sacId = sacId;
        }

        public double getSacRevePrice() {
            return sacRevePrice;
        }

        public void setSacRevePrice(double sacRevePrice) {
            this.sacRevePrice = sacRevePrice;
        }
    }
}
