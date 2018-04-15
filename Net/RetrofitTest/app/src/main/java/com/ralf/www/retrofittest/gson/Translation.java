package com.ralf.www.retrofittest.gson;

import java.util.List;

/**
 * Author：Ralf on
 * Create on: 2018/3/14 21:34
 * description：
 * email: wang_lxin@163.com
 */

public class Translation {

    private String type;
    private int errorCode;
    private int elapsedTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<TranslationBean>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<TranslationBean>> translateResult) {
        this.translateResult = translateResult;
    }

    private List<List<TranslationBean>> translateResult;

    public static class TranslationBean {

        public String src;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTgt() {
            return tgt;
        }

        public void setTgt(String tgt) {
            this.tgt = tgt;
        }

        public String tgt;
    }
}
