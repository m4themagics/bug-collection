package com.bugvoyage.bugcollection.service.Impl;

import com.bugvoyage.bugcollection.model.Beetle;
import com.bugvoyage.bugcollection.repository.BeetleRepository;
import com.bugvoyage.bugcollection.service.BeetleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeetleServiceImpl implements BeetleService {

    private final BeetleRepository beetleRepository;

    @Autowired
    public BeetleServiceImpl(BeetleRepository beetleRepository) {
        this.beetleRepository = beetleRepository;
    }

    @Override
    public List<Beetle> findAllBeetles() {
        return beetleRepository.findAll();
    }

    @Override
    public Optional<Beetle> findBeetleById(Integer beetleId) {
        return beetleRepository.findById(beetleId);
    }

    @Override
    public Beetle saveBeetle(Beetle beetle) {
        return beetleRepository.save(beetle);
    }

    @Override
    public Optional<Beetle> updateBeetle(Integer beetleId, Beetle beetleDetails) {
        return beetleRepository.findById(beetleId)
                .map(beetle -> {
                    beetle.setName(beetleDetails.getName());
                    beetle.setSpecies(beetleDetails.getSpecies());
                    beetle.setDescription(beetleDetails.getDescription());
                    beetle.setImageUrl(beetleDetails.getImageUrl());
                    return beetleRepository.save(beetle);
                });
    }

    @Override
    public boolean deleteBeetle(Integer beetleId) {
        return beetleRepository.findById(beetleId)
                .map(beetle -> {
                    beetleRepository.delete(beetle);
                    return true;
                }).orElse(false);
    }
}