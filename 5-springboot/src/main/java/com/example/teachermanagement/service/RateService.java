package com.example.teachermanagement.service;

import com.example.teachermanagement.model.Group;
import com.example.teachermanagement.model.Rate;
import com.example.teachermanagement.repository.GroupRepository;
import com.example.teachermanagement.repository.RateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {
    private final RateRepository rateRepository;
    private final GroupRepository groupRepository;

    public RateService(RateRepository rateRepository, GroupRepository groupRepository) {
        this.rateRepository = rateRepository;
        this.groupRepository = groupRepository;
    }

    public Rate addRate(Long groupId, Double rating) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Rate rate = new Rate();
        rate.setGroup(group);
        rate.setRating(rating);

        return rateRepository.save(rate);
    }

    public List<Rate> getRatesForGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getRates();
    }
}
