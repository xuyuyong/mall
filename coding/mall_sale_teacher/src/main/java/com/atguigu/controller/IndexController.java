package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuyuyong
 * Date: 2018/9/17
 * Time: 21:20
 * Content:
 */
@Controller
public class IndexController {

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
