package com.hotelres.controller;

import com.hotelres.bean.Order;
import com.hotelres.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author scqzy
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderServiceImpl orderService;

    @RequestMapping("/findOrders")
    @ResponseBody
    public List<Order> findOrders(String hotelName, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        List<Order> list = orderService.findOrders(hotelName,username);

        return list;
    }

    @RequestMapping("/removeOrder")
    @ResponseBody
    public String removeHotel(String[] oid){
        int len = oid.length;
        for(int i=0;i<len;i++){
            orderService.delete(Integer.parseInt(oid[i]));
        }
        return "success";
    }
}
