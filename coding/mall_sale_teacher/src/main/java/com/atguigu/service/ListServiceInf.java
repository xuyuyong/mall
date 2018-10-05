package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.List;

/**
 * @author xuyuyong
 */
public interface ListServiceInf {

	/**
	 * flbh2商品列表
	 * @param flbh2
	 * @return
	 */
	List<OBJECT_T_MALL_SKU> get_list_by_flbh2(int flbh2);

	/**
	 * 多条件查询商品
	 * @param list_attr
	 * @param flbh2
	 * @return
	 */
	List<OBJECT_T_MALL_SKU> get_list_by_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2);

}
