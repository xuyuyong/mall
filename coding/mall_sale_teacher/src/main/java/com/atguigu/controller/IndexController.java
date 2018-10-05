package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.service.AttrServiceInf;
import com.atguigu.service.ListServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/9/17
 * Time: 21:20
 * Content:
 */
@Controller
public class IndexController {

    @Autowired
    AttrServiceInf attrServiceInf;

    @Autowired
    ListServiceInf listServiceInf;

    /**
     * 获取商品的分类选择
     * @param flbh2
     * @param map
     * @return
     */
    @RequestMapping("goto_list")
    public String goto_list(int flbh2, ModelMap map) {
        // flbh2属性的集合
        List<OBJECT_T_MALL_ATTR> list_attr = attrServiceInf.get_attr_list(flbh2);

        // flbh2商品列表
        List<OBJECT_T_MALL_SKU> list_sku = listServiceInf.get_list_by_flbh2(flbh2);

        map.put("list_attr", list_attr);
        map.put("list_sku", list_sku);
        map.put("flbh2", flbh2);
        return "list";
    }

    /**
     * 跳转至登录页
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("goto_login")
    public String goto_login(HttpServletRequest request, ModelMap map) {
        return "login";
    }

    /**
     * 跳转至首页
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, ModelMap map) {
        return "index";
    }

}
