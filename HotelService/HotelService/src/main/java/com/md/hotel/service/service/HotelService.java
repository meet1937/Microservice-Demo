package com.md.hotel.service.service;

import com.md.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    Hotel getHotel(String hotelId);

    List<Hotel> getAllHotels();
}
