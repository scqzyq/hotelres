package com.hotelres.service.impl;

import com.hotelres.bean.Order;
import com.hotelres.dao.IOrderDao;
import com.hotelres.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author scqzy
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Resource
    IOrderDao iOrderDao;
    @Override
    public void addOrder(Order order){
        iOrderDao.addOrder(order);
    }

    @Override
    public List<Order> findOrders(String hotelName,String username) {
        return iOrderDao.findOrders(hotelName,username);
    }

    @Override
    public void delete(int oid) {
        iOrderDao.delete(oid);
    }
}
