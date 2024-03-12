package com.md.user.service.service.impl;

import com.md.user.service.entities.Hotel;
import com.md.user.service.entities.Rating;
import com.md.user.service.entities.User;
import com.md.user.service.exceptions.ResourceNotFoundException;
import com.md.user.service.repositories.UserRepository;
import com.md.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not present" + userId));

        Rating[] ratingListForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        logger.info("{} ", ratingListForUser);

        List<Rating> ratings = Arrays.stream(ratingListForUser).toList();

        List<Rating> list = ratings.stream().map(rating -> {
            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(list);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
}
