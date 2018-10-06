package com.atguigu.service;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

/**
 * @author xuyuyong
 * @create 2018-10-06 14:10
 * @content
 */
public interface CartServiceInf {

    /**
     * 保存购物车商品
     * @param cart
     */
    void add_cart(T_MALL_SHOPPINGCAR cart);

    /**
     * 修改购物车商品
     * @param cart
     */
    void update_cart(T_MALL_SHOPPINGCAR cart);

    /**
     * 判断购物车是否有相同商品
     * @param cart
     * @return
     */
    boolean if_cart_exists(T_MALL_SHOPPINGCAR cart);
}
