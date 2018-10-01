package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_PRODUCT;

import java.util.List;
import java.util.Map;

/**
 * @author xuyuyong
 * Date: 2018/9/26
 * Time: 21:28
 * Content:
 */
public interface SpuMapper {

    /**
     * 保存
     * @param spu
     */
    void insert_spu(T_MALL_PRODUCT spu);

    /**
     * 保存图片
     * @param map
     */
    void insert_images(Map<Object, Object> map);

    /**
     * 获取spu列表
     * @param map
     * @return
     */
    List<T_MALL_PRODUCT> select_spu_list(Map<Object, Object> map);
}
