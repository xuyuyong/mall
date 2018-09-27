package com.mall.bean;


import java.io.Serializable;
import java.util.List;

/**
 * @author xuyuyong
 * @create 2018-09-17 15:25
 */
public class SailOrderParam implements Serializable {

    private static final long serialVersionUID = -2640814218076716551L;

    /**
     * 绿厨订单号
     */
    private String LCCode;

    /**
     * 联系人
     */
    private String Contact;

    /**
     * 手机号
     */
    private String Phone;

    /**
     * 收货地址
     */
    private String Address;

    /**
     * 备注
     */
    private String Remark;

    /**
     * 产品信息
     */
    private List<ProSpecification> ProSpecification;

    public String getLCCode() {
        return LCCode;
    }

    public void setLCCode(String LCCode) {
        this.LCCode = LCCode;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public List<com.mall.bean.ProSpecification> getProSpecification() {
        return ProSpecification;
    }

    public void setProSpecification(List<com.mall.bean.ProSpecification> proSpecification) {
        ProSpecification = proSpecification;
    }
}
