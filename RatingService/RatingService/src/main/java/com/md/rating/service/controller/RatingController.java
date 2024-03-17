package com.md.rating.service.controller;

import com.md.rating.service.entities.Rating;
import com.md.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getAllRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getAllRatingByHotelId(hotelId));
    }

}
