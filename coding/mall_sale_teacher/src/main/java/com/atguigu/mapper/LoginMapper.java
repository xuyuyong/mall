package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 *
 * @author xuyuyong
 * Date: 2018/10/2
 * Time: 12:56
 */
public interface LoginMapper {

	/**
	 * 查询用户是否存在
	 * @param user
	 * @return
	 */
	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

}
