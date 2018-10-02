package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuyuyong
 * Date: 2018/9/17
 * Time: 21:20
 * Content:
 */
@Controller
public class IndexController {

    /**
     * 跳转至商品库存
     * @return
     */
    @RequestMapping("goto_sku")
    public String goto_sku() {
        return "sku";
    }

    /**
     * 跳转至属性
     * @return
     */
    @RequestMapping("goto_attr")
    public String goto_attr() {
        return "attr";
    }

    /**
     * 跳转至spu
     * @return
     */
    @RequestMapping("goto_spu")
    public String goto_spu() {
        return "spu";
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/index")
    private String index(String url, String title, ModelMap map){
        map.put("url", url);
        map.put("title", title);
        return "main";
    }
}
