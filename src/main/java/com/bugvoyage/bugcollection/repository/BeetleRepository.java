package com.bugvoyage.bugcollection.repository;

import com.bugvoyage.bugcollection.model.Beetle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeetleRepository extends JpaRepository<Beetle, Integer> {
    List<Beetle> findBySpecies(String species);

    List<Beetle> findByNameContaining(String name);
}