package com.ssm.domain;

import com.ssm.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

//订单
public class Order {

    private String id;
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date orderTime;                  //下单时间
    private int orderStatus;                 //订单状态
    private Integer payType;                 //支付方式
    private String orderDesc;                //订单描述
    private String productId;
    private String userId;

    private Product product;
    private UserInfo userInfo;
    private String payTypeStr;               //支付方式，0支付宝，1微信，2其他
    private String orderTimeStr;
    private String orderStatusStr;           //订单状态，0，申请退款，1已支付,3退款成功

    public Order() {
    }

    public String getOrderStatusStr() {
        //订单状态（0，未支付，1已支付）
        if(orderStatus == 0){
            orderStatusStr="申请退款";
        }
        if(orderStatus == 1){
            orderStatusStr="已支付";
        }
        if(orderStatus == 3){
            orderStatusStr="退款成功";
        }
        return orderStatusStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime!=null){
            orderTimeStr = DateUtils.dateToString(orderTime,"yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        //支付方式，0支付宝，1微信，2其他
        if(payType==0){
            payTypeStr="支付宝";
        }else if(payType==1){
            payTypeStr="微信";
        }else if(payType==2){
            payTypeStr="其他";
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
