package com.example.teachermanagement.controller;

import com.example.teachermanagement.model.Rate;
import com.example.teachermanagement.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    private final RateService rateService;

    public RatingController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public List<Rate> getRatings() {
        return rateService.getAllRates();
    }

    @PostMapping
    public Rate addRating(@RequestBody Rate rate) {
        return rateService.addRate(rate);
    }

    @GetMapping("/{groupId}")
    public List<Rate> getRatingsForGroup(@PathVariable Long groupId) {
        return rateService.getRatesForGroup(groupId);
    }
}
