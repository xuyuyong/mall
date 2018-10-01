package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_SKU;

import java.util.Map;

/**
 *
 * @author xuyuyong
 *
 */
public interface SkuMapper {
	/**
	 * 保存sku
	 * @param sku
	 */
	void insert_sku(T_MALL_SKU sku);

	/**
	 * 保存sku属性和属性值关联
	 * @param map
	 */
	void insert_sku_av(Map<Object, Object> map);

}
