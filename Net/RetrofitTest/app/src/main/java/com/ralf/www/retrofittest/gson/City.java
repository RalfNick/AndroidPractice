package com.ralf.www.retrofittest.gson;

/**
 * Author：Ralf on
 * Create on: 2018/3/13 08:59
 * description：
 * email: wang_lxin@163.com
 */

public class City {

    int id;
    String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void show() {

        System.out.println("----");
        System.out.println("id = " + id + ", name = " + name);
        System.out.println("----");

    }

    public String toString() {

        return "[id = " + id + ", name = " + name + "]";
    }

}
