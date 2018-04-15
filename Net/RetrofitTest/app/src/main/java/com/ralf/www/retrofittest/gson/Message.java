package com.ralf.www.retrofittest.gson;

/**
 * Author：Ralf on
 * Create on: 2018/3/13 17:26
 * description：
 * email: wang_lxin@163.com
 */

public class Message {

    private int status;

    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {

        System.out.println(status);
        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }
}
