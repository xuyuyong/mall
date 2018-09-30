package com.atguigu.mapper;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyuyong
 * @create 2018-09-28 7:06
 * @content
 */
public interface AttrMapper {

    /**
     * 增加属性值
     * @param flbh2
     * @param attr
     */
    void insert_attr(@Param("flbh2") int flbh2, @Param("attr") OBJECT_T_MALL_ATTR attr);

    /**
     * 增加属性值的具体属性
     * @param attr_id
     * @param list_value
     */
    void insert_values(@Param("attr_id") int attr_id, @Param("list_value") List<T_MALL_VALUE> list_value);

    /**
     * 查询属性
     * @param flbh2
     * @return
     */
    List<OBJECT_T_MALL_ATTR> select_attr_list(int flbh2);
}
