package com.ralf.www.retrofittest.gson;

/**
 * Author：Ralf on
 * Create on: 2018/3/13 16:56
 * description：
 * email: wang_lxin@163.com
 */

public class Country {

    int id;
    String name;
    String weather_id;

    public void show(){

        System.out.println("----");
        System.out.println("id = " + id + ", name = " + name + ", weather_id = " + weather_id);
        System.out.println("----");

    }

    public String toString(){

        return "[id = " + id + ", name = " + name + ", weather_id = " + weather_id +"]";
    }
}
