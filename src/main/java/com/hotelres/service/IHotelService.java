package com.hotelres.service;

import com.hotelres.bean.Hotel;

import java.util.List;

/**
 * @author scqzy
 */
public interface IHotelService {

    public void addHotel(Hotel hotel);

    public Hotel findByHid(int hid);

    public List<Hotel> findAll(String hotelName);

    public void update(Hotel hotel);

    public void delete(int hid);
}
