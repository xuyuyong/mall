package com.mall.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xuyuyong
 * Date: 2018/10/7
 * Time: 20:35
 * Content:
 */
@Data
public class ClassJson implements Serializable {

    private Integer code;

    private String msg;

    public ClassJson(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
