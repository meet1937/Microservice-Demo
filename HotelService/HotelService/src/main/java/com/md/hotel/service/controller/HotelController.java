package com.md.hotel.service.controller;

import com.md.hotel.service.entities.Hotel;
import com.md.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> gethotel(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

}
