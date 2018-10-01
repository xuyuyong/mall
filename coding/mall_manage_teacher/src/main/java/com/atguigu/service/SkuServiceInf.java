package com.atguigu.service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.List;

/**
 * @author xuyuyong
 */
public interface SkuServiceInf {

	/**
	 * 保存sku
	 * @param sku
	 * @param spu
	 * @param list_attr
	 */
	void save_sku(T_MALL_SKU sku, T_MALL_PRODUCT spu, List<T_MALL_SKU_ATTR_VALUE> list_attr);

}
