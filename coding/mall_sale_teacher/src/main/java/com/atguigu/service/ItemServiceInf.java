package com.atguigu.service;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;

import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/10/4
 * Time: 17:20
 * Content:
 */
public interface ItemServiceInf {

    DETAIL_T_MALL_SKU get_sku_detail(int sku_id);

    List<T_MALL_SKU> get_sku_list_by_spu(int spu_id);
}
