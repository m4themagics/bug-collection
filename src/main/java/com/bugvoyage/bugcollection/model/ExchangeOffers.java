package com.bugvoyage.bugcollection.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exchange_offers", schema = "public", catalog = "BugCollection")
public class ExchangeOffers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "offer_id", nullable = false)
    private int offerId;
    @Basic
    @Column(name = "beetle_id", nullable = false)
    private int beetleId;
    @Basic
    @Column(name = "requested_species", nullable = false)
    private String requestedSpecies;
    @Basic
    @Column(name = "status", length = 50)
    private String status;
    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt;
}
