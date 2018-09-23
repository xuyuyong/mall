package com.atguigu.service;

import com.atguigu.bean.T_MALL_PRODUCT;

import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/9/23
 * Time: 8:14
 * Content:
 */
public interface SpuServiceInf {

    /**
     * 保存商品信息
     * @param list_image
     * @param spu
     */
    void save_spu(List<String> list_image, T_MALL_PRODUCT spu);
}
