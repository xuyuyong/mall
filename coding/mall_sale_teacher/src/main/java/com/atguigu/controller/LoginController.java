package com.atguigu.controller;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author xuyuyong
 * Date: 2018/10/2
 * Time: 12:56
 * Content:
 */
@Controller
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @RequestMapping("login")
    public String goto_login(HttpServletResponse response, HttpSession session, T_MALL_USER_ACCOUNT user,
                             HttpServletRequest request, ModelMap map) {

        // 登陆，远程用户认证接口
        T_MALL_USER_ACCOUNT select_user = loginMapper.select_user(user);

        if (select_user == null) {
            return "redirect:/login.do";
        } else {
            session.setAttribute("user", select_user);

            // 在客户端存储用户个性化信息，方便用户下次再访问网站时使用
            try {
                Cookie cookie = new Cookie("yh_mch", URLEncoder.encode(select_user.getYh_mch(), "utf-8"));
                // cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);

               /* Cookie cookie2 = new Cookie("yh_nch", URLEncoder.encode("周润发", "utf-8"));
                // cookie.setPath("/");
                cookie2.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie2);*/
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return "redirect:/index.do";
    }
}
