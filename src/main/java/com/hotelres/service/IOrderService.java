package com.hotelres.service;

import com.hotelres.bean.Order;

import java.util.List;

public interface IOrderService {
    public void addOrder(Order order);
    public List<Order> findOrders(String hotelName,String username);
    public void delete(int oid);
}
