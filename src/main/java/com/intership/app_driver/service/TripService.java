package com.intership.app_driver.service;

import com.intership.app_driver.entity.Trip;
import com.intership.app_driver.entity.TripEvent;
import com.intership.app_driver.repository.TripEventRepository;
import com.intership.app_driver.repository.TripRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final TripEventRepository tripEventRepository;

    public TripService(TripRepository tripRepository, TripEventRepository tripEventRepository) {
        this.tripRepository = tripRepository;
        this.tripEventRepository = tripEventRepository;
    }

    public Mono<Trip> createTrip(Trip trip) {
        trip.setCreatedAt(LocalDateTime.now());
        trip.setStatus("created");
        return tripRepository.save(trip);
    }

    public Flux<Trip> getTripsByTaskId(UUID taskId) {
        return tripRepository.findAll()
                .filter(trip -> trip.getTaskId().equals(taskId));
    }

    public Mono<Trip> getTripById(UUID tripId) {
        return tripRepository.findById(tripId);
    }

    public Flux<TripEvent> getTripEventsByTripId(UUID tripId) {
        return tripEventRepository.findAll()
                .filter(event -> event.getTripId().equals(tripId));
    }

    public Mono<TripEvent> createTripEvent(TripEvent tripEvent) {
        tripEvent.setEventTime(LocalDateTime.now());
        return tripEventRepository.save(tripEvent);
    }

    public Mono<Trip> updateTripStatus(UUID tripId, String status) {
        return tripRepository.findById(tripId)
                .flatMap(trip -> {
                    trip.setStatus(status);
                    if ("started".equals(status)) {
                        trip.setStartedAt(LocalDateTime.now());
                    } else if ("ended".equals(status)) {
                        trip.setEndedAt(LocalDateTime.now());
                    }
                    return tripRepository.save(trip);
                });
    }


        // существующие методы

        @KafkaListener(topics = "trip-events", groupId = "driver-service")
        public void handleTripEvent(TripEvent event) {
            // Сохранение события
            tripEventRepository.save(event).subscribe();
            // Обновление статуса рейса
            updateTripStatus(event.getTripId(), event.getEventType()).subscribe();
        }

}

