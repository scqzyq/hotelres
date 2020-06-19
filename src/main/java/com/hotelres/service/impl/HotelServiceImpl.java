package com.hotelres.service.impl;

import com.hotelres.bean.Hotel;
import com.hotelres.dao.IHotelDao;
import com.hotelres.service.IHotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author scqzy
 */
@Service("hotelService")
public class HotelServiceImpl implements IHotelService {
    @Resource
    IHotelDao iHotelDao;

    @Override
    public void addHotel(Hotel hotel) {
        iHotelDao.addHotel(hotel);
    }

    @Override
    public Hotel findByHid(int hid) {
        return iHotelDao.findByHid(hid);
    }

    @Override
    public List<Hotel> findAll(String hotelName) {
        return iHotelDao.findAll(hotelName);
    }

    @Override
    public void update(Hotel hotel) {
        iHotelDao.update(hotel);
    }

    @Override
    public void delete(int hid) {
        iHotelDao.delete(hid);
    }
}
