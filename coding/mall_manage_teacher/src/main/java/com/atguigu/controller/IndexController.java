package com.atguigu.controller;

import org.springframework.stereotype.Controller;
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
     * 跳转首页
     * @return
     */
    @RequestMapping("/index")
    private String index(){
        return "main";
    }
}
