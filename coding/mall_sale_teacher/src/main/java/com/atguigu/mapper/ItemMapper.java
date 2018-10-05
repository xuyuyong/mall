package com.atguigu.mapper;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;

import java.util.List;
import java.util.Map;

/**
 * @author xuyuyong
 * Date: 2018/10/4
 * Time: 17:21
 * Content:
 */
public interface ItemMapper {

    DETAIL_T_MALL_SKU select_detail_sku(Map<Object, Object> map);

    List<T_MALL_SKU> select_skuList_by_spu(int spu_id);
}
