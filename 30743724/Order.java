package com.retail;

import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;
    private Date orderDate;
    private String status;

    public Order(int orderId, int customerId, Date orderDate, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getOrderId() {

        return orderId;
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public int getCustomerId() {

        return customerId;
    }

    public void setCustomerId(int customerId) {

        this.customerId = customerId;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
