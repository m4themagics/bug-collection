package com.bugvoyage.bugcollection.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exchange_offers", schema = "public", catalog = "BugCollection")
public class ExchangeOffers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "offer_id", nullable = false)
    private User offer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "beetle_id", nullable = false)
    private Beetle beetle;

    @Column(name = "requested_species", nullable = false)
    private String requestedSpecies;

    @Column(nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
