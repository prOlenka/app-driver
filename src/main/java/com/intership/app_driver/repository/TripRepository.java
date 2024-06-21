package com.intership.app_driver.repository;

import com.intership.app_driver.entity.Trip;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface TripRepository extends ReactiveCrudRepository<Trip, UUID> {
}
