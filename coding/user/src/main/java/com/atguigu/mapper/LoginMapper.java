package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @author xuyuyong
 * Date: 2018/10/14
 * Time: 17:09
 * Content:
 */
public interface LoginMapper {

    /**
     * 查询会员信息
     * @param user
     * @return
     */
    T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);
}
