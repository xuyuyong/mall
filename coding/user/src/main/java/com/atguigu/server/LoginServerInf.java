package com.atguigu.server;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

import javax.jws.WebService;

/**
 * @author xuyuyong
 * Date: 2018/10/14
 * Time: 17:05
 * Content:
 */
@WebService
public interface LoginServerInf {

    /**
     * 登陆
     * @param user
     * @return
     */
    String login(T_MALL_USER_ACCOUNT user);
}
