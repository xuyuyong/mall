package com.atguigu.server;

import javax.jws.WebService;

/**
 * @author xuyuyong
 * Date: 2018/10/11
 * Time: 20:42
 * Content:
 */
@WebService
public interface TestServerInf {
    /**
     * 测试
     * @param hello
     * @return
     */
    String ping(String hello);
}
