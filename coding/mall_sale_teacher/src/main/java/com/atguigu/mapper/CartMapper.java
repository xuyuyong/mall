package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

import java.util.List;

/**
 * @author xuyuyong
 * @create 2018-10-06 14:04
 * @content
 */
public interface CartMapper {

    /**
     * 查询本人的购物车信息
     * @param user_id
     * @return
     */
    List<T_MALL_SHOPPINGCAR> select_list_cart_by_user(int user_id);

    /**
     * 保存购物车商品
     * @param cart
     */
    void insert_cart(T_MALL_SHOPPINGCAR cart);

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
    int select_cart_exists(T_MALL_SHOPPINGCAR cart);
}
