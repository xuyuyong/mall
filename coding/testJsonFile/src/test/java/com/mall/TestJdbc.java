package com.mall;

import com.google.gson.Gson;
import com.mall.bean.T_MALL_CLASS_1;
import com.mall.mapper.T_MALL_CLASS_1_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author xuyuyong
 * Date: 2018/9/16
 * Time: 21:32
 * Content:
 */
public class TestJdbc {

    @Test
    public void test() throws IOException {
        //获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream  = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        T_MALL_CLASS_1_mapper mapper = sqlSession.getMapper(T_MALL_CLASS_1_mapper.class);
        List<T_MALL_CLASS_1> list = mapper.getList();
        System.out.println("个数:"+list.size());
        //获取gson对象
        Gson gson = new Gson();
        //转gson
        String class1Str = gson.toJson(list);
        //生成静态文件
        FileOutputStream out = new FileOutputStream("D:/project/test/mall/file/class_1.js");
        out.write(class1Str.getBytes());
        out.close();
    }
}
