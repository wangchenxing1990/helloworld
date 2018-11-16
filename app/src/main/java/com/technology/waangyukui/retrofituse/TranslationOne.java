package com.technology.waangyukui.retrofituse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenvo on 2018/6/8.
 */

public class TranslationOne {

    /**
     * status : 1
     * content : {"from":"en-EU","to":"zh-CN","out":"示例","vendor":"ciba","err_no":0}
     */

    @SerializedName("status")
    private int status;
    @SerializedName("content")
    private ContentBean content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * from : en-EU
         * to : zh-CN
         * out : 示例
         * vendor : ciba
         * err_no : 0
         */

        @SerializedName("from")
        private String from;
        @SerializedName("to")
        private String to;
        @SerializedName("out")
        private String out;
        @SerializedName("vendor")
        private String vendor;
        @SerializedName("err_no")
        private int errNo;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public int getErrNo() {
            return errNo;
        }

        public void setErrNo(int errNo) {
            this.errNo = errNo;
        }
    }
}
