package com.atguigu.mapper;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

import java.util.HashMap;
import java.util.List;

/**
 * @author xuyuyong
 */
public interface ListMapper {

    /**
     * flbh2商品列表
     * @param flbh2
     * @return
     */
	List<OBJECT_T_MALL_SKU> select_list_by_flbh2(int flbh2);

	/**
	 * 多条件查询商品
	 * @param hashMap
	 * @return
	 */
	List<OBJECT_T_MALL_SKU> select_list_by_attr(HashMap<Object, Object> hashMap);

}
