package com.atguigu.controller;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuServiceInf;
import com.atguigu.util.MyFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/9/17
 * Time: 22:00
 * Content:
 */
@Controller
public class SpuController {

    @Autowired
    private SpuServiceInf spuServiceInf;

    @RequestMapping("goto_spu_add")
    public String goto_spu_add(ModelMap map, T_MALL_PRODUCT spu) {
        map.put("spu", spu);
        return "spuAdd";
    }

    @RequestMapping("spu_add")
    public ModelAndView spu_add(@RequestParam("files") MultipartFile[] files,T_MALL_PRODUCT spu) {

        //图片上传
        List<String> list_image = MyFileUpload.upload_image(files);
        //商品信息提交
        spuServiceInf.save_spu(list_image, spu);

        ModelAndView mv = new ModelAndView("redirect:/goto_spu_add.do");
        mv.addObject("flbh1", spu.getFlbh1());
        mv.addObject("flbh2", spu.getFlbh2());
        mv.addObject("pp_id", spu.getPp_id());
        return mv;
    }
}
