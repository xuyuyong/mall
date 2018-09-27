package com.mall.bean;


import java.io.Serializable;

/**
 * @author xuyuyong
 * @create 2018-09-17 15:31
 */
public class ProSpecification implements Serializable {
    private static final long serialVersionUID = -6289003523515949580L;

    /**
     * 数量
     */
    private Integer Qty;

    /**
     * 产品规格ID
     */
    private String ProductCode;

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }
}
