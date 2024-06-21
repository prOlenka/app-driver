package com.intership.app_driver.repository;

import com.intership.app_driver.entity.TripEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface TripEventRepository extends ReactiveCrudRepository<TripEvent, UUID> {
}
