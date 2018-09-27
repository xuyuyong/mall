package com.mall.bean;

import java.io.Serializable;

/**
 * @author xuyuyong
 * @create 2018-09-17 16:44
 */
public class SailOrderResult implements Serializable {
    private static final long serialVersionUID = 2701022853502187236L;
    /**
     * 结果
     */
    private String result;

    /**
     * 处理结果
     */
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
