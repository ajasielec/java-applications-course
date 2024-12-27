package com.example.teachermanagement.service;

import com.example.teachermanagement.model.TeacherGroup;
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
        TeacherGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Rate rate = new Rate();
        rate.setGroup(group);
        rate.setRating(rating);

        return rateRepository.save(rate);
    }

    public List<Rate> getRatesForGroup(Long groupId) {
        TeacherGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getRates();
    }

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }
}
