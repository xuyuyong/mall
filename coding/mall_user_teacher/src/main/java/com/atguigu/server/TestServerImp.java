package com.atguigu.server;

/**
 * @author xuyuyong
 * Date: 2018/10/11
 * Time: 22:33
 * Content:
 */
public class TestServerImp implements TestServerInf {
    public String ping(String hello) {
        System.out.println("cxf接口调用:" + hello);
        return "pong";
    }
}
