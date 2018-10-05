package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;

import java.util.List;

/**
 * @author xuyuyong
 */
public interface AttrServiceInf {

	void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr);

	List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);

}
