package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;

import java.util.List;

/**
 * @author xuyuyong
 * @create 2018-09-28 7:05
 * @content
 */
public interface AttrServiceInf {

    /**
     * 保存属性值
     * @param flbh2
     * @param list_attr
     */
    void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr);

    List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);

}
