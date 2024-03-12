package com.md.rating.service.service;

import com.md.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);

    List<Rating> getAllRating();

    List<Rating> getAllRatingByUserId(String userId);

    List<Rating> getAllRatingByHotelId(String hotelId);
}
