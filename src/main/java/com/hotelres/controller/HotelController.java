package com.hotelres.controller;

import com.hotelres.bean.Hotel;
import com.hotelres.service.impl.HotelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author scqzy
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    HotelServiceImpl hotelService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Hotel> findAll(String hotelName){
        List<Hotel> list = hotelService.findAll(hotelName);
        return list;
    }

    @RequestMapping("/addHotel")
    @ResponseBody
    public String addHotel(Hotel hotel){
        System.out.println(hotel);
        hotelService.addHotel(hotel);
        return "success";
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(Hotel hotel){
        hotelService.update(hotel);
        System.out.println(hotel);
    }

    @RequestMapping("/removeHotel")
    @ResponseBody
    public String removeHotel(String[] hid){
        int len = hid.length;
        for(int i=0;i<len;i++){
            hotelService.delete(Integer.parseInt(hid[i]));
        }
        return "success";
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(Hotel hotel){
        hotelService.update(hotel);

        System.out.println("调用add");
    }
}
