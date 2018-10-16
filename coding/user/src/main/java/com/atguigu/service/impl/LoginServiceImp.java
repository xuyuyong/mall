package com.atguigu.service.impl;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.LoginMapper;
import com.atguigu.service.LoginServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuyuyong
 * Date: 2018/10/14
 * Time: 17:08
 * Content:
 */
@Service
public class LoginServiceImp implements LoginServiceInf {

    @Autowired
    LoginMapper loginMapper;


    @Override
    public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
        return loginMapper.select_user(user);
    }
}
