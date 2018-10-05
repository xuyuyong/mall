package com.atguigu.controller;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.service.ListServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/10/4
 * Time: 13:55
 * Content:
 */
@Controller
public class ListController {

    @Autowired
    ListServiceInf listServiceInf;

    /**
     * 查询商品
     * @param list_attr
     * @param flbh2
     * @param map
     * @return
     */
    @RequestMapping("get_list_by_attr")
    public String get_list_by_attr(MODEL_T_MALL_SKU_ATTR_VALUE list_attr, int flbh2, ModelMap map) {

        // 根据属性查询列表的业务
        List<OBJECT_T_MALL_SKU> list_sku = listServiceInf.get_list_by_attr(list_attr.getList_attr(), flbh2);
        map.put("list_sku", list_sku);
        return "skuList";
    }
}
