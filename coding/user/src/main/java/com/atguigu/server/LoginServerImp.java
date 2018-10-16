package com.atguigu.server;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.LoginServiceInf;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.ws.rs.*;

/**
 * @author xuyuyong
 * Date: 2018/10/14
 * Time: 17:05
 * Content:
 */
public class LoginServerImp implements LoginServerInf {

    @Autowired
    LoginServiceInf loginServiceInf;


    @Override
    public String login(T_MALL_USER_ACCOUNT user) {
        T_MALL_USER_ACCOUNT select_user = loginServiceInf.login(user);
        Gson gson = new Gson();
        return gson.toJson(select_user);
    }
}
