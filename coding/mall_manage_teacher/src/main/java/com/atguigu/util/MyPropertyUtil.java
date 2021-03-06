package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xuyuyong
 * Date: 2018/9/23
 * Time: 8:37
 * Content: 获取properties数据工具类
 */
public class MyPropertyUtil {

    public static String getProperty(String pro, String key) {
        Properties properties = new Properties();

        InputStream resourceAsStream = MyPropertyUtil.class.getClassLoader().getResourceAsStream(pro);

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String property = properties.getProperty(key);
        return property;
    }
}
