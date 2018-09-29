package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.service.AttrServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuyuyong
 * @create 2018-09-28 6:58
 */
@Controller
public class AttrController {

    @Autowired
    AttrServiceInf attrServiceInf;

    @RequestMapping("get_attr_list")
    public String get_attr_list(int flbh2, ModelMap map) {
        List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<OBJECT_T_MALL_ATTR>();
        list_attr = attrServiceInf.get_attr_list(flbh2);
        map.put("flbh2", flbh2);
        map.put("list_attr", list_attr);
        return "attrListInner";
    }

    @RequestMapping("goto_attr_add")
    public String goto_attr_add(int flbh2, ModelMap map) {
        map.put("flbh2", flbh2);
        return "attrAdd";
    }

   /* @RequestMapping("attr_add")
    public ModelAndView attr_add(int flbh2, MODEL_T_MALL_ATTR list_attr) {
        // 保存属性
        attrServiceInf.save_attr(flbh2, list_attr.getList_attr());
        ModelAndView mv = new ModelAndView("redirect:/goto_attr_add.do");
        mv.addObject("flbh2", flbh2);
        return mv;
    }*/
}
