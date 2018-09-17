package com.atguigu.controller;

import com.atguigu.bean.T_MALL_PRODUCT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuyuyong
 * Date: 2018/9/17
 * Time: 22:00
 * Content:
 */
@Controller
public class SpuController {

    @RequestMapping("goto_spu_add")
    public String goto_spu_add(ModelMap map, T_MALL_PRODUCT spu) {

        map.put("spu", spu);
        return "spuAdd";
    }

    @RequestMapping("spu_add")
    public String spu_add() {
        return "redirect:/goto_spu_add.do";
    }
}
