package com.bugvoyage.bugcollection.service;

import com.bugvoyage.bugcollection.model.Beetle;

import java.util.List;
import java.util.Optional;

public interface BeetleService {

    List<Beetle> findAllBeetles();

    Optional<Beetle> findBeetleById(Integer beetleId);

    Beetle saveBeetle(Beetle beetle);

    Optional<Beetle> updateBeetle(Integer beetleId, Beetle beetleDetails);

    boolean deleteBeetle(Integer beetleId);
}
