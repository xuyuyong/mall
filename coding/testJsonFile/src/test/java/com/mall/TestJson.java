package com.mall;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mall.bean.ClassJson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/10/7
 * Time: 20:32
 * Content:
 */
public class TestJson {

    @Test
    public void testGson() {
        //对象 与 json字符串 互换
        ClassJson json = new ClassJson(1, "手机");
        System.out.println(json);

        //创建gson对象
        Gson gson = new Gson();
        String str = gson.toJson(json);
        System.out.println(str);

        ClassJson json1 = gson.fromJson(str, ClassJson.class);
        System.out.println(json1);

        List<ClassJson> jsons = new ArrayList<ClassJson>();
        jsons.add(json);
        jsons.add(json1);
        System.out.println(jsons);

        String strs = gson.toJson(jsons);
        //集合 与 json字符串 互换
        TypeToken token = new TypeToken<List<ClassJson>>(){};
        ArrayList<ClassJson> list = gson.fromJson(strs, token.getType());
        System.out.println(list);
    }

    @Test
    public void testJsonLib() {
        List<ClassJson> jsons = new ArrayList<ClassJson>();
        ClassJson json = new ClassJson(1, "手机"), json1 = new ClassJson(2, "电脑");

        JSONObject jsonObject = JSONObject.fromObject(json);
        String s = jsonObject.toString();
        System.out.println("1."+s);

        Object o = JSONObject.toBean(jsonObject, ClassJson.class);
        System.out.println("2."+o);

        jsons.add(json);
        jsons.add(json1);
        JSONArray jsonArray = JSONArray.fromObject(jsons);
        List<ClassJson> classJsons = (List<ClassJson>)JSONArray.toCollection(jsonArray, ClassJson.class);
        System.out.println("3."+classJsons);
    }

    @Test
    public void testFastJson() {
        //对象 与 json字符串 互换
        ClassJson json = new ClassJson(1, "手机");
        System.out.println(json);

        String str = JSON.toJSONString(json);
        System.out.println("1."+str);

        ClassJson json1 = JSON.parseObject(str, ClassJson.class);
        System.out.println("2."+json1);

        //集合 与 json 字符串互转
        List<ClassJson> jsons = new ArrayList<ClassJson>();
        jsons.add(json);
        jsons.add(new ClassJson(2, "电脑"));
        System.out.println("3."+jsons);

        String string = JSON.toJSONString(jsons);
        System.out.println(string);
        List<ClassJson> classJsons = JSON.parseArray(string, ClassJson.class);

        System.out.println("4."+classJsons);
    }

}
